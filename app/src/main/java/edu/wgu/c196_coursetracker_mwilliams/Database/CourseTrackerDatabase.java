package edu.wgu.c196_coursetracker_mwilliams.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentDAO;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseDAO;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorDAO;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermDAO;
import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;
import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;

@Database(entities = {TermEntity.class, InstructorEntity.class, CourseEntity.class, AssessmentEntity.class},version = 1, exportSchema = false)
public abstract class CourseTrackerDatabase extends RoomDatabase{

    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();
    public abstract InstructorDAO instructorDAO();
    public static final int NUM_OF_THREADS = 4;

    public static final ExecutorService dataWriteExecutor =
            Executors.newFixedThreadPool(NUM_OF_THREADS);

    private static volatile CourseTrackerDatabase INSTANCE;

    public static CourseTrackerDatabase getDatabase(final Context context){
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


//                InstructorDAO instructorDAO = INSTANCE.instructorDAO();
//                instructorDAO.deleteAllInstructors();
//                for (int j = 0; j < 9; j++) {
//                    InstructorEntity instructorEntity = new InstructorEntity(
//                            "Instructor "+j,
//                            "777-777-777"+j,
//                            "instructor"+j+"@wgu.edu"
//                    );
//                    instructorDAO.insert(instructorEntity);
//                }

            });

        }
    };

}
