package edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.TermAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities.TermDetailActivity;

public class CourseDetailActivity extends AppCompatActivity {
    CourseViewModel courseViewModel;
    AssessmentViewModel assessmentViewModel;

    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        Intent intent = getIntent();
        setCourseId(intent.getIntExtra("courseID",0));
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        CourseEntity courseEntity = courseViewModel.getCourseById(courseId);

        TextView courseStartTextView = findViewById(R.id.courseStartTextView);
        courseStartTextView.setText(courseEntity.getCourse_start());
        TextView courseEndTextView = findViewById(R.id.courseEndTextView);
        TextView courseStatusTextView = findViewById(R.id.courseStatusTextView);
        courseStatusTextView.setText(courseEntity.getCourse_status());
        courseEndTextView.setText(courseEntity.getCourse_end());
        TextView courseNoteTextView = findViewById(R.id.courseNoteTextView);
        courseNoteTextView.setText(courseEntity.getCourse_note());
        RecyclerView assessmentRecycler = findViewById(R.id.courseAssessmentRecyclerView);



        FloatingActionButton editCourseFAB = findViewById(R.id.editCourseFAB);
        editCourseFAB.setOnClickListener(v -> {
            Intent editIntent = new Intent(CourseDetailActivity.this,CourseAddEditActivity.class);
            editIntent.putExtra("courseID", courseId);
            startActivity(editIntent);
        });



        setTitle(courseEntity.getCourse_title());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);

    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(CourseDetailActivity.this,CourseActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}