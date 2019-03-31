package com.example.redcourserating;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private List<CourseModel> courseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView courseName, courseID;

        public MyViewHolder(View view) {
            super(view);
            courseName = view.findViewById(R.id.course_name);
            courseID = view.findViewById(R.id.course_id);
        }
    }

    public CourseAdapter(List<CourseModel> courseList) {
        this.courseList = courseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CourseModel course = courseList.get(position);
        holder.courseName.setText(course.getName());
        holder.courseID.setText(course.getCourseID());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}


