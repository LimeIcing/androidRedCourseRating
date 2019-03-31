package com.example.redcourserating;

public class CourseModel {
    private String name, courseID, teacherMail;
    private int rating0, rating1, rating2;

    public CourseModel(String name, String courseID) {
        this.name = name;
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTeacherMail() {
        return teacherMail;
    }

    public void setTeacherMail(String teacherMail) {
        this.teacherMail = teacherMail;
    }

    public int getRating0() {
        return rating0;
    }

    public void setRating0(int rating0) {
        this.rating0 = rating0;
    }

    public int getRating1() {
        return rating1;
    }

    public void setRating1(int rating1) {
        this.rating1 = rating1;
    }

    public int getRating2() {
        return rating2;
    }

    public void setRating2(int rating2) {
        this.rating2 = rating2;
    }
}
