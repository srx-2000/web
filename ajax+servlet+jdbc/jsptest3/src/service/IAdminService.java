package service;

import domain.Admin;

/**
 * @author srx
 * @description
 * @create 2020-06-06 19:04:32
 */
public interface IAdminService {
    /**
     * 将传入的管理员用户加入到数据库中
     * @param admin
     * @return
     */
    boolean register(Admin admin);

    /**
     * 将传入的用户名和密码在数据库中检索，并返回检索到的用户信息，没有检索到返回null
     * @param adminName
     * @param password
     * @return
     */
    Admin Login(String adminName,String password);
}
