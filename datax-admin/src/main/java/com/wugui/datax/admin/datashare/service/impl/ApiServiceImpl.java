package com.wugui.datax.admin.datashare.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wugui.datax.admin.datashare.entity.TDataCatalog;
import com.wugui.datax.admin.datashare.entity.TDatabaseInfo;
import com.wugui.datax.admin.datashare.entity.TDatabaseInfoExample;
import com.wugui.datax.admin.datashare.entity.TInterfaceWithBLOBs;
import com.wugui.datax.admin.datashare.mapper.TDataCatalogMapper;
import com.wugui.datax.admin.datashare.mapper.TDatabaseInfoMapper;
import com.wugui.datax.admin.datashare.service.ApiService;
import com.wugui.datax.admin.datashare.service.InterfaceInfoSevice;
import com.wugui.datax.admin.datashare.tools.ConnectUtil;
import com.wugui.datax.admin.datashare.tools.Result;
import com.wugui.datax.admin.datashare.tools.ResultToJsonUtil;
import com.wugui.datax.admin.datashare.vo.DataBaseInfoQuery;
import com.wugui.datax.admin.datashare.vo.InputParam;
import com.wugui.datax.admin.datashare.vo.OutputParam;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.tool.query.BaseQueryTool;
import com.wugui.datax.admin.tool.query.QueryToolFactory;
import com.wugui.datax.admin.util.AESUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wugui.datax.admin.util.AESUtil.decrypt;

@Service
public class ApiServiceImpl implements ApiService {
    private static final Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Autowired
    private InterfaceInfoSevice interfaceInfoSevice;

    @Autowired
    private TDatabaseInfoMapper databaseInfoMapper;

    @Autowired
    private TDataCatalogMapper dataCatalogMapper;

    @Autowired
    private JobDatasourceService jobDatasourceService;
    /**
     * 实现接口服务查询
     * @param interCode
     * @param request
     * @return
     */
    @Override
    public Object getDatas(String interCode, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(interCode)) {
            map.put("code", "01");
            map.put("msg", "接口编码不能为空");
            map.put("datas", "");
            map.put("dataCount", 0);
        }
        //验证接口是否存在
        TInterfaceWithBLOBs withBLOBs = interfaceInfoSevice.getInterfaceByCode(interCode);
        if (org.springframework.util.StringUtils.isEmpty(withBLOBs)) {
            map.put("code", "04");
            map.put("msg", "接口不存在");
            map.put("datas", "");
            map.put("dataCount", 0);
            return map;
        } else {
            String intput = withBLOBs.getInputParam();//获取输入参数
            logger.info(intput);
            List<InputParam> params = JSON.parseArray(intput, InputParam.class);//json数组转成对象集合
            StringBuffer inParam = new StringBuffer();
            for (InputParam inputParam : params) {
                String columnCode = inputParam.getColumnCode();
                logger.info("得到的请求输入参数：" + columnCode);
                String value = request.getParameter(columnCode);
                logger.info("得到的请求参数值：" + value);
                if (value == null || value.equals("")) {
                    map.put("code", "02");
                    map.put("msg", "请求参数不能为空");
                    map.put("datas", "");
                    map.put("dataCount", 0);
                    return map;
                } else {
                    inParam.append(columnCode + "='" + value + "' and ");//拼接输入参数到where
                }
            }

            String in = inParam.toString().substring(0, inParam.toString().length() - 4);
            logger.info("输入参数为："+in);
            List<OutputParam> outputParams = JSON.parseArray(withBLOBs.getOutputParam(), OutputParam.class);
            StringBuffer outParam=new StringBuffer();
            for (OutputParam outputParam:outputParams) {
                outParam.append(outputParam.getFieldCode()+",");
            }
            String out=outParam.toString().substring(0,outParam.toString().length()-1);//截取字符串
            logger.info("输出字段为："+out);
            TDataCatalog dataCatalog=new TDataCatalog();
            dataCatalog=dataCatalogMapper.selectByInfoId(withBLOBs.getInfoId());
            map=this.queryTableData(withBLOBs.getTableEnglish(),in,out,dataCatalog.getDataServerName());

        }


