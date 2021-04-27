package edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.MyNotificationBroadcastReceiver;

public class AssessmentDetailActivity extends AppCompatActivity {
    public static int alertID;
    Date assessmentDate;
    AssessmentViewModel assessmentViewModel;
    AssessmentEntity assessmentEntity;
    int assessmentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment_detail);

        Intent intent = getIntent();
        setAssessmentID(intent.getIntExtra("assessmentID",0));
        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        
        assessmentEntity = assessmentViewModel.getAssessmentByID(assessmentID);
        setTitle(assessmentEntity.getAssessment_title());
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton editAssessmentFAB = findViewById(R.id.editAssessmentFAB);

        editAssessmentFAB.setOnClickListener(v->{
            Intent editIntent = new Intent(AssessmentDetailActivity.this,AssessmentAddEditActivity.class);
            editIntent.putExtra("assessmentID",assessmentID);
            startActivity(editIntent);
        });


        TextView assessmentTypeTextView = findViewById(R.id.assessmentTypeTextView);
        if (assessmentEntity.isOA()){
            assessmentTypeTextView.setText("OA");
        }
        else assessmentTypeTextView.setText("PA");


        TextView assessmentDateText = findViewById(R.id.assessmentDateText);
        assessmentDateText.setText(assessmentEntity.getAssessment_date());

        DateFormat formatter = new SimpleDateFormat("MM/dd/yy", Locale.US);
        Date dateObject;


        try {
            String textDate=assessmentDateText.getText().toString();
            Log.d("Text date", textDate);
            dateObject = formatter.parse(textDate);
            assessmentDate = dateObject;
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assessment_detail_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.delete){

            AlertDialog.Builder builder = new AlertDialog.Builder(AssessmentDetailActivity.this);
            builder.setMessage("Are you sure you want to delete "+ assessmentEntity.getAssessment_title() +"?")
                    .setPositiveButton("OK", (dialog, which) -> {
                        assessmentViewModel.deleteAssessment(assessmentEntity);
                        Toast.makeText(AssessmentDetailActivity.this, assessmentEntity.getAssessment_title()+" Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AssessmentDetailActivity.this, AssessmentActivity.class);
                        startActivity(intent);
                    }).setNegativeButton("Cancel", (dialog, which) -> {
                Intent intent = new Intent(AssessmentDetailActivity.this, AssessmentDetailActivity.class);
                startActivity(intent);
            });
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }
        if (id==R.id.set_notification){

            Toast.makeText(this,"Assessment Notification Added!", Toast.LENGTH_SHORT).show();

            Intent setReminderIntent = new Intent(AssessmentDetailActivity.this, MyNotificationBroadcastReceiver.class);
            setReminderIntent.putExtra("key", "Assessment Today: " + assessmentEntity.getAssessment_title());
            PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetailActivity.this,++alertID,setReminderIntent,0);
            AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.set(AlarmManager.RTC_WAKEUP, assessmentDate.getTime() ,sender);
            return true;
        }
        if (id==android.R.id.home){
            Intent intent = new Intent(AssessmentDetailActivity.this, AssessmentActivity.class);
            startActivity(intent);
            return true;
        }
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