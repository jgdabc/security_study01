package jgdabc.service.serviceImpl;

import jgdabc.entity.SysMenu;
import jgdabc.mapper.SysMenuDao;
import jgdabc.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(SysMenu record) {
        return 0;
    }

    @Override
    public int insertSelective(SysMenu record) {
        return 0;
    }

    @Override
    public SysMenu selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SysMenu record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SysMenu record) {
        return 0;
    }

    @Override
    public List<SysMenu> selectAuthByUid(Integer uid) {
         return sysMenuDao.selectAuthByUid(uid);
    }
}
