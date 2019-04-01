package com.example.redcourserating;

import android.content.Intent;
import android.net.Uri;
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
    private CourseModel selectedCourse;

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

        if (!teacherMap.containsKey(spinnerTeachers.getSelectedItem().toString())) {
            Toast.makeText(this, "Please select a teacher", Toast.LENGTH_LONG).show();
        } else if (ratings.contains(0)) {
            Toast.makeText(
                    this, "Please fill out all ratings", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Submitting ratings...", Toast.LENGTH_LONG).show();

            String mailBody = "Hello " + spinnerTeachers.getSelectedItem() + ",\n\n" +
                    "Your course \"" + selectedCourse.getName() +
                    "\" has been rated by " + extras.get("userID") + ":\n" +
                    "Subject relevance: " + ratings.get(0) + "/5.0\n" +
                    "Your overall performance: " + ratings.get(1) + "/5.0\n" +
                    "Your preparation: " + ratings.get(2) + "/5.0\n" +
                    "Amount of feedback: " + ratings.get(3) + "/5.0\n" +
                    "Quality of your examples: " + ratings.get(4) + "/5.0\n\n" +
                    "Your grade: " + grading();

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto",
                    teacherMap.get(spinnerTeachers.getSelectedItem().toString()) + "@kea.dk",
                    null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "KEA Course Rating Survey Feedback");
            emailIntent.putExtra(Intent.EXTRA_TEXT, mailBody);
            Log.d(TAG, "Sending...");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
    }

    private String grading() {
        Log.d(TAG, "Grading...");

        float avgRating = 0;

        for (float r:ratings) {
            avgRating += r * 4;
        }

        Log.d(TAG, "Average rating: " + avgRating);

        if (avgRating == 100) {
            return "A+";
        } else if (avgRating > 90) {
            return "A";
        } else if (90 > avgRating && avgRating > 80) {
            return "B";
        } else if (80 > avgRating && avgRating > 70) {
            return "C";
        } else if (70 > avgRating && avgRating > 60) {
            return "D";
        } else if (60 > avgRating && avgRating > 50) {
            return "E";
        } else {
            return "F";
        }
    }

    private void init() {
        Log.d(TAG, "Initializing...");

        extras = getIntent().getExtras();
        if (extras.get("course") != null) {
            selectedCourse = (CourseModel) extras.get("course");
            setTitle(selectedCourse.getName());
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
        teacherMap.put("Faisal Fawzi Jarkass", "fafj");
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
