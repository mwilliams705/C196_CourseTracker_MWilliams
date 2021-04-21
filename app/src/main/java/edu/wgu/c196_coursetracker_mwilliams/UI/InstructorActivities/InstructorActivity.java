package edu.wgu.c196_coursetracker_mwilliams.UI.InstructorActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Objects;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.InstructorAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.MainActivity;

public class InstructorActivity extends AppCompatActivity {
    InstructorViewModel instructorViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        FloatingActionButton addInstructorFAB = findViewById(R.id.addInstructorFAB);
        RecyclerView instructorRecyclerView = findViewById(R.id.instructorRecyclerView);
        InstructorAdapter instructorAdapter = new InstructorAdapter(this);

        setTitle("Instructors");
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        instructorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        instructorRecyclerView.setAdapter(instructorAdapter);

        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
        instructorViewModel.getAllInstructors().observe(this, instructorAdapter::setInstructors);

        addInstructorFAB.setOnClickListener(this::addInstructor);

    }

    private void addInstructor(View view){
        Intent intent = new Intent(InstructorActivity.this, InstructorAddEditActivity.class);
        startActivity(intent);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(InstructorActivity.this, MainActivity.class);
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