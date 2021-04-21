package edu.wgu.c196_coursetracker_mwilliams.UI.InstructorActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;

public class InstructorDetailActivity extends AppCompatActivity {
    InstructorViewModel instructorViewModel;

    private int instructorID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_detail);

        Intent intent = getIntent();
        setInstructorID(intent.getIntExtra("instructorID",0));
        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
        InstructorEntity instructorEntity = instructorViewModel.getInstructorByID(instructorID);
        FloatingActionButton editInstructorFAB = findViewById(R.id.editInstructorFAB);

        TextView instructorName = findViewById(R.id.instructorNameTextView);
        TextView instructorPhone = findViewById(R.id.instructorPhoneTextView);
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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(InstructorDetailActivity.this, InstructorActivity.class);
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