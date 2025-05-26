<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html><html><head>
  <meta charset="UTF-8"><title>Edit Student</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head><body class="bg-light">
  <div class="container mt-5">
    <h3>Edit Student</h3>
    <form action="editStudent" method="post">
      <input type="hidden" name="authorId" value="${user.authorId}"/>
      <div class="mb-3">
        <label>Name</label>
        <input name="name" value="${user.name}" class="form-control"/>
      </div>
      <div class="mb-3">
        <label>Email</label>
        <input name="email" value="${user.email}" class="form-control"/>
      </div>
      <button class="btn btn-primary">Save</button>
      <a href="admin" class="btn btn-secondary">Cancel</a>
    </form>
  </div>
</body></html>
