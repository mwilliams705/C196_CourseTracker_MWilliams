package edu.wgu.c196_coursetracker_mwilliams.Database.Assessment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AssessmentEntity assessmentEntity);

    @Query("DELETE FROM ASSESSMENTS")
    void deleteAllAssessments();

    @Query("SELECT * FROM ASSESSMENTS")
    LiveData<List<AssessmentEntity>> getAllAssessments();

    @Query("SELECT * FROM ASSESSMENTS where course_id = :id")
    LiveData<List<AssessmentEntity>> getAllAssessmentsByCourseID(int id);

    @Query("select * from assessments where assessment_id = :id")
    AssessmentEntity getAssessmentByID(int id);

    @Delete
    void delete(AssessmentEntity assessmentEntity);
}
