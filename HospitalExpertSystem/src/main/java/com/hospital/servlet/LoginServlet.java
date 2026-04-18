package com.hospital.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hospital.dao.DBConnection;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                response.getWriter().println("Database Connection Failed");
                return;
            }

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?");

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", user);

                response.sendRedirect("index.html");
            } else {
                response.getWriter().println("Invalid Login");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Login Error: " + e.getMessage());
        }
    }
}