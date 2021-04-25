package edu.wgu.c196_coursetracker_mwilliams.UI.InstructorActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseDetailActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.MainActivity;

public class InstructorDetailActivity extends AppCompatActivity {
    InstructorViewModel instructorViewModel;
    InstructorEntity instructorEntity;
    private int instructorID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_detail);

        Intent intent = getIntent();
        setInstructorID(intent.getIntExtra("instructorID",0));
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
        instructorEntity = instructorViewModel.getInstructorByID(instructorID);
        FloatingActionButton editInstructorFAB = findViewById(R.id.editAssessmentFAB);

        TextView instructorName = findViewById(R.id.assessmentTypeTextView);
        TextView instructorPhone = findViewById(R.id.assessmentDateText);
        TextView instructorEmail = findViewById(R.id.instructorEmailTextView);

        instructorName.setText(instructorEntity.getInstructor_name());
        instructorPhone.setText(instructorEntity.getInstructor_phone());
        instructorEmail.setText(instructorEntity.getInstructor_email());

        editInstructorFAB.setOnClickListener(this::editInstructor);

        setTitle(instructorEntity.getInstructor_name());
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    private void editInstructor(View view){
        Intent intent = new Intent(InstructorDetailActivity.this, InstructorAddEditActivity.class);
        intent.putExtra("instructorID",instructorID);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.delete){


            AlertDialog.Builder builder = new AlertDialog.Builder(InstructorDetailActivity.this);
            builder.setMessage("Are you sure you want to delete "+ instructorEntity.getInstructor_name() +"?")
                    .setPositiveButton("OK", (dialog, which) -> {
                        instructorViewModel.deleteInstructor(instructorEntity);
                        Toast.makeText(InstructorDetailActivity.this, instructorEntity.getInstructor_name()+" Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(InstructorDetailActivity.this, InstructorActivity.class);
                        startActivity(intent);
                    }).setNegativeButton("Cancel", (dialog, which) -> {
                Intent intent = new Intent(InstructorDetailActivity.this, InstructorDetailActivity.class);
                startActivity(intent);
            });
            AlertDialog alert = builder.create();
            alert.show();
        }else {
            Intent intent = new Intent(InstructorDetailActivity.this, InstructorActivity.class);
            startActivity(intent);
        }
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