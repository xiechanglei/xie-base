package io.github.xiechanglei.base.rbac.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RBAC权限模型下的菜单
 */
@Getter
@Setter
@Entity
@Table(name = "rbac_auth_menu")
@NoArgsConstructor
public class RbacAuthMenu {
    @Id
    @Column(length = 100, columnDefinition = "varchar(100) comment '物理主键'")
    private String id;
    @Column(length = 100, columnDefinition = "varchar(200) comment '菜单名称'")
    private String menuName;
    @Column(length = 100, columnDefinition = "varchar(200) comment '菜单图标'")
    private String menuIcon;
    @Column(length = 200, columnDefinition = "varchar(200) comment '菜单url'")
    private String menuUrl;
    @Column(length = 1, nullable = false, columnDefinition = "int(1) comment '菜单类型，0表示folder，1表示page'")
    private Integer menuType; //菜单类型，0表示folder，1表示page
    @Column(length = 5, columnDefinition = "int(5) comment '菜单排序号码'")
    private Integer orderNumber; //菜单排序号码
    @Column(length = 32, columnDefinition = "varchar(32) comment '父菜单id'")
    private String parentId; //父菜单id
    @Column(length = 1, nullable = false, columnDefinition = "int(1) comment '是否启用'")
    private Boolean enable;

    public static final Integer MENU_TYPE_FOLDER = 0;
    public static final Integer MENU_TYPE_PAGE = 1;

    public RbacAuthMenu(String id, String menuName, Integer menuType) {
        this.setId(id);
        this.setMenuName(menuName);
        this.setMenuType(menuType);
        this.setEnable(true);
    }
}
