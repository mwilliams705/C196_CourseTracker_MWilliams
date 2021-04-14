package edu.wgu.c196_coursetracker_mwilliams.Database.Term;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import edu.wgu.c196_coursetracker_mwilliams.Database.CourseTrackerDatabase;

public class TermRepository {
    private TermDAO termDAO;
    private LiveData<List<TermEntity>> allTerms;


    public TermRepository(Application application){
        CourseTrackerDatabase db=CourseTrackerDatabase.getDatabase(application);
        termDAO = db.termDAO();
        allTerms = termDAO.getAllTerms();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Get methods
    public LiveData<List<TermEntity>> getAllTerms(){
        CourseTrackerDatabase.dataWriteExecutor.execute(()->{
            allTerms=termDAO.getAllTerms();
        });
        return allTerms;
    }

    public TermEntity getTermByID(int termID){
        return termDAO.getTermByID(termID);
    }

    //Insert methods
    public void insertTerm(TermEntity termEntity){
        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
            termDAO.insertTerm(termEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //Delete methods
    public void deleteTerm(TermEntity termEntity) {
        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
            termDAO.deleteTerm(termEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllTerms(){
        CourseTrackerDatabase.dataWriteExecutor.execute(() -> {
            termDAO.deleteAllTerms();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
}

    public int getTermCount() {
        return termDAO.getTermCount();
    }
}
