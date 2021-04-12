package edu.wgu.c196_coursetracker_mwilliams.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Entity.TermEntity;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TermEntity termEntity);

    @Query("SELECT * FROM terms")
    List<TermEntity> getAllTerms();

    @Query("DELETE FROM terms")
    void deleteAllTerms();

    @Delete
    void delete(TermEntity termEntity);
}
