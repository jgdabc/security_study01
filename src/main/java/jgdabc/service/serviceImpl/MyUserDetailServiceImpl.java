package jgdabc.service.serviceImpl;

import jgdabc.entity.SysMenu;
import jgdabc.entity.SysUser;
import jgdabc.mapper.SysMenuDao;
import jgdabc.mapper.SysUserDao;
import jgdabc.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailServiceImpl implements MyUserDetailService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysMenuDao sysMenuDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//根据用户名查询用户
        SysUser sysUser = sysUserDao.selectByUserName(username);
        if (ObjectUtils.isEmpty(sysUser))
        {
            throw  new RuntimeException("账号不存在");
        }
        List<SysMenu> sysMenus = sysMenuDao.selectAuthByUid(sysUser.getId());
        //循环权限数据，把它封装为SimpleGrantedAuthority权限对象

        List<String> authList  =  new ArrayList<>();
        sysMenus.forEach(sysMenu -> {
            String code  =  sysMenu.getCode();
            if (StringUtils.hasText(code))
            {
                authList.add(code);
            }
        });
        sysUser.setAuthorities(authList);
//        sysUser.setEnabled(sysUser.getEnabled());
//        sysUser.setAccountNoExpired(sysUser.getCredentialsNoExpired());
//        sysUser.setCredentialsNoExpired(sysUser.getCredentialsNoExpired());
//        sysUser.setAccountNoLocked(sysUser.getAccountNoLocked());
        return  sysUser;


    }
}
