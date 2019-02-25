package com.example.bysj_1.moduls.response;

public class Response<T> {

    /**
     * 成功状态码
     */
    public final static int SUCCESS_STATUS = 666;

    /**
     * 服务端错误状态码
     */
    public final static int SERVERCE_ERROR_STATUS = 400;

    /**
     * 请求格式错误状态码
     */
    public final static int FORMAT_ERROR_STATUS = 500;

    /**
     * 状态码
     */
    private int status;
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 数据体
     */
    private T body;

    public Response() {
        this.status = 200;
    }

    public Response(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
