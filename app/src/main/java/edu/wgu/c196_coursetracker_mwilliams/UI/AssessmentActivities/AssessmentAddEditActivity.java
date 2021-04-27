package edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseAddEditActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseDetailActivity;

public class AssessmentAddEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AssessmentViewModel assessmentViewModel;
    CourseViewModel courseViewModel;

    public Integer assessmentID;
    public Integer courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_add_edit);
        Intent intent = getIntent();
        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        EditText assessmentNameEditText = findViewById(R.id.assessmentNameEditText);
        EditText assessmentDateEditText = findViewById(R.id.assessmentDateEditText);
        Button saveAssessmentBtn = findViewById(R.id.saveAssessmentBtn);


//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------

        Spinner assessmentEditTypeSpinner = findViewById(R.id.assessmentEditTypeSpinner);
        List<String> courseType = new ArrayList<>();
        courseType.add("OA");
        courseType.add("PA");
        ArrayAdapter<String> courseTypeSpinnerAdapter = new ArrayAdapter<>(this,R.layout.spinner_item,courseType);
        assessmentEditTypeSpinner.setAdapter(courseTypeSpinnerAdapter);
        assessmentEditTypeSpinner.setOnItemSelectedListener(this);

//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------

        Spinner assessmentCourseSpinnerEdit = findViewById(R.id.assessmentCourseSpinnerEdit);
        List<CourseEntity> courses = new ArrayList<>();

        ArrayAdapter<CourseEntity> assessmentCourseSpinnerAdapter = new ArrayAdapter<>(this,R.layout.spinner_item,courses);
        assessmentCourseSpinnerAdapter.setDropDownViewResource(R.layout.spinner_item);
        courseViewModel.getAllCourses().observe(this,assessmentCourseSpinnerAdapter::addAll);

        assessmentCourseSpinnerAdapter.notifyDataSetChanged();
        assessmentCourseSpinnerEdit.setAdapter(assessmentCourseSpinnerAdapter);
        assessmentCourseSpinnerEdit.setOnItemSelectedListener(this);

//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);




        if (intent.hasExtra("assessmentID")){
            setTitle("Edit Assessment");

            AssessmentEntity assessmentEntity = assessmentViewModel.getAssessmentByID(intent.getIntExtra("assessmentID",0));
            setAssessmentID(assessmentEntity.getId());
            setCourseID(assessmentEntity.getCourse_id());
            CourseEntity courseEntity = courseViewModel.getCourseById(courseID);

            assessmentNameEditText.setText(assessmentEntity.getAssessment_title());
            assessmentDateEditText.setText(assessmentEntity.getAssessment_date());
//            assessmentEditTypeSpinner.setSelection(getSpinnerIndex(assessmentEditTypeSpinner,intent.getStringExtra("assessmentName")));
//            assessmentCourseSpinnerEdit.setSelection(getSpinnerIndex(assessmentCourseSpinnerEdit,courseEntity.getCourse_title()));


            saveAssessmentBtn.setOnClickListener(v->{
                int courseID = ((CourseEntity)(assessmentCourseSpinnerEdit.getSelectedItem())).getCourse_id();

                assessmentEntity.setAssessment_title(assessmentNameEditText.getText().toString());
                assessmentEntity.setAssessment_date(assessmentDateEditText.getText().toString());
                if (assessmentEditTypeSpinner.getSelectedItem().toString().equals("OA")){
                    assessmentEntity.setOA(true);
                }else assessmentEntity.setOA(false);
                assessmentEntity.setCourse_id(courseID);
                assessmentViewModel.updateAssessment(assessmentEntity);
                Intent updateIntent = new Intent(AssessmentAddEditActivity.this,AssessmentDetailActivity.class);
                updateIntent.putExtra("assessmentID",assessmentID);
                startActivity(updateIntent);
            });

        }
        else {
            setTitle("Add Assessment");
            saveAssessmentBtn.setOnClickListener(v->{


                int courseID = ((CourseEntity)(assessmentCourseSpinnerEdit.getSelectedItem())).getCourse_id();

                boolean type;
                type = assessmentEditTypeSpinner.getSelectedItem().toString().equals("OA");


                AssessmentEntity assessment = new AssessmentEntity(
                        assessmentNameEditText.getText().toString(),
                        type,
                        assessmentDateEditText.getText().toString(),
                        courseID
                        );

                assessmentViewModel.insertAssessment(assessment);
                Intent insertIntent = new Intent(AssessmentAddEditActivity.this,AssessmentActivity.class);
                startActivity(insertIntent);
            });
        }


    }




    public void setAssessmentID(Integer assessmentID) {
        this.assessmentID = assessmentID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
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
        System.out.println("Nothing Selected");
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if (courseID == null){
            intent = new Intent(AssessmentAddEditActivity.this, AssessmentActivity.class);
        }else {
            intent = new Intent(AssessmentAddEditActivity.this, AssessmentDetailActivity.class);
            intent.putExtra("courseID",courseID);
        }
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }


    //    Lifecycle Logs
    private final String TAG = "Lifecycle";
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }


}