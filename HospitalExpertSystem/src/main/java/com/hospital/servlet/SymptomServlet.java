package com.hospital.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.*;
import javax.servlet.http.*;

import com.hospital.dao.DBConnection;
import com.hospital.model.Rule;
import com.hospital.service.ExpertEngine;

public class SymptomServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String[] symptoms = request.getParameterValues("symptom");

        // ✅ get days from form
        int days = Integer.parseInt(request.getParameter("days"));

        ExpertEngine engine = new ExpertEngine();

        // ✅ updated method call
        Rule result = engine.diagnose(symptoms, days);

        try {
            Connection con = DBConnection.getConnection();

            // ✅ fixed placeholders (6 values → 6 ?)
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO patient_history(username, symptom, diagnosis, treatment, doctor, severity) VALUES(?,?,?,?,?,?)"
            );

            ps.setString(1, username);
            ps.setString(2, String.join(", ", symptoms));
            ps.setString(3, result.getDisease());
            ps.setString(4, result.getTreatment());
            ps.setString(5, result.getDoctor());
            ps.setString(6, result.getSeverity());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("disease", result.getDisease());
        request.setAttribute("treatment", result.getTreatment());
        request.setAttribute("doctor", result.getDoctor());
        request.setAttribute("severity", result.getSeverity());

        request.getRequestDispatcher("result.jsp")
               .forward(request, response);
    }
}