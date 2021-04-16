package edu.wgu.c196_coursetracker_mwilliams.Database.Instructor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;

@Dao
public interface InstructorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InstructorEntity instructorEntity);

    @Query("DELETE FROM instructors")
    void deleteAllInstructors();

    @Query("SELECT * FROM instructors")
    LiveData<List<InstructorEntity>> getAllInstructors();

    @Delete
    void delete(InstructorEntity instructorEntity);

}
