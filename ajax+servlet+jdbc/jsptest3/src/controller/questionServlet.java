package controller;

import com.google.gson.Gson;
import domain.Admin;
import domain.Paper;
import domain.Question;
import service.IPaperService;
import service.IQuestionService;
import service.implement.PaperService;
import service.implement.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-06-17 03:58:03
 */
@WebServlet("/questionServlet")
public class questionServlet extends baseServlet {
    /**
     * 该方法中处理了增加、更新两个对问题的功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void editQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question question = new Question();
        IQuestionService questionService = new QuestionService();
        int paperId = (int) request.getSession().getAttribute("paperId");
        int questionOrder = Integer.parseInt(request.getParameter("questionOrder"));
        String questionContent = request.getParameter("questionContent");
        int questionType = Integer.parseInt(request.getParameter("questionType"));
        int questionId= Integer.parseInt(request.getParameter("questionId"));
        question.setQuestionType(questionType);
        question.setQuestionOrder(questionOrder);
        question.setQuestionContent(questionContent);
        question.setPaperId(paperId);
        question.setQuestionId(questionId);
        if (questionId !=-1) {
            questionService.updateQuestion(question);
        }
        else{
            System.out.println(questionId);
            if (questionService.isExists(questionId)) {
                if (questionService.getIs_live(questionId) == 0) {
                    questionService.updateIs_live(question);
                    int questionid=questionService.getQuestionId(question,paperId);
                    Map<String, Object> questionMap = new HashMap<>();
                    questionMap.put("questionId", questionid);
                    Gson gson = new Gson();
                    String jsonMap = gson.toJson(questionMap);
                    System.out.println(jsonMap);
                    response.getWriter().write(jsonMap);
                }
            } else {
                questionService.insertQuestion(question);
                int questionid=questionService.getQuestionId(question,paperId);
                Map<String, Object> questionMap = new HashMap<>();
                questionMap.put("questionId", questionid);
                Gson gson = new Gson();
                String jsonMap = gson.toJson(questionMap);
                System.out.println(jsonMap);
                response.getWriter().write(jsonMap);
            }
        }
    }

    /**
     * 用来返回数据库中已经存在的问题
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showQuestioninfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        IQuestionService questionService = new QuestionService();
        HttpSession session = request.getSession();
        int paperId = (int) session.getAttribute("paperId");
        Admin admin= (Admin) session.getAttribute("user");
        List<Question> list = questionService.showQuestionById(paperId);
//        System.out.println(list.get(0));
//        for (int i = 0; i <list.size() ; i++) {
//            System.out.println(list.get(i).toString());
//        }
        if (list != null) {
//            JSONArray jsonArray=JSONArray.fromObject(list);
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String jsonArray = gson.toJson(list);
            System.out.println(jsonArray);
            out.write(jsonArray);
//            out.write(admin.getAdminName());
//            out.write(list.size());
        }
    }

    /**
     * 用于删除问题
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteQuestion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IQuestionService questionService = new QuestionService();
        boolean flag;
        int paperId = (int) request.getSession().getAttribute("paperId");
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        if (questionService.isExists(questionId)) {
            if (questionService.getIs_live(questionId) == 1) {
                flag = questionService.deleteQuestion(questionId);
                flag=true;
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("question_delete", flag);
        Gson gson = new Gson();
        String jsonmap = gson.toJson(map);
        response.getWriter().write(jsonmap);
    }

    /**
     * 废方法没用上，功能已经集成到editQuestion中
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
//    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Question question = new Question();
//        QuestionService questionService = new QuestionService();
//        String[] questionContent = request.getParameterValues("questionContent[]");
//        String[] questionType = request.getParameterValues("questionType[]");
//        int paperId = (int) request.getSession().getAttribute("paperId");
//        String[] questionOrder = request.getParameterValues("questionOrder[]");
//        for (int i = 0; i < questionContent.length; i++) {
//            question.setQuestionType(Integer.parseInt(questionType[i]));
//            question.setQuestionOrder(Integer.parseInt(questionOrder[i]));
//            question.setQuestionContent(questionContent[i]);
//            question.setPaperId(paperId);
//            if (questionService.isExists(questionContent[i], paperId, Integer.parseInt(questionType[i]))) {
//                if (questionService.getIs_live(questionContent[i], paperId, Integer.parseInt(questionType[i])) == 0) {
//                    questionService.updateIs_live(question);
//                }
//            } else {
//                questionService.insertQuestion(question);
//            }
//        }
//        Map<String, Object> questionMap = new HashMap<>();
//        questionMap.put("addQuestion", true);
//        Gson gson = new Gson();
//        String jsonMap = gson.toJson(questionMap);
//        response.getWriter().write(jsonMap);
//    }

    /**
     * 用来处理问题的上下移动并更新序号
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void movie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        int order= Integer.parseInt(request.getParameter("order"));
        int id= Integer.parseInt(request.getParameter("questionId"));
        int childorser= Integer.parseInt(request.getParameter("childorder"));
        int prevquestionId= Integer.parseInt(request.getParameter("prevquestionId"));
        IQuestionService questionService=new QuestionService();
        if("up".equals(type)){
            questionService.updateOrder(order,id,childorser,prevquestionId);
        }else if("down".equals(type)){
            questionService.updateOrder(order,id,childorser,prevquestionId);
        }
    }

    protected void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        int paperId= Integer.parseInt(request.getParameter("paperId"));
        IQuestionService questionService = new QuestionService();
        List<Question> list = questionService.showQuestionById(paperId);
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String jsonArray = gson.toJson(list);
        System.out.println(jsonArray);
        out.write("题目列表"+jsonArray);
            out.write("\n用户名："+username);
            out.write("\n题目数量："+list.size());
    }
}
