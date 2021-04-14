package edu.wgu.c196_coursetracker_mwilliams.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermRepository;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.TermAdapter;

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





}