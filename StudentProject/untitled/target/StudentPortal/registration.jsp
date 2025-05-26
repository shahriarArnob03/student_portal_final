<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Student Registration</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body { background-color: #f0f8ff; }
    .container { max-width: 500px; margin-top: 50px; }
  </style>
</head>
<body>
  <div class="container">
    <h2 class="text-primary text-center mb-4">Registration Form</h2>
    <% if (request.getAttribute("error") != null) { %>
      <div class="alert alert-danger text-center"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="register" method="post" enctype="multipart/form-data">
      <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input type="text" name="name" id="name" class="form-control" required>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" name="email" id="email" class="form-control" required>
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" name="password" id="password" class="form-control" required>
      </div>
      <div class="mb-3">
        <label for="confirmPassword" class="form-label">Confirm Password</label>
        <input type="password" name="confirmPassword" id="confirmPassword" class="form-control" required>
      </div>
      <div class="mb-3">
        <label for="authorId" class="form-label">Student ID</label>
        <input type="text" name="authorId" id="authorId" class="form-control" required>
      </div>
      <div class="mb-3">
        <label for="profilePicture" class="form-label">Profile Picture</label>
        <input type="file" name="profilePicture" id="profilePicture" class="form-control" accept="image/*" required>
      </div>
      <button type="submit" class="btn btn-primary w-100">Register</button>
    </form>
    <p class="text-center mt-3">
      Already have an account? <a href="login.jsp">Log in here</a>
    </p>
  </div>

  <!-- Footer -->
  <footer class="text-center mt-5 mb-3 text-muted">
    © 2025 Shahriar Arnob – Assignment Portal
  </footer>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
