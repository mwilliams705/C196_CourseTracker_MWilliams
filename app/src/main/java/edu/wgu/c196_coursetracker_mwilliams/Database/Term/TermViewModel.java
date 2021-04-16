package edu.wgu.c196_coursetracker_mwilliams.Database.Term;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class TermViewModel extends AndroidViewModel {
    private final TermRepository repository;
    private final LiveData<List<TermEntity>> allTerms;

    public TermViewModel(@NonNull Application application) {
        super(application);
        repository = new TermRepository(application);
        allTerms = repository.getAllTerms();
    }

    public LiveData<List<TermEntity>> getAllTerms() {
        return allTerms;
    }

    public void insertTerm(TermEntity termEntity){repository.insertTerm(termEntity);}

    public int termCount(){
        return repository.getTermCount();
    }

    public TermEntity getTermById(int termId){return repository.getTermByID(termId);}

    public void updateTerm(TermEntity termEntity){
        repository.updateTerm(termEntity);
    }
}
