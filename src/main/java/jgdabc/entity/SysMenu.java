package jgdabc.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * sys_menu
 */
@Data
public class SysMenu implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 父级编号
     */
    private Integer pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 0代表菜单1权限
     */
    private Integer type;

    private static final long serialVersionUID = 1L;
}