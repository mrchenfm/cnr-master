package com.ecut.cnr.view.fastdfs;

import lombok.Getter;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/3 09:26
 * @Description:
 */
public class BusinessException extends RuntimeException  {

    /**
     *	错误码
     */
    @Getter
    private String errorCode;

    private boolean smsFlag = false;//是否警报

    private static final long serialVersionUID = 1L;
    private Object[] tipsPlaceHolderValues;

    public BusinessException(String errorCode){
        super();
        this.errorCode = errorCode;
    }
    public BusinessException(String errorCode,Throwable throwable){
        super(throwable);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode,String message){
        super(message);
        this.errorCode = errorCode;
    }
    public BusinessException(String errorCode,String message,Object[] tipsPlaceHolderValues){
        super(message);
        this.errorCode = errorCode;
        this.tipsPlaceHolderValues = tipsPlaceHolderValues;
    }

    public BusinessException(String errorCode,String message,Throwable throwable){
        super(message,throwable);
        this.errorCode = errorCode;
    }


    public BusinessException(String errorCode, Boolean smsFlag){
        super();
        this.smsFlag = smsFlag;
        this.errorCode = errorCode;
    }
    public BusinessException(String errorCode, Boolean smsFlag,Throwable throwable){
        super(throwable);
        this.smsFlag = smsFlag;
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, Boolean smsFlag,String message){
        super(message);
        this.smsFlag = smsFlag;
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, Boolean smsFlag,String message, Object[] tipsPlaceHolderValues){
        super(message);
        this.smsFlag = smsFlag;
        this.errorCode = errorCode;
        this.tipsPlaceHolderValues = tipsPlaceHolderValues;
    }

    public BusinessException(String errorCode,String message, Boolean smsFlag,Throwable throwable){
        super(message,throwable);
        this.smsFlag = smsFlag;
        this.errorCode = errorCode;
    }
}
