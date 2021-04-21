package edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities;

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

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentRepository;
import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.AssessmentAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.MainActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermActivity;

public class AssessmentActivity extends AppCompatActivity {

    AssessmentViewModel assessmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        setTitle("Assessments");
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView assessmentRecyclerView = findViewById(R.id.assessmentRecyclerView);
        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        assessmentRecyclerView.setAdapter(assessmentAdapter);
        assessmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        assessmentViewModel.getAllAssessments().observe(this,assessmentAdapter::setAssessments);

        FloatingActionButton addAssessmentFAB = findViewById(R.id.addAssessmentFAB);
        addAssessmentFAB.setOnClickListener(this::addAssessment);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(AssessmentActivity.this, MainActivity.class);
        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }

    public void addAssessment(View view){
        Intent intent = new Intent(AssessmentActivity.this,AssessmentAddEditActivity.class);
        startActivity(intent);
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