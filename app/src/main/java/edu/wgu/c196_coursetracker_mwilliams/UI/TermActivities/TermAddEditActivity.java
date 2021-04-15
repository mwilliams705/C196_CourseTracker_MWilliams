package edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;

public class TermAddEditActivity extends AppCompatActivity {

    int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_add_edit);
        Intent intent = getIntent();
//        TODO: Start here tomorrow!!!!!!!
        EditText


        setTermID(intent.getIntExtra("termID",0));

        TermViewModel viewModel = new TermViewModel(getApplication());
        TermEntity term = viewModel.getTermById(termID);

        setTitle(intent.getStringExtra("header"));
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(TermAddEditActivity.this, TermDetailActivity.class);
        intent.putExtra("termID",termID);
        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }
}