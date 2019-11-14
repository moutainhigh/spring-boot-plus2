package com.ws.lay.adminconsole.entity;

import com.ws.ldy.admincore.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@Entity
@Table(name = "t_admin_menu")
@DynamicUpdate(value = true)
public class Menu extends BaseEntity {

    private static final long serialVersionUID = -33297418791559528L;
    /** 菜单id  */
    @Id
    private int id;

    /** 父id  */
    private int pid;

    /** 菜单名称 */
    private String name;

    /** 菜单路径 */
    private String url;

    /** 菜单图标 */
    private String icon;

    /** 排序 */
    private int sort;

    /** 菜单级别，(0、根目录,1、子目录, 2、菜单 3、页面 */
    private int root;

    /** 当前页面权限Id(菜单级别3设置权限Id-权限表对应 )  */
    private int authority;

    /** 当前节点的子节点，获取菜单树数据使用 */
    @Transient
    private List<Menu> menus;
}
