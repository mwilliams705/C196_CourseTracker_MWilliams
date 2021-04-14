package edu.wgu.c196_coursetracker_mwilliams.Database.Note;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.Note.NoteEntity;

@Dao
public interface NoteDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteEntity noteEntity);

    @Query("SELECT * FROM notes")
    List<NoteEntity> getAllNotes();

    @Query("DELETE FROM notes")
    void deleteAllNotes();

    @Delete
    void delete(NoteEntity noteEntity);

}
