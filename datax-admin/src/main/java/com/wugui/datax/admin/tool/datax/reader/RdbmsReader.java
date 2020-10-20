package com.wugui.datax.admin.tool.datax.reader;

import java.util.Map;

/**
 * 关系型数据库通用Reader，暂时hive，impala使用这个通用类
 */
public class RdbmsReader extends BaseReaderPlugin implements DataxReaderInterface{
    @Override
    public String getName() {
        return "rdbmsreader";
    }

    @Override
    public Map<String, Object> sample() {
        return null;
    }
}
