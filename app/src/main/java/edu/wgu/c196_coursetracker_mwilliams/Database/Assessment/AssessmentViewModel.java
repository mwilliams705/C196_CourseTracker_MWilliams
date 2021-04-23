package edu.wgu.c196_coursetracker_mwilliams.Database.Assessment;

import androidx.lifecycle.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class AssessmentViewModel extends AndroidViewModel {

    private AssessmentRepository repository;
    private LiveData<List<AssessmentEntity>> allAssessments;
    private AssessmentEntity assessment;

    public AssessmentViewModel(@NotNull Application application) {
        super(application);
        repository = new AssessmentRepository(application);
        allAssessments = repository.getAllAssessments();
    }

    public LiveData<List<AssessmentEntity>> getAllAssessments(){
        return repository.getAllAssessments();
    }

    public LiveData<List<AssessmentEntity>> getAllAssessmentsByCourseID(int id){
        return repository.getAllAssessmentByCourseID(id);
    }

    public AssessmentEntity getAssessmentByID(int assessmentID){
        return repository.getAssessmentByID(assessmentID);
    }

    public void deleteAssessment(AssessmentEntity assessmentEntity){
        repository.deleteAssessment(assessmentEntity);
    }
}