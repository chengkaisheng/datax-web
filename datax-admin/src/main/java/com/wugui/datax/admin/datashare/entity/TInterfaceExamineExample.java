package com.wugui.datax.admin.datashare.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TInterfaceExamineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TInterfaceExamineExample() {
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

        public Criteria andInterIdIsNull() {
            addCriterion("inter_id is null");
            return (Criteria) this;
        }

        public Criteria andInterIdIsNotNull() {
            addCriterion("inter_id is not null");
            return (Criteria) this;
        }

        public Criteria andInterIdEqualTo(String value) {
            addCriterion("inter_id =", value, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdNotEqualTo(String value) {
            addCriterion("inter_id <>", value, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdGreaterThan(String value) {
            addCriterion("inter_id >", value, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdGreaterThanOrEqualTo(String value) {
            addCriterion("inter_id >=", value, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdLessThan(String value) {
            addCriterion("inter_id <", value, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdLessThanOrEqualTo(String value) {
            addCriterion("inter_id <=", value, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdLike(String value) {
            addCriterion("inter_id like", value, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdNotLike(String value) {
            addCriterion("inter_id not like", value, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdIn(List<String> values) {
            addCriterion("inter_id in", values, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdNotIn(List<String> values) {
            addCriterion("inter_id not in", values, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdBetween(String value1, String value2) {
            addCriterion("inter_id between", value1, value2, "interId");
            return (Criteria) this;
        }

        public Criteria andInterIdNotBetween(String value1, String value2) {
            addCriterion("inter_id not between", value1, value2, "interId");
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

        public Criteria andApplyTimeIsNull() {
            addCriterion("apply_time is null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNotNull() {
            addCriterion("apply_time is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeEqualTo(Date value) {
            addCriterion("apply_time =", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotEqualTo(Date value) {
            addCriterion("apply_time <>", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThan(Date value) {
            addCriterion("apply_time >", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("apply_time >=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThan(Date value) {
            addCriterion("apply_time <", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("apply_time <=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIn(List<Date> values) {
            addCriterion("apply_time in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotIn(List<Date> values) {
            addCriterion("apply_time not in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeBetween(Date value1, Date value2) {
            addCriterion("apply_time between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("apply_time not between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andReviewerIsNull() {
            addCriterion("reviewer is null");
            return (Criteria) this;
        }

        public Criteria andReviewerIsNotNull() {
            addCriterion("reviewer is not null");
            return (Criteria) this;
        }

        public Criteria andReviewerEqualTo(String value) {
            addCriterion("reviewer =", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotEqualTo(String value) {
            addCriterion("reviewer <>", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerGreaterThan(String value) {
            addCriterion("reviewer >", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerGreaterThanOrEqualTo(String value) {
            addCriterion("reviewer >=", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLessThan(String value) {
            addCriterion("reviewer <", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLessThanOrEqualTo(String value) {
            addCriterion("reviewer <=", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerLike(String value) {
            addCriterion("reviewer like", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotLike(String value) {
            addCriterion("reviewer not like", value, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerIn(List<String> values) {
            addCriterion("reviewer in", values, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotIn(List<String> values) {
            addCriterion("reviewer not in", values, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerBetween(String value1, String value2) {
            addCriterion("reviewer between", value1, value2, "reviewer");
            return (Criteria) this;
        }

        public Criteria andReviewerNotBetween(String value1, String value2) {
            addCriterion("reviewer not between", value1, value2, "reviewer");
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