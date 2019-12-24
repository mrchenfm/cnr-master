package com.ecut.cnr.framework.common.exception;

import com.ecut.cnr.framework.common.enums.ErrorEnum;

/**
 * @Classname MyException
 * @Description
 * @Date 2019/12/20 22:26
 * @Create by fangming_chen
 */
public class MyException extends RuntimeException {

    private String msg;
    private int code = 500;

    public MyException(){
        super(ErrorEnum.UNKNOWN.getMsg());
        this.msg=ErrorEnum.UNKNOWN.getMsg();
    }


    public MyException(ErrorEnum eEnum,Throwable e){
        super(eEnum.getMsg(),e);
        this.msg=eEnum.getMsg();
        this.code=eEnum.getCode();
    }

    public MyException(ErrorEnum eEnum){
        this.msg=eEnum.getMsg();
        this.code=eEnum.getCode();
    }

    public MyException(String exception){
        this.msg=exception;
    }
}
