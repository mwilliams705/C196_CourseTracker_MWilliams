package edu.wgu.c196_coursetracker_mwilliams.Database.Course;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;

@Dao
public interface CourseDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseEntity courseEntity);

    @Query("DELETE FROM courses")
    void deleteAllCourses();

    @Query("SELECT * FROM COURSES")
    List<CourseEntity> getAllCourses();

    @Delete
    void delete(CourseEntity courseEntity);
}
