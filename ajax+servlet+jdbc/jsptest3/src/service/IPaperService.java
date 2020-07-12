package service;
import domain.Paper;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-06-08 17:21:15
 */
public interface IPaperService {
    /**
     * 用于统计试卷数目
     * @return 返回试卷数目
     */
    Long paperCount();

    /**
     * 添加试卷
     * @param paper
     * @return 如果添加成功那么就是返回true，添加失败返回false
     */
    boolean addPaper(Paper paper);

    /**
     * 用于删除试卷
     * @param adminId,paperId
     * @return 如果成功那么返回true，失败返回false
     */
    boolean deletePaper(int adminId,int paperId);
    /**
     * 用来返回所有的paper
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
     * 根据管理员编号、卷名、开始日期，结束日期来判断该管理员是否已经建立了一个相同的问卷，如果是，则返回true，如果不是则返回false（主要用在添加的时候）
     * @param title
     * @param openDate
     * @param closeDate
     * @param adminId
     * @return
     */
    boolean isExists(String title,String openDate,String closeDate,int adminId);

    Paper findPaper(String title,String openDate,String closeDate,int adminId);

    boolean is_live(Paper paper);

    int getIs_live(Paper paper);

}
