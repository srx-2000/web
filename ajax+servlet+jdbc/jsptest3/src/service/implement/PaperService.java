package service.implement;

import dao.IPaperDao;
import dao.implement.PaperDao;
import domain.Paper;
import service.IPaperService;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-06-08 17:26:01
 */
public class PaperService implements IPaperService {
    IPaperDao paperDao=new PaperDao();

    @Override
    public Long paperCount() {
        return paperDao.paperCount();
    }

    @Override
    public boolean addPaper(Paper paper) {
        if (paperDao.insertPaper(paper)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePaper(int adminId,int paperId) {
        if (paperDao.deletePaper(adminId,paperId)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Paper> showPaperById(int adminId) {
        if(paperDao.showPaperById(adminId)!=null)
            return paperDao.showPaperById(adminId);
        return null;
    }

    @Override
    public Object findPaperId(String title, String openDate, String closeDate, int adminId) {
        return paperDao.findPaperId(title,openDate,closeDate,adminId);
    }

    @Override
    public boolean isExists(String title, String openDate, String closeDate, int adminId) {
        return paperDao.isExists(title,openDate,closeDate,adminId);
    }

    @Override
    public Paper findPaper(String title, String openDate, String closeDate, int adminId) {
        return paperDao.findPaper(title,openDate,closeDate,adminId);
    }

    @Override
    public boolean is_live(Paper paper) {
        return paperDao.updateIs_live(paper);
    }

    @Override
    public int getIs_live(Paper paper) {
        return paperDao.getIs_live(paper);
    }
}
