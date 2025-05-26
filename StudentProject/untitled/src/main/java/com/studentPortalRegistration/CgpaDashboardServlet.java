package com.studentPortalRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cgpa-dashboard")
public class CgpaDashboardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<SubjectMark> subjectMarks = (List<SubjectMark>) session.getAttribute("subjectMarks");
        if (subjectMarks == null) {
            subjectMarks = new ArrayList<>();
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            try {
                String subjectName = request.getParameter("subjectName");
                double attendance = Double.parseDouble(request.getParameter("attendance"));
                double ct = Double.parseDouble(request.getParameter("ct"));
                double assignment = Double.parseDouble(request.getParameter("assignment"));
                double midterm = Double.parseDouble(request.getParameter("midterm"));
                double finalExam = Double.parseDouble(request.getParameter("final"));
                double credit = Double.parseDouble(request.getParameter("credit"));

                SubjectMark mark = new SubjectMark(subjectName, attendance, ct, assignment, midterm, finalExam, credit);
                subjectMarks.add(mark);

                session.setAttribute("subjectMarks", subjectMarks);
                session.removeAttribute("cgpa"); // Reset CGPA if previously calculated

                response.sendRedirect("cgpaDashboard.jsp");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Please enter valid numbers for all fields.");
                request.getRequestDispatcher("cgpaDashboard.jsp").forward(request, response);
            }

        } else if ("calculate".equals(action)) {
            double totalWeighted = 0;
            double totalCredits = 0;

            for (SubjectMark m : subjectMarks) {
                totalWeighted += m.getWeightedGrade();
                totalCredits += m.getCredit();
            }

            double cgpa = totalCredits == 0 ? 0 : totalWeighted / totalCredits;
            session.setAttribute("cgpa", cgpa);

            response.sendRedirect("cgpaDashboard.jsp");

        } else {
            request.setAttribute("error", "Invalid action.");
            request.getRequestDispatcher("cgpaDashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("cgpaDashboard.jsp").forward(request, response);
    }
}
