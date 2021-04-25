package edu.wgu.c196_coursetracker_mwilliams.UI.InstructorActivities;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Objects;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.InstructorAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.MainActivity;

public class InstructorActivity extends AppCompatActivity {
    InstructorViewModel instructorViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        FloatingActionButton addInstructorFAB = findViewById(R.id.addInstructorFAB);
        RecyclerView instructorRecyclerView = findViewById(R.id.instructorRecyclerView);
        InstructorAdapter instructorAdapter = new InstructorAdapter(this);

        setTitle("Instructors");
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        instructorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        instructorRecyclerView.setAdapter(instructorAdapter);

        instructorViewModel = new ViewModelProvider(this).get(InstructorViewModel.class);
        instructorViewModel.getAllInstructors().observe(this, instructorAdapter::setInstructors);

        addInstructorFAB.setOnClickListener(this::addInstructor);






        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InstructorActivity.this);
                builder.setMessage("Are you sure you want to delete this instructor?")
                        .setPositiveButton("OK", (dialog, which) -> {
                            instructorViewModel.deleteInstructor(instructorAdapter.getInstructorAtPosition(viewHolder.getAdapterPosition()));
                            Toast.makeText(InstructorActivity.this, "Instructor was deleted!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(InstructorActivity.this, InstructorActivity.class);
                            startActivity(intent);
                        }).setNegativeButton("Cancel", (dialog, which) -> {
                    Intent intent = new Intent(InstructorActivity.this, InstructorActivity.class);
                    startActivity(intent);
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }).attachToRecyclerView(instructorRecyclerView);

    }

    private void addInstructor(View view){
        Intent intent = new Intent(InstructorActivity.this, InstructorAddEditActivity.class);
        startActivity(intent);

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

        }else {
            Intent intent = new Intent(InstructorActivity.this,MainActivity.class);
            startActivity(intent);
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