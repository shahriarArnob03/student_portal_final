package com.studentPortalRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/edit-subject")
public class EditSubjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        HttpSession session = request.getSession(false);
        List<SubjectMark> marks = (List<SubjectMark>) session.getAttribute("subjectMarks");

        if (marks != null && index >= 0 && index < marks.size()) {
            request.setAttribute("index", index);
            request.setAttribute("subject", marks.get(index));
            request.getRequestDispatcher("editSubject.jsp").forward(request, response);
        } else {
            response.sendRedirect("cgpa-dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        String subjectName = request.getParameter("subjectName");
        double attendance = Double.parseDouble(request.getParameter("attendance"));
        double ct = Double.parseDouble(request.getParameter("ct"));
        double assignment = Double.parseDouble(request.getParameter("assignment"));
        double mid = Double.parseDouble(request.getParameter("mid"));
        double fin = Double.parseDouble(request.getParameter("final"));
        double credit = Double.parseDouble(request.getParameter("credit"));

        HttpSession session = request.getSession(false);
        List<SubjectMark> marks = (List<SubjectMark>) session.getAttribute("subjectMarks");

        if (marks != null && index >= 0 && index < marks.size()) {
            SubjectMark updated = new SubjectMark(subjectName, attendance, ct, assignment, mid, fin, credit);
            marks.set(index, updated);

            double totalWeighted = 0, totalCredit = 0;
            for (SubjectMark m : marks) {
                totalWeighted += m.getWeightedGrade();
                totalCredit += m.getCredit();
            }

            session.setAttribute("subjectMarks", marks);
            session.setAttribute("cgpa", totalCredit > 0 ? totalWeighted / totalCredit : 0.0);
        }

        response.sendRedirect("cgpa-dashboard");
    }
}
