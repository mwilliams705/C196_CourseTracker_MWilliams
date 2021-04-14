//package edu.wgu.c196_coursetracker_mwilliams.Database;
//
//import android.app.Application;
//
//import androidx.lifecycle.LiveData;
//
//import java.util.List;
//
//import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentDAO;
//import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseDAO;
//import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorDAO;
//import edu.wgu.c196_coursetracker_mwilliams.Database.Note.NoteDAO;
//import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermDAO;
//import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentEntity;
//import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;
//import edu.wgu.c196_coursetracker_mwilliams.Database.Instructor.InstructorEntity;
//import edu.wgu.c196_coursetracker_mwilliams.Database.Note.NoteEntity;
//import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;
//
//public class CourseTrackerRepository {
//
//    private CourseDAO courseDAO;
//    private AssessmentDAO assessmentDAO;
//    private InstructorDAO instructorDAO;
//    private NoteDAO noteDAO;
//    private List<TermEntity> allTerms;
//    private List<CourseEntity> allCourses;
//    private List<AssessmentEntity> allAssessments;
//    private List<InstructorEntity> allInstructors;
//    private List<NoteEntity> allNotes;
//    private int termID;
//
//    public CourseTrackerRepository(Application application){
//        CourseTrackerDatabase db=CourseTrackerDatabase.getDatabase(application);
//        termDAO=db.termDAO();
//        courseDAO=db.courseDAO();
//        assessmentDAO=db.assessmentDAO();
//        instructorDAO=db.instructorDAO();
//        noteDAO=db.noteDAO();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public List<CourseEntity> getAllCourses() {
//        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
//            allCourses=courseDAO.getAllCourses();
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return allCourses;
//    }
//
//    public List<AssessmentEntity> getAllAssessments() {
//        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
//            allAssessments=assessmentDAO.getAllAssessments();
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return allAssessments;
//    }
//
//    public List<InstructorEntity> getAllInstructors() {
//        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
//            allInstructors=instructorDAO.getAllInstructors();
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return allInstructors;
//    }
//
//    public List<NoteEntity> getAllNotes() {
//        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
//            allNotes=noteDAO.getAllNotes();
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return allNotes;
//    }
//
//    public void insert(TermEntity termEntity){
//        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
//            termDAO.insert(termEntity);
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void insert(CourseEntity courseEntity){
//        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
//            courseDAO.insert(courseEntity);
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void insert(AssessmentEntity assessmentEntity){
//        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
//            assessmentDAO.insert(assessmentEntity);
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void insert(InstructorEntity instructorEntity){
//        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
//            instructorDAO.insert(instructorEntity);
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void insert(NoteEntity noteEntity){
//        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
//            noteDAO.insert(noteEntity);
//        });
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void delete (TermEntity termEntity) {
//        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
//            termDAO.delete(termEntity);
//        });
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void delete (CourseEntity courseEntity) {
//        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
//            courseDAO.delete(courseEntity);
//        });
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void delete (AssessmentEntity assessmentEntity) {
//        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
//            assessmentDAO.delete(assessmentEntity);
//        });
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void delete (InstructorEntity instructorEntity) {
//        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
//            instructorDAO.delete(instructorEntity);
//        });
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void delete (NoteEntity noteEntity) {
//        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
//            noteDAO.delete(noteEntity);
//        });
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
