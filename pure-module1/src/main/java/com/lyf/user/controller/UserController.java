package com/lyf.user.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lyf.base.entity.BaseController;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.lyf.base.vo.Result;
import org.springframework.web.bind.annotation.*;
import com/lyf.user.entity.User;
import com/lyf.user.service.UserService;
/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lyf
 * @since 2021-03-01
 */
@RestController
@RequestMapping("user")
@Slf4j
@Api(tags = "用户表-Controller")
public class UserController extends BaseController {
    private UserService userService;

    @ApiOperation("用户表--列表")
    @PostMapping("list")
    public Result list(){
        return Result.OK(userService.list());
    }

    @ApiOperation("用户表--详情")
    @ApiImplicitParam(name = "id", value = "用户表id主键", dataType = "Long", required = true)
    @GetMapping("get")
    public Result getById(Long userId){
        return Result.OK(userService.getById(userId));
    }

    @ApiOperation("新增/保存")
    @PostMapping("save")
    @ResponseBody
    public Result saveUpdate(User user){
        if (UserVo.getId() == null) {
            UserService.save(User);
        } else {
            UserService.update(User);
        }
        return Result.OK(userService.save(user));
    }
}

