package com.technexus.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String eventName = request.getParameter("eventName");
        String userName = request.getParameter("userName");
        String githubLink = request.getParameter("userGithub");
        String cover = request.getParameter("userCover");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/technexus", "root", "root123"
            );

            String sql = "INSERT INTO registrations (event_name, user_name, github_link, cover_letter) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, eventName);
            ps.setString(2, userName);
            ps.setString(3, githubLink);
            ps.setString(4, cover);

            ps.executeUpdate();
            conn.close();

            response.sendRedirect("FetchEventsServlet?status=success");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("FetchEventsServlet?status=error");
        }
    }
}Explain each and every line as I have to explain it to sir