package edu.wgu.c196_coursetracker_mwilliams.Database.Course;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import edu.wgu.c196_coursetracker_mwilliams.Database.Term.TermEntity;

@Entity(tableName = "courses",
        indices = {@Index(value = "course_id",unique = true),@Index(value = "term_id",unique = true),@Index(value = "instructor_id",unique = true)},
        foreignKeys = {@ForeignKey(entity = TermEntity.class,
        parentColumns = "term_id",childColumns = "term_id",onDelete = ForeignKey.CASCADE)})
public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private int course_id;
    @ColumnInfo(name = "title")
    private String course_title;
    @ColumnInfo(name = "start")
    private String course_start;
    @ColumnInfo(name = "end")
    private String course_end;
    @ColumnInfo(name = "status")
    private String course_status;
    @ColumnInfo(name = "note")
    private String course_note;
    @ColumnInfo(name = "term_id")
    private int term_id;
    @ColumnInfo(name = "instructor_id")
    private int instructor_id;


    public CourseEntity(String course_title, String course_start, String course_end, String course_status, String course_note, int term_id, int instructor_id) {
        this.course_title = course_title;
        this.course_start = course_start;
        this.course_end = course_end;
        this.course_status = course_status;
        this.course_note = course_note;
        this.term_id = term_id;
        this.instructor_id = instructor_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_start() {
        return course_start;
    }

    public void setCourse_start(String course_start) {
        this.course_start = course_start;
    }

    public String getCourse_end() {
        return course_end;
    }

    public void setCourse_end(String course_end) {
        this.course_end = course_end;
    }

    public String getCourse_status() {
        return course_status;
    }

    public void setCourse_status(String course_status) {
        this.course_status = course_status;
    }

    public String getCourse_note() {
        return course_note;
    }

    public void setCourse_note(String course_note) {
        this.course_note = course_note;
    }

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public int getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    @Override
    public String toString() {
        return course_title;
    }
}
