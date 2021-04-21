package edu.wgu.c196_coursetracker_mwilliams.Database.Instructor;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.CourseTrackerDatabase;

public class InstructorRepository {
    private InstructorDAO instructorDAO;
    private LiveData<List<InstructorEntity>> allInstructors;
    private InstructorEntity instructor;

    public InstructorRepository(Application application) {
        CourseTrackerDatabase db = CourseTrackerDatabase.getDatabase(application);
        instructorDAO = db.instructorDAO();
        allInstructors = instructorDAO.getAllInstructors();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


//    Get Methods
    public LiveData<List<InstructorEntity>> getAllInstructors(){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            allInstructors=instructorDAO.getAllInstructors();
        });
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allInstructors;
    }

    public InstructorEntity getInstructorByID(int instructorID){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            instructor = instructorDAO.getInstructorByID(instructorID);
        });

        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return instructor;
    }

    public void insertInstructor(InstructorEntity instructorEntity){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            instructorDAO.insert(instructorEntity);
        });


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateInstructor(InstructorEntity instructorEntity){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            instructorDAO.updateInstructor(instructorEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
