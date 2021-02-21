package com.lyf.controller;

import com.lyf.base.vo.Result;
import com.lyf.entity.User;
import com.lyf.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Administrator
 * 2021/2/9 14:09 周二
 * description
 **/
@Api(tags = "用户")
@Slf4j
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("查询所有")
    @GetMapping("list")
    @ResponseBody
    public Result list() {
        return Result.OK(userService.list());
    }

    @ApiOperation("根据id查询")
    @GetMapping("getById")
    @ResponseBody
    public Result getById(String userId) {
        return Result.OK(userService.getById(userId));
    }

    @ApiOperation("新增")
    @PostMapping("save")
    @ResponseBody
    public Result save(User user) {
        return Result.OK(userService.save(user));
    }

    @ApiOperation("静态页面测试")
    @GetMapping("index")
    public String index() {
        return "/views/index.html";
    }
}
