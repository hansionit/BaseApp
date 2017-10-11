package com.hansion.baseapp.model.rxbus;

/**
 * Description：
 * Author: Hansion
 * Time: 2016/12/5 17:14
 */
public class Message {
    private int code;
    private Object object;

    public Message() {

    }

    public Message(int code, Object o) {
        this.code = code;
        this.object = o;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
