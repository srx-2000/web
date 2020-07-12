package service.implement;

import dao.IAdminDao;
import dao.implement.AdminDao;
import domain.Admin;
import service.IAdminService;

/**
 * @author srx
 * @description
 * @create 2020-06-06 19:05:13
 */
public class AdminService implements IAdminService {
    IAdminDao adminDao=new AdminDao();

    @Override
    public boolean register(Admin admin) {
        if(adminDao.findAdmin(admin.getAdminName(),admin.getPassword())==null&admin.getAdminName()!=null&admin.getPassword()!=null){
            adminDao.addAdmin(admin);
            return true;
        }
        return false;
    }

    @Override
    public Admin Login(String adminName, String password) {
        return adminDao.findAdmin(adminName,password);
    }
}
