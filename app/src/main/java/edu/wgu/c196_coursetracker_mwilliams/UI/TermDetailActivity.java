package edu.wgu.c196_coursetracker_mwilliams.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;
import edu.wgu.c196_coursetracker_mwilliams.UI.Fragments.CourseDetailFragment;
import edu.wgu.c196_coursetracker_mwilliams.UI.Transformers.ZoomOutPageTransformer;

public class TermDetailActivity extends FragmentActivity {
    CourseViewModel courseViewModel;
    private ViewPager2 courseViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        courseViewPager = findViewById(R.id.courseViewPager);
        ScreenSlidePagerAdapter fragmentStateAdapter = new ScreenSlidePagerAdapter(this);
        courseViewPager.setPageTransformer(new ZoomOutPageTransformer());
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, fragmentStateAdapter::setCourses);

    }



    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        private List<CourseEntity> courses = new ArrayList<>();

        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return new CourseDetailFragment(courses.get(position));
        }

        @Override
        public int getItemCount() {
            return courses.size();
        }

        public void setCourses(List<CourseEntity> courses){
            this.courses = courses;
            notifyDataSetChanged();

        }
    }


}