package service.implement;

import dao.ISelectDao;
import dao.implement.SelectDao;
import domain.Select;
import service.ISelectService;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-06-19 02:42:35
 */
public class SelectService implements ISelectService {
    ISelectDao selectDao=new SelectDao();
    @Override
    public boolean insertSelect(Select select) {
        return selectDao.insertSelect(select);
    }

    @Override
    public boolean deleteSelect(int selectId) {
        return selectDao.deleteSelect(selectId);
    }

    @Override
    public Long count() {
        return selectDao.count();
    }

    @Override
    public boolean updateIs_live(Select select) {
        return selectDao.updateIs_live(select);
    }

    @Override
    public int getIs_live(int selectId) {
        return selectDao.getIs_live(selectId);
    }

    @Override
    public boolean isExists(int selectId) {
        return selectDao.isExists(selectId);
    }

    @Override
    public List<Select> showSelectById(int questionId) {
        return selectDao.showSelectById(questionId);
    }

    @Override
    public boolean updateSelect(Select select) {
        return selectDao.updateSelect(select);
    }

    @Override
    public int getSelectId(Select select, int questionId) {
        String selectId=selectDao.getSelectId(select,questionId).toString();
        System.out.println(selectId);
        return Integer.parseInt(selectId);
    }
}
