package com.hospital.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.hospital.dao.DBConnection;

public class HistoryServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        ArrayList<String[]> historyList = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT symptom, diagnosis, treatment, doctor, severity " +
                "FROM patient_history WHERE username=?"
            );

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] row = new String[5];

                row[0] = rs.getString("symptom");
                row[1] = rs.getString("diagnosis");
                row[2] = rs.getString("treatment");
                row[3] = rs.getString("doctor");
                row[4] = rs.getString("severity");

                historyList.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("historyList", historyList);
        request.getRequestDispatcher("history.jsp")
               .forward(request, response);
    }
}