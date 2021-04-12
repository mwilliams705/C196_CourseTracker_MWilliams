package edu.wgu.c196_coursetracker_mwilliams.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.wgu.c196_coursetracker_mwilliams.Database.CourseTrackerDatabase;
import edu.wgu.c196_coursetracker_mwilliams.Database.CourseTrackerRepository;
import edu.wgu.c196_coursetracker_mwilliams.R;

public class MainActivity extends AppCompatActivity {

    private CourseTrackerRepository courseTrackerRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courseTrackerRepository = new CourseTrackerRepository(getApplication());
        System.out.println(courseTrackerRepository.getAllTerms().toString());

    }
}