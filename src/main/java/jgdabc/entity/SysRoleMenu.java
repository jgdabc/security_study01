package jgdabc.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * sys_role_menu
 */
@Data
public class SysRoleMenu implements Serializable {
    /**
     * 菜单表的编号
     */
    private Integer mid;

    /**
     * 角色表的编号
     */
    private Integer rid;

    private static final long serialVersionUID = 1L;
}