package domain;

/**
 * @author srx
 * @description
 * @create 2020-06-06 15:16:20
 */
public class Admin {
    private int adminId;
    private String adminName;
    private String password;
    private String realName;

    public Admin() {
    }

    public Admin(int adminId, String adminName, String password, String realName) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.password = password;
        this.realName = realName;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "admin{" +
                "adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
