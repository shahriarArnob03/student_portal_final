<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html><html><head>
  <meta charset="UTF-8"><title>Submit Assignment</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head><body class="bg-light">
  <div class="container mt-5">
    <h3 class="text-center mb-4">Submit Your Assignment</h3>
    <form action="submitFile" method="post" enctype="multipart/form-data" class="card p-4 shadow-sm">
      <div class="mb-3">
        <label class="form-label">Submission Type</label>
        <select name="submissionType" class="form-select" required>
          <option value="assignment">Assignment</option>
          <option value="lab task">Lab Report</option>
          <option value="performance">Performance</option>
        </select>
      </div>
      <div class="mb-3">
        <label class="form-label">Choose File</label>
        <input type="file" name="file" class="form-control" required>
      </div>
      <button type="submit" class="btn btn-success w-100">Upload</button>
    </form>
  </div>
</body></html>
