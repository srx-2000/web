package dao;

import domain.Paper;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-06-07 02:56:45
 */
public interface IPaperDao {
    /**
     * 用于添加问卷
     * @param paper
     * @return
     */
    boolean insertPaper(Paper paper);

    /**
     * 根据传进来的管理员编号和试卷编号删除问卷
     * @param adminId,paperId
     * @return
     */
    boolean deletePaper(int adminId,int paperId);

    /**
     * 统计试卷数目
     * @return 返回试卷数目
     */
    Long paperCount();

    /**
     * 根据管理者id返回其所拥有的所有paper
     * @return
     */
    List<Paper> showPaperById(int adminId);

    /**
     * 根据管理员编号、卷名、开始日期，结束日期查找数据库中相对应的问卷编号
     * @param title
     * @param openDate
     * @param closeDate
     * @param adminId
     * @return
     */
    Object findPaperId(String title,String openDate,String closeDate,int adminId);

    /**
     * 根据管理员编号、卷名、开始日期，结束日期来判断该管理员是否已经建立了一个相同的问卷，如果是，则返回true，如果不是则返回false（主要用在添加的时候）,(这里的存在是的的确确的存在在数据库中，而并没有判断is_live属性是否为0)
     * @param title
     * @param openDate
     * @param closeDate
     * @param adminId
     * @return
     */
    boolean isExists(String title,String openDate,String closeDate,int adminId);

    /**
     * 用于返回一个paper对象
     * @param title
     * @param openDate
     * @param closeDate
     * @param adminId
     * @return
     */
    Paper findPaper(String title,String openDate,String closeDate,int adminId);

    /**
     * 将is_live属性从 0 更新为 1
     * @param paper
     * @return
     */
    boolean updateIs_live(Paper paper);

    /**
     * 用来返回传入的paper的is_live
     * @param paper
     * @return
     */
    int getIs_live(Paper paper);
}
