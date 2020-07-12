package controller;

import com.google.gson.Gson;
import domain.Admin;
import service.IPaperService;
import service.IQuestionService;
import service.implement.PaperService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import domain.Paper;
import service.implement.QuestionService;


/**
 * @author srx
 * @description
 * @create 2020-06-08 17:36:10
 */
@WebServlet("/paperServlet")
public class paperServlet extends baseServlet {

    protected void addPaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Paper paper = new Paper();
        Date date = new Date();
        IPaperService paperService = new PaperService();
        //这里用来将html中读入的时间和当前时间格式化，以便存入数据库
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String createdate = df.format(date);
        String opendate = request.getParameter("opentime");
        String closedate = request.getParameter("closetime");
        LocalDateTime localDate1 = LocalDateTime.parse(opendate, formatter);
        LocalDateTime localDate2 = LocalDateTime.parse(closedate, formatter);
        String openDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDate1);
        String closeDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDate2);
        long count = 0;
        try {
            Date open = df.parse(openDate);
            Date close = df.parse(closeDate);
            count = close.getTime() - open.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        paper.setCreateDate(createdate);
        paper.setOpenDate(openDate);
        paper.setCloseDate(closeDate);
        paper.setTitle(request.getParameter("title1"));
        //利用session传递的adminId来初始化paper
        Admin admin = (Admin) request.getSession().getAttribute("user");
        paper.setAdminId(admin.getAdminId());
        //这里调用showpaper方法，返回一个paper的list用于显示问卷信息
        paper.setIs_live(paperService.getIs_live(paper));
        //这里判断问卷开始时间和结束 时间之间的是否相差1分钟以上
        if(paperService.isExists(paper.getTitle(), paper.getOpenDate(), paper.getCloseDate(), paper.getAdminId())){
            if((paper.getIs_live()==1)){
                Map<String, Boolean> paperMap = new HashMap<>();
                paperMap.put("paper", true);
                PrintWriter printWriter = response.getWriter();
                Gson gson = new Gson();
                String mapJson = gson.toJson(paperMap);
                printWriter.write(mapJson);
                printWriter.flush();
            }else {
                if (paperService.is_live(paper)) {
                    Map<String, Boolean> paperMap = new HashMap<>();
                    paperMap.put("paper", false);
                    PrintWriter printWriter = response.getWriter();
                    Gson gson = new Gson();
                    String mapJson = gson.toJson(paperMap);
                    printWriter.write(mapJson);
                    printWriter.flush();
                }
            }
        }else{
            if (paperService.addPaper(paper)) {
                Map<String, Boolean> paperMap = new HashMap<>();
                paperMap.put("paper", false);
                PrintWriter printWriter = response.getWriter();
                Gson gson = new Gson();
                String mapJson = gson.toJson(paperMap);
                printWriter.write(mapJson);
                printWriter.flush();
            }
        }
    }

    protected void deletePaper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IPaperService paperService = new PaperService();
//        paperService.findPaperId()
        Admin admin = (Admin) request.getSession().getAttribute("user");
        int adminId = admin.getAdminId();
        String title = request.getParameter("title");
        String opentime = request.getParameter("opentime");
        String closetime = request.getParameter("closetime");
        int paperId;
        if (paperService.findPaperId(title, opentime, closetime, adminId) != null) {
            String paperIdString=paperService.findPaperId(title, opentime, closetime, adminId).toString();
            paperId = Integer.parseInt(paperIdString);
            paperService.deletePaper(adminId,paperId);
        } else {
            paperId=-1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("title", paperId);
        Gson gson = new Gson();
        String jsonmap = gson.toJson(map);
        response.getWriter().write(jsonmap);
    }

    protected void showPaperinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("调用完成");
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        IPaperService paperService = new PaperService();
        Admin admin=(Admin)request.getSession().getAttribute("user");
        List<Paper> list = paperService.showPaperById(admin.getAdminId());
        if (list != null) {
//            JSONArray jsonArray=JSONArray.fromObject(list);
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String jsonArray = gson.toJson(list);
            out.write(jsonArray);
        }
    }

    protected void turn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IPaperService paperService=new PaperService();
        HttpSession session=request.getSession();
        Admin admin = (Admin) session.getAttribute("user");
        String opentime=request.getParameter("opentime");
        String closetime=request.getParameter("closetime");
        String title=request.getParameter("title");
        int paperId= Integer.parseInt(paperService.findPaperId(title,opentime,closetime,admin.getAdminId()).toString());
        session.setAttribute("paperId",paperId);
        request.getRequestDispatcher("/WEB-INF/page/addQuestion.html").forward(request,response);
    }

    protected void turnCountPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IPaperService paperService=new PaperService();
        HttpSession session=request.getSession();
        Admin admin = (Admin) session.getAttribute("user");
        String opentime=request.getParameter("opentime");
        String closetime=request.getParameter("closetime");
        String title=request.getParameter("title");
        int paperId= Integer.parseInt(paperService.findPaperId(title,opentime,closetime,admin.getAdminId()).toString());
        session.setAttribute("paperId",paperId);
        request.getRequestDispatcher("/WEB-INF/page/count.html").forward(request,response);
    }

    protected void returnPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/page/adminPage.html").forward(request, response);
    }

    protected void turnCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        IPaperService paperService=new PaperService();
        HttpSession session=request.getSession();
        Admin admin = (Admin) session.getAttribute("user");
        String opentime=request.getParameter("opentime");
        String closetime=request.getParameter("closetime");
        String title=request.getParameter("title");
        int paperId= (int) request.getSession().getAttribute("paperId");
        IQuestionService questionService=new QuestionService();
        String count=questionService.count(String.valueOf(paperId));
        Gson gson=new Gson();
        String data=gson.toJson(count);
        response.getWriter().write(data);
//        request.getRequestDispatcher("/WEB-INF/page/count.html").forward(request,response);
    }
}
