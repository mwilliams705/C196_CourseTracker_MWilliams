package edu.wgu.c196_coursetracker_mwilliams.Database.Course;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.CourseTrackerDatabase;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;

public class CourseRepository {
    private final CourseDAO courseDAO;
    private LiveData<List<CourseEntity>> allCourses;
    private CourseEntity courseEntity;

    public CourseRepository(Application application) {
        CourseTrackerDatabase db = CourseTrackerDatabase.getDatabase(application);
        courseDAO = db.courseDAO();
        allCourses = courseDAO.getAllCourses();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    //Get methods
    public LiveData<List<CourseEntity>> getAllCoursesByTermId(int term_id){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            allCourses=courseDAO.getCoursesByTermId(term_id);
        });
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allCourses;
    }

    public LiveData<List<CourseEntity>> getAllCourses(){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            allCourses=courseDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allCourses;
    }

    public CourseEntity getCourseByID(int courseID){

        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            courseEntity = courseDAO.getCourseByID(courseID);
        });
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return courseEntity;

    }

    //Insert methods
    public void insertCourse(CourseEntity courseEntity){
        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
            courseDAO.insertCourse(courseEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

//    Update methods
    public void updateCourse(CourseEntity courseEntity){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            courseDAO.updateCourse(courseEntity);
        });
    }

    //Delete methods
    public void deleteCourse(CourseEntity CourseEntity) {
        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
            courseDAO.deleteCourse(CourseEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCourses() {
        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
            courseDAO.deleteAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCourseCount() {
        return courseDAO.getCourseCount();
    }
}
