package jgdabc.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * sys_role_user
 */
@Data
public class SysRoleUser implements Serializable {
    /**
     * 用户编号
     */
    private Integer uid;

    /**
     * 角色编号
     */
    private Integer rid;

    private static final long serialVersionUID = 1L;
}