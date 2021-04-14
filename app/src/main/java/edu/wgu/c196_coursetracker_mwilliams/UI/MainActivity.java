package edu.wgu.c196_coursetracker_mwilliams.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities.AssessmentActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Welcome");




        Button termBtn = findViewById(R.id.termBtn);
        Button courseBtn = findViewById(R.id.courseBtn);
        Button assessmentBtn = findViewById(R.id.assessmentBtn);

        termBtn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, TermActivity.class);
            startActivity(intent);
        });

        courseBtn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, CourseActivity.class);
            startActivity(intent);
        });

        assessmentBtn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, AssessmentActivity.class);
            startActivity(intent);
        });
    }


}