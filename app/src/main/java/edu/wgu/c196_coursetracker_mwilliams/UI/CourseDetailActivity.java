package edu.wgu.c196_coursetracker_mwilliams.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toolbar;

import edu.wgu.c196_coursetracker_mwilliams.R;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);


        Toolbar termDetailToolbar = findViewById(R.id.termDetailToolbar);
        TableLayout termCourseTabLayout = findViewById(R.id.termCourseTabLayout);
        ViewPager2 courseViewPager = findViewById(R.id.courseViewPager);

        termDetailToolbar.setTitle("Term Detail (update to show term name)");
        termDetailToolbar.setSubtitle("Term Dates: (update to show term start and end dates");




    }
}