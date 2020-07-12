package domain;

import java.sql.Date;

/**
 * @author srx
 * @description
 * @create 2020-06-06 15:18:21
 */
public class Paper {
    private int paperId;
    private String title;
    private int adminId;
    private String createDate;
    private String openDate;
    private String closeDate;
    int is_live;

    public Paper() {
    }

    public Paper(int paperId, String title, int adminId, String createDate, String openDate, String closeDate, int is_live) {
        this.paperId = paperId;
        this.title = title;
        this.adminId = adminId;
        this.createDate = createDate;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.is_live = is_live;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public int getIs_live() {
        return is_live;
    }

    public void setIs_live(int is_live) {
        this.is_live = is_live;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "paperId=" + paperId +
                ", title='" + title + '\'' +
                ", adminId=" + adminId +
                ", createDate='" + createDate + '\'' +
                ", openDate='" + openDate + '\'' +
                ", closeDate='" + closeDate + '\'' +
                ", is_live=" + is_live +
                '}';
    }
}
