package edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.CourseAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.MainActivity;

public class CourseActivity extends AppCompatActivity {

    CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        CourseAdapter courseAdapter = new CourseAdapter(this);
        RecyclerView courseRecyclerView = findViewById(R.id.instructorRecyclerView);
        FloatingActionButton addCourseFAB = findViewById(R.id.addInstructorFAB);

        courseRecyclerView.setAdapter(courseAdapter);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, courseAdapter::setCourses);
        addCourseFAB.setOnClickListener(this::addCourse);



        setTitle("Courses");
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this);
                builder.setMessage("Are you sure you want to delete this course?")
                        .setPositiveButton("OK", (dialog, which) -> {
                            courseViewModel.deleteCourse(courseAdapter.getCourseAtPosition(viewHolder.getAdapterPosition()));
                            Toast.makeText(CourseActivity.this, "Course was deleted!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CourseActivity.this, CourseActivity.class);
                            startActivity(intent);
                        }).setNegativeButton("Cancel", (dialog, which) -> {
                            Intent intent = new Intent(CourseActivity.this, CourseActivity.class);
                            startActivity(intent);
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }).attachToRecyclerView(courseRecyclerView);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.delete){
            Toast.makeText(this,"Swipe to delete", Toast.LENGTH_LONG).show();

        }if(id == android.R.id.home) {
            Intent intent = new Intent(CourseActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void addCourse(View view){
        Intent intent = new Intent(CourseActivity.this,CourseAddEditActivity.class);

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