package edu.wgu.c196_coursetracker_mwilliams.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.sql.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.wgu.c196_coursetracker_mwilliams.DAO.AssessmentDAO;
import edu.wgu.c196_coursetracker_mwilliams.DAO.CourseDAO;
import edu.wgu.c196_coursetracker_mwilliams.DAO.InstructorDAO;
import edu.wgu.c196_coursetracker_mwilliams.DAO.NoteDAO;
import edu.wgu.c196_coursetracker_mwilliams.DAO.TermDAO;
import edu.wgu.c196_coursetracker_mwilliams.Entity.AssessmentEntity;
import edu.wgu.c196_coursetracker_mwilliams.Entity.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Entity.InstructorEntity;
import edu.wgu.c196_coursetracker_mwilliams.Entity.NoteEntity;
import edu.wgu.c196_coursetracker_mwilliams.Entity.TermEntity;

@Database(entities = {TermEntity.class, InstructorEntity.class, CourseEntity.class, AssessmentEntity.class, NoteEntity.class},version = 1, exportSchema = false)
public abstract class CourseTrackerDatabase extends RoomDatabase{

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    public abstract InstructorDAO instructorDAO();
    public abstract NoteDAO noteDAO();
    public static final int NUM_OF_THREADS = 4;

    static final ExecutorService dataWriteExecutor =
            Executors.newFixedThreadPool(NUM_OF_THREADS);

    private static volatile CourseTrackerDatabase INSTANCE;

    static CourseTrackerDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (CourseTrackerDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CourseTrackerDatabase.class, "course_tracker_database.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            dataWriteExecutor.execute(()->{

                TermDAO termDAO = INSTANCE.termDAO();

                TermEntity termEntity = new TermEntity(
                        "Android Dev",
                        "07/01/2021",
                        "01/31/2022");
                termDAO.deleteAllTerms();
                termDAO.insert(termEntity);

            });

        }
    };

}
