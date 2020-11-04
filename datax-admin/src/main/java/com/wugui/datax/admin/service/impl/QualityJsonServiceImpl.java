package com.wugui.datax.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.wugui.datax.admin.dto.QualityJsonBuildDto;
import com.wugui.datax.admin.dto.RuleIdInfoDto;
import com.wugui.datax.admin.dto.RuleInfoDto;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.PersonaliseRule;
import com.wugui.datax.admin.entity.UniversalRule;
import com.wugui.datax.admin.mapper.PersonaliseRuleMapper;
import com.wugui.datax.admin.mapper.UniversalRuleMapper;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.service.QualityJsonService;
import com.wugui.datax.admin.tool.datax.DataxJsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.wugui.datax.admin.util.JdbcConstants.*;

@Service
public class QualityJsonServiceImpl implements QualityJsonService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JobDatasourceService jobJdbcDatasourceService;

    @Resource
    private PersonaliseRuleMapper personaliseRuleMapper;

    @Resource
    private UniversalRuleMapper universalRuleMapper;

    @Override
    public String buildJobJson(QualityJsonBuildDto dto) {
        DataxJsonHelper dataxJsonHelper = new DataxJsonHelper();
        // reader
        JobDatasource readerDatasource = jobJdbcDatasourceService.getById(dto.getReaderDatasourceId());
        // reader plugin init

        //构建querySql
        //String sqlWhere = getWhere(dto,readerDatasource);
        //dto.getRdbmsReader().setWhereParams(sqlWhere);
        String querySql = buildQuerySql(dto,readerDatasource);
        dto.getRdbmsReader().setQuerySql(querySql);

        dataxJsonHelper.initQualityReader(dto, readerDatasource);
        //writer
        JobDatasource writerDatasource = jobJdbcDatasourceService.getById(dto.getWriterDatasourceId());
        //writer plugin init
        dataxJsonHelper.initWriter(dto, writerDatasource);

        return JSON.toJSONString(dataxJsonHelper.buildJob());
    }

    private String buildQuerySql(QualityJsonBuildDto dto, JobDatasource readerDatasource) {
        List<String> list = new ArrayList<>();
        //获取数据库类型
        String dataSource = readerDatasource.getDatasource();
        //先获取列信息
        List<String> columns = dto.getReaderColumns();
        //构建select
        StringBuffer sb = new StringBuffer("select ");
        String querySqlTemp = "";

        //拼接列
        for(int i = 0; i < columns.size(); i++){
            if(dataSource.equals(HIVE)){
                sb.append(columns.get(i).split("\\:")[1]).append(",");
            }else {
                sb.append(columns.get(i)).append(",");
            }

        }

        //删除列的最后一个字符','
        if(sb.length() > 0){
            querySqlTemp = sb.substring(0,sb.length()-1);
        }
        //拼接table
        if(dto.getReaderTables().size() > 0){
            querySqlTemp = querySqlTemp + " from " + dto.getReaderTables().get(0) +" where 1=1 ";
        }


        //获取规则信息，对规则信息进行拼接
        List<RuleInfoDto> ruleInfoDtoList = dto.getRule();
        for(int i = 0; i < ruleInfoDtoList.size(); i++){
            String columnName = ruleInfoDtoList.get(i).getColumnName();
            List<RuleIdInfoDto> ruleIdInfoDtoList = ruleInfoDtoList.get(i).getRuleId();
            for(int j = 0; j < ruleIdInfoDtoList.size(); j++){
                String[] codeArray = ruleIdInfoDtoList.get(j).getCode().split("\\:");
                String regular = null;
                if(codeArray.length == 1){
                    //通用规则不需要关联个性化规则
                    UniversalRule universalRule = universalRuleMapper.selectByCode(codeArray[codeArray.length-1]);
                    regular = universalRule.getRegular();
                }else if (codeArray.length == 2){
                    //通用规则关联个性化规则
                    PersonaliseRule personaliseRule = personaliseRuleMapper.selectByCode(codeArray[codeArray.length-1]);
                    regular = personaliseRule.getRegular();
                }

                if(dataSource.equalsIgnoreCase(MYSQL) || dataSource.equalsIgnoreCase(IMPALA)){
                    list.add(" and " + columnName + " REGEXP " + "'" +regular + "'");
                }else if(dataSource.equalsIgnoreCase(ORACLE)){
                    list.add(" and REGEXP_LIKE(" + "\"" + columnName + "\"" +","+ "'" + regular + "')" );
                }else if(dataSource.equalsIgnoreCase(POSTGRESQL) || dataSource.equalsIgnoreCase(GREENPLUM)){
                    list.add(" and " + columnName + " ~ " + "'" +regular + "'" );
                } else if (dataSource.equalsIgnoreCase(CLICKHOUSE)) {
                    list.add(" and match(" + columnName + "'" + regular + "')");
                }else if(dataSource.equals(DB2)){
                    list.add(" and xmlcast(xmlquery('fn:matches($v," + "\""+regular + "\""+ ")' PASSING "+columnName+" as \"v\") as integer) =1 " );
                }else if(dataSource.equalsIgnoreCase(HIVE)){
                    list.add(" and " + columnName.split("\\:")[1] + " REGEXP " + "'" + regular + "'");
                }

            }
        }

        //把list字段和querySqlTemp拼接
        StringBuffer stringBuffer = new StringBuffer(querySqlTemp);
        for (String str : list) {
            stringBuffer.append(str);
        }

        logger.info("querySql={}",stringBuffer);
        return stringBuffer.toString();
    }

}
