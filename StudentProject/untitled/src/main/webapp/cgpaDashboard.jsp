<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.studentPortalRegistration.SubjectMark" %>
<%
    List<SubjectMark> marks = (List<SubjectMark>) session.getAttribute("subjectMarks");
    Double cgpa = (Double) session.getAttribute("cgpa");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CGPA Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <!-- Header -->
    <div class="bg-primary text-white p-4 rounded">
        <h3>📊 CGPA Tracker</h3>
    </div>

    <!-- Error message -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="alert alert-danger mt-3"><%= error %></div>
    <%
        }
    %>

    <!-- Form to Add One Subject -->
    <form action="cgpa-dashboard" method="post" class="mt-4">
        <input type="hidden" name="action" value="add" />
        <h4 class="text-primary">Add New Subject</h4>
        <div class="row g-3">
            <div class="col-md-4">
                <label>Subject Name</label>
                <input type="text" name="subjectName" class="form-control" required />
            </div>
            <div class="col-md-2">
                <label>Attendance</label>
                <input type="number" name="attendance" class="form-control" required />
            </div>
            <div class="col-md-2">
                <label>CT</label>
                <input type="number" name="ct" class="form-control" required />
            </div>
            <div class="col-md-2">
                <label>Assignment</label>
                <input type="number" name="assignment" class="form-control" required />
            </div>
            <div class="col-md-2">
                <label>Midterm</label>
                <input type="number" name="midterm" class="form-control" required />
            </div>
            <div class="col-md-2">
                <label>Final</label>
                <input type="number" name="final" class="form-control" required />
            </div>
            <div class="col-md-2">
                <label>Credit</label>
                <input type="number" name="credit" step="0.1" class="form-control" required />
            </div>
        </div>
        <button type="submit" class="btn btn-success mt-3">Add Subject</button>
    </form>

    <!-- Subject List Table -->
    <%
        if (marks != null && !marks.isEmpty()) {
    %>
        <h4 class="mt-5 text-primary">Submitted Subjects</h4>
        <table class="table table-bordered mt-3">
            <thead class="table-info">
            <tr>
                <th>#</th>
                <th>Subject</th>
                <th>Total Marks</th>
                <th>Grade Point</th>
                <th>Credit</th>
                <th>Weighted Grade</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                int idx = 0;
                for (SubjectMark m : marks) {
            %>
                <tr>
                    <td><%= ++idx %></td>
                    <td><%= m.getSubjectName() %></td>
                    <td><%= m.getTotalMarks() %></td>
                    <td><%= String.format("%.2f", m.getGradePoint()) %></td>
                    <td><%= m.getCredit() %></td>
                    <td><%= String.format("%.2f", m.getWeightedGrade()) %></td>
                    <td>
                        <a href="edit-subject?index=<%= idx - 1 %>"
                           class="btn btn-sm btn-warning">Edit</a>
                        <a href="delete-subject?index=<%= idx - 1 %>"
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('Delete this subject?');">Delete</a>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>

        <form action="cgpa-dashboard" method="post">
            <input type="hidden" name="action" value="calculate" />
            <button type="submit" class="btn btn-primary mt-3">Calculate CGPA</button>
        </form>

        <%
            if (cgpa != null) {
        %>
        <div class="alert alert-success mt-3">
            <strong>Your CGPA: </strong><%= String.format("%.2f", cgpa) %>
        </div>
        <%
            }
        %>
    <%
        } // end of if(marks != null)
    %>
</div>
</body>
</html>
