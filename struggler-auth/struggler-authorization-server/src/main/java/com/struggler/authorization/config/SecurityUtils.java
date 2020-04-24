package com.struggler.authorization.config;

import com.struggler.authorization.model.BaseUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanglp
 * @date 2019/6/8
 **/
@Component
public class SecurityUtils {

    public BaseUserDetail getCurrentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (BaseUserDetail) authentication.getPrincipal();
    }
}
