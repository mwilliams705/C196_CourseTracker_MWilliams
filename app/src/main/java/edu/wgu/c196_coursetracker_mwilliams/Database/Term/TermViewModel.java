package edu.wgu.c196_coursetracker_mwilliams.Database.Term;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.CourseTrackerRepository;


public class TermViewModel extends AndroidViewModel {
    private CourseTrackerRepository repository;
    private final List<TermEntity> allTerms;

    public TermViewModel(@NonNull Application application) {
        super(application);
        repository = new CourseTrackerRepository(application);
        allTerms = repository.getAllTerms();
    }

    List<TermEntity> getAllTerms() {
        return allTerms;
    }

    public void insert(TermEntity termEntity){repository.insert(termEntity);}

}
