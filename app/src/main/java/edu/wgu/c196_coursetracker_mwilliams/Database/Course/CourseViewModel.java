package edu.wgu.c196_coursetracker_mwilliams.Database.Course;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private final CourseRepository repository;
    private final LiveData<List<CourseEntity>> allCourses;

    public CourseViewModel(@NonNull Application application) {

        super(application);
        repository = new CourseRepository(application);
        allCourses = repository.getAllCourses();
    }

    public LiveData<List<CourseEntity>> getAllCourses(){return allCourses;}

    public void insertCourse(CourseEntity courseEntity){repository.insertCourse(courseEntity);}

    public int courseCount(){return repository.getCourseCount();}
}
