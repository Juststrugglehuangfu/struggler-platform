package com.struggler.authorization.service.impl;

import com.struggler.authorization.mapper.SysMenuMapper;
import com.struggler.authorization.mapper.SysUserMapper;
import com.struggler.authorization.model.BaseUserDetail;
import com.struggler.common.model.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义UserDetailService
 *
 * @author wanglp
 * @date 2019/6/2
 **/
@SuppressWarnings("ALL")
@Service("userDetailService")
public class StrugglerUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.findByUserName(userName);
        if (null == sysUser) {
            throw new UsernameNotFoundException(userName);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        GrantedAuthority temp = new SimpleGrantedAuthority("ROLE_ADMIN");
        grantedAuthorities.add(temp);
        //读取菜单权限
        List<String> permissionsList = sysMenuMapper.findUserPermissionsByUserId(sysUser.getId());
        for (String permission : permissionsList) {
            if (StringUtils.isBlank(permission)) {
                continue;
            }
            String[] perms = permission.trim().split(",");
            for (String perm : perms) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(perm);
                grantedAuthorities.add(grantedAuthority);
            }
        }
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        User user = new User(sysUser.getAccount(), sysUser.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        BaseUserDetail userDetail = new BaseUserDetail(sysUser, user);
        return userDetail;
    }
}
