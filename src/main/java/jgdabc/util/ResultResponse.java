package jgdabc.util;

import java.io.Serializable;

//封装泛型工具类
public class ResultResponse<T>implements Serializable {
    private static final  String CODE_SUCCESS = "success";
    private static  final String CODE_FAIL =  "fail";
    private String code;
    private T data;
    private String msg;
    public ResultResponse()
    {

    }
    public ResultResponse(String code)
    {
        this.code  =  code;
    }
    public ResultResponse(String code,String msg){
        this.code  =  code;
        this.msg  =  msg;

    }
    public ResultResponse(String code,T data)
    {
        this.code  =  code;
        this.data  =  data;
    }
    public static ResultResponse success(){
        return  new ResultResponse(CODE_SUCCESS);
    }
    public static ResultResponse fail()
    {
         return  new ResultResponse(CODE_FAIL);
    }
    public static ResultResponse success(Object data)
    {
        return   new ResultResponse(CODE_SUCCESS,data);
    }
    public static ResultResponse fail(String data)
    {
        return  new ResultResponse(CODE_FAIL, success().msg);
    }

    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
