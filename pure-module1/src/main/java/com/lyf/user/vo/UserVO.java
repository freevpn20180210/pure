package com/lyf.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;
import com/lyf.user.entity.User;

/**
 * <p>
 * 用户表VO
 * </p>
 *
 * @author lyf
 * @since 2021-03-01
 */
@Data
    @Accessors(chain = true)
@ApiModel(value="UserVO对象", description="用户表")
public class UserVO extends User {
    private static final long serialVersionUID = 1L;

}
