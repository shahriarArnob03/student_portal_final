<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Submissions</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
        rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container mt-5">
    <h3 class="mb-4">Submissions</h3>

    <c:choose>
      <c:when test="${not empty subs}">
        <table class="table table-bordered">
          <thead class="table-primary">
            <tr>
              <th>File</th>
              <th>Type</th>
              <th>Date</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="s" items="${subs}">
              <tr>
                <td>
                  <a href="${pageContext.request.contextPath}/${s.filePath}"
                     target="_blank">${s.fileName}</a>
                </td>
                <td>${s.submissionType}</td>
                <td>${s.uploadDate}</td>
                <td>
                  <c:if test="${sessionScope.role == 'admin'
                               or sessionScope.authorId == s.studentId}">
                    <a href="deleteSubmission?submissionId=${s.submissionId}"
                       class="btn btn-sm btn-danger"
                       onclick="return confirm('Delete this file?');">Delete</a>
                  </c:if>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:when>
      <c:otherwise>
        <div class="alert alert-info">No submissions found.</div>
      </c:otherwise>
    </c:choose>

    <div class="mt-4">
      <a href="dashboard" class="btn btn-secondary me-2">Back to Dashboard</a>
      <a href="logout"    class="btn btn-danger">Logout</a>
    </div>
  </div>
</body>
</html>
