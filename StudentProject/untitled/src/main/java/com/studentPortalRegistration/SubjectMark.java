package com.studentPortalRegistration;

public class SubjectMark {
    private String subjectName;
    private double attendance;
    private double ct;
    private double assignment;
    private double midterm;
    private double finalExam;
    private double credit;

    public SubjectMark(String subjectName, double attendance, double ct, double assignment, double midterm, double finalExam, double credit) {
        this.subjectName = subjectName;
        this.attendance = attendance;
        this.ct = ct;
        this.assignment = assignment;
        this.midterm = midterm;
        this.finalExam = finalExam;
        this.credit = credit;
    }

    // Getters
    public String getSubjectName() {
        return subjectName;
    }

    public double getAttendance() {
        return attendance;
    }

    public double getCt() {
        return ct;
    }

    public double getAssignment() {
        return assignment;
    }

    public double getMidterm() {
        return midterm;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public double getCredit() {
        return credit;
    }

    // Setters
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public void setCt(double ct) {
        this.ct = ct;
    }

    public void setAssignment(double assignment) {
        this.assignment = assignment;
    }

    public void setMidterm(double midterm) {
        this.midterm = midterm;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    // Logic for total marks and grade point
    public double getTotalMarks() {
        return attendance + ct + assignment + midterm + finalExam;
    }

    public double getGradePoint() {
        double total = getTotalMarks();
        if (total >= 80) return 4.00;
        else if (total >= 75) return 3.75;
        else if (total >= 70) return 3.50;
        else if (total >= 65) return 3.25;
        else if (total >= 60) return 3.00;
        else if (total >= 55) return 2.75;
        else if (total >= 50) return 2.50;
        else if (total >= 45) return 2.25;
        else if (total >= 40) return 2.00;
        else return 0.00;
    }

    public double getWeightedGrade() {
        return getGradePoint() * credit;
    }
}
