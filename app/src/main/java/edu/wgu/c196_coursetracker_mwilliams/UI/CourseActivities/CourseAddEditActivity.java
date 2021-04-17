package edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;

public class CourseAddEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TermViewModel termViewModel;
    InstructorViewModel instructorViewModel;
    CourseViewModel courseViewModel;

    public Integer courseID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add_edit);

        EditText courseNameEditText = findViewById(R.id.courseNameEditText);
        EditText courseStartDateEditText = findViewById(R.id.courseStartDateEditText);
        EditText courseEndDateEditText = findViewById(R.id.courseEndDateEditText);
        EditText courseNoteMultiLine = findViewById(R.id.courseNoteMultiLine);
        Button courseSaveBtn = findViewById(R.id.courseSaveBtn);

//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
        Spinner courseTermSpinner = findViewById(R.id.courseTermSpinner);
        List<TermEntity> terms = new ArrayList<>();

        ArrayAdapter<TermEntity> termSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, terms);

        termSpinnerAdapter.setDropDownViewResource(R.layout.spinner_item);

        termViewModel.getAllTerms().observe(this, termSpinnerAdapter::addAll);

        termSpinnerAdapter.notifyDataSetChanged();


        courseTermSpinner.setAdapter(termSpinnerAdapter);
        courseTermSpinner.setOnItemSelectedListener(this);



//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------

        Spinner courseInstructorSpinner = findViewById(R.id.courseInstructorSpinner);
        List<InstructorEntity> instructors = new ArrayList<>();



        ArrayAdapter<InstructorEntity> instructorSpinnerAdapter = new ArrayAdapter<>(this,R.layout.spinner_item,instructors);
        instructorSpinnerAdapter.setDropDownViewResource(R.layout.spinner_item);

        instructorViewModel.getAllInstructors().observe(this, instructorSpinnerAdapter::addAll);

        instructorSpinnerAdapter.notifyDataSetChanged();


        courseInstructorSpinner.setAdapter(instructorSpinnerAdapter);
        courseInstructorSpinner.setOnItemSelectedListener(this);

//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------

        Spinner courseStatusSpinner = findViewById(R.id.courseStatusSpinner);
        ArrayList<String> statuses = new ArrayList<>();
        statuses.add("In Progress");
        statuses.add("Completed");
        statuses.add("Dropped");
        statuses.add("Plan To Take");

        ArrayAdapter<String> statusSpinnerAdapter = new ArrayAdapter<>(this,R.layout.spinner_item,statuses);
        statusSpinnerAdapter.setDropDownViewResource(R.layout.spinner_item);

        courseStatusSpinner.setAdapter(statusSpinnerAdapter);
        courseStatusSpinner.setOnItemSelectedListener(this);

//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------



        if (intent.hasExtra("course_id")){
            setTitle("Edit Course");
            setCourseID(intent.getIntExtra("course_id",0));
            CourseEntity courseEntity = courseViewModel.getCourseById(courseID);

            courseNameEditText.setText(courseEntity.getCourse_title());
            courseStartDateEditText.setText(courseEntity.getCourse_start());
            courseEndDateEditText.setText(courseEntity.getCourse_end());
            courseInstructorSpinner.setSelection(getSpinnerIndex(courseInstructorSpinner,intent.getStringExtra("course_status")));


        }
        else{
            setTitle("Add Course");

        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);


        String status = courseStatusSpinner.getSelectedItem().toString();

        courseSaveBtn.setOnClickListener(v -> {

            int termID =((TermEntity)( courseTermSpinner.getSelectedItem())).getTerm_id();
            int instructorID = ((InstructorEntity)(courseInstructorSpinner.getSelectedItem())).getId();


            CourseEntity courseEntity = new CourseEntity(
              courseNameEditText.getText().toString(),
              courseStartDateEditText.getText().toString(),
              courseEndDateEditText.getText().toString(),
              status,
              courseNoteMultiLine.getText().toString(),
              termID,
            instructorID
            );
            courseViewModel.insertCourse(courseEntity);
            Intent saveIntent = new Intent(CourseAddEditActivity.this,CourseActivity.class);
            Toast.makeText(this,"Course "+courseEntity.getCourse_title()+" saved!",Toast.LENGTH_SHORT).show();
            startActivity(saveIntent);
        });


    }

    private int getSpinnerIndex(Spinner spinner, String myString) {
        int index = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().trim().equals(myString.trim())) {
                index = i;
            }
        }
        return index;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object item = parent.getItemAtPosition(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        System.out.println("Nothing Selected!");
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if (courseID == null){
            intent = new Intent(CourseAddEditActivity.this, CourseActivity.class);
        }else {
            intent = new Intent(CourseAddEditActivity.this, CourseDetailActivity.class);
            intent.putExtra("courseID",courseID);
        }
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }



//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------
}