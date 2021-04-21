package edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;

public class TermAddEditActivity extends AppCompatActivity {

    public Integer termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_add_edit);
        Intent intent = getIntent();

        EditText termNameEditText = findViewById(R.id.instructorNameEditText);
        EditText startEditTextDate = findViewById(R.id.startEditTextDate);
        EditText endEditTextDate = findViewById(R.id.instructorEmailTextEdit);
        Button saveTermBtn = findViewById(R.id.saveInstructorBtn);



        if(intent.hasExtra("term_id")){
            setTitle("Edit Term");
            setTermID(intent.getIntExtra("term_id",0));
            TermViewModel viewModel = new TermViewModel(getApplication());
            TermEntity term = viewModel.getTermById(termID);
            termNameEditText.setText(term.getTerm_title());
            startEditTextDate.setText((term.getTerm_start()));
            endEditTextDate.setText(term.getTerm_end());


            saveTermBtn.setOnClickListener(v->{
                term.setTerm_id(termID);
                term.setTerm_title(termNameEditText.getText().toString());
                term.setTerm_start(startEditTextDate.getText().toString());
                term.setTerm_end(endEditTextDate.getText().toString());
                viewModel.updateTerm(term);
                Intent intentUpdate = new Intent(TermAddEditActivity.this,TermDetailActivity.class);
                intentUpdate.putExtra("termID", term.getTerm_id());
                startActivity(intentUpdate);
            });

        }else {
            setTitle("Add Term");
            TermViewModel viewModel = new TermViewModel(getApplication());


            saveTermBtn.setOnClickListener(v->{
                TermEntity term = new TermEntity(
                        termNameEditText.getText().toString(),
                        startEditTextDate.getText().toString(),
                        endEditTextDate.getText().toString());
                viewModel.insertTerm(term);
                Intent intentSave = new Intent(TermAddEditActivity.this,TermActivity.class);
                startActivity(intentSave);
            });
        }




        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    public void setTermID(int termID) {
        this.termID = termID;
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