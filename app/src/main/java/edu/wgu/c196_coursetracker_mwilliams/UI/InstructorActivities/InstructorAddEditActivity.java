package edu.wgu.c196_coursetracker_mwilliams.UI.InstructorActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermAddEditActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermDetailActivity;

public class InstructorAddEditActivity extends AppCompatActivity {

    public Integer instructorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_add_edit);
        Intent intent = getIntent();

        EditText instructorNameEditText = findViewById(R.id.instructorNameEditText);
        EditText instructorPhoneEditText = findViewById(R.id.instructorPhoneEditText);
        EditText instructorEmailEditText = findViewById(R.id.instructorEmailTextEdit);
        Button saveInstructorBtn = findViewById(R.id.saveInstructorBtn);

        if (intent.hasExtra("instructorID")){
            setTitle("Edit Instructor");
            setInstructorID(intent.getIntExtra("instructorID",0));
            InstructorViewModel instructorViewModel = new InstructorViewModel(getApplication());
            InstructorEntity instructor = instructorViewModel.getInstructorByID(instructorID);
            instructorNameEditText.setText(instructor.getInstructor_name());
            instructorPhoneEditText.setText(instructor.getInstructor_phone());
            instructorEmailEditText.setText(instructor.getInstructor_email());

            saveInstructorBtn.setOnClickListener(v->{

                       instructor.setInstructor_name(instructorNameEditText.getText().toString());
                       instructor.setInstructor_phone(instructorPhoneEditText.getText().toString());
                       instructor.setInstructor_email(instructorEmailEditText.getText().toString());

                instructorViewModel.updateInstructor(instructor);
                Intent intentUpdate = new Intent(InstructorAddEditActivity.this,InstructorDetailActivity.class);
                intentUpdate.putExtra("instructorID", instructor.getId());
                startActivity(intentUpdate);
            });
        }else {
            setTitle("Add Instructor");
            InstructorViewModel instructorViewModel = new InstructorViewModel(getApplication());

            saveInstructorBtn.setOnClickListener(v->{
                InstructorEntity instructorEntity = new InstructorEntity(
                    instructorNameEditText.getText().toString(),
                    instructorPhoneEditText.getText().toString(),
                    instructorEmailEditText.getText().toString()
                );
                instructorViewModel.insertInstructor(instructorEntity);
                Intent intentSave = new Intent(InstructorAddEditActivity.this,InstructorActivity.class);
                startActivity(intentSave);
            });
        }

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if (instructorID == null){
            intent = new Intent(InstructorAddEditActivity.this, InstructorActivity.class);
        }else {
            intent = new Intent(InstructorAddEditActivity.this, InstructorDetailActivity.class);
            intent.putExtra("instructorID",instructorID);
        }
        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }

    public void setInstructorID(Integer instructorID) {
        this.instructorID = instructorID;
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