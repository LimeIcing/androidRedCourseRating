package com.example.redcourserating;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseModel implements Parcelable {
    private String name, courseID, teacherMail;

    public CourseModel(String name, String courseID) {
        this.name = name;
        this.courseID = courseID;
    }

    public CourseModel(Parcel in) {
        String[] data = new String[2];

        in.readStringArray(data);

        this.name = data[0];
        this.courseID = data[1];
    }

    public static final Parcelable.Creator<CourseModel> CREATOR
            = new Parcelable.Creator<CourseModel>() {
        public CourseModel createFromParcel(Parcel in) {
            return new CourseModel(in);
        }

        public CourseModel[] newArray(int size) {
            return new CourseModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name, this.courseID});
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
}
