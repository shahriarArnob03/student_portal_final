<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isErrorPage="true"
         isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Error Occurred</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
        rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container mt-5">
    <div class="alert alert-danger">
      <h4 class="alert-heading">Something Went Wrong</h4>
      <p>${error}</p>
      <hr>
      <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-primary">
        Back to Login
      </a>
    </div>
  </div>
</body>
</html>
