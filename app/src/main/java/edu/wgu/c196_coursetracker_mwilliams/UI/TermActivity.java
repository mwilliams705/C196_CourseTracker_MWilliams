package edu.wgu.c196_coursetracker_mwilliams.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.wgu.c196_coursetracker_mwilliams.Database.CourseTrackerRepository;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.TermAdapter;

public class TermActivity extends AppCompatActivity {
    private CourseTrackerRepository courseTrackerRepository;
    TermAdapter termAdapter = new TermAdapter(this);
    RecyclerView termRecyclerView = findViewById(R.id.termRecyclerView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        termRecyclerView.setAdapter(termAdapter);
        termRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(courseTrackerRepository.getAllTerms());
    }

    public void addTerm(View view){
        Intent intent = new Intent(TermActivity.this,TermDetailActivity.class);
        startActivity(intent);
    }

    public void updateTerm(View view){
        Intent intent = new Intent(TermActivity.this,TermDetailActivity.class);

        startActivity(intent);
    }

}