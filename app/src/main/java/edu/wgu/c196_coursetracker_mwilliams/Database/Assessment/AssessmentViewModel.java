package edu.wgu.c196_coursetracker_mwilliams.Database.Assessment;



import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class AssessmentViewModel extends AndroidViewModel {

    private final AssessmentRepository repository;
    private final LiveData<List<AssessmentEntity>> allAssessments;

    public AssessmentViewModel(@NotNull Application application) {
        super(application);
        repository = new AssessmentRepository(application);
        allAssessments = repository.getAllAssessments();
    }

    public LiveData<List<AssessmentEntity>> getAllAssessments(){
        return allAssessments;
    }

    public LiveData<List<AssessmentEntity>> getAllAssessmentsByCourseID(int id){
        return repository.getAllAssessmentByCourseID(id);
    }

    public void updateAssessment(AssessmentEntity assessmentEntity){
        repository.updateAssessment(assessmentEntity);
    }

    public void insertAssessment(AssessmentEntity assessmentEntity){
        repository.insertAssessment(assessmentEntity);
    }

    public AssessmentEntity getAssessmentByID(int assessmentID){
        return repository.getAssessmentByID(assessmentID);
    }

    public void deleteAssessment(AssessmentEntity assessmentEntity){
        repository.deleteAssessment(assessmentEntity);
    }
}