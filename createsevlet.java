package com.technexus.servlet;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CreateEventServlet")
public class CreateEventServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String location = request.getParameter("location");
        String roles = request.getParameter("roles");
        String description = request.getParameter("description");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/technexus", "root", "root123"
            );

            String sql = "INSERT INTO events (title, location, roles, description) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, location);
            ps.setString(3, roles);
            ps.setString(4, description);

            ps.executeUpdate();
            conn.close();

            response.sendRedirect("FetchEventsServlet?event=success");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("FetchEventsServlet?event=error");
        }
    }
}  