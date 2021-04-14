package edu.wgu.c196_coursetracker_mwilliams.Database.Term;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(TermEntity termEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTerms(List<TermEntity> terms);

    @Delete
    void deleteTerm(TermEntity termEntity);

    @Query("SELECT * FROM terms WHERE term_id = :termID")
    TermEntity getTermByID(int termID);

    @Query("SELECT * FROM terms ORDER BY term_start_date ASC")
    LiveData<List<TermEntity>> getAllTerms();

    @Query("DELETE FROM terms")
    int deleteAllTerms();

    @Query("SELECT COUNT(*) FROM terms")
    int getTermCount();
}
