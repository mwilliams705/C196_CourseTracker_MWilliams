package edu.wgu.c196_coursetracker_mwilliams.Database.Assessment;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.CourseTrackerDatabase;

public class AssessmentRepository {
    private final AssessmentDAO assessmentDAO;
    private LiveData<List<AssessmentEntity>> allAssessments;
    private AssessmentEntity assessmentEntity;

    public AssessmentRepository(Application application) {
        CourseTrackerDatabase db = CourseTrackerDatabase.getDatabase(application);
        assessmentDAO = db.assessmentDAO();
        allAssessments = assessmentDAO.getAllAssessments();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<AssessmentEntity>> getAllAssessments(){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            allAssessments = assessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allAssessments;
    }

    public LiveData<List<AssessmentEntity>> getAllAssessmentByCourseID(int id){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            allAssessments = assessmentDAO.getAllAssessmentsByCourseID(id);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allAssessments;
    }

    public AssessmentEntity getAssessmentByID(int id){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            assessmentEntity = assessmentDAO.getAssessmentByID(id);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return assessmentEntity;
    }

    public void deleteAssessment(AssessmentEntity assessmentEntity){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            assessmentDAO.delete(assessmentEntity);
        });

    }

    public void insertAssessment(AssessmentEntity assessmentEntity){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            assessmentDAO.insert(assessmentEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateAssessment(AssessmentEntity assessmentEntity){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            assessmentDAO.updateAssessment(assessmentEntity);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
