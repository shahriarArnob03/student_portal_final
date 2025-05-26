//
//package com.studentPortalRegistration;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//
//public class Submission {
//    private final String fileName;
//    private final String filePath;
//    private final String submissionType;
//    private final String uploadDate;  // formatted
//    private final int studentId;      // DB PK
//
//    public Submission(String fileName,
//                      String filePath,
//                      String submissionType,
//                      Timestamp timestamp,
//                      int studentId) {
//        this.fileName       = fileName;
//        this.filePath       = filePath;
//        this.submissionType = submissionType;
//        this.uploadDate     = formatTimestamp(timestamp);
//        this.studentId      = studentId;
//    }
//
//    private String formatTimestamp(Timestamp ts) {
//        if (ts == null) return "";
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(ts);
//    }
//
//    public String getFileName() {
//        return fileName;
//    }
//
//    public String getFilePath() {
//        return filePath;
//    }
//
//    public String getSubmissionType() {
//        return submissionType;
//    }
//
//    public String getUploadDate() {
//        return uploadDate;
//    }
//
//    public int getStudentId() {
//        return studentId;
//    }
//}

package com.studentPortalRegistration;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Submission {
    private final int    submissionId;
    private final String fileName;
    private final String filePath;
    private final String submissionType;
    private final String uploadDate;
    private final String studentId;

    public Submission(int submissionId,
                      String fileName,
                      String filePath,
                      String submissionType,
                      Timestamp ts,
                      String studentId) {
        this.submissionId   = submissionId;
        this.fileName       = fileName;
        this.filePath       = filePath;
        this.submissionType = submissionType;
        this.uploadDate     = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                .format(ts);
        this.studentId      = studentId;
    }

    public int    getSubmissionId()   { return submissionId; }
    public String getFileName()       { return fileName; }
    public String getFilePath()       { return filePath; }
    public String getSubmissionType() { return submissionType; }
    public String getUploadDate()     { return uploadDate; }
    public String getStudentId()      { return studentId; }
}

