package jgdabc.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * sys_role
 */
@Data
public class SysRole implements Serializable {
    /**
     * 角色ID
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String rolename;

    private static final long serialVersionUID = 1L;
}