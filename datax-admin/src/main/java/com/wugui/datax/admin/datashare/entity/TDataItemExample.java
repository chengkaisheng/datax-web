package com.wugui.datax.admin.datashare.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TDataItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TDataItemExample(Class<TDataItem> tDataItemClass) {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("item_id like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("item_id not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andInfoIdIsNull() {
            addCriterion("info_id is null");
            return (Criteria) this;
        }

        public Criteria andInfoIdIsNotNull() {
            addCriterion("info_id is not null");
            return (Criteria) this;
        }

        public Criteria andInfoIdEqualTo(String value) {
            addCriterion("info_id =", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotEqualTo(String value) {
            addCriterion("info_id <>", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdGreaterThan(String value) {
            addCriterion("info_id >", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("info_id >=", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLessThan(String value) {
            addCriterion("info_id <", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLessThanOrEqualTo(String value) {
            addCriterion("info_id <=", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLike(String value) {
            addCriterion("info_id like", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotLike(String value) {
            addCriterion("info_id not like", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdIn(List<String> values) {
            addCriterion("info_id in", values, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotIn(List<String> values) {
            addCriterion("info_id not in", values, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdBetween(String value1, String value2) {
            addCriterion("info_id between", value1, value2, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotBetween(String value1, String value2) {
            addCriterion("info_id not between", value1, value2, "infoId");
            return (Criteria) this;
        }

        public Criteria andIdentifierIsNull() {
            addCriterion("identifier is null");
            return (Criteria) this;
        }

        public Criteria andIdentifierIsNotNull() {
            addCriterion("identifier is not null");
            return (Criteria) this;
        }

        public Criteria andIdentifierEqualTo(String value) {
            addCriterion("identifier =", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierNotEqualTo(String value) {
            addCriterion("identifier <>", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierGreaterThan(String value) {
            addCriterion("identifier >", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierGreaterThanOrEqualTo(String value) {
            addCriterion("identifier >=", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierLessThan(String value) {
            addCriterion("identifier <", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierLessThanOrEqualTo(String value) {
            addCriterion("identifier <=", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierLike(String value) {
            addCriterion("identifier like", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierNotLike(String value) {
            addCriterion("identifier not like", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierIn(List<String> values) {
            addCriterion("identifier in", values, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierNotIn(List<String> values) {
            addCriterion("identifier not in", values, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierBetween(String value1, String value2) {
            addCriterion("identifier between", value1, value2, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierNotBetween(String value1, String value2) {
            addCriterion("identifier not between", value1, value2, "identifier");
            return (Criteria) this;
        }

        public Criteria andChineseNameIsNull() {
            addCriterion("chinese_name is null");
            return (Criteria) this;
        }

        public Criteria andChineseNameIsNotNull() {
            addCriterion("chinese_name is not null");
            return (Criteria) this;
        }

        public Criteria andChineseNameEqualTo(String value) {
            addCriterion("chinese_name =", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameNotEqualTo(String value) {
            addCriterion("chinese_name <>", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameGreaterThan(String value) {
            addCriterion("chinese_name >", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameGreaterThanOrEqualTo(String value) {
            addCriterion("chinese_name >=", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameLessThan(String value) {
            addCriterion("chinese_name <", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameLessThanOrEqualTo(String value) {
            addCriterion("chinese_name <=", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameLike(String value) {
            addCriterion("chinese_name like", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameNotLike(String value) {
            addCriterion("chinese_name not like", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameIn(List<String> values) {
            addCriterion("chinese_name in", values, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameNotIn(List<String> values) {
            addCriterion("chinese_name not in", values, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameBetween(String value1, String value2) {
            addCriterion("chinese_name between", value1, value2, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameNotBetween(String value1, String value2) {
            addCriterion("chinese_name not between", value1, value2, "chineseName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameIsNull() {
            addCriterion("english_name is null");
            return (Criteria) this;
        }

        public Criteria andEnglishNameIsNotNull() {
            addCriterion("english_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishNameEqualTo(String value) {
            addCriterion("english_name =", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameNotEqualTo(String value) {
            addCriterion("english_name <>", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameGreaterThan(String value) {
            addCriterion("english_name >", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameGreaterThanOrEqualTo(String value) {
            addCriterion("english_name >=", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameLessThan(String value) {
            addCriterion("english_name <", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameLessThanOrEqualTo(String value) {
            addCriterion("english_name <=", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameLike(String value) {
            addCriterion("english_name like", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameNotLike(String value) {
            addCriterion("english_name not like", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameIn(List<String> values) {
            addCriterion("english_name in", values, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameNotIn(List<String> values) {
            addCriterion("english_name not in", values, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameBetween(String value1, String value2) {
            addCriterion("english_name between", value1, value2, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameNotBetween(String value1, String value2) {
            addCriterion("english_name not between", value1, value2, "englishName");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(String value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(String value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(String value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(String value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(String value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLike(String value) {
            addCriterion("data_type like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotLike(String value) {
            addCriterion("data_type not like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<String> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<String> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(String value1, String value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(String value1, String value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andDataLengthIsNull() {
            addCriterion("data_length is null");
            return (Criteria) this;
        }

        public Criteria andDataLengthIsNotNull() {
            addCriterion("data_length is not null");
            return (Criteria) this;
        }

        public Criteria andDataLengthEqualTo(String value) {
            addCriterion("data_length =", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotEqualTo(String value) {
            addCriterion("data_length <>", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthGreaterThan(String value) {
            addCriterion("data_length >", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthGreaterThanOrEqualTo(String value) {
            addCriterion("data_length >=", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLessThan(String value) {
            addCriterion("data_length <", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLessThanOrEqualTo(String value) {
            addCriterion("data_length <=", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLike(String value) {
            addCriterion("data_length like", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotLike(String value) {
            addCriterion("data_length not like", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthIn(List<String> values) {
            addCriterion("data_length in", values, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotIn(List<String> values) {
            addCriterion("data_length not in", values, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthBetween(String value1, String value2) {
            addCriterion("data_length between", value1, value2, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotBetween(String value1, String value2) {
            addCriterion("data_length not between", value1, value2, "dataLength");
            return (Criteria) this;
        }

        public Criteria andInfoNameIsNull() {
            addCriterion("info_name is null");
            return (Criteria) this;
        }

        public Criteria andInfoNameIsNotNull() {
            addCriterion("info_name is not null");
            return (Criteria) this;
        }

        public Criteria andInfoNameEqualTo(String value) {
            addCriterion("info_name =", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameNotEqualTo(String value) {
            addCriterion("info_name <>", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameGreaterThan(String value) {
            addCriterion("info_name >", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameGreaterThanOrEqualTo(String value) {
            addCriterion("info_name >=", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameLessThan(String value) {
            addCriterion("info_name <", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameLessThanOrEqualTo(String value) {
            addCriterion("info_name <=", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameLike(String value) {
            addCriterion("info_name like", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameNotLike(String value) {
            addCriterion("info_name not like", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameIn(List<String> values) {
            addCriterion("info_name in", values, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameNotIn(List<String> values) {
            addCriterion("info_name not in", values, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameBetween(String value1, String value2) {
            addCriterion("info_name between", value1, value2, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameNotBetween(String value1, String value2) {
            addCriterion("info_name not between", value1, value2, "infoName");
            return (Criteria) this;
        }

        public Criteria andIsNullIsNull() {
            addCriterion("is_null is null");
            return (Criteria) this;
        }

        public Criteria andIsNullIsNotNull() {
            addCriterion("is_null is not null");
            return (Criteria) this;
        }

        public Criteria andIsNullEqualTo(String value) {
            addCriterion("is_null =", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullNotEqualTo(String value) {
            addCriterion("is_null <>", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullGreaterThan(String value) {
            addCriterion("is_null >", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullGreaterThanOrEqualTo(String value) {
            addCriterion("is_null >=", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullLessThan(String value) {
            addCriterion("is_null <", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullLessThanOrEqualTo(String value) {
            addCriterion("is_null <=", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullLike(String value) {
            addCriterion("is_null like", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullNotLike(String value) {
            addCriterion("is_null not like", value, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullIn(List<String> values) {
            addCriterion("is_null in", values, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullNotIn(List<String> values) {
            addCriterion("is_null not in", values, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullBetween(String value1, String value2) {
            addCriterion("is_null between", value1, value2, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsNullNotBetween(String value1, String value2) {
            addCriterion("is_null not between", value1, value2, "isNull");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyIsNull() {
            addCriterion("is_primarykey is null");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyIsNotNull() {
            addCriterion("is_primarykey is not null");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyEqualTo(String value) {
            addCriterion("is_primarykey =", value, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyNotEqualTo(String value) {
            addCriterion("is_primarykey <>", value, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyGreaterThan(String value) {
            addCriterion("is_primarykey >", value, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyGreaterThanOrEqualTo(String value) {
            addCriterion("is_primarykey >=", value, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyLessThan(String value) {
            addCriterion("is_primarykey <", value, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyLessThanOrEqualTo(String value) {
            addCriterion("is_primarykey <=", value, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyLike(String value) {
            addCriterion("is_primarykey like", value, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyNotLike(String value) {
            addCriterion("is_primarykey not like", value, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyIn(List<String> values) {
            addCriterion("is_primarykey in", values, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyNotIn(List<String> values) {
            addCriterion("is_primarykey not in", values, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyBetween(String value1, String value2) {
            addCriterion("is_primarykey between", value1, value2, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsPrimarykeyNotBetween(String value1, String value2) {
            addCriterion("is_primarykey not between", value1, value2, "isPrimarykey");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolIsNull() {
            addCriterion("is_notional_pool is null");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolIsNotNull() {
            addCriterion("is_notional_pool is not null");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolEqualTo(String value) {
            addCriterion("is_notional_pool =", value, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolNotEqualTo(String value) {
            addCriterion("is_notional_pool <>", value, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolGreaterThan(String value) {
            addCriterion("is_notional_pool >", value, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolGreaterThanOrEqualTo(String value) {
            addCriterion("is_notional_pool >=", value, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolLessThan(String value) {
            addCriterion("is_notional_pool <", value, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolLessThanOrEqualTo(String value) {
            addCriterion("is_notional_pool <=", value, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolLike(String value) {
            addCriterion("is_notional_pool like", value, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolNotLike(String value) {
            addCriterion("is_notional_pool not like", value, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolIn(List<String> values) {
            addCriterion("is_notional_pool in", values, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolNotIn(List<String> values) {
            addCriterion("is_notional_pool not in", values, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolBetween(String value1, String value2) {
            addCriterion("is_notional_pool between", value1, value2, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andIsNotionalPoolNotBetween(String value1, String value2) {
            addCriterion("is_notional_pool not between", value1, value2, "isNotionalPool");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateIsNull() {
            addCriterion("notional_pool_state is null");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateIsNotNull() {
            addCriterion("notional_pool_state is not null");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateEqualTo(String value) {
            addCriterion("notional_pool_state =", value, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateNotEqualTo(String value) {
            addCriterion("notional_pool_state <>", value, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateGreaterThan(String value) {
            addCriterion("notional_pool_state >", value, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateGreaterThanOrEqualTo(String value) {
            addCriterion("notional_pool_state >=", value, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateLessThan(String value) {
            addCriterion("notional_pool_state <", value, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateLessThanOrEqualTo(String value) {
            addCriterion("notional_pool_state <=", value, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateLike(String value) {
            addCriterion("notional_pool_state like", value, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateNotLike(String value) {
            addCriterion("notional_pool_state not like", value, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateIn(List<String> values) {
            addCriterion("notional_pool_state in", values, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateNotIn(List<String> values) {
            addCriterion("notional_pool_state not in", values, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateBetween(String value1, String value2) {
            addCriterion("notional_pool_state between", value1, value2, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andNotionalPoolStateNotBetween(String value1, String value2) {
            addCriterion("notional_pool_state not between", value1, value2, "notionalPoolState");
            return (Criteria) this;
        }

        public Criteria andStandardIsNull() {
            addCriterion("standard is null");
            return (Criteria) this;
        }

        public Criteria andStandardIsNotNull() {
            addCriterion("standard is not null");
            return (Criteria) this;
        }

        public Criteria andStandardEqualTo(String value) {
            addCriterion("standard =", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotEqualTo(String value) {
            addCriterion("standard <>", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardGreaterThan(String value) {
            addCriterion("standard >", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardGreaterThanOrEqualTo(String value) {
            addCriterion("standard >=", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLessThan(String value) {
            addCriterion("standard <", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLessThanOrEqualTo(String value) {
            addCriterion("standard <=", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardLike(String value) {
            addCriterion("standard like", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotLike(String value) {
            addCriterion("standard not like", value, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardIn(List<String> values) {
            addCriterion("standard in", values, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotIn(List<String> values) {
            addCriterion("standard not in", values, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardBetween(String value1, String value2) {
            addCriterion("standard between", value1, value2, "standard");
            return (Criteria) this;
        }

        public Criteria andStandardNotBetween(String value1, String value2) {
            addCriterion("standard not between", value1, value2, "standard");
            return (Criteria) this;
        }

        public Criteria andItemDefaultIsNull() {
            addCriterion("item_default is null");
            return (Criteria) this;
        }

        public Criteria andItemDefaultIsNotNull() {
            addCriterion("item_default is not null");
            return (Criteria) this;
        }

        public Criteria andItemDefaultEqualTo(String value) {
            addCriterion("item_default =", value, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultNotEqualTo(String value) {
            addCriterion("item_default <>", value, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultGreaterThan(String value) {
            addCriterion("item_default >", value, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultGreaterThanOrEqualTo(String value) {
            addCriterion("item_default >=", value, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultLessThan(String value) {
            addCriterion("item_default <", value, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultLessThanOrEqualTo(String value) {
            addCriterion("item_default <=", value, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultLike(String value) {
            addCriterion("item_default like", value, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultNotLike(String value) {
            addCriterion("item_default not like", value, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultIn(List<String> values) {
            addCriterion("item_default in", values, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultNotIn(List<String> values) {
            addCriterion("item_default not in", values, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultBetween(String value1, String value2) {
            addCriterion("item_default between", value1, value2, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andItemDefaultNotBetween(String value1, String value2) {
            addCriterion("item_default not between", value1, value2, "itemDefault");
            return (Criteria) this;
        }

        public Criteria andDataCompanyIsNull() {
            addCriterion("data_company is null");
            return (Criteria) this;
        }

        public Criteria andDataCompanyIsNotNull() {
            addCriterion("data_company is not null");
            return (Criteria) this;
        }

        public Criteria andDataCompanyEqualTo(String value) {
            addCriterion("data_company =", value, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyNotEqualTo(String value) {
            addCriterion("data_company <>", value, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyGreaterThan(String value) {
            addCriterion("data_company >", value, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("data_company >=", value, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyLessThan(String value) {
            addCriterion("data_company <", value, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyLessThanOrEqualTo(String value) {
            addCriterion("data_company <=", value, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyLike(String value) {
            addCriterion("data_company like", value, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyNotLike(String value) {
            addCriterion("data_company not like", value, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyIn(List<String> values) {
            addCriterion("data_company in", values, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyNotIn(List<String> values) {
            addCriterion("data_company not in", values, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyBetween(String value1, String value2) {
            addCriterion("data_company between", value1, value2, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andDataCompanyNotBetween(String value1, String value2) {
            addCriterion("data_company not between", value1, value2, "dataCompany");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesIsNull() {
            addCriterion("is_dictionaries is null");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesIsNotNull() {
            addCriterion("is_dictionaries is not null");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesEqualTo(String value) {
            addCriterion("is_dictionaries =", value, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesNotEqualTo(String value) {
            addCriterion("is_dictionaries <>", value, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesGreaterThan(String value) {
            addCriterion("is_dictionaries >", value, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesGreaterThanOrEqualTo(String value) {
            addCriterion("is_dictionaries >=", value, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesLessThan(String value) {
            addCriterion("is_dictionaries <", value, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesLessThanOrEqualTo(String value) {
            addCriterion("is_dictionaries <=", value, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesLike(String value) {
            addCriterion("is_dictionaries like", value, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesNotLike(String value) {
            addCriterion("is_dictionaries not like", value, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesIn(List<String> values) {
            addCriterion("is_dictionaries in", values, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesNotIn(List<String> values) {
            addCriterion("is_dictionaries not in", values, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesBetween(String value1, String value2) {
            addCriterion("is_dictionaries between", value1, value2, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andIsDictionariesNotBetween(String value1, String value2) {
            addCriterion("is_dictionaries not between", value1, value2, "isDictionaries");
            return (Criteria) this;
        }

        public Criteria andShareAttributeIsNull() {
            addCriterion("share_attribute is null");
            return (Criteria) this;
        }

        public Criteria andShareAttributeIsNotNull() {
            addCriterion("share_attribute is not null");
            return (Criteria) this;
        }

        public Criteria andShareAttributeEqualTo(String value) {
            addCriterion("share_attribute =", value, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeNotEqualTo(String value) {
            addCriterion("share_attribute <>", value, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeGreaterThan(String value) {
            addCriterion("share_attribute >", value, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeGreaterThanOrEqualTo(String value) {
            addCriterion("share_attribute >=", value, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeLessThan(String value) {
            addCriterion("share_attribute <", value, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeLessThanOrEqualTo(String value) {
            addCriterion("share_attribute <=", value, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeLike(String value) {
            addCriterion("share_attribute like", value, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeNotLike(String value) {
            addCriterion("share_attribute not like", value, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeIn(List<String> values) {
            addCriterion("share_attribute in", values, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeNotIn(List<String> values) {
            addCriterion("share_attribute not in", values, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeBetween(String value1, String value2) {
            addCriterion("share_attribute between", value1, value2, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareAttributeNotBetween(String value1, String value2) {
            addCriterion("share_attribute not between", value1, value2, "shareAttribute");
            return (Criteria) this;
        }

        public Criteria andShareConditionIsNull() {
            addCriterion("share_condition is null");
            return (Criteria) this;
        }

        public Criteria andShareConditionIsNotNull() {
            addCriterion("share_condition is not null");
            return (Criteria) this;
        }

        public Criteria andShareConditionEqualTo(String value) {
            addCriterion("share_condition =", value, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionNotEqualTo(String value) {
            addCriterion("share_condition <>", value, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionGreaterThan(String value) {
            addCriterion("share_condition >", value, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionGreaterThanOrEqualTo(String value) {
            addCriterion("share_condition >=", value, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionLessThan(String value) {
            addCriterion("share_condition <", value, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionLessThanOrEqualTo(String value) {
            addCriterion("share_condition <=", value, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionLike(String value) {
            addCriterion("share_condition like", value, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionNotLike(String value) {
            addCriterion("share_condition not like", value, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionIn(List<String> values) {
            addCriterion("share_condition in", values, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionNotIn(List<String> values) {
            addCriterion("share_condition not in", values, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionBetween(String value1, String value2) {
            addCriterion("share_condition between", value1, value2, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andShareConditionNotBetween(String value1, String value2) {
            addCriterion("share_condition not between", value1, value2, "shareCondition");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeIsNull() {
            addCriterion("open_attribute is null");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeIsNotNull() {
            addCriterion("open_attribute is not null");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeEqualTo(String value) {
            addCriterion("open_attribute =", value, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeNotEqualTo(String value) {
            addCriterion("open_attribute <>", value, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeGreaterThan(String value) {
            addCriterion("open_attribute >", value, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeGreaterThanOrEqualTo(String value) {
            addCriterion("open_attribute >=", value, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeLessThan(String value) {
            addCriterion("open_attribute <", value, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeLessThanOrEqualTo(String value) {
            addCriterion("open_attribute <=", value, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeLike(String value) {
            addCriterion("open_attribute like", value, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeNotLike(String value) {
            addCriterion("open_attribute not like", value, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeIn(List<String> values) {
            addCriterion("open_attribute in", values, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeNotIn(List<String> values) {
            addCriterion("open_attribute not in", values, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeBetween(String value1, String value2) {
            addCriterion("open_attribute between", value1, value2, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenAttributeNotBetween(String value1, String value2) {
            addCriterion("open_attribute not between", value1, value2, "openAttribute");
            return (Criteria) this;
        }

        public Criteria andOpenConditionIsNull() {
            addCriterion("open_condition is null");
            return (Criteria) this;
        }

        public Criteria andOpenConditionIsNotNull() {
            addCriterion("open_condition is not null");
            return (Criteria) this;
        }

        public Criteria andOpenConditionEqualTo(String value) {
            addCriterion("open_condition =", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionNotEqualTo(String value) {
            addCriterion("open_condition <>", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionGreaterThan(String value) {
            addCriterion("open_condition >", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionGreaterThanOrEqualTo(String value) {
            addCriterion("open_condition >=", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionLessThan(String value) {
            addCriterion("open_condition <", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionLessThanOrEqualTo(String value) {
            addCriterion("open_condition <=", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionLike(String value) {
            addCriterion("open_condition like", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionNotLike(String value) {
            addCriterion("open_condition not like", value, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionIn(List<String> values) {
            addCriterion("open_condition in", values, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionNotIn(List<String> values) {
            addCriterion("open_condition not in", values, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionBetween(String value1, String value2) {
            addCriterion("open_condition between", value1, value2, "openCondition");
            return (Criteria) this;
        }

        public Criteria andOpenConditionNotBetween(String value1, String value2) {
            addCriterion("open_condition not between", value1, value2, "openCondition");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDataServerNameIsNull() {
            addCriterion("data_server_name is null");
            return (Criteria) this;
        }

        public Criteria andDataServerNameIsNotNull() {
            addCriterion("data_server_name is not null");
            return (Criteria) this;
        }

        public Criteria andDataServerNameEqualTo(String value) {
            addCriterion("data_server_name =", value, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameNotEqualTo(String value) {
            addCriterion("data_server_name <>", value, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameGreaterThan(String value) {
            addCriterion("data_server_name >", value, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameGreaterThanOrEqualTo(String value) {
            addCriterion("data_server_name >=", value, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameLessThan(String value) {
            addCriterion("data_server_name <", value, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameLessThanOrEqualTo(String value) {
            addCriterion("data_server_name <=", value, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameLike(String value) {
            addCriterion("data_server_name like", value, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameNotLike(String value) {
            addCriterion("data_server_name not like", value, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameIn(List<String> values) {
            addCriterion("data_server_name in", values, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameNotIn(List<String> values) {
            addCriterion("data_server_name not in", values, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameBetween(String value1, String value2) {
            addCriterion("data_server_name between", value1, value2, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andDataServerNameNotBetween(String value1, String value2) {
            addCriterion("data_server_name not between", value1, value2, "dataServerName");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(String value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(String value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(String value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(String value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(String value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(String value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLike(String value) {
            addCriterion("is_delete like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotLike(String value) {
            addCriterion("is_delete not like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<String> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<String> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(String value1, String value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(String value1, String value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}