package com.lyf.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.lyf.user.entity.User;

/**
 * 用户表Vo
 * @author lyf
 * @since 2021-03-03
 */
@ApiModel(value="UserVo对象", description="用户表Vo")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserVo extends User {

    private static final long serialVersionUID = 1L;

}
