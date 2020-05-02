package com.ecut.cnr.framework.common;

import com.alibaba.fastjson.JSONArray;
import com.ecut.cnr.framework.common.enums.ErrorEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Result
 * @Description
 * @Date 2019/12/15 21:27
 * @Create by fangming_chen
 */
public class Result extends HashMap<String,Object> {

    public Result(){
        put("code",200);
        put("msg","success");
    }

    public static Result addMap(Map<String,Object> map){
        Result result = new Result();
        for(Map.Entry<String,Object> entry : map.entrySet()){
            result.put(entry.getKey(), JSONArray.toJSON(entry.getValue()));
        }
        return result;
    }


    public static Result ok(){
        return new Result();
    }

    public static Result error(){
        return error();
    }

    public static Result error(ErrorEnum eEnum) {
        return new Result().put("code", eEnum.getCode()).put("msg", eEnum.getMsg());
    }

    public static Result error(String msg) {
        return new Result().put("msg",msg).put("code", ErrorEnum.UNKNOWN.getCode());
    }

    public static Result error(Integer code , String msg){
        return new Result().put("code",code).put("msg",msg);
    }

    public static Result exception() {
        return exception(ErrorEnum.UNKNOWN);
    }

    public static Result exception(ErrorEnum eEnum) {
        return new Result().put("code", eEnum.getCode()).put("msg", eEnum.getMsg());
    }

    /**
     * 封装业务数据
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        //将HashMap对象本身返回
        return this;
    }

}
