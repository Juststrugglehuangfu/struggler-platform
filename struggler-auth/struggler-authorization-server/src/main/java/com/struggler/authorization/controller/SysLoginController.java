package com.struggler.authorization.controller;

import com.struggler.authorization.model.BaseUserDetail;
import com.struggler.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 用户登录和退出信息
 * @author wanglp
 * @date 2019/6/5
 **/
@RestController
@RequestMapping("/api")
public class SysLoginController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    /**
     * 获取当前用户信息
     *
     * @param user Principal
     * @return Principal
     */
    @GetMapping("/userInfo")
    public Result userInfo(Principal user) {
        //((OAuth2Authentication)principal).getUserAuthentication().getPrincipal()
        BaseUserDetail userDetail = (BaseUserDetail) ((OAuth2Authentication) user).getUserAuthentication().getPrincipal();
        return Result.success(userDetail);
    }

    /**
     * 退出登录
     *
     * @param access_token token
     * @return Result
     */
    @DeleteMapping(value = "/logout")
    public Result revokeToken(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return Result.success("注销成功");
        } else {
            return Result.success("注销失败");
        }
    }

}
