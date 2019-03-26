package com.example.redcourserating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class CourseListActivity extends AppCompatActivity {

    private final String TAG = "CourseListActivity";
    private RecyclerView recyclerCourseList;

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
    }
}
