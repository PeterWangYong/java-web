package com.damu.servlet;

import com.damu.dao.UsersDao;
import com.damu.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deluser")
public class UsersDelServlet extends HttpServlet {

    private UsersDao usersDao = new UsersDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数
        String id = req.getParameter("id");
        String type = req.getParameter("type");

        // 执行删除或锁定
        if ("lock".equals(type)) {
            // 执行锁定操作： update操作
            Users user = new Users();
            user.setId(Integer.parseInt(id));
            user.setUserStatus(1);
            usersDao.updateUser(user);
        } else if ("del".equals(type)) {
            usersDao.deleteUser(Integer.parseInt(id));
        } else if ("unlock".equals(type)) {
            // 执行解锁操作： update操作
            Users user = new Users();
            user.setId(Integer.parseInt(id));
            user.setUserStatus(0);
            usersDao.updateUser(user);
        }
        // 跳转到首页
        resp.sendRedirect("/index");
    }
}
