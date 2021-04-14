package edu.wgu.c196_coursetracker_mwilliams.Database.Course;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;

@Dao
public interface CourseDAO {
//    @Insert (onConflict = OnConflictStrategy.REPLACE)
    @Update
    void insertCourse(CourseEntity courseEntity);

    @Query("DELETE FROM courses")
    void deleteAllCourses();

    @Query("SELECT * FROM COURSES")
    LiveData<List<CourseEntity> > getAllCourses();

    @Delete
    void deleteCourse(CourseEntity courseEntity);

    @Query("SELECT * FROM COURSES WHERE course_id = :courseID")
    CourseEntity getCourseByID(int courseID);

    @Query("SELECT COUNT(*) FROM COURSES")
    int getCourseCount();
}
