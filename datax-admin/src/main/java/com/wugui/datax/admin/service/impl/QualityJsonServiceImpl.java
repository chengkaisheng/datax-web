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
import java.util.regex.Pattern;

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
        List<String> list1 = new ArrayList<>();
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
            String columnName = null;
            if(dataSource.equals(HIVE)){
                columnName = ruleInfoDtoList.get(i).getColumnName().split("\\:")[1];
            }else {
                columnName = ruleInfoDtoList.get(i).getColumnName();
            }

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
                //替换正则中的column
                String temp = regular.replaceAll("column",columnName);

                /*if(dataSource.equalsIgnoreCase(MYSQL) || dataSource.equalsIgnoreCase(IMPALA)){
                    list.add(" and " + columnName + " REGEXP " + "'" +regular + "'");

                }else if(dataSource.equalsIgnoreCase(ORACLE)){
                    list.add(" and REGEXP_LIKE(" + "\"" + columnName + "\"" +","+ "'" + regular + "')" );
                }else if(dataSource.equalsIgnoreCase(POSTGRESQL) || dataSource.equalsIgnoreCase(GREENPLUM)){
                    list.add(" and " + columnName + " ~ " + "'" +regular + "'" );
                } else if (dataSource.equalsIgnoreCase(CLICKHOUSE)) {
                    list.add(" and match(" + columnName + +", ""'" + regular + "')");
                }else if(dataSource.equals(DB2)){
                    list.add(" and xmlcast(xmlquery('fn:matches($v," + "\""+regular + "\""+ ")' PASSING "+columnName+" as \"v\") as integer) =1 " );
                }else if(dataSource.equalsIgnoreCase(HIVE)){
                    list.add(" and " + columnName.split("\\:")[1] + " REGEXP " + "'" + regular + "'");
                }*/
                //list.add(" and " + temp);
                switch (dataSource){
                    case MYSQL :
                    case IMPALA :
                        list.add(" and " + temp);
                        break;
                    case HIVE :
                        if (temp.contains("NOW()")) {
                            list.add(" and " + temp.replace("NOW()","from_unixtime(unix_timestamp(),'yyyy-MM-dd HH:mm:ss')"));
                        }else if(temp.contains("now()")){
                            list.add(" and " + temp.replace("now()","from_unixtime(unix_timestamp(),'yyyy-MM-dd HH:mm:ss')"));
                        }else {
                            list.add(" and " + temp);
                        }
                        break;
                    case ORACLE :
                        if(temp.contains("regexp") || temp.contains("REGEXP")){
                            //String regexp = temp.substring(temp.indexOf("'")+1,temp.lastIndexOf("'"));
                            //可能存在多个regexp
                            /*Matcher matcher = pattern.matcher(temp);
                            while (matcher.find()){
                                regexpList.add(matcher.group());
                            }*/

                            /*if(temp.contains("not") || temp.contains("NOT")){
                                list.add(" and NOT REGEXP_LIKE("+"\"" + columnName + "\""+","+"'"+str + "')");
                            }else {
                                list.add(" and REGEXP_LIKE("+"\"" + columnName + "\""+","+"'"+str + "')");
                            }*/
                            //拆分
                            String[] ands = temp.split("and");
                            for (String or : ands){
                                String[] ors = or.split("or");
                                for (String str : ors){
                                    list1.add(str);
                                }
                            }

                            for (String str : list1){
                                //1.拿到正则表达式的
                                String regexp = str.substring(str.indexOf("'")+1,str.lastIndexOf("'"));
                                //2.判断是否包含not
                                if(str.contains("not") || str.contains("NOT")){
                                    list.add(" and NOT REGEXP_LIKE("+"\"" + columnName + "\""+","+"'"+regexp + "')");
                                }else {
                                    list.add(" and REGEXP_LIKE("+"\"" + columnName + "\""+","+"'"+regexp + "')");
                                }
                            }


                        }else if(temp.contains("NOW()")){
                            list.add(" and " + temp.replace("NOW()","TO_DATE(SYSDATE,'yyyy-mm-dd hh24:mi:ss')"));
                        }else if(temp.contains("now()")){
                            list.add(" and " + temp.replace("now()","TO_DATE(SYSDATE,'yyyy-mm-dd hh24:mi:ss')"));
                        }else {
                            list.add(" and " + temp);
                        }
                        break;
                    default :
                        logger.error("暂不支持当前数据库");
                        break;
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
