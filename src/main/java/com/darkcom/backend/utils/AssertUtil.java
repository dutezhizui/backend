package com.darkcom.backend.utils;


import com.darkcom.backend.common.enums.BizCodeEnum;
import com.darkcom.backend.exception.BusinessException;

public class AssertUtil {



    public static void isTrue(boolean condition, RuntimeException e){
        if(!condition){
            throw e;
        }
    }

    public static void isTrueForRpc(boolean condition, BizCodeEnum bizCodeEnum){
        if(!condition){
            throw new BusinessException(Integer.parseInt(bizCodeEnum.getCode()),bizCodeEnum.getMsg());
        }
    }

    public static void isTrueForRpc(boolean condition, String code,String desc){
        if(!condition){
            throw new BusinessException(Integer.parseInt(code),desc);
        }
    }

    public static void isTrueForRpc(boolean condition, BusinessException e){
        if(!condition){
            throw e;
        }
    }



}
