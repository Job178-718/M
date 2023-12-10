package com.sun.m.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseStatus {
    //状态码 100失败 200成功
    private int codeState;

    //返回信息
    private String message;

    private Map<String,Object> extend = new HashMap<String,Object>();

    //成功
    public static ResponseStatus sucess(){
        ResponseStatus sta = new ResponseStatus();
        sta.setCodeState(100);
        sta.setMessage("处理成功");
        return sta;
    }

    //失败
    public static ResponseStatus fail(){
        ResponseStatus sta = new ResponseStatus();
        sta.setCodeState(200);
        sta.setMessage("处理失败");
        return sta;
    }

    public ResponseStatus add(String str,Object obj){
        this.getExtend().put(str,obj);
        return this;
    }



    public int getCodeState() {
        return codeState;
    }

    public void setCodeState(int codeState) {
        this.codeState = codeState;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtend() {
        //直接获取对象
        return extend;
    }


    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