        return map;
    }

    /**
     * 成功示例
     * @param tableName
     * @param out
     * @return
     */
    @Override
    public Map querySucessExamples(String id,String tableName, String out) {
        Map<String, Object> map = new HashMap<>();
        JobDatasource datasource = jobDatasourceService.getById(id);
        if (ObjectUtil.isNull(datasource)) {
            map.put("code", "500");
            map.put("msg", "失败，没有相匹配的数据源");
            map.put("dataCount", 1);
        }
        try {
//            conn = ConnectUtil.getNewConnection(datasource.getJdbcUrl(),datasource.getJdbcUsername(),datasource.getJdbcPassword(),datasource.getJdbcDriverClass());
            BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
            map=queryTool.querySucessExamples(out,tableName);
           /* String sql = "select "+out+" from " + tableName+" limit 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            JSON json=resultSetToJSON(rs);
            logger.info(json.toJSONString());
            map.put("code", "00");
            map.put("msg", "成功");
            map.put("datas", json);
            map.put("dataCount", 1);*/
//            ConnectUtil.CloseConn(conn, ps, rs);//关闭连接
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            ConnectUtil.CloseConn(conn, ps, rs);//关闭连接
        }

        return map;
    }

    /**
     * 测试连接
     * @param dataBaseInfoQuery
     * @return
     */
    @Override
    public Object deleteDataBaseInfoServer(DataBaseInfoQuery dataBaseInfoQuery) {
        Connection connection=null;
        String erro=null;
        try {
            Class.forName(dataBaseInfoQuery.getDatabaseDriver());
            connection=DriverManager.getConnection(dataBaseInfoQuery.getDatabaseUrl(),dataBaseInfoQuery.getUserName(),dataBaseInfoQuery.getPassword());
            connection.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            erro=e.getMessage();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            erro=e.getMessage();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                erro=e.getMessage();
            }
        }
        if(StringUtils.isNotBlank(erro)){
            return new Result(false,"连接失败,"+erro);
        }else {
            return new Result(true,"连接成功");
        }
    }

    /**
     * jdbc连接执行拼接的查询sql
     * @param tableName 表名
     * @param in where条件
     * @param out  查询结果字段
     * @return
     */
    public Map queryTableData(String tableName, String in,String out,String serverName) {
        Map<String, Object> map = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn=null;
        TDatabaseInfoExample example=new TDatabaseInfoExample(TDatabaseInfo.class);
        TDatabaseInfoExample.Criteria criteria=example.createCriteria();
        if(StringUtils.isNotBlank(serverName)){//数据源名称
            criteria.andDataServerNameEqualTo(serverName);
        }
        List<TDatabaseInfo> databaseInfos=databaseInfoMapper.selectByExample(example);
        try {
            logger.info("得到的输入参数where条件："+in);
            logger.info("输出参数："+out);
            conn = ConnectUtil.getNewConnection(databaseInfos.get(0).getDatabaseUrl(),databaseInfos.get(0).getUserName(),databaseInfos.get(0).getPassword(),databaseInfos.get(0).getDatabaseDriver());
            String sql = "select "+out+" from " + tableName+" where "+in;
            logger.info("拼接要执行的sql："+sql);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            JSON json= ResultToJsonUtil.resultSetToJSON(rs);
            logger.info(json.toJSONString());
            map.put("code", "00");
            map.put("msg", "成功");
            map.put("datas", json);
            map.put("dataCount", 1);
            ConnectUtil.CloseConn(conn, ps, rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectUtil.CloseConn(conn, ps, rs);
        }

        return map;
    }


    /**
     * 将数据集中的数据转成Json数据格式
     *
     * @param resultSet 查询出来的数据集
     * @return JSON 转换后的对象
     */
    private JSON resultSetToJSON(ResultSet resultSet) {
        //数据集JSON格式
        JSONArray jsonArray = new JSONArray();
        //数据库中每行的数据对象
        JSONObject rowObj = null;
        try {
            //ResultSetMetaData 有关 ResultSet 中列的名称和类型的信息。
            ResultSetMetaData rsmd = resultSet.getMetaData();
            //遍历数据集
            while (resultSet.next()) {
                //每读取一行，新建对象
                rowObj = new JSONObject();
                // 获取表列数
                int columnCount = rsmd.getColumnCount();
                //列从1开始，要等于
                for (int i = 1; i <= columnCount; i++) {
                    //获取列名
                    String columnName = rsmd.getColumnName(i);
                    //取数据
                    String value = resultSet.getString(columnName);
                    //添加到rowObj中
                    rowObj.put(columnName, value);
                }
                 //添加到数据集Json
                jsonArray.add(rowObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }



}
