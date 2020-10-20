package com.wugui.datax.admin.datashare.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TInterfaceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TInterfaceExample(Class<TInterface> tInterfaceClass) {
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

        public Criteria andRegisterCompanyIsNull() {
            addCriterion("register_company is null");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyIsNotNull() {
            addCriterion("register_company is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyEqualTo(String value) {
            addCriterion("register_company =", value, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyNotEqualTo(String value) {
            addCriterion("register_company <>", value, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyGreaterThan(String value) {
            addCriterion("register_company >", value, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("register_company >=", value, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyLessThan(String value) {
            addCriterion("register_company <", value, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyLessThanOrEqualTo(String value) {
            addCriterion("register_company <=", value, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyLike(String value) {
            addCriterion("register_company like", value, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyNotLike(String value) {
            addCriterion("register_company not like", value, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyIn(List<String> values) {
            addCriterion("register_company in", values, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyNotIn(List<String> values) {
            addCriterion("register_company not in", values, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyBetween(String value1, String value2) {
            addCriterion("register_company between", value1, value2, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andRegisterCompanyNotBetween(String value1, String value2) {
            addCriterion("register_company not between", value1, value2, "registerCompany");
            return (Criteria) this;
        }

        public Criteria andInterNameIsNull() {
            addCriterion("inter_name is null");
            return (Criteria) this;
        }

        public Criteria andInterNameIsNotNull() {
            addCriterion("inter_name is not null");
            return (Criteria) this;
        }

        public Criteria andInterNameEqualTo(String value) {
            addCriterion("inter_name =", value, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameNotEqualTo(String value) {
            addCriterion("inter_name <>", value, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameGreaterThan(String value) {
            addCriterion("inter_name >", value, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameGreaterThanOrEqualTo(String value) {
            addCriterion("inter_name >=", value, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameLessThan(String value) {
            addCriterion("inter_name <", value, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameLessThanOrEqualTo(String value) {
            addCriterion("inter_name <=", value, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameLike(String value) {
            addCriterion("inter_name like", value, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameNotLike(String value) {
            addCriterion("inter_name not like", value, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameIn(List<String> values) {
            addCriterion("inter_name in", values, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameNotIn(List<String> values) {
            addCriterion("inter_name not in", values, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameBetween(String value1, String value2) {
            addCriterion("inter_name between", value1, value2, "interName");
            return (Criteria) this;
        }

        public Criteria andInterNameNotBetween(String value1, String value2) {
            addCriterion("inter_name not between", value1, value2, "interName");
            return (Criteria) this;
        }

        public Criteria andInterRemarkIsNull() {
            addCriterion("inter_remark is null");
            return (Criteria) this;
        }

        public Criteria andInterRemarkIsNotNull() {
            addCriterion("inter_remark is not null");
            return (Criteria) this;
        }

        public Criteria andInterRemarkEqualTo(String value) {
            addCriterion("inter_remark =", value, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkNotEqualTo(String value) {
            addCriterion("inter_remark <>", value, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkGreaterThan(String value) {
            addCriterion("inter_remark >", value, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("inter_remark >=", value, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkLessThan(String value) {
            addCriterion("inter_remark <", value, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkLessThanOrEqualTo(String value) {
            addCriterion("inter_remark <=", value, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkLike(String value) {
            addCriterion("inter_remark like", value, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkNotLike(String value) {
            addCriterion("inter_remark not like", value, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkIn(List<String> values) {
            addCriterion("inter_remark in", values, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkNotIn(List<String> values) {
            addCriterion("inter_remark not in", values, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkBetween(String value1, String value2) {
            addCriterion("inter_remark between", value1, value2, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterRemarkNotBetween(String value1, String value2) {
            addCriterion("inter_remark not between", value1, value2, "interRemark");
            return (Criteria) this;
        }

        public Criteria andInterStateIsNull() {
            addCriterion("inter_state is null");
            return (Criteria) this;
        }

        public Criteria andInterStateIsNotNull() {
            addCriterion("inter_state is not null");
            return (Criteria) this;
        }

        public Criteria andInterStateEqualTo(String value) {
            addCriterion("inter_state =", value, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateNotEqualTo(String value) {
            addCriterion("inter_state <>", value, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateGreaterThan(String value) {
            addCriterion("inter_state >", value, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateGreaterThanOrEqualTo(String value) {
            addCriterion("inter_state >=", value, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateLessThan(String value) {
            addCriterion("inter_state <", value, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateLessThanOrEqualTo(String value) {
            addCriterion("inter_state <=", value, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateLike(String value) {
            addCriterion("inter_state like", value, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateNotLike(String value) {
            addCriterion("inter_state not like", value, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateIn(List<String> values) {
            addCriterion("inter_state in", values, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateNotIn(List<String> values) {
            addCriterion("inter_state not in", values, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateBetween(String value1, String value2) {
            addCriterion("inter_state between", value1, value2, "interState");
            return (Criteria) this;
        }

        public Criteria andInterStateNotBetween(String value1, String value2) {
            addCriterion("inter_state not between", value1, value2, "interState");
            return (Criteria) this;
        }

        /*public Criteria andDataRangeIsNull() {
            addCriterion("data_range is null");
            return (Criteria) this;
        }

        public Criteria andDataRangeIsNotNull() {
            addCriterion("data_range is not null");
            return (Criteria) this;
        }

        public Criteria andDataRangeEqualTo(String value) {
            addCriterion("data_range =", value, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeNotEqualTo(String value) {
            addCriterion("data_range <>", value, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeGreaterThan(String value) {
            addCriterion("data_range >", value, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeGreaterThanOrEqualTo(String value) {
            addCriterion("data_range >=", value, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeLessThan(String value) {
            addCriterion("data_range <", value, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeLessThanOrEqualTo(String value) {
            addCriterion("data_range <=", value, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeLike(String value) {
            addCriterion("data_range like", value, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeNotLike(String value) {
            addCriterion("data_range not like", value, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeIn(List<String> values) {
            addCriterion("data_range in", values, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeNotIn(List<String> values) {
            addCriterion("data_range not in", values, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeBetween(String value1, String value2) {
            addCriterion("data_range between", value1, value2, "dataRange");
            return (Criteria) this;
        }

        public Criteria andDataRangeNotBetween(String value1, String value2) {
            addCriterion("data_range not between", value1, value2, "dataRange");
            return (Criteria) this;
        }*/

        /*public Criteria andDataCompanyIsNull() {
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
        }*/

        /*public Criteria andImplMethodIsNull() {
            addCriterion("impl_method is null");
            return (Criteria) this;
        }

        public Criteria andImplMethodIsNotNull() {
            addCriterion("impl_method is not null");
            return (Criteria) this;
        }

        public Criteria andImplMethodEqualTo(String value) {
            addCriterion("impl_method =", value, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodNotEqualTo(String value) {
            addCriterion("impl_method <>", value, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodGreaterThan(String value) {
            addCriterion("impl_method >", value, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodGreaterThanOrEqualTo(String value) {
            addCriterion("impl_method >=", value, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodLessThan(String value) {
            addCriterion("impl_method <", value, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodLessThanOrEqualTo(String value) {
            addCriterion("impl_method <=", value, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodLike(String value) {
            addCriterion("impl_method like", value, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodNotLike(String value) {
            addCriterion("impl_method not like", value, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodIn(List<String> values) {
            addCriterion("impl_method in", values, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodNotIn(List<String> values) {
            addCriterion("impl_method not in", values, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodBetween(String value1, String value2) {
            addCriterion("impl_method between", value1, value2, "implMethod");
            return (Criteria) this;
        }

        public Criteria andImplMethodNotBetween(String value1, String value2) {
            addCriterion("impl_method not between", value1, value2, "implMethod");
            return (Criteria) this;
        }*/

        /*public Criteria andBusinessTypeIsNull() {
            addCriterion("business_type is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNotNull() {
            addCriterion("business_type is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeEqualTo(String value) {
            addCriterion("business_type =", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotEqualTo(String value) {
            addCriterion("business_type <>", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThan(String value) {
            addCriterion("business_type >", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThanOrEqualTo(String value) {
            addCriterion("business_type >=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThan(String value) {
            addCriterion("business_type <", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThanOrEqualTo(String value) {
            addCriterion("business_type <=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLike(String value) {
            addCriterion("business_type like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotLike(String value) {
            addCriterion("business_type not like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIn(List<String> values) {
            addCriterion("business_type in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotIn(List<String> values) {
            addCriterion("business_type not in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeBetween(String value1, String value2) {
            addCriterion("business_type between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotBetween(String value1, String value2) {
            addCriterion("business_type not between", value1, value2, "businessType");
            return (Criteria) this;
        }*/

        public Criteria andResponseModeIsNull() {
            addCriterion("response_mode is null");
            return (Criteria) this;
        }

        public Criteria andResponseModeIsNotNull() {
            addCriterion("response_mode is not null");
            return (Criteria) this;
        }

        public Criteria andResponseModeEqualTo(String value) {
            addCriterion("response_mode =", value, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeNotEqualTo(String value) {
            addCriterion("response_mode <>", value, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeGreaterThan(String value) {
            addCriterion("response_mode >", value, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeGreaterThanOrEqualTo(String value) {
            addCriterion("response_mode >=", value, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeLessThan(String value) {
            addCriterion("response_mode <", value, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeLessThanOrEqualTo(String value) {
            addCriterion("response_mode <=", value, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeLike(String value) {
            addCriterion("response_mode like", value, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeNotLike(String value) {
            addCriterion("response_mode not like", value, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeIn(List<String> values) {
            addCriterion("response_mode in", values, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeNotIn(List<String> values) {
            addCriterion("response_mode not in", values, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeBetween(String value1, String value2) {
            addCriterion("response_mode between", value1, value2, "responseMode");
            return (Criteria) this;
        }

        public Criteria andResponseModeNotBetween(String value1, String value2) {
            addCriterion("response_mode not between", value1, value2, "responseMode");
            return (Criteria) this;
        }
        /*public Criteria andDeployMethodIsNull() {
            addCriterion("deploy_method is null");
            return (Criteria) this;
        }

        public Criteria andDeployMethodIsNotNull() {
            addCriterion("deploy_method is not null");
            return (Criteria) this;
        }

        public Criteria andDeployMethodEqualTo(String value) {
            addCriterion("deploy_method =", value, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodNotEqualTo(String value) {
            addCriterion("deploy_method <>", value, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodGreaterThan(String value) {
            addCriterion("deploy_method >", value, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodGreaterThanOrEqualTo(String value) {
            addCriterion("deploy_method >=", value, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodLessThan(String value) {
            addCriterion("deploy_method <", value, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodLessThanOrEqualTo(String value) {
            addCriterion("deploy_method <=", value, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodLike(String value) {
            addCriterion("deploy_method like", value, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodNotLike(String value) {
            addCriterion("deploy_method not like", value, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodIn(List<String> values) {
            addCriterion("deploy_method in", values, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodNotIn(List<String> values) {
            addCriterion("deploy_method not in", values, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodBetween(String value1, String value2) {
            addCriterion("deploy_method between", value1, value2, "deployMethod");
            return (Criteria) this;
        }

        public Criteria andDeployMethodNotBetween(String value1, String value2) {
            addCriterion("deploy_method not between", value1, value2, "deployMethod");
            return (Criteria) this;
        }*/

        /*public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }
*/
       /* public Criteria andIsLimitIsNull() {
            addCriterion("is_limit is null");
            return (Criteria) this;
        }

        public Criteria andIsLimitIsNotNull() {
            addCriterion("is_limit is not null");
            return (Criteria) this;
        }

        public Criteria andIsLimitEqualTo(String value) {
            addCriterion("is_limit =", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitNotEqualTo(String value) {
            addCriterion("is_limit <>", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitGreaterThan(String value) {
            addCriterion("is_limit >", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitGreaterThanOrEqualTo(String value) {
            addCriterion("is_limit >=", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitLessThan(String value) {
            addCriterion("is_limit <", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitLessThanOrEqualTo(String value) {
            addCriterion("is_limit <=", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitLike(String value) {
            addCriterion("is_limit like", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitNotLike(String value) {
            addCriterion("is_limit not like", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitIn(List<String> values) {
            addCriterion("is_limit in", values, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitNotIn(List<String> values) {
            addCriterion("is_limit not in", values, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitBetween(String value1, String value2) {
            addCriterion("is_limit between", value1, value2, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitNotBetween(String value1, String value2) {
            addCriterion("is_limit not between", value1, value2, "isLimit");
            return (Criteria) this;
        }*/

       /* public Criteria andProvideServiceIsNull() {
            addCriterion("provide_service is null");
            return (Criteria) this;
        }

        public Criteria andProvideServiceIsNotNull() {
            addCriterion("provide_service is not null");
            return (Criteria) this;
        }

        public Criteria andProvideServiceEqualTo(String value) {
            addCriterion("provide_service =", value, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceNotEqualTo(String value) {
            addCriterion("provide_service <>", value, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceGreaterThan(String value) {
            addCriterion("provide_service >", value, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceGreaterThanOrEqualTo(String value) {
            addCriterion("provide_service >=", value, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceLessThan(String value) {
            addCriterion("provide_service <", value, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceLessThanOrEqualTo(String value) {
            addCriterion("provide_service <=", value, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceLike(String value) {
            addCriterion("provide_service like", value, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceNotLike(String value) {
            addCriterion("provide_service not like", value, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceIn(List<String> values) {
            addCriterion("provide_service in", values, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceNotIn(List<String> values) {
            addCriterion("provide_service not in", values, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceBetween(String value1, String value2) {
            addCriterion("provide_service between", value1, value2, "provideService");
            return (Criteria) this;
        }

        public Criteria andProvideServiceNotBetween(String value1, String value2) {
            addCriterion("provide_service not between", value1, value2, "provideService");
            return (Criteria) this;
        }*/

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

        public Criteria andInterCodeIsNull() {
            addCriterion("inter_code is null");
            return (Criteria) this;
        }

        public Criteria andInterCodeIsNotNull() {
            addCriterion("inter_code is not null");
            return (Criteria) this;
        }

        public Criteria andInterCodeEqualTo(String value) {
            addCriterion("inter_code =", value, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeNotEqualTo(String value) {
            addCriterion("inter_code <>", value, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeGreaterThan(String value) {
            addCriterion("inter_code >", value, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeGreaterThanOrEqualTo(String value) {
            addCriterion("inter_code >=", value, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeLessThan(String value) {
            addCriterion("inter_code <", value, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeLessThanOrEqualTo(String value) {
            addCriterion("inter_code <=", value, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeLike(String value) {
            addCriterion("inter_code like", value, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeNotLike(String value) {
            addCriterion("inter_code not like", value, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeIn(List<String> values) {
            addCriterion("inter_code in", values, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeNotIn(List<String> values) {
            addCriterion("inter_code not in", values, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeBetween(String value1, String value2) {
            addCriterion("inter_code between", value1, value2, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterCodeNotBetween(String value1, String value2) {
            addCriterion("inter_code not between", value1, value2, "interCode");
            return (Criteria) this;
        }

        public Criteria andInterUrlIsNull() {
            addCriterion("inter_url is null");
            return (Criteria) this;
        }

        public Criteria andInterUrlIsNotNull() {
            addCriterion("inter_url is not null");
            return (Criteria) this;
        }

        public Criteria andInterUrlEqualTo(String value) {
            addCriterion("inter_url =", value, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlNotEqualTo(String value) {
            addCriterion("inter_url <>", value, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlGreaterThan(String value) {
            addCriterion("inter_url >", value, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlGreaterThanOrEqualTo(String value) {
            addCriterion("inter_url >=", value, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlLessThan(String value) {
            addCriterion("inter_url <", value, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlLessThanOrEqualTo(String value) {
            addCriterion("inter_url <=", value, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlLike(String value) {
            addCriterion("inter_url like", value, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlNotLike(String value) {
            addCriterion("inter_url not like", value, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlIn(List<String> values) {
            addCriterion("inter_url in", values, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlNotIn(List<String> values) {
            addCriterion("inter_url not in", values, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlBetween(String value1, String value2) {
            addCriterion("inter_url between", value1, value2, "interUrl");
            return (Criteria) this;
        }

        public Criteria andInterUrlNotBetween(String value1, String value2) {
            addCriterion("inter_url not between", value1, value2, "interUrl");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatIsNull() {
            addCriterion("encoding_format is null");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatIsNotNull() {
            addCriterion("encoding_format is not null");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatEqualTo(String value) {
            addCriterion("encoding_format =", value, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatNotEqualTo(String value) {
            addCriterion("encoding_format <>", value, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatGreaterThan(String value) {
            addCriterion("encoding_format >", value, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatGreaterThanOrEqualTo(String value) {
            addCriterion("encoding_format >=", value, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatLessThan(String value) {
            addCriterion("encoding_format <", value, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatLessThanOrEqualTo(String value) {
            addCriterion("encoding_format <=", value, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatLike(String value) {
            addCriterion("encoding_format like", value, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatNotLike(String value) {
            addCriterion("encoding_format not like", value, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatIn(List<String> values) {
            addCriterion("encoding_format in", values, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatNotIn(List<String> values) {
            addCriterion("encoding_format not in", values, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatBetween(String value1, String value2) {
            addCriterion("encoding_format between", value1, value2, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andEncodingFormatNotBetween(String value1, String value2) {
            addCriterion("encoding_format not between", value1, value2, "encodingFormat");
            return (Criteria) this;
        }

        public Criteria andRequestMethodIsNull() {
            addCriterion("request_method is null");
            return (Criteria) this;
        }

        public Criteria andRequestMethodIsNotNull() {
            addCriterion("request_method is not null");
            return (Criteria) this;
        }

        public Criteria andRequestMethodEqualTo(String value) {
            addCriterion("request_method =", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodNotEqualTo(String value) {
            addCriterion("request_method <>", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodGreaterThan(String value) {
            addCriterion("request_method >", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodGreaterThanOrEqualTo(String value) {
            addCriterion("request_method >=", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodLessThan(String value) {
            addCriterion("request_method <", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodLessThanOrEqualTo(String value) {
            addCriterion("request_method <=", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodLike(String value) {
            addCriterion("request_method like", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodNotLike(String value) {
            addCriterion("request_method not like", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodIn(List<String> values) {
            addCriterion("request_method in", values, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodNotIn(List<String> values) {
            addCriterion("request_method not in", values, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodBetween(String value1, String value2) {
            addCriterion("request_method between", value1, value2, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodNotBetween(String value1, String value2) {
            addCriterion("request_method not between", value1, value2, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andInterVersionIsNull() {
            addCriterion("inter_version is null");
            return (Criteria) this;
        }

        public Criteria andInterVersionIsNotNull() {
            addCriterion("inter_version is not null");
            return (Criteria) this;
        }

        public Criteria andInterVersionEqualTo(String value) {
            addCriterion("inter_version =", value, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionNotEqualTo(String value) {
            addCriterion("inter_version <>", value, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionGreaterThan(String value) {
            addCriterion("inter_version >", value, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionGreaterThanOrEqualTo(String value) {
            addCriterion("inter_version >=", value, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionLessThan(String value) {
            addCriterion("inter_version <", value, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionLessThanOrEqualTo(String value) {
            addCriterion("inter_version <=", value, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionLike(String value) {
            addCriterion("inter_version like", value, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionNotLike(String value) {
            addCriterion("inter_version not like", value, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionIn(List<String> values) {
            addCriterion("inter_version in", values, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionNotIn(List<String> values) {
            addCriterion("inter_version not in", values, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionBetween(String value1, String value2) {
            addCriterion("inter_version between", value1, value2, "interVersion");
            return (Criteria) this;
        }

        public Criteria andInterVersionNotBetween(String value1, String value2) {
            addCriterion("inter_version not between", value1, value2, "interVersion");
            return (Criteria) this;
        }

        public Criteria andIsApplyIsNull() {
            addCriterion("is_apply is null");
            return (Criteria) this;
        }

        public Criteria andIsApplyIsNotNull() {
            addCriterion("is_apply is not null");
            return (Criteria) this;
        }

        public Criteria andIsApplyEqualTo(String value) {
            addCriterion("is_apply =", value, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyNotEqualTo(String value) {
            addCriterion("is_apply <>", value, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyGreaterThan(String value) {
            addCriterion("is_apply >", value, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyGreaterThanOrEqualTo(String value) {
            addCriterion("is_apply >=", value, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyLessThan(String value) {
            addCriterion("is_apply <", value, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyLessThanOrEqualTo(String value) {
            addCriterion("is_apply <=", value, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyLike(String value) {
            addCriterion("is_apply like", value, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyNotLike(String value) {
            addCriterion("is_apply not like", value, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyIn(List<String> values) {
            addCriterion("is_apply in", values, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyNotIn(List<String> values) {
            addCriterion("is_apply not in", values, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyBetween(String value1, String value2) {
            addCriterion("is_apply between", value1, value2, "isApply");
            return (Criteria) this;
        }

        public Criteria andIsApplyNotBetween(String value1, String value2) {
            addCriterion("is_apply not between", value1, value2, "isApply");
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