<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin Panel</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container mt-4">
    <div class="p-4 bg-primary text-white text-center rounded">
      <h2>Admin Panel – Manage Users</h2>
    </div>

    <table class="table table-striped table-bordered mt-3">
      <thead class="table-primary">
        <tr>
          <th>Photo</th>
          <th>ID</th>
          <th>Name</th>
          <th>Email</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="u" items="${users}">
          <tr>
            <td>
              <img src="${pageContext.request.contextPath}/profile-image?authorId=${u.authorId}"
                   width="50" class="rounded-circle"/>
            </td>

            <td>${u.authorId}</td>
            <td>${u.name}</td>
            <td>${u.email}</td>
<td>
  <a href="editStudent?authorId=${u.authorId}" class="btn btn-sm btn-warning">Edit</a>
  <a href="deleteStudent?authorId=${u.authorId}" class="btn btn-sm btn-danger"
     onclick="return confirm('Delete this user?');">Delete</a>
  <a href="viewSubmissions?studentId=${u.authorId}" class="btn btn-sm btn-info mt-1">View Uploads</a>
</td>

          </tr>
        </c:forEach>
      </tbody>
    </table>

    <a href="logout" class="btn btn-secondary">Logout</a>
  </div>
</body>
</html>
