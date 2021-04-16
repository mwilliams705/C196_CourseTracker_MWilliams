package edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseRepository;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.CourseAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.AssessmentActivities.AssessmentActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.MainActivity;

public class CourseActivity extends AppCompatActivity {

    CourseViewModel courseViewModel;
    LiveData<List<CourseEntity>> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CourseRepository courseRepository = new CourseRepository(getApplication());

        courses = courseRepository.getAllCourses();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        CourseAdapter courseAdapter = new CourseAdapter(this);
        RecyclerView courseRecyclerView = findViewById(R.id.courseRecyclerView);
        FloatingActionButton addCourseFAB = findViewById(R.id.addCourseFAB);

        courseRecyclerView.setAdapter(courseAdapter);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, courseAdapter::setCourses);
        addCourseFAB.setOnClickListener(this::addCourse);



        setTitle("Courses");
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(CourseActivity.this, MainActivity.class);
        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }

    public void addCourse(View view){
        Intent intent = new Intent(CourseActivity.this,CourseAddEditActivity.class);
        startActivity(intent);
    }
}