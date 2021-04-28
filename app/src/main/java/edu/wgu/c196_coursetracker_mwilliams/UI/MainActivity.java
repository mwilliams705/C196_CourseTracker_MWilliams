package edu.wgu.c196_coursetracker_mwilliams.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentRepository;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseRepository;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorRepository;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermRepository;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities.AssessmentActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities.AssessmentAddEditActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseAddEditActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.InstructorActivities.InstructorActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.InstructorActivities.InstructorAddEditActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermAddEditActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Welcome");





        Button termBtn = findViewById(R.id.termBtn);
        Button courseBtn = findViewById(R.id.courseBtn);
        Button assessmentBtn = findViewById(R.id.assessmentBtn);
        Button instructorsBtn = findViewById(R.id.instructorBtn);
        FloatingActionButton addTermsFromMainFAB = findViewById(R.id.addTermsFromMainFAB);
        FloatingActionButton addCoursesFromMainFAB = findViewById(R.id.addCoursesFromMainFAB);
        FloatingActionButton addAssessmentsFromMainFAB = findViewById(R.id.addAssessmentFromMainFAB);
        FloatingActionButton addInstructorsFromMainFAB = findViewById(R.id.addInstructorFromMainFAB);


        termBtn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, TermActivity.class);
            startActivity(intent);
        });

        courseBtn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, CourseActivity.class);
            startActivity(intent);
        });

        assessmentBtn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, AssessmentActivity.class);
            startActivity(intent);
        });

        instructorsBtn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, InstructorActivity.class);
            startActivity(intent);
        });

        addTermsFromMainFAB.setOnClickListener(v->{
            Intent intent= new Intent(MainActivity.this, TermAddEditActivity.class);
            startActivity(intent);
        });

        addCoursesFromMainFAB.setOnClickListener(v->{
            Intent intent= new Intent(MainActivity.this, CourseAddEditActivity.class);
            startActivity(intent);
        });

        addAssessmentsFromMainFAB.setOnClickListener(v->{
            Intent intent= new Intent(MainActivity.this, AssessmentAddEditActivity.class);
            startActivity(intent);
        });

        addInstructorsFromMainFAB.setOnClickListener(v->{
            Intent intent= new Intent(MainActivity.this, InstructorAddEditActivity.class);
            startActivity(intent);
        });
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