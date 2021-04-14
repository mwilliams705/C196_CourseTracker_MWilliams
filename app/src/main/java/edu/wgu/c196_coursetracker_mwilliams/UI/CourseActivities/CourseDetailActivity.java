package edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toolbar;

import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermDetailActivity;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);


        Toolbar termDetailToolbar = findViewById(R.id.courseDetailToolbar);


        termDetailToolbar.setTitle("Term Detail (update to show term name)");
        termDetailToolbar.setSubtitle("Term Dates: (update to show term start and end dates");
        termDetailToolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(CourseDetailActivity.this, TermDetailActivity.class);
            startActivity(intent);
        });
    }
}