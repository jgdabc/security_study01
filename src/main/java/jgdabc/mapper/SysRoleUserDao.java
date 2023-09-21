package jgdabc.mapper;

import jgdabc.entity.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleUserDao {
    int deleteByPrimaryKey(SysRoleUser key);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);
}