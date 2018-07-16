package com.wolf.zero.greenroad.bean;

/**
 * @author sineom
 * @version 1.0
 * @time 2016/7/5 10:08
 * @updateAuthor ${Author}
 * @updataTIme 2016/7/5
 * @updataDes ${描述更新内容}
 */
public class ActivationResult {
    public static final String SUCCESS_CODE = "Success";
    public static final String FAILD_CODE_INVALID_KEY = "InvalidKey";
    public static final String FAILD_CODE_USED_KEY = "UsedKey";

    private String code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

}
