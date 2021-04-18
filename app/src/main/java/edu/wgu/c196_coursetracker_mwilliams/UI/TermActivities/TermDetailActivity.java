package edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.CourseAdapter;

public class TermDetailActivity extends AppCompatActivity {
    TermViewModel termViewModel;
    CourseViewModel courseViewModel;

    private int termId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        Intent intent = getIntent();
        setTermId(intent.getIntExtra("termID",0));
        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        TermEntity termEntity = termViewModel.getTermById(termId);

        RecyclerView courseRecycler = findViewById(R.id.courseAssessmentRecyclerView);
        CourseAdapter courseAdapter = new CourseAdapter(this);
        FloatingActionButton addCourseFab = findViewById(R.id.editCourseFAB);


        TextView termStartTextView = findViewById(R.id.courseStartTextView);
        TextView termEndTextView = findViewById(R.id.courseEndTextView);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(TermDetailActivity.this, TermActivity.class);

        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }
}