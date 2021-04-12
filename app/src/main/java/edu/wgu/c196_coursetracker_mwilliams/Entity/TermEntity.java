package edu.wgu.c196_coursetracker_mwilliams.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "terms")
public class TermEntity {
    @PrimaryKey
    private int term_id;
    @ColumnInfo(name = "title")
    private String term_title;
    @ColumnInfo(name = "start")
    private Long term_start;
    @ColumnInfo(name = "end")
    private Long term_end;

    public TermEntity(int term_id, String term_title, Long term_start, Long term_end) {
        this.term_id = term_id;
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

    public Long getTerm_start() {
        return term_start;
    }

    public void setTerm_start(Long term_start) {
        this.term_start = term_start;
    }

    public Long getTerm_end() {
        return term_end;
    }

    public void setTerm_end(Long term_end) {
        this.term_end = term_end;
    }

    @Override
    public String toString() {
        return "TermEntity{" +
                "term_id=" + term_id +
                ", term_title='" + term_title + '\'' +
                ", term_start=" + term_start +
                ", term_end=" + term_end +
                '}';
    }
}
