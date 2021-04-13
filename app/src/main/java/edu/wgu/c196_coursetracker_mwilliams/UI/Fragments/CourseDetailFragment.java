package edu.wgu.c196_coursetracker_mwilliams.UI.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.wgu.c196_coursetracker_mwilliams.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDetailFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String COURSE_TITLE = "param1";
    private static final String COURSE_START = "param2";
    private static final String COURSE_END = "param3";
    private static final String COURSE_STATUS = "param4";
    private static final String COURSE_NOTE = "param5";


    private String courseTitle;
    private String courseStart;
    private String courseEnd;
    private String courseStatus;
    private String courseNote;



    public CourseDetailFragment() {
        // Required empty public constructor
    }



    public static CourseDetailFragment newInstance(String courseTitle, Long courseStart, Long courseEnd, String courseStatus, String courseNote) {
        CourseDetailFragment fragment = new CourseDetailFragment();
        Bundle args = new Bundle();
        args.putString(COURSE_TITLE, courseTitle);
        args.putString(COURSE_START, courseStart.toString());
        args.putString(COURSE_END, courseEnd.toString());
        args.putString(COURSE_STATUS, courseStatus);
        args.putString(COURSE_NOTE, courseNote);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            courseTitle = getArguments().getString(COURSE_TITLE);
            courseStart = getArguments().getString(COURSE_START);
            courseEnd = getArguments().getString(COURSE_END);
            courseStatus = getArguments().getString(COURSE_STATUS);
            courseNote = getArguments().getString(COURSE_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_course_detail, container, false);
        TextView courseNameTextView = v.findViewById(R.id.courseNameTextView);
        TextView courseStartTextView = v.findViewById(R.id.courseStartTextView);
        TextView courseEndTextView = v.findViewById(R.id.courseEndTextView);
        TextView courseStatusTextView = v.findViewById(R.id.courseStatusTextView);
        TextView courseNoteTextView = v.findViewById(R.id.courseNoteTextView);

        courseNameTextView.setText(courseTitle);
        courseStartTextView.setText(courseStart);
        courseEndTextView.setText(courseEnd);
        courseStatusTextView.setText(courseStatus);
        courseNoteTextView.setText(courseNote);

        return v;
    }
}