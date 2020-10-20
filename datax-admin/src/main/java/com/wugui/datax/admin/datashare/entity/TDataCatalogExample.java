package com.wugui.datax.admin.datashare.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TDataCatalogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TDataCatalogExample(Class<TDataCatalog> tDataCatalogClass) {
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

        public Criteria andInfoCodeIsNull() {
            addCriterion("info_code is null");
            return (Criteria) this;
        }

        public Criteria andInfoCodeIsNotNull() {
            addCriterion("info_code is not null");
            return (Criteria) this;
        }

        public Criteria andInfoCodeEqualTo(String value) {
            addCriterion("info_code =", value, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeNotEqualTo(String value) {
            addCriterion("info_code <>", value, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeGreaterThan(String value) {
            addCriterion("info_code >", value, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeGreaterThanOrEqualTo(String value) {
            addCriterion("info_code >=", value, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeLessThan(String value) {
            addCriterion("info_code <", value, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeLessThanOrEqualTo(String value) {
            addCriterion("info_code <=", value, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeLike(String value) {
            addCriterion("info_code like", value, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeNotLike(String value) {
            addCriterion("info_code not like", value, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeIn(List<String> values) {
            addCriterion("info_code in", values, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeNotIn(List<String> values) {
            addCriterion("info_code not in", values, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeBetween(String value1, String value2) {
            addCriterion("info_code between", value1, value2, "infoCode");
            return (Criteria) this;
        }

        public Criteria andInfoCodeNotBetween(String value1, String value2) {
            addCriterion("info_code not between", value1, value2, "infoCode");
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

        public Criteria andCompanyCodeIsNull() {
            addCriterion("company_code is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNotNull() {
            addCriterion("company_code is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeEqualTo(String value) {
            addCriterion("company_code =", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotEqualTo(String value) {
            addCriterion("company_code <>", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThan(String value) {
            addCriterion("company_code >", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("company_code >=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThan(String value) {
            addCriterion("company_code <", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("company_code <=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLike(String value) {
            addCriterion("company_code like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotLike(String value) {
            addCriterion("company_code not like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIn(List<String> values) {
            addCriterion("company_code in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotIn(List<String> values) {
            addCriterion("company_code not in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeBetween(String value1, String value2) {
            addCriterion("company_code between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotBetween(String value1, String value2) {
            addCriterion("company_code not between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameIsNull() {
            addCriterion("info_system_name is null");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameIsNotNull() {
            addCriterion("info_system_name is not null");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameEqualTo(String value) {
            addCriterion("info_system_name =", value, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameNotEqualTo(String value) {
            addCriterion("info_system_name <>", value, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameGreaterThan(String value) {
            addCriterion("info_system_name >", value, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameGreaterThanOrEqualTo(String value) {
            addCriterion("info_system_name >=", value, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameLessThan(String value) {
            addCriterion("info_system_name <", value, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameLessThanOrEqualTo(String value) {
            addCriterion("info_system_name <=", value, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameLike(String value) {
            addCriterion("info_system_name like", value, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameNotLike(String value) {
            addCriterion("info_system_name not like", value, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameIn(List<String> values) {
            addCriterion("info_system_name in", values, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameNotIn(List<String> values) {
            addCriterion("info_system_name not in", values, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameBetween(String value1, String value2) {
            addCriterion("info_system_name between", value1, value2, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoSystemNameNotBetween(String value1, String value2) {
            addCriterion("info_system_name not between", value1, value2, "infoSystemName");
            return (Criteria) this;
        }

        public Criteria andInfoExtractIsNull() {
            addCriterion("info_extract is null");
            return (Criteria) this;
        }

        public Criteria andInfoExtractIsNotNull() {
            addCriterion("info_extract is not null");
            return (Criteria) this;
        }

        public Criteria andInfoExtractEqualTo(String value) {
            addCriterion("info_extract =", value, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractNotEqualTo(String value) {
            addCriterion("info_extract <>", value, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractGreaterThan(String value) {
            addCriterion("info_extract >", value, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractGreaterThanOrEqualTo(String value) {
            addCriterion("info_extract >=", value, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractLessThan(String value) {
            addCriterion("info_extract <", value, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractLessThanOrEqualTo(String value) {
            addCriterion("info_extract <=", value, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractLike(String value) {
            addCriterion("info_extract like", value, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractNotLike(String value) {
            addCriterion("info_extract not like", value, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractIn(List<String> values) {
            addCriterion("info_extract in", values, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractNotIn(List<String> values) {
            addCriterion("info_extract not in", values, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractBetween(String value1, String value2) {
            addCriterion("info_extract between", value1, value2, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andInfoExtractNotBetween(String value1, String value2) {
            addCriterion("info_extract not between", value1, value2, "infoExtract");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andImportSortIsNull() {
            addCriterion("import_sort is null");
            return (Criteria) this;
        }

        public Criteria andImportSortIsNotNull() {
            addCriterion("import_sort is not null");
            return (Criteria) this;
        }

        public Criteria andImportSortEqualTo(String value) {
            addCriterion("import_sort =", value, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortNotEqualTo(String value) {
            addCriterion("import_sort <>", value, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortGreaterThan(String value) {
            addCriterion("import_sort >", value, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortGreaterThanOrEqualTo(String value) {
            addCriterion("import_sort >=", value, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortLessThan(String value) {
            addCriterion("import_sort <", value, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortLessThanOrEqualTo(String value) {
            addCriterion("import_sort <=", value, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortLike(String value) {
            addCriterion("import_sort like", value, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortNotLike(String value) {
            addCriterion("import_sort not like", value, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortIn(List<String> values) {
            addCriterion("import_sort in", values, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortNotIn(List<String> values) {
            addCriterion("import_sort not in", values, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortBetween(String value1, String value2) {
            addCriterion("import_sort between", value1, value2, "importSort");
            return (Criteria) this;
        }

        public Criteria andImportSortNotBetween(String value1, String value2) {
            addCriterion("import_sort not between", value1, value2, "importSort");
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

        public Criteria andInfoFormatIsNull() {
            addCriterion("info_format is null");
            return (Criteria) this;
        }

        public Criteria andInfoFormatIsNotNull() {
            addCriterion("info_format is not null");
            return (Criteria) this;
        }

        public Criteria andInfoFormatEqualTo(String value) {
            addCriterion("info_format =", value, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatNotEqualTo(String value) {
            addCriterion("info_format <>", value, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatGreaterThan(String value) {
            addCriterion("info_format >", value, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatGreaterThanOrEqualTo(String value) {
            addCriterion("info_format >=", value, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatLessThan(String value) {
            addCriterion("info_format <", value, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatLessThanOrEqualTo(String value) {
            addCriterion("info_format <=", value, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatLike(String value) {
            addCriterion("info_format like", value, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatNotLike(String value) {
            addCriterion("info_format not like", value, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatIn(List<String> values) {
            addCriterion("info_format in", values, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatNotIn(List<String> values) {
            addCriterion("info_format not in", values, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatBetween(String value1, String value2) {
            addCriterion("info_format between", value1, value2, "infoFormat");
            return (Criteria) this;
        }

        public Criteria andInfoFormatNotBetween(String value1, String value2) {
            addCriterion("info_format not between", value1, value2, "infoFormat");
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

        public Criteria andUpdateFrequencyIsNull() {
            addCriterion("update_frequency is null");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyIsNotNull() {
            addCriterion("update_frequency is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyEqualTo(String value) {
            addCriterion("update_frequency =", value, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyNotEqualTo(String value) {
            addCriterion("update_frequency <>", value, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyGreaterThan(String value) {
            addCriterion("update_frequency >", value, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyGreaterThanOrEqualTo(String value) {
            addCriterion("update_frequency >=", value, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyLessThan(String value) {
            addCriterion("update_frequency <", value, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyLessThanOrEqualTo(String value) {
            addCriterion("update_frequency <=", value, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyLike(String value) {
            addCriterion("update_frequency like", value, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyNotLike(String value) {
            addCriterion("update_frequency not like", value, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyIn(List<String> values) {
            addCriterion("update_frequency in", values, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyNotIn(List<String> values) {
            addCriterion("update_frequency not in", values, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyBetween(String value1, String value2) {
            addCriterion("update_frequency between", value1, value2, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateFrequencyNotBetween(String value1, String value2) {
            addCriterion("update_frequency not between", value1, value2, "updateFrequency");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(String value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(String value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(String value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(String value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(String value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(String value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLike(String value) {
            addCriterion("update_date like", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotLike(String value) {
            addCriterion("update_date not like", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<String> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<String> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(String value1, String value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(String value1, String value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
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

        public Criteria andDetailedListIsNull() {
            addCriterion("detailed_list is null");
            return (Criteria) this;
        }

        public Criteria andDetailedListIsNotNull() {
            addCriterion("detailed_list is not null");
            return (Criteria) this;
        }

        public Criteria andDetailedListEqualTo(String value) {
            addCriterion("detailed_list =", value, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListNotEqualTo(String value) {
            addCriterion("detailed_list <>", value, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListGreaterThan(String value) {
            addCriterion("detailed_list >", value, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListGreaterThanOrEqualTo(String value) {
            addCriterion("detailed_list >=", value, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListLessThan(String value) {
            addCriterion("detailed_list <", value, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListLessThanOrEqualTo(String value) {
            addCriterion("detailed_list <=", value, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListLike(String value) {
            addCriterion("detailed_list like", value, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListNotLike(String value) {
            addCriterion("detailed_list not like", value, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListIn(List<String> values) {
            addCriterion("detailed_list in", values, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListNotIn(List<String> values) {
            addCriterion("detailed_list not in", values, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListBetween(String value1, String value2) {
            addCriterion("detailed_list between", value1, value2, "detailedList");
            return (Criteria) this;
        }

        public Criteria andDetailedListNotBetween(String value1, String value2) {
            addCriterion("detailed_list not between", value1, value2, "detailedList");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineIsNull() {
            addCriterion("business_examine is null");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineIsNotNull() {
            addCriterion("business_examine is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineEqualTo(String value) {
            addCriterion("business_examine =", value, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineNotEqualTo(String value) {
            addCriterion("business_examine <>", value, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineGreaterThan(String value) {
            addCriterion("business_examine >", value, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineGreaterThanOrEqualTo(String value) {
            addCriterion("business_examine >=", value, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineLessThan(String value) {
            addCriterion("business_examine <", value, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineLessThanOrEqualTo(String value) {
            addCriterion("business_examine <=", value, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineLike(String value) {
            addCriterion("business_examine like", value, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineNotLike(String value) {
            addCriterion("business_examine not like", value, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineIn(List<String> values) {
            addCriterion("business_examine in", values, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineNotIn(List<String> values) {
            addCriterion("business_examine not in", values, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineBetween(String value1, String value2) {
            addCriterion("business_examine between", value1, value2, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andBusinessExamineNotBetween(String value1, String value2) {
            addCriterion("business_examine not between", value1, value2, "businessExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineIsNull() {
            addCriterion("technology_examine is null");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineIsNotNull() {
            addCriterion("technology_examine is not null");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineEqualTo(String value) {
            addCriterion("technology_examine =", value, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineNotEqualTo(String value) {
            addCriterion("technology_examine <>", value, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineGreaterThan(String value) {
            addCriterion("technology_examine >", value, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineGreaterThanOrEqualTo(String value) {
            addCriterion("technology_examine >=", value, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineLessThan(String value) {
            addCriterion("technology_examine <", value, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineLessThanOrEqualTo(String value) {
            addCriterion("technology_examine <=", value, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineLike(String value) {
            addCriterion("technology_examine like", value, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineNotLike(String value) {
            addCriterion("technology_examine not like", value, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineIn(List<String> values) {
            addCriterion("technology_examine in", values, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineNotIn(List<String> values) {
            addCriterion("technology_examine not in", values, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineBetween(String value1, String value2) {
            addCriterion("technology_examine between", value1, value2, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andTechnologyExamineNotBetween(String value1, String value2) {
            addCriterion("technology_examine not between", value1, value2, "technologyExamine");
            return (Criteria) this;
        }

        public Criteria andIsQuoteIsNull() {
            addCriterion("is_quote is null");
            return (Criteria) this;
        }

        public Criteria andIsQuoteIsNotNull() {
            addCriterion("is_quote is not null");
            return (Criteria) this;
        }

        public Criteria andIsQuoteEqualTo(String value) {
            addCriterion("is_quote =", value, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteNotEqualTo(String value) {
            addCriterion("is_quote <>", value, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteGreaterThan(String value) {
            addCriterion("is_quote >", value, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteGreaterThanOrEqualTo(String value) {
            addCriterion("is_quote >=", value, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteLessThan(String value) {
            addCriterion("is_quote <", value, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteLessThanOrEqualTo(String value) {
            addCriterion("is_quote <=", value, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteLike(String value) {
            addCriterion("is_quote like", value, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteNotLike(String value) {
            addCriterion("is_quote not like", value, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteIn(List<String> values) {
            addCriterion("is_quote in", values, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteNotIn(List<String> values) {
            addCriterion("is_quote not in", values, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteBetween(String value1, String value2) {
            addCriterion("is_quote between", value1, value2, "isQuote");
            return (Criteria) this;
        }

        public Criteria andIsQuoteNotBetween(String value1, String value2) {
            addCriterion("is_quote not between", value1, value2, "isQuote");
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