package service;

import domain.Select;

import java.util.List;

/**
 * @author srx
 * @description
 * @create 2020-06-19 02:41:53
 */
public interface ISelectService {
    /**
     * 用于添加选项
     * @return select
     */
    boolean insertSelect(Select select);

    /**
     * 用于删除选项
     * @param selectId
     * @return
     */
    boolean deleteSelect(int selectId);

    /**
     * 该接口待完成，参数应该是一个answer的javabean，question的javabean
     * 主要作用是用来统计每道题的用户选项
     * @return
     */
    Long count();

    /**
     * 作用同paperdao中的同名方法
     *
     * @param select
     * @return
     */
    boolean updateIs_live(Select select);

    /**
     * 作用同paperdao中的同名方法
     * @param selectId
     * @return
     */
    int getIs_live(int selectId);

    /**
     * 作用同paperdao中的同名方法
     *
     * @param selectId
     * @return
     */
    boolean isExists(int selectId);

    /**
     * 返回全部选项
     *
     * @param questionId
     * @return
     */
    List<Select> showSelectById(int questionId);

    /**
     * 用来更新选项的，主要用在选项的编辑上
     * @param select
     * @return
     */
    boolean updateSelect(Select select);

    /**
     * 用来获取当前select的id
     * @param questionId
     * @param select
     * @return
     */
    int getSelectId(Select select,int questionId);

}
