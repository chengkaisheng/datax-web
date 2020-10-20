package com.wugui.datax.admin.tool.datax.writer;

import java.util.Map;

public class ImpalaWriter extends BaseWriterPlugin implements DataxWriterInterface{
    @Override
    public String getName() {
        return "rdbmswriter";
    }

    @Override
    public Map<String, Object> sample() {
        return null;
    }
}
