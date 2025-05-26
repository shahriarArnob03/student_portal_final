<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Student Portal</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white text-center">
                    <h4> Portal Login</h4>
                </div>
                <div class="card-body">
<form action="${pageContext.request.contextPath}/login" method="post">

    <div class="mb-3">
        <label for="authorId" class="form-label">Author ID</label>
        <input type="text" class="form-control" id="authorId" name="authorId" required>
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <button type="submit" class="btn btn-primary w-100">Login</button>
</form>
                </div>
                <div class="card-footer text-center">
                    <small>Don't have an account?</small><br>
                    <a href="registration.jsp" class="btn btn-outline-primary mt-2">Register</a><br><br>
                    <small class="text-muted"> © 2025 Shahriar Arnob – Assignment Portal</small>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
