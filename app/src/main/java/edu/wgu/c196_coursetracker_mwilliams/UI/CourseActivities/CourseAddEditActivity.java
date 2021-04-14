package edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import edu.wgu.c196_coursetracker_mwilliams.R;

public class CourseAddEditActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add_edit);

        Toolbar courseAddEditToolbar = findViewById(R.id.courseAddEditToolbar);
        EditText courseNameEditText = findViewById(R.id.courseNameEditText);
        EditText courseStartDateEditText = findViewById(R.id.courseStartDateEditText);
        EditText courseEndDateEditText = findViewById(R.id.courseEndDateEditText);

        courseStartDateEditText.setOnClickListener(
                v -> datePickerDialog = new DatePickerDialog(
                        CourseAddEditActivity.this, (view, year, month, dayOfMonth) -> {
            String output = month+"/"+dayOfMonth+"/"+year;
            courseStartDateEditText.setText(output);
        },2021,1,1));

        courseEndDateEditText.setOnClickListener(
                v -> datePickerDialog = new DatePickerDialog(
                        CourseAddEditActivity.this, (view, year, month, dayOfMonth) -> {
                    String output = month+"/"+dayOfMonth+"/"+year;
                    courseStartDateEditText.setText(output);
                },2021,1,1));
    }
}