package com.example.redcourserating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private final String TAG = "CourseListActivity";
    private RecyclerView recyclerCourseList;
    private CourseAdapter courseAdapter;
    private List<CourseModel> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        Log.d(TAG, "onCreate() called");

        init();
    }

    private void init() {
        Log.d(TAG, "initializing...");

        recyclerCourseList = findViewById(R.id.recyclerCourseList);
        courses = new LinkedList<>();
        courseAdapter = new CourseAdapter(courses);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerCourseList.setLayoutManager(layoutManager);
        recyclerCourseList.setItemAnimator(new DefaultItemAnimator());
        recyclerCourseList.addItemDecoration(
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerCourseList.setAdapter(courseAdapter);
        final Intent intent = new Intent(this, CourseDetailsActivity.class);

        recyclerCourseList.addOnItemTouchListener(
                new RecyclerTouchListener(
                        getApplicationContext(), recyclerCourseList,
                        new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CourseModel selectedCourse = courses.get(position);
                intent.putExtra("courseName", selectedCourse.getName());
                intent.putExtra("courseID", selectedCourse.getCourseID());
                intent.putExtra("teacherMail", selectedCourse.getTeacherMail());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // region data insertion
        courses.add(new CourseModel("Software Construction 1", "SWC1"));
        courses.add(new CourseModel("Software Construction 2", "SWC2"));
        courses.add(new CourseModel("Software Construction 3", "SWC3"));
        courses.add(new CourseModel("Android App, Elective", "Android App"));

        courseAdapter.notifyDataSetChanged();
        // endregion
    }
}
