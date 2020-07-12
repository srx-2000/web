package dao;

import domain.Admin;

/**
 * @author srx
 * @description
 * @create 2020-06-06 15:42:50
 */
public interface IAdminDao {
    /**
     * 根据用户名和密码查找管理员用户
     * @param adminName
     * @param password
     * @return 查找到的管理员用户
     */
    Admin findAdmin(String adminName,String password);

    /**
     * 添加管理员用户
     * @param admin
     */
    Boolean addAdmin(Admin admin);


}
