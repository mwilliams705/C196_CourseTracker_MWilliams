package edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.AssessmentAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermDetailActivity;

public class CourseDetailActivity extends AppCompatActivity {
    CourseViewModel courseViewModel;
    InstructorViewModel instructorViewModel;
    AssessmentViewModel assessmentViewModel;
    AssessmentAdapter assessmentAdapter;

    CourseEntity courseEntity;


    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        Intent intent = getIntent();
        setCourseId(intent.getIntExtra("courseID",0));
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseEntity = courseViewModel.getCourseById(courseId);
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
        InstructorEntity instructorEntity = instructorViewModel.getInstructorByID(courseEntity.getInstructor_id());
        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        assessmentAdapter = new AssessmentAdapter(this);
        assessmentViewModel.getAllAssessmentsByCourseID(courseId).observe(this,assessmentAdapter::setAssessments);

        TextView courseStartTextView = findViewById(R.id.assessmentTypeTextView);
        TextView courseEndTextView = findViewById(R.id.assessmentDateText);
        TextView courseStatusTextView = findViewById(R.id.courseStatusTextView);
        TextView courseNoteTextView = findViewById(R.id.courseNoteTextView);
        TextView courseInstructorEmailTextView = findViewById(R.id.courseInstructorEmailTextView);
        TextView courseInstructorNameTextView = findViewById(R.id.courseInstructorNameTextView);
        TextView courseInstructorPhoneTextView = findViewById(R.id.courseInstructorPhoneTextView);

        courseStartTextView.setText(courseEntity.getCourse_start());

        courseEndTextView.setText(courseEntity.getCourse_end());

        courseStatusTextView.setText(courseEntity.getCourse_status());

        courseNoteTextView.setText(courseEntity.getCourse_note());

        courseInstructorNameTextView.setText(instructorEntity.getInstructor_name());

        courseInstructorPhoneTextView.setText(instructorEntity.getInstructor_phone());

        courseInstructorEmailTextView.setText(instructorEntity.getInstructor_email());



        RecyclerView assessmentRecycler = findViewById(R.id.courseAssessmentRecyclerView);


        assessmentRecycler.setLayoutManager(new LinearLayoutManager(this));
        assessmentRecycler.setAdapter(assessmentAdapter);



        FloatingActionButton editCourseFAB = findViewById(R.id.editAssessmentFAB);
        editCourseFAB.setOnClickListener(v -> {
            Intent editIntent = new Intent(CourseDetailActivity.this,CourseAddEditActivity.class);
            editIntent.putExtra("courseID", courseId);
            editIntent.putExtra("instructorName",instructorEntity.getInstructor_name());
            startActivity(editIntent);
        });





        setTitle(courseEntity.getCourse_title());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course_detail_menu, menu);
        return true;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.delete){


            AlertDialog.Builder builder = new AlertDialog.Builder(CourseDetailActivity.this);
            builder.setMessage("Are you sure you want to delete "+ courseEntity.getCourse_title() +"?")
                    .setPositiveButton("OK", (dialog, which) -> {
                        courseViewModel.deleteCourse(courseEntity);
                        Toast.makeText(CourseDetailActivity.this, courseEntity.getCourse_title()+" Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CourseDetailActivity.this, CourseActivity.class);
                        startActivity(intent);
                    }).setNegativeButton("Cancel", (dialog, which) -> {
                Intent intent = new Intent(CourseDetailActivity.this, CourseDetailActivity.class);
                startActivity(intent);
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        if (id==R.id.share_note){

            Intent shareNoteIntent = new Intent(Intent.ACTION_SEND);
            shareNoteIntent.setType("text/plain");
            shareNoteIntent.putExtra(Intent.EXTRA_TEXT,courseEntity.getCourse_note());
            startActivity(Intent.createChooser(shareNoteIntent,"Share Using?"));
        }
        else {
            Intent intent = new Intent(CourseDetailActivity.this,CourseActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



    private void checkThenDelete(){
        if (assessmentAdapter.getItemCount() == 0){

        }
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.put;
//    }

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