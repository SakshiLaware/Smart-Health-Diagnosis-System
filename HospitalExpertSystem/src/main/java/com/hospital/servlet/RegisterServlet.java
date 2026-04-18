package com.hospital.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hospital.dao.DBConnection;

//@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                response.getWriter().println("Database Connection Failed");
                return;
            }

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username,password) VALUES (?, ?)");

            ps.setString(1, user);
            ps.setString(2, pass);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                response.sendRedirect("Login.html");
            } else {
                response.getWriter().println("Registration Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}