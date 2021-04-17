package edu.wgu.c196_coursetracker_mwilliams.Database.Term;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "terms")
public class TermEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "term_id")
    private int term_id;
    @ColumnInfo(name = "title")
    private String term_title;
    @ColumnInfo(name = "start")
    private String term_start;
    @ColumnInfo(name = "end")
    private String term_end;


    public TermEntity(String term_title, String term_start, String term_end) {
        this.term_title = term_title;
        this.term_start = term_start;
        this.term_end = term_end;
    }

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public String getTerm_title() {
        return term_title;
    }

    public void setTerm_title(String term_title) {
        this.term_title = term_title;
    }

    public String getTerm_start() {
        return term_start;
    }

    public void setTerm_start(String term_start) {
        this.term_start = term_start;
    }

    public String getTerm_end() {
        return term_end;
    }

    public void setTerm_end(String term_end) {
        this.term_end = term_end;
    }

    @Override
    public String toString() {
        return term_title;
    }
}
