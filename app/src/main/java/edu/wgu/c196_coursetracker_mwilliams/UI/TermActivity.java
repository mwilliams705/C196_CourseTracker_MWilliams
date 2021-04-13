package edu.wgu.c196_coursetracker_mwilliams.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.CourseTrackerRepository;
import edu.wgu.c196_coursetracker_mwilliams.Entity.TermEntity;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.TermAdapter;

public class TermActivity extends AppCompatActivity {

    List<TermEntity> terms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        CourseTrackerRepository courseTrackerRepository = new CourseTrackerRepository(getApplication());
        terms = courseTrackerRepository.getAllTerms();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        TermAdapter termAdapter = new TermAdapter(this);
        RecyclerView termRecyclerView = findViewById(R.id.termRecyclerView);
        FloatingActionButton addTermFAB = findViewById(R.id.addTermFAB);


        termRecyclerView.setAdapter(termAdapter);
        termRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(terms);

        addTermFAB.setOnClickListener(this::addTerm);

    }


    public void addTerm(View view){
        Intent intent = new Intent(TermActivity.this,TermDetailActivity.class);
        startActivity(intent);
    }





}