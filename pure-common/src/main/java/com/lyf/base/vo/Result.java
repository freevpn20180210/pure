package com.lyf.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Administrator
 * 2021/2/9 14:21 周二
 * description
 **/

@Data
@ApiModel("Result--接口通用返回对象")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回的成功标识")
    private boolean success = true;

    @ApiModelProperty(value = "返回的状态码")
    private int code = 200;

    @ApiModelProperty(value = "返回的消息")
    private String msg = "操作成功";

    @ApiModelProperty(value = "返回的数据对象")
    private T result;

    @ApiModelProperty(value = "返回的时间")
    private LocalDateTime time = LocalDateTime.now();

    /****************************构造方法****************************/
    public Result(boolean success, int code, String msg) {
        this.setSuccess(success);
        this.setCode(code);
        this.setMsg(msg);
    }

    public Result(boolean success, int code, String msg, T data) {
        this.setSuccess(success);
        this.setCode(code);
        this.setMsg(msg);
        this.setResult(data);
    }

    /****************************操作成功****************************/
    public static <T> Result<T> OK() {
        return new Result<T>(true, 200, "操作成功");
    }

    public static <T> Result<T> OK(String msg) {
        return new Result<T>(true, 200, msg);
    }

    public static <T> Result<T> OK(T data) {
        return new Result<T>(true, 200, "操作成功", data);
    }

    public static <T> Result<T> OK(String msg, T data) {
        return new Result<T>(true, 200, msg, data);
    }

    public static <T> Result<T> OK(String msg, int code, T data) {
        return new Result<T>(true, code, msg, data);
    }

    /****************************操作失败****************************/
    public static <T> Result<T> error() {
        return new Result<T>(false, 500, "操作失败");
    }

    public static <T> Result<T> error(String msg) {
        return new Result<T>(false, 500, msg);
    }

    public static <T> Result<T> error(T data) {
        return new Result<T>(false, 500, "操作失败", data);
    }

    public static <T> Result<T> error(String msg, T data) {
        return new Result<T>(false, 500, msg, data);
    }

    public static <T> Result<T> error(String msg, int code, T data) {
        return new Result<T>(false, code, msg, data);
    }

    public static <T> Result<T> noAuth() {
        return new Result<T>(false, 403, "无访问权限");
    }

}
