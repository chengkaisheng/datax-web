package com.wugui.datax.admin.tool.database;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * 表信息
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/7/30
 */
@Data
public class TableInfo {
    /**
     * 表名
     */
    private String name;

    /**
     * 注释
     */
    private String comment;
    /**
     * 所有列
     */
    private List<ColumnInfo> columns;

    private String dataLength;

    public TableInfo() {
        this.comment = "";
        this.dataLength = "";
        this.name="";
    }

    public TableInfo(String name, String comment) {
        this.name = name;
        this.comment = comment;
        this.dataLength = "";
    }

    public TableInfo(String name) {
        this.dataLength = "";
        this.comment = "";
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableInfo tableInfo = (TableInfo) o;
        return Objects.equals(name, tableInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
