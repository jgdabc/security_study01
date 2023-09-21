package jgdabc;

import jgdabc.entity.SysUser;
import jgdabc.mapper.SysUserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityStudy01ApplicationTests {
    @Autowired
    SysUserDao sysUserDao;
    @Test
    void contextLoads() {
        SysUser sysUser = sysUserDao.selectByPrimaryKey(1);
        System.out.println(sysUser);

    }

}
