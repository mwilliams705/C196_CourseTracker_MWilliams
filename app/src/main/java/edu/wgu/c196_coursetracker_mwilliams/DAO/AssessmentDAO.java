package edu.wgu.c196_coursetracker_mwilliams.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Entity.AssessmentEntity;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AssessmentEntity assessmentEntity);

    @Query("DELETE FROM ASSESSMENTS")
    void deleteAllAssessments();

    @Query("SELECT * FROM ASSESSMENTS")
    List<AssessmentEntity> getAllAssessments();

    @Delete
    void delete(AssessmentEntity assessmentEntity);
}
