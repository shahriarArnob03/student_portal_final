<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studentPortalRegistration.SubjectMark" %>
<%
    SubjectMark subject = (SubjectMark) request.getAttribute("subject");
    int index = (Integer) request.getAttribute("index");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Subject</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="bg-warning text-dark p-4 rounded text-center">
        <h3>✏️ Edit Subject Marks</h3>
    </div>

    <form action="edit-subject" method="post" class="mt-4">
        <input type="hidden" name="index" value="<%= index %>" />

        <div class="mb-3">
            <label for="subjectName" class="form-label">Subject Name:</label>
            <input type="text" name="subjectName" id="subjectName" value="<%= subject.getSubjectName() %>" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="attendance" class="form-label">Attendance:</label>
            <input type="number" name="attendance" id="attendance" value="<%= subject.getAttendance() %>" step="0.01" min="0" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="ct" class="form-label">Class Test:</label>
            <input type="number" name="ct" id="ct" value="<%= subject.getCt() %>" step="0.01" min="0" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="assignment" class="form-label">Assignment:</label>
            <input type="number" name="assignment" id="assignment" value="<%= subject.getAssignment() %>" step="0.01" min="0" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="midterm" class="form-label">Midterm:</label>
            <input type="number" name="mid" id="midterm" value="<%= subject.getMidterm() %>" step="0.01" min="0" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="finalExam" class="form-label">Final Exam:</label>
            <input type="number" name="final" id="finalExam" value="<%= subject.getFinalExam() %>" step="0.01" min="0" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="credit" class="form-label">Credit:</label>
            <input type="number" name="credit" id="credit" value="<%= subject.getCredit() %>" step="0.5" min="0" class="form-control" required />
        </div>

        <button type="submit" class="btn btn-primary">✅ Update Subject</button>
        <a href="cgpa-dashboard" class="btn btn-secondary">❌ Cancel</a>
    </form>
</div>
</body>
</html>
