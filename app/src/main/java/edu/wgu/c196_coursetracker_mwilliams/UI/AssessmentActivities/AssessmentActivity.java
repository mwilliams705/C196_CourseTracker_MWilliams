package edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

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

        RecyclerView assessmentRecyclerView = findViewById(R.id.instructorRecyclerView);
        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        assessmentRecyclerView.setAdapter(assessmentAdapter);
        assessmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        assessmentViewModel.getAllAssessments().observe(this,assessmentAdapter::setAssessments);

        FloatingActionButton addAssessmentFAB = findViewById(R.id.addInstructorFAB);
        addAssessmentFAB.setOnClickListener(this::addAssessment);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AssessmentActivity.this);
                builder.setMessage("Are you sure you want to delete this assessment?")
                        .setPositiveButton("OK", (dialog, which) -> {
                            assessmentViewModel.deleteAssessment(assessmentAdapter.getAssessmentAtPosition(viewHolder.getAdapterPosition()));
                            Toast.makeText(AssessmentActivity.this, "Assessment was deleted!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AssessmentActivity.this, AssessmentActivity.class);
                            startActivity(intent);
                        }).setNegativeButton("Cancel", (dialog, which) -> {
                    Intent intent = new Intent(AssessmentActivity.this, AssessmentActivity.class);
                    startActivity(intent);
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }).attachToRecyclerView(assessmentRecyclerView);


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