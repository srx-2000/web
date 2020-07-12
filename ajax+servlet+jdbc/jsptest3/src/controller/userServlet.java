package controller;

import domain.Admin;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import service.IAdminService;
import service.implement.AdminService;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author srx
 * @description
 * @create 2020-06-14 04:23:18
 */
@WebServlet("/userServlet")
public class userServlet extends baseServlet {


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminName = request.getParameter("adminName");
        String adminPassword = request.getParameter("password");
        IAdminService adminService = new AdminService();
        System.out.println("登录：" + adminName);
        try {
            //设置计算方式
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            adminPassword = base64en.encode(md5.digest(adminPassword.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Admin admin = adminService.Login(adminName, adminPassword);
        HttpSession session = request.getSession();
        session.setAttribute("user", admin);
        if (admin != null) {
//            response.getWriter().write("state:success\n");
//            response.getWriter().write("userType:admin");
            request.getRequestDispatcher("/WEB-INF/page/adminPage.html").forward(request, response);
//            request.getRequestDispatcher("addPaperServlet").forward(request,response);
        } else
            response.sendRedirect("/jsptest3_war_exploded/index.html");
//        session.invalidate();
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IAdminService adminService = new AdminService();
        String adminName = request.getParameter("adminName");
        String password = request.getParameter("password");
        String realName = request.getParameter("realName");
        System.out.println("注册" + adminName);
        System.out.println(password);
        Admin admin = new Admin();
        try {
            //设置计算方式
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            password = base64en.encode(md5.digest(password.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        admin.setAdminName(adminName);
        admin.setPassword(password);
        admin.setRealName(realName);
        if (adminService.register(admin))
            response.sendRedirect("/jsptest3_war_exploded/index.html");
        else
            response.sendRedirect("/jsptest3_war_exploded/register.html");
    }

    protected void destroySession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("/jsptest3_war_exploded/index.html");
    }

}
