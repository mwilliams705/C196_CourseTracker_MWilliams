package edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.AssessmentAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities.AssessmentDetailActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.MainActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.MyNotificationBroadcastReceiver;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermDetailActivity;

public class CourseDetailActivity extends AppCompatActivity {
    CourseViewModel courseViewModel;
    InstructorViewModel instructorViewModel;
    AssessmentViewModel assessmentViewModel;
    AssessmentAdapter assessmentAdapter;
    Date startDate, endDate;
    public static int alertID;

    CourseEntity courseEntity;

    Bundle bundle;

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
        ImageButton notifyStartBtn = findViewById(R.id.notifyStartBtn);
        ImageButton notifyEndBtn = findViewById(R.id.notifyEndBtn);
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


        DateFormat formatter = new SimpleDateFormat("MM/dd/yy", Locale.US);


        try {
            String startDateText = courseStartTextView.getText().toString();
            String endDateText = courseEndTextView.getText().toString();
            startDate = formatter.parse(startDateText);
            endDate = formatter.parse(endDateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        FloatingActionButton editCourseFAB = findViewById(R.id.editAssessmentFAB);
        editCourseFAB.setOnClickListener(v -> {
            Intent editIntent = new Intent(CourseDetailActivity.this,CourseAddEditActivity.class);
            editIntent.putExtra("courseID", courseId);
            editIntent.putExtra("instructorName",instructorEntity.getInstructor_name());
            startActivity(editIntent);
        });

        notifyStartBtn.setOnClickListener(v -> {

            AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent setStartReminderIntent = new Intent(CourseDetailActivity.this, MyNotificationBroadcastReceiver.class);

            setStartReminderIntent.putExtra("key", "Course " + courseEntity.getCourse_title() + " starts today!");
            PendingIntent startSender = PendingIntent.getBroadcast(CourseDetailActivity.this,++alertID,setStartReminderIntent,0);
            Toast.makeText(this,"You will be notified on the start date of this course!",Toast.LENGTH_LONG).show();
            alarmManager.set(AlarmManager.RTC_WAKEUP, startDate.getTime() ,startSender);

        });

        // TODO: 4/28/21 This does not work correctly!!!!!!!!!!!!!! 
        notifyEndBtn.setOnClickListener(v -> {

            AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent setEndReminderIntent = new Intent(CourseDetailActivity.this, MyNotificationBroadcastReceiver.class);
            setEndReminderIntent.putExtra("key", "Course " + courseEntity.getCourse_title() + " ends today!");

            PendingIntent endSender = PendingIntent.getBroadcast(CourseDetailActivity.this,++alertID,setEndReminderIntent,0);
            Toast.makeText(this,"You will be notified on the end date of this course!",Toast.LENGTH_LONG).show();
            alarmManager.set(AlarmManager.RTC_WAKEUP, endDate.getTime() ,endSender);

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
            shareNoteIntent.putExtra(Intent.EXTRA_TITLE,"Note for " + courseEntity.getCourse_title());
            shareNoteIntent.putExtra(Intent.EXTRA_SUBJECT,"Note for " + courseEntity.getCourse_title());
            startActivity(Intent.createChooser(shareNoteIntent,"Share Using?"));
            return true;
        }
        if(id == android.R.id.home) {
            Intent intent = new Intent(CourseDetailActivity.this, CourseActivity.class);
            startActivity(intent);
            return true;
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