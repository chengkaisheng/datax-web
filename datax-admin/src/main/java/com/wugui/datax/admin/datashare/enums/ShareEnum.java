package com.wugui.datax.admin.datashare.enums;

import lombok.Getter;

@Getter
public enum ShareEnum {
    NO("0","否"),
    YES("1","是"),

    Add_MANUAL("手动添加"),
    Add_EXCEL("excel导入"),

    Add_SUCCESS("新增成功"),
    Add_Fail("新增失败"),

    UPDATE_SUCCESS("修改成功"),
    UPDATE_Fail("修改失败"),

    DETELE_SUCCESS("删除成功"),
    DETELE_FAIL("删除失败"),

    SUBMIT_SUCCESS("提交成功"),
    SUMBIT_FAIL("提交失败"),

    IMPORT_SUCCESS("导入成功"),
    IMPORT_Fail("导入失败"),

    WAIT_CHECK("待审批"),
    NO_APPLY("未申请"),
    YES_APPLY("已申请"),
    Online("已上线"),
    GREED("已同意"),
    REJECT("已驳回"),

    SUMBIT_APPLY("目录提交申请"),
    CANCEL_APPLY("目录注销申请"),
    DATA_APPLY("目录数据申请"),
    DATA_CHAIN("数据上链申请"),

    TO_BE_IMPROVED("待完善"),
    NOT_SUMBIT("未提交"),
    SUMBIT_CHECK("提交待审核"),
    SUMBIT_NOPASS("提交不通过"),
    SUMBIT("已提交"),
    CANCEL_CHECK("注销待审核"),
    CANCEL_NOPASS("注销不通过"),
    CANCEL("已注销"),
    WAIT_COLLECT("待归集"),
    CHECK_NOPASS("审核不通过"),
    CHECK_PASS("审核通过");

    private String code;
    private String message;
    private String describe;

    ShareEnum(String code, String message) {
        this.code=code;
        this.message=message;
    }

    ShareEnum(String describe) {
        this.describe=describe;
    }
}
