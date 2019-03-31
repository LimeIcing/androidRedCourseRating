package com.example.redcourserating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CourseDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "CourseDetailsActivity";
    private Bundle extras;
    private Map<String, String> teacherMap;
    private List<String> teachers;
    private Spinner spinnerTeachers;
    private Button btnSubmit;
    private RatingBar ratingRelevance, ratingPerf, ratingPrep, ratingFeedback, ratingExamples;
    private RatingBar[] ratingBars;
    private List<Float> ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        Log.d(TAG, "onCreate() called");

        init();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "Button press registered");

        ratings.clear();
        for (RatingBar rb:ratingBars) {
            ratings.add(rb.getRating());
        }
        Log.d(TAG, ratings.toString());

        if (!teacherMap.containsKey(spinnerTeachers.getSelectedItem().toString())) {
            Toast.makeText(this, "Please select a teacher", Toast.LENGTH_LONG).show();
        } else if (ratings.contains(0)) {
            Toast.makeText(
                    this, "Please fill out all ratings", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Submitting ratings...", Toast.LENGTH_LONG).show();
        }
    }

    private void init() {
        Log.d(TAG, "Initializing...");

        extras = getIntent().getExtras();
        if (extras != null) {
            setTitle(extras.getString("courseName"));
        }

        spinnerTeachers = findViewById(R.id.spinnerTeachers);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        ratingRelevance = findViewById(R.id.ratingRelevance);
        ratingPerf = findViewById(R.id.ratingTeacherPerf);
        ratingPrep = findViewById(R.id.ratingTeacherPrep);
        ratingFeedback = findViewById(R.id.ratingFeedback);
        ratingExamples = findViewById(R.id.ratingExamples);
        ratingBars = new RatingBar[]
                {ratingRelevance, ratingPerf, ratingPrep, ratingFeedback, ratingExamples};
        ratings = new LinkedList<>();

        // region teacher data insertion
        teacherMap = new HashMap<>();
        teachers = new ArrayList<>();

        teacherMap.put("Claus Bove", "clbo");
        teacherMap.put("Asger Clausen", "asbc");
        teacherMap.put("Jón Eikhólm", "jone");
        teacherMap.put("David Ema", "de");
        teacherMap.put("Cay Holmegaard Larsen", "cahl");
        teacherMap.put("Faisal Fawsi Jarkass", "fafj");
        teacherMap.put("Troels Helbo Jensen", "trhj");
        teacherMap.put("Kristoffer Michael Miklas", "krmm");
        teacherMap.put("Janus Pedersen", "janp");
        teacherMap.put("Tom Stevns", "toms");
        teacherMap.put("Oskar Tuska", "ostu");
        teacherMap.put("Jarl Tuxen", "jart");

        teachers.add("Teacher");
        teachers.addAll(teacherMap.keySet());
        // endregion

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, teachers);
        spinnerTeachers.setAdapter(adapter);
    }
}
