package edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.CourseAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.MainActivity;

public class TermDetailActivity extends AppCompatActivity {
    TermViewModel termViewModel;
    CourseViewModel courseViewModel;
    TermEntity termEntity;

    CourseAdapter courseAdapter;

    List<CourseEntity> coursesForCount = new ArrayList<>();
    private int termId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        Intent intent = getIntent();
        setTermId(intent.getIntExtra("termID",0));
        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        termEntity = termViewModel.getTermById(termId);

        RecyclerView courseRecycler = findViewById(R.id.courseAssessmentRecyclerView);
        courseAdapter = new CourseAdapter(this);
        FloatingActionButton addCourseFab = findViewById(R.id.editAssessmentFAB);



        TextView termStartTextView = findViewById(R.id.assessmentTypeTextView);
        TextView termEndTextView = findViewById(R.id.assessmentDateText);
        termStartTextView.setText(termEntity.getTerm_start());
        termEndTextView.setText(termEntity.getTerm_end());

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCoursesByTermId(termId).observe(this, courseAdapter::setCourses);



        courseRecycler.setAdapter(courseAdapter);
        courseRecycler.setLayoutManager(new LinearLayoutManager(this));

        addCourseFab.setOnClickListener(this::editTerm);

        setTitle(termEntity.getTerm_title());
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

    public void editTerm(View view){
        Intent intent = new Intent(TermDetailActivity.this, TermAddEditActivity.class);
        intent.putExtra("term_id",termId);
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

            int count = courseAdapter.getItemCount();
            Log.d("Count",String.valueOf(count));


            if (count == 0){
                Log.d("Delete menu clicked","count was " + count + " and can be deleted");
                AlertDialog.Builder builder = new AlertDialog.Builder(TermDetailActivity.this);
                builder.setMessage("Are you sure you want to delete "+ termEntity.getTerm_title() +"?")
                        .setPositiveButton("OK", (dialog, which) -> {
                            termViewModel.deleteTerm(termEntity);
                            Toast.makeText(TermDetailActivity.this, termEntity.getTerm_title()+" Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TermDetailActivity.this, TermActivity.class);
                            startActivity(intent);
                        }).setNegativeButton("Cancel", (dialog, which) -> {
                    Intent intent = new Intent(TermDetailActivity.this, TermDetailActivity.class);
                    intent.putExtra("termID",termId);
                    startActivity(intent);
                });
                AlertDialog alert = builder.create();
                alert.show();
            }if (count>0){
                Log.d("Delete menu clicked","count was " + count + " and cant be deleted");
                AlertDialog.Builder builder = new AlertDialog.Builder(TermDetailActivity.this);
                builder.setMessage("Term "+ termEntity.getTerm_title() +" can't be deleted because it has " + count +" courses associated with it. Delete courses and try again.")
                        .setPositiveButton("OK", (dialog, which) -> {
                            Intent intent = new Intent(TermDetailActivity.this, TermDetailActivity.class);
                            intent.putExtra("termID",termId);
                            startActivity(intent);
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }




        }
        if (id==android.R.id.home){
            Intent intent = new Intent(TermDetailActivity.this, TermActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTermId(int termId) {
        this.termId = termId;
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