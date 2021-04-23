package edu.wgu.c196_coursetracker_mwilliams.UI.TermActivities;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Objects;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Adapters.TermAdapter;
import edu.wgu.c196_coursetracker_mwilliams.UI.CourseActivities.CourseActivity;
import edu.wgu.c196_coursetracker_mwilliams.UI.MainActivity;

public class TermActivity extends AppCompatActivity {
    TermViewModel termViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        TermAdapter termAdapter = new TermAdapter(this);
        RecyclerView termRecyclerView = findViewById(R.id.instructorRecyclerView);
        FloatingActionButton addTermFAB = findViewById(R.id.addInstructorFAB);

        setTitle("Terms");
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_36);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        termRecyclerView.setAdapter(termAdapter);
        termRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        termViewModel.getAllTerms().observe(this, termAdapter::setTerms);

        addTermFAB.setOnClickListener(this::addTerm);





        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TermActivity.this);
                builder.setMessage("Are you sure you want to delete this term?")
                        .setPositiveButton("OK", (dialog, which) -> {
                            termViewModel.deleteTerm(termAdapter.getTermAtPosition(viewHolder.getAdapterPosition()));
                            Toast.makeText(TermActivity.this, "Term was deleted!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TermActivity.this, TermActivity.class);
                            startActivity(intent);
                        }).setNegativeButton("Cancel", (dialog, which) -> {
                    Intent intent = new Intent(TermActivity.this, TermActivity.class);
                    startActivity(intent);
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }).attachToRecyclerView(termRecyclerView);

    }




    public void addTerm(View view){
        Intent intent = new Intent(TermActivity.this,TermAddEditActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(TermActivity.this,MainActivity.class);
        startActivity(intent);


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