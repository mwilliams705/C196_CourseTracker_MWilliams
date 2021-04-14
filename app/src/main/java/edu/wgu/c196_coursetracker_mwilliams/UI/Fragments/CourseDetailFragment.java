package edu.wgu.c196_coursetracker_mwilliams.UI.Fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseViewModel;
import edu.wgu.c196_coursetracker_mwilliams.R;

public class CourseDetailFragment extends Fragment {

    private TextView courseName;
    CourseViewModel viewModel;
    CourseEntity courseEntity;
    public static CourseDetailFragment newInstance(CourseEntity courseEntity) {
        return new CourseDetailFragment(courseEntity);
    }

    public CourseDetailFragment(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.course_detail_fragment, container, false);
        courseName = v.findViewById(R.id.courseName);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseName.setText(courseEntity.getCourse_title());

    }

}