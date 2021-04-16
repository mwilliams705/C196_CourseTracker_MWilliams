package edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorRepository;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermRepository;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;

public class CourseAddEditActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    TermViewModel termViewModel;
    InstructorViewModel instructorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TermRepository termRepository = new TermRepository(getApplication());
        InstructorRepository instructorRepository = new InstructorRepository(getApplication());
        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add_edit);

        EditText courseNameEditText = findViewById(R.id.courseNameEditText);
        EditText courseStartDateEditText = findViewById(R.id.courseStartDateEditText);
        EditText courseEndDateEditText = findViewById(R.id.courseEndDateEditText);

//        Date EditTexts
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

        setUpSpinners();

    }

    private void setUpSpinners(){
        List<InstructorEntity> instructors = new ArrayList<>();
        List<TermEntity> terms = new ArrayList<>();


        Spinner courseInstructorSpinner = findViewById(R.id.courseInstructorSpinner);
        Spinner courseTermSpinner = findViewById(R.id.courseTermSpinner);


        ArrayAdapter<TermEntity> termSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        termSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinnerAdapter.addAll(terms);
        termSpinnerAdapter.notifyDataSetChanged();
        termViewModel.getAllTerms().observe(this,termEntities -> {
            termEntities.addAll(terms);
//            terms.addAll(termEntities);
            termSpinnerAdapter.notifyDataSetChanged();
        });


        courseTermSpinner.setAdapter(termSpinnerAdapter);



        ArrayAdapter<InstructorEntity> instructorSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        instructorSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instructorSpinnerAdapter.addAll(instructors);
        instructorSpinnerAdapter.notifyDataSetChanged();
        instructorViewModel.getAllInstructors().observe(this, instructorEntities -> {
            instructorEntities.addAll(instructors);
//            instructors.addAll(instructorEntities);
            instructorSpinnerAdapter.notifyDataSetChanged();
        });

        courseInstructorSpinner.setAdapter(instructorSpinnerAdapter);
    }
}