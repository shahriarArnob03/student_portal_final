<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Assignment Portal – Dashboard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
        rel="stylesheet">
  <style>
    body { background-color: #f8f9fa; }
    .profile-card {
      border-radius: 15px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
      transition: 0.3s;
    }
    .profile-card:hover { transform: scale(1.02); }
    .profile-pic {
      width: 100px;
      height: 100px;
      object-fit: cover;
      border-radius: 50%;
      border: 3px solid #007bff;
    }
  </style>
</head>
<body>
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
      <span class="navbar-brand">📘 Assignment Portal</span>
      <div class="ms-auto d-flex align-items-center">
        <span class="navbar-text text-white me-3">
          Logged in as: <strong>${sessionScope.userName} (${sessionScope.authorId})</strong>
        </span>
        <a href="logout" class="btn btn-outline-light btn-sm">Logout</a>
      </div>
    </div>
  </nav>

  <!-- Dashboard Grid -->
  <div class="container mt-4">
    <h3 class="text-center mb-4">Your Dashboard</h3>
    <div class="row g-4">
      <c:forEach var="user" items="${users}">
        <div class="col-md-4">
          <div class="card profile-card text-center p-3">
            <img src="profile-image?authorId=${user.authorId}"
                 alt="${user.name}" class="profile-pic mx-auto d-block">
            <h5 class="mt-3">${user.name}</h5>
            <p>ID: ${user.authorId}</p>
            <div class="d-grid gap-2 mt-3">
              <c:choose>
                <c:when test="${sessionScope.authorId == user.authorId}">
                  <a href="submission.jsp" class="btn btn-success">
                    Upload to My Folder
                  </a>
                  <a href="viewSubmissions" class="btn btn-outline-primary">
                    View My Submissions
                  </a>
                  <a href="cgpaDashboard.jsp" class="btn btn-outline-dark">
                    🎓 CGPA Tracker
                  </a>
                </c:when>
                <c:otherwise>
                  <a href="viewSubmissions?studentId=${user.authorId}"
                     class="btn btn-outline-primary">
                    View Submissions
                  </a>
                </c:otherwise>
              </c:choose>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>

  <!-- Footer -->
  <footer class="text-center mt-5 mb-3 text-muted">
    © 2025 Shahriar Arnob – Assignment Portal
  </footer>
</body>
</html>
