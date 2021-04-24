package edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermDetailActivity;

public class AssessmentDetailActivity extends AppCompatActivity {
    AssessmentViewModel assessmentViewModel;

    int assessmentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment_detail);

        Intent intent = getIntent();
        setAssessmentID(intent.getIntExtra("assessmentID",0));
        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        
        AssessmentEntity assessmentEntity = assessmentViewModel.getAssessmentByID(assessmentID);
        setTitle(assessmentEntity.getAssessment_title());
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton editAssessmentFAB = findViewById(R.id.editAssessmentFAB);

        editAssessmentFAB.setOnClickListener(v->{
            Intent editIntent = new Intent(AssessmentDetailActivity.this,AssessmentAddEditActivity.class);
            editIntent.getIntExtra("assessmentID", assessmentID);
            startActivity(editIntent);
        });


        TextView assessmentTypeTextView = findViewById(R.id.assessmentTypeTextView);
        if (assessmentEntity.isOA()){
            assessmentTypeTextView.setText("OA");
        }
        else assessmentTypeTextView.setText("PA");

        TextView assessmentDateText = findViewById(R.id.assessmentDateText);
        assessmentDateText.setText(assessmentEntity.getAssessment_date());



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(AssessmentDetailActivity.this, AssessmentActivity.class);

        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }


    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
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