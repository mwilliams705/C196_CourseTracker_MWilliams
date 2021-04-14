package edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermRepository;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.TermAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.MainActivity;

public class TermActivity extends AppCompatActivity {
    TermViewModel termViewModel;
    LiveData<List<TermEntity>> terms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TermRepository termRepository = new TermRepository(getApplication());

        terms = termRepository.getAllTerms();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        TermAdapter termAdapter = new TermAdapter(this);
        RecyclerView termRecyclerView = findViewById(R.id.termRecyclerView);
        FloatingActionButton addTermFAB = findViewById(R.id.addTermFAB);

        setTitle("Terms");
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        termRecyclerView.setAdapter(termAdapter);
        termRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);

        termViewModel.getAllTerms().observe(this, termAdapter::setTerms);

        addTermFAB.setOnClickListener(this::addTerm);

    }


    public void addTerm(View view){
        Intent intent = new Intent(TermActivity.this,TermDetailActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(TermActivity.this,MainActivity.class);
        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }


}