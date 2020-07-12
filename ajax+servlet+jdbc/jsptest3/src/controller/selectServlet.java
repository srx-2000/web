package controller;

import com.google.gson.Gson;
import domain.Question;
import domain.Select;
import service.ISelectService;
import service.implement.QuestionService;
import service.implement.SelectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-06-19 01:11:28
 */
@WebServlet("/selectServlet")
public class selectServlet extends baseServlet {
    protected void editSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Select select = new Select();
        ISelectService selectService = new SelectService();
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String selectContent = request.getParameter("selectContent");
        int selectId = Integer.parseInt(request.getParameter("selectId"));
        select.setQuestionId(questionId);
        select.setSelectContent(selectContent);
        select.setSelectId(selectId);
        if (selectId != -1) {
            selectService.updateSelect(select);
        } else {
            System.out.println(questionId);
            if (selectService.isExists(selectId)) {
                if (selectService.getIs_live(selectId) == 0) {
                    selectService.updateIs_live(select);
                    int selectid = selectService.getSelectId(select, questionId);
                    Map<String, Object> questionMap = new HashMap<>();
                    questionMap.put("selectId", selectid);
                    Gson gson = new Gson();
                    String jsonMap = gson.toJson(questionMap);
                    System.out.println(jsonMap);
                    response.getWriter().write(jsonMap);
                }
            } else {
                selectService.insertSelect(select);
                int selectid = selectService.getSelectId(select, questionId);
                Map<String, Object> questionMap = new HashMap<>();
                questionMap.put("selectId", selectid);
                Gson gson = new Gson();
                String jsonMap = gson.toJson(questionMap);
                System.out.println(jsonMap);
                response.getWriter().write(jsonMap);
            }
        }
    }

    protected void deleteSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int selectId = Integer.parseInt(request.getParameter("selectId"));
        ISelectService selectService = new SelectService();
        boolean flag;
        if (selectService.isExists(selectId)) {
            if (selectService.getIs_live(selectId) == 1) {
                selectService.deleteSelect(selectId);
                flag = true;
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("select_delete", flag);
        Gson gson = new Gson();
        String jsonmap = gson.toJson(map);
        response.getWriter().write(jsonmap);
    }

    protected void showSelectinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        ISelectService selectService = new SelectService();
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        List<Select> list = selectService.showSelectById(questionId);
        if (list != null) {
            Gson gson = new Gson();
            String jsonlist = gson.toJson(list);
            response.getWriter().write(jsonlist);
        }
    }
}
