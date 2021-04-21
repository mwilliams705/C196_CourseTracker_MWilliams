package edu.wgu.c196_coursetracker_mwilliams.Database.Instructor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class InstructorViewModel extends AndroidViewModel {
    private final InstructorRepository repository;
    private final LiveData<List<InstructorEntity>> allInstructors;

    public InstructorViewModel(@NonNull Application application) {
        super(application);
        repository = new InstructorRepository(application);
        allInstructors = repository.getAllInstructors();

    }


    public LiveData<List<InstructorEntity>> getAllInstructors(){return allInstructors;}
    public InstructorEntity getInstructorByID(int instructorID){
        return repository.getInstructorByID(instructorID);
    }
}
