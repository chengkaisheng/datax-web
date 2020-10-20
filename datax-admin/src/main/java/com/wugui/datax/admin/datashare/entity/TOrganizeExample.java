package com.wugui.datax.admin.datashare.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TOrganizeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TOrganizeExample() {
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

        public Criteria andOidIsNull() {
            addCriterion("oid is null");
            return (Criteria) this;
        }

        public Criteria andOidIsNotNull() {
            addCriterion("oid is not null");
            return (Criteria) this;
        }

        public Criteria andOidEqualTo(String value) {
            addCriterion("oid =", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotEqualTo(String value) {
            addCriterion("oid <>", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThan(String value) {
            addCriterion("oid >", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThanOrEqualTo(String value) {
            addCriterion("oid >=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThan(String value) {
            addCriterion("oid <", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThanOrEqualTo(String value) {
            addCriterion("oid <=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLike(String value) {
            addCriterion("oid like", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotLike(String value) {
            addCriterion("oid not like", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidIn(List<String> values) {
            addCriterion("oid in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotIn(List<String> values) {
            addCriterion("oid not in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidBetween(String value1, String value2) {
            addCriterion("oid between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotBetween(String value1, String value2) {
            addCriterion("oid not between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andPoidIsNull() {
            addCriterion("poid is null");
            return (Criteria) this;
        }

        public Criteria andPoidIsNotNull() {
            addCriterion("poid is not null");
            return (Criteria) this;
        }

        public Criteria andPoidEqualTo(String value) {
            addCriterion("poid =", value, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidNotEqualTo(String value) {
            addCriterion("poid <>", value, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidGreaterThan(String value) {
            addCriterion("poid >", value, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidGreaterThanOrEqualTo(String value) {
            addCriterion("poid >=", value, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidLessThan(String value) {
            addCriterion("poid <", value, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidLessThanOrEqualTo(String value) {
            addCriterion("poid <=", value, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidLike(String value) {
            addCriterion("poid like", value, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidNotLike(String value) {
            addCriterion("poid not like", value, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidIn(List<String> values) {
            addCriterion("poid in", values, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidNotIn(List<String> values) {
            addCriterion("poid not in", values, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidBetween(String value1, String value2) {
            addCriterion("poid between", value1, value2, "poid");
            return (Criteria) this;
        }

        public Criteria andPoidNotBetween(String value1, String value2) {
            addCriterion("poid not between", value1, value2, "poid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andFullnameIsNull() {
            addCriterion("fullname is null");
            return (Criteria) this;
        }

        public Criteria andFullnameIsNotNull() {
            addCriterion("fullname is not null");
            return (Criteria) this;
        }

        public Criteria andFullnameEqualTo(String value) {
            addCriterion("fullname =", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotEqualTo(String value) {
            addCriterion("fullname <>", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameGreaterThan(String value) {
            addCriterion("fullname >", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameGreaterThanOrEqualTo(String value) {
            addCriterion("fullname >=", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLessThan(String value) {
            addCriterion("fullname <", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLessThanOrEqualTo(String value) {
            addCriterion("fullname <=", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLike(String value) {
            addCriterion("fullname like", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotLike(String value) {
            addCriterion("fullname not like", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameIn(List<String> values) {
            addCriterion("fullname in", values, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotIn(List<String> values) {
            addCriterion("fullname not in", values, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameBetween(String value1, String value2) {
            addCriterion("fullname between", value1, value2, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotBetween(String value1, String value2) {
            addCriterion("fullname not between", value1, value2, "fullname");
            return (Criteria) this;
        }

        public Criteria andOrgtypeIsNull() {
            addCriterion("orgtype is null");
            return (Criteria) this;
        }

        public Criteria andOrgtypeIsNotNull() {
            addCriterion("orgtype is not null");
            return (Criteria) this;
        }

        public Criteria andOrgtypeEqualTo(String value) {
            addCriterion("orgtype =", value, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeNotEqualTo(String value) {
            addCriterion("orgtype <>", value, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeGreaterThan(String value) {
            addCriterion("orgtype >", value, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeGreaterThanOrEqualTo(String value) {
            addCriterion("orgtype >=", value, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeLessThan(String value) {
            addCriterion("orgtype <", value, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeLessThanOrEqualTo(String value) {
            addCriterion("orgtype <=", value, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeLike(String value) {
            addCriterion("orgtype like", value, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeNotLike(String value) {
            addCriterion("orgtype not like", value, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeIn(List<String> values) {
            addCriterion("orgtype in", values, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeNotIn(List<String> values) {
            addCriterion("orgtype not in", values, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeBetween(String value1, String value2) {
            addCriterion("orgtype between", value1, value2, "orgtype");
            return (Criteria) this;
        }

        public Criteria andOrgtypeNotBetween(String value1, String value2) {
            addCriterion("orgtype not between", value1, value2, "orgtype");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andUncodeIsNull() {
            addCriterion("uncode is null");
            return (Criteria) this;
        }

        public Criteria andUncodeIsNotNull() {
            addCriterion("uncode is not null");
            return (Criteria) this;
        }

        public Criteria andUncodeEqualTo(String value) {
            addCriterion("uncode =", value, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeNotEqualTo(String value) {
            addCriterion("uncode <>", value, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeGreaterThan(String value) {
            addCriterion("uncode >", value, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeGreaterThanOrEqualTo(String value) {
            addCriterion("uncode >=", value, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeLessThan(String value) {
            addCriterion("uncode <", value, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeLessThanOrEqualTo(String value) {
            addCriterion("uncode <=", value, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeLike(String value) {
            addCriterion("uncode like", value, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeNotLike(String value) {
            addCriterion("uncode not like", value, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeIn(List<String> values) {
            addCriterion("uncode in", values, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeNotIn(List<String> values) {
            addCriterion("uncode not in", values, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeBetween(String value1, String value2) {
            addCriterion("uncode between", value1, value2, "uncode");
            return (Criteria) this;
        }

        public Criteria andUncodeNotBetween(String value1, String value2) {
            addCriterion("uncode not between", value1, value2, "uncode");
            return (Criteria) this;
        }

        public Criteria andOrderbyIsNull() {
            addCriterion("orderby is null");
            return (Criteria) this;
        }

        public Criteria andOrderbyIsNotNull() {
            addCriterion("orderby is not null");
            return (Criteria) this;
        }

        public Criteria andOrderbyEqualTo(String value) {
            addCriterion("orderby =", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyNotEqualTo(String value) {
            addCriterion("orderby <>", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyGreaterThan(String value) {
            addCriterion("orderby >", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyGreaterThanOrEqualTo(String value) {
            addCriterion("orderby >=", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyLessThan(String value) {
            addCriterion("orderby <", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyLessThanOrEqualTo(String value) {
            addCriterion("orderby <=", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyLike(String value) {
            addCriterion("orderby like", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyNotLike(String value) {
            addCriterion("orderby not like", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyIn(List<String> values) {
            addCriterion("orderby in", values, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyNotIn(List<String> values) {
            addCriterion("orderby not in", values, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyBetween(String value1, String value2) {
            addCriterion("orderby between", value1, value2, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyNotBetween(String value1, String value2) {
            addCriterion("orderby not between", value1, value2, "orderby");
            return (Criteria) this;
        }

        public Criteria andUniquecodingIsNull() {
            addCriterion("uniquecoding is null");
            return (Criteria) this;
        }

        public Criteria andUniquecodingIsNotNull() {
            addCriterion("uniquecoding is not null");
            return (Criteria) this;
        }

        public Criteria andUniquecodingEqualTo(String value) {
            addCriterion("uniquecoding =", value, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingNotEqualTo(String value) {
            addCriterion("uniquecoding <>", value, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingGreaterThan(String value) {
            addCriterion("uniquecoding >", value, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingGreaterThanOrEqualTo(String value) {
            addCriterion("uniquecoding >=", value, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingLessThan(String value) {
            addCriterion("uniquecoding <", value, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingLessThanOrEqualTo(String value) {
            addCriterion("uniquecoding <=", value, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingLike(String value) {
            addCriterion("uniquecoding like", value, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingNotLike(String value) {
            addCriterion("uniquecoding not like", value, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingIn(List<String> values) {
            addCriterion("uniquecoding in", values, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingNotIn(List<String> values) {
            addCriterion("uniquecoding not in", values, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingBetween(String value1, String value2) {
            addCriterion("uniquecoding between", value1, value2, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andUniquecodingNotBetween(String value1, String value2) {
            addCriterion("uniquecoding not between", value1, value2, "uniquecoding");
            return (Criteria) this;
        }

        public Criteria andAdcodeIsNull() {
            addCriterion("AdCode is null");
            return (Criteria) this;
        }

        public Criteria andAdcodeIsNotNull() {
            addCriterion("AdCode is not null");
            return (Criteria) this;
        }

        public Criteria andAdcodeEqualTo(String value) {
            addCriterion("AdCode =", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeNotEqualTo(String value) {
            addCriterion("AdCode <>", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeGreaterThan(String value) {
            addCriterion("AdCode >", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeGreaterThanOrEqualTo(String value) {
            addCriterion("AdCode >=", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeLessThan(String value) {
            addCriterion("AdCode <", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeLessThanOrEqualTo(String value) {
            addCriterion("AdCode <=", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeLike(String value) {
            addCriterion("AdCode like", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeNotLike(String value) {
            addCriterion("AdCode not like", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeIn(List<String> values) {
            addCriterion("AdCode in", values, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeNotIn(List<String> values) {
            addCriterion("AdCode not in", values, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeBetween(String value1, String value2) {
            addCriterion("AdCode between", value1, value2, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeNotBetween(String value1, String value2) {
            addCriterion("AdCode not between", value1, value2, "adcode");
            return (Criteria) this;
        }

        public Criteria andOrgcodingIsNull() {
            addCriterion("orgcoding is null");
            return (Criteria) this;
        }

        public Criteria andOrgcodingIsNotNull() {
            addCriterion("orgcoding is not null");
            return (Criteria) this;
        }

        public Criteria andOrgcodingEqualTo(String value) {
            addCriterion("orgcoding =", value, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingNotEqualTo(String value) {
            addCriterion("orgcoding <>", value, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingGreaterThan(String value) {
            addCriterion("orgcoding >", value, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingGreaterThanOrEqualTo(String value) {
            addCriterion("orgcoding >=", value, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingLessThan(String value) {
            addCriterion("orgcoding <", value, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingLessThanOrEqualTo(String value) {
            addCriterion("orgcoding <=", value, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingLike(String value) {
            addCriterion("orgcoding like", value, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingNotLike(String value) {
            addCriterion("orgcoding not like", value, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingIn(List<String> values) {
            addCriterion("orgcoding in", values, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingNotIn(List<String> values) {
            addCriterion("orgcoding not in", values, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingBetween(String value1, String value2) {
            addCriterion("orgcoding between", value1, value2, "orgcoding");
            return (Criteria) this;
        }

        public Criteria andOrgcodingNotBetween(String value1, String value2) {
            addCriterion("orgcoding not between", value1, value2, "orgcoding");
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