package jgdabc.mapper;

import jgdabc.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectByUserName(String name);
}