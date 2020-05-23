package com.chengcheng.configration;

import com.chengcheng.cases.plug.token.Result;
import com.chengcheng.cases.type.exception.exceptentity.CMSException;
import com.chengcheng.cases.type.exception.exceptentity.R;
import com.chengcheng.cases.type.exception.exceptentity.ResultCodeEnum;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@Api("异常拦截类")
//@ControllerAdvice  // 异常处理注解-已关闭(要想开启,打开注解) 3-3
@ResponseBody
public class ExceptionInterceptor {

    @ExceptionHandler(Exception.class)
    public Object InterceptException(Exception e, HttpServletRequest request) {

        System.out.println("系统出现了异常或错误,异常信息↓↓↓↓↓");
        e.printStackTrace();

        return Result.fail("500","有错了" + e.getMessage());

    }


    /**-------- 指定异常处理方法 --------**/
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public R error(NullPointerException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.UNKNOWN_ERROR);  // UNKNOWN_ERROR: 用时需在枚举类型中定义
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public R error(IndexOutOfBoundsException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.UNKNOWN_ERROR);
    }

    /**-------- 自定义定异常处理方法 --------**/
    @ExceptionHandler(CMSException.class)
    @ResponseBody
    public R error(CMSException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }


}
