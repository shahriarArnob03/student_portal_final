package com.studentPortalRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/delete-subject")
public class DeleteSubjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int index = Integer.parseInt(request.getParameter("index"));
            HttpSession session = request.getSession(false);
            List<SubjectMark> marks = (List<SubjectMark>) session.getAttribute("subjectMarks");

            if (marks != null && index >= 0 && index < marks.size()) {
                marks.remove(index); // Remove the selected subject

                // Recalculate CGPA
                double totalWeighted = 0.0;
                double totalCredit = 0.0;

                for (SubjectMark mark : marks) {
                    totalWeighted += mark.getWeightedGrade();
                    totalCredit += mark.getCredit();
                }

                // Update session attributes
                session.setAttribute("subjectMarks", marks);
                session.setAttribute("cgpa", totalCredit > 0 ? totalWeighted / totalCredit : 0.0);
            }

        } catch (NumberFormatException e) {
            // You can log the error if needed
            System.err.println("Invalid index format: " + e.getMessage());
        }

        // Redirect to the dashboard after deletion
        response.sendRedirect("cgpa-dashboard");
    }
}
