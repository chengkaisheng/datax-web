package com.wugui.datax.admin.entity;

import java.util.List;

/**
 * @author hf
 * @creat 2020-09-24-14:55
 */

public class Property {
    private String id;

    @Override
    public String toString() {
        return "Property{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", dataType='" + dataType + '\'' +
                ", description='" + description + '\'' +
                ", displayName='" + displayName + '\'' +
                ", features=" + features +
                ", value='" + value + '\'' +
                '}';
    }

    private String category;

    private String dataType;

    private String description;

    private String displayName;

    private List<String> features;

    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
