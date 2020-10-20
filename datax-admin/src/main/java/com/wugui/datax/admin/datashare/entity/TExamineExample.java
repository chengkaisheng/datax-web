package com.wugui.datax.admin.datashare.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TExamineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TExamineExample(Class<TExamine> tExamineClass) {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNull() {
            addCriterion("apply_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNotNull() {
            addCriterion("apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyIdEqualTo(String value) {
            addCriterion("apply_id =", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotEqualTo(String value) {
            addCriterion("apply_id <>", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThan(String value) {
            addCriterion("apply_id >", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThanOrEqualTo(String value) {
            addCriterion("apply_id >=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThan(String value) {
            addCriterion("apply_id <", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThanOrEqualTo(String value) {
            addCriterion("apply_id <=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLike(String value) {
            addCriterion("apply_id like", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotLike(String value) {
            addCriterion("apply_id not like", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(List<String> values) {
            addCriterion("apply_id in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(List<String> values) {
            addCriterion("apply_id not in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdBetween(String value1, String value2) {
            addCriterion("apply_id between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotBetween(String value1, String value2) {
            addCriterion("apply_id not between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNull() {
            addCriterion("department_name is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNotNull() {
            addCriterion("department_name is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameEqualTo(String value) {
            addCriterion("department_name =", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotEqualTo(String value) {
            addCriterion("department_name <>", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThan(String value) {
            addCriterion("department_name >", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("department_name >=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThan(String value) {
            addCriterion("department_name <", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThanOrEqualTo(String value) {
            addCriterion("department_name <=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLike(String value) {
            addCriterion("department_name like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotLike(String value) {
            addCriterion("department_name not like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIn(List<String> values) {
            addCriterion("department_name in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotIn(List<String> values) {
            addCriterion("department_name not in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameBetween(String value1, String value2) {
            addCriterion("department_name between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotBetween(String value1, String value2) {
            addCriterion("department_name not between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNull() {
            addCriterion("apply_type is null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNotNull() {
            addCriterion("apply_type is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeEqualTo(String value) {
            addCriterion("apply_type =", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotEqualTo(String value) {
            addCriterion("apply_type <>", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThan(String value) {
            addCriterion("apply_type >", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("apply_type >=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThan(String value) {
            addCriterion("apply_type <", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThanOrEqualTo(String value) {
            addCriterion("apply_type <=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLike(String value) {
            addCriterion("apply_type like", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotLike(String value) {
            addCriterion("apply_type not like", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIn(List<String> values) {
            addCriterion("apply_type in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotIn(List<String> values) {
            addCriterion("apply_type not in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeBetween(String value1, String value2) {
            addCriterion("apply_type between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotBetween(String value1, String value2) {
            addCriterion("apply_type not between", value1, value2, "applyType");
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

        public Criteria andTableEnglishIsNull() {
            addCriterion("table_english is null");
            return (Criteria) this;
        }

        public Criteria andTableEnglishIsNotNull() {
            addCriterion("table_english is not null");
            return (Criteria) this;
        }

        public Criteria andTableEnglishEqualTo(String value) {
            addCriterion("table_english =", value, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNotEqualTo(String value) {
            addCriterion("table_english <>", value, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishGreaterThan(String value) {
            addCriterion("table_english >", value, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishGreaterThanOrEqualTo(String value) {
            addCriterion("table_english >=", value, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishLessThan(String value) {
            addCriterion("table_english <", value, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishLessThanOrEqualTo(String value) {
            addCriterion("table_english <=", value, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishLike(String value) {
            addCriterion("table_english like", value, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNotLike(String value) {
            addCriterion("table_english not like", value, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishIn(List<String> values) {
            addCriterion("table_english in", values, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNotIn(List<String> values) {
            addCriterion("table_english not in", values, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishBetween(String value1, String value2) {
            addCriterion("table_english between", value1, value2, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNotBetween(String value1, String value2) {
            addCriterion("table_english not between", value1, value2, "tableEnglish");
            return (Criteria) this;
        }

        public Criteria andContactsIsNull() {
            addCriterion("contacts is null");
            return (Criteria) this;
        }

        public Criteria andContactsIsNotNull() {
            addCriterion("contacts is not null");
            return (Criteria) this;
        }

        public Criteria andContactsEqualTo(String value) {
            addCriterion("contacts =", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotEqualTo(String value) {
            addCriterion("contacts <>", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThan(String value) {
            addCriterion("contacts >", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThanOrEqualTo(String value) {
            addCriterion("contacts >=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThan(String value) {
            addCriterion("contacts <", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThanOrEqualTo(String value) {
            addCriterion("contacts <=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLike(String value) {
            addCriterion("contacts like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotLike(String value) {
            addCriterion("contacts not like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsIn(List<String> values) {
            addCriterion("contacts in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotIn(List<String> values) {
            addCriterion("contacts not in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsBetween(String value1, String value2) {
            addCriterion("contacts between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotBetween(String value1, String value2) {
            addCriterion("contacts not between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andExaTimeIsNull() {
            addCriterion("exa_time is null");
            return (Criteria) this;
        }

        public Criteria andExaTimeIsNotNull() {
            addCriterion("exa_time is not null");
            return (Criteria) this;
        }

        public Criteria andExaTimeEqualTo(Date value) {
            addCriterion("exa_time =", value, "exaTime");
            return (Criteria) this;
        }

        public Criteria andExaTimeNotEqualTo(Date value) {
            addCriterion("exa_time <>", value, "exaTime");
            return (Criteria) this;
        }

        public Criteria andExaTimeGreaterThan(Date value) {
            addCriterion("exa_time >", value, "exaTime");
            return (Criteria) this;
        }

        public Criteria andExaTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exa_time >=", value, "exaTime");
            return (Criteria) this;
        }

        public Criteria andExaTimeLessThan(Date value) {
            addCriterion("exa_time <", value, "exaTime");
            return (Criteria) this;
        }

        public Criteria andExaTimeLessThanOrEqualTo(Date value) {
            addCriterion("exa_time <=", value, "exaTime");
            return (Criteria) this;
        }

        public Criteria andExaTimeIn(List<Date> values) {
            addCriterion("exa_time in", values, "exaTime");
            return (Criteria) this;
        }

        public Criteria andExaTimeNotIn(List<Date> values) {
            addCriterion("exa_time not in", values, "exaTime");
            return (Criteria) this;
        }

        public Criteria andExaTimeBetween(Date value1, Date value2) {
            addCriterion("exa_time between", value1, value2, "exaTime");
            return (Criteria) this;
        }

        public Criteria andExaTimeNotBetween(Date value1, Date value2) {
            addCriterion("exa_time not between", value1, value2, "exaTime");
            return (Criteria) this;
        }

        public Criteria andCatalogStateIsNull() {
            addCriterion("catalog_state is null");
            return (Criteria) this;
        }

        public Criteria andCatalogStateIsNotNull() {
            addCriterion("catalog_state is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogStateEqualTo(String value) {
            addCriterion("catalog_state =", value, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateNotEqualTo(String value) {
            addCriterion("catalog_state <>", value, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateGreaterThan(String value) {
            addCriterion("catalog_state >", value, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateGreaterThanOrEqualTo(String value) {
            addCriterion("catalog_state >=", value, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateLessThan(String value) {
            addCriterion("catalog_state <", value, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateLessThanOrEqualTo(String value) {
            addCriterion("catalog_state <=", value, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateLike(String value) {
            addCriterion("catalog_state like", value, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateNotLike(String value) {
            addCriterion("catalog_state not like", value, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateIn(List<String> values) {
            addCriterion("catalog_state in", values, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateNotIn(List<String> values) {
            addCriterion("catalog_state not in", values, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateBetween(String value1, String value2) {
            addCriterion("catalog_state between", value1, value2, "catalogState");
            return (Criteria) this;
        }

        public Criteria andCatalogStateNotBetween(String value1, String value2) {
            addCriterion("catalog_state not between", value1, value2, "catalogState");
            return (Criteria) this;
        }

        public Criteria andExaResultIsNull() {
            addCriterion("exa_result is null");
            return (Criteria) this;
        }

        public Criteria andExaResultIsNotNull() {
            addCriterion("exa_result is not null");
            return (Criteria) this;
        }

        public Criteria andExaResultEqualTo(String value) {
            addCriterion("exa_result =", value, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultNotEqualTo(String value) {
            addCriterion("exa_result <>", value, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultGreaterThan(String value) {
            addCriterion("exa_result >", value, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultGreaterThanOrEqualTo(String value) {
            addCriterion("exa_result >=", value, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultLessThan(String value) {
            addCriterion("exa_result <", value, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultLessThanOrEqualTo(String value) {
            addCriterion("exa_result <=", value, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultLike(String value) {
            addCriterion("exa_result like", value, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultNotLike(String value) {
            addCriterion("exa_result not like", value, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultIn(List<String> values) {
            addCriterion("exa_result in", values, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultNotIn(List<String> values) {
            addCriterion("exa_result not in", values, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultBetween(String value1, String value2) {
            addCriterion("exa_result between", value1, value2, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaResultNotBetween(String value1, String value2) {
            addCriterion("exa_result not between", value1, value2, "exaResult");
            return (Criteria) this;
        }

        public Criteria andExaDescribeIsNull() {
            addCriterion("exa_describe is null");
            return (Criteria) this;
        }

        public Criteria andExaDescribeIsNotNull() {
            addCriterion("exa_describe is not null");
            return (Criteria) this;
        }

        public Criteria andExaDescribeEqualTo(String value) {
            addCriterion("exa_describe =", value, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeNotEqualTo(String value) {
            addCriterion("exa_describe <>", value, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeGreaterThan(String value) {
            addCriterion("exa_describe >", value, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("exa_describe >=", value, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeLessThan(String value) {
            addCriterion("exa_describe <", value, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeLessThanOrEqualTo(String value) {
            addCriterion("exa_describe <=", value, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeLike(String value) {
            addCriterion("exa_describe like", value, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeNotLike(String value) {
            addCriterion("exa_describe not like", value, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeIn(List<String> values) {
            addCriterion("exa_describe in", values, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeNotIn(List<String> values) {
            addCriterion("exa_describe not in", values, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeBetween(String value1, String value2) {
            addCriterion("exa_describe between", value1, value2, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andExaDescribeNotBetween(String value1, String value2) {
            addCriterion("exa_describe not between", value1, value2, "exaDescribe");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpIsNull() {
            addCriterion("database_ip is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpIsNotNull() {
            addCriterion("database_ip is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpEqualTo(String value) {
            addCriterion("database_ip =", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpNotEqualTo(String value) {
            addCriterion("database_ip <>", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpGreaterThan(String value) {
            addCriterion("database_ip >", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpGreaterThanOrEqualTo(String value) {
            addCriterion("database_ip >=", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpLessThan(String value) {
            addCriterion("database_ip <", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpLessThanOrEqualTo(String value) {
            addCriterion("database_ip <=", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpLike(String value) {
            addCriterion("database_ip like", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpNotLike(String value) {
            addCriterion("database_ip not like", value, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpIn(List<String> values) {
            addCriterion("database_ip in", values, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpNotIn(List<String> values) {
            addCriterion("database_ip not in", values, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpBetween(String value1, String value2) {
            addCriterion("database_ip between", value1, value2, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andDatabaseIpNotBetween(String value1, String value2) {
            addCriterion("database_ip not between", value1, value2, "databaseIp");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNull() {
            addCriterion("login_name is null");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("login_name is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("login_name =", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("login_name <>", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("login_name >", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("login_name >=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("login_name <", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("login_name <=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("login_name like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("login_name not like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameIn(List<String> values) {
            addCriterion("login_name in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotIn(List<String> values) {
            addCriterion("login_name not in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("login_name between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("login_name not between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(String value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(String value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(String value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(String value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(String value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(String value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLike(String value) {
            addCriterion("port like", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotLike(String value) {
            addCriterion("port not like", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<String> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<String> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(String value1, String value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(String value1, String value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIsNull() {
            addCriterion("database_name is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIsNotNull() {
            addCriterion("database_name is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameEqualTo(String value) {
            addCriterion("database_name =", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotEqualTo(String value) {
            addCriterion("database_name <>", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameGreaterThan(String value) {
            addCriterion("database_name >", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("database_name >=", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLessThan(String value) {
            addCriterion("database_name <", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLessThanOrEqualTo(String value) {
            addCriterion("database_name <=", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLike(String value) {
            addCriterion("database_name like", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotLike(String value) {
            addCriterion("database_name not like", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIn(List<String> values) {
            addCriterion("database_name in", values, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotIn(List<String> values) {
            addCriterion("database_name not in", values, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameBetween(String value1, String value2) {
            addCriterion("database_name between", value1, value2, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotBetween(String value1, String value2) {
            addCriterion("database_name not between", value1, value2, "databaseName");
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