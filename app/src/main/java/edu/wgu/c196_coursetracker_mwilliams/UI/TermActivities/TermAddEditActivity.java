package edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_add_edit);
        Intent intent = getIntent();
//        TODO: Start here tomorrow!!!!!!!
        EditText termNameEditText = findViewById(R.id.termNameEditText);
        EditText startEditTextDate = findViewById(R.id.startEditTextDate);
        EditText endEditTextDate = findViewById(R.id.endEditTextDate);
        Button saveTermBtn = findViewById(R.id.saveTermBtn);



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
                Intent intentSave = new Intent(TermAddEditActivity.this,TermDetailActivity.class);
                intentSave.putExtra("termID", term.getTerm_id());
                startActivity(intentSave);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if (termID == null){
            intent = new Intent(TermAddEditActivity.this, TermActivity.class);
        }else {
            intent = new Intent(TermAddEditActivity.this, TermDetailActivity.class);
            intent.putExtra("termID",termID);
            System.out.println(termID);
        }
        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }
}