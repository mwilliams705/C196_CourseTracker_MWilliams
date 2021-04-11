package edu.wgu.c196_coursetracker_mwilliams.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "courses")
public class CourseEntity {
    @PrimaryKey
    private int course_id;
    @ColumnInfo(name = "title")
    private String course_title;
    @ColumnInfo(name = "start")
    private Timestamp course_start;
    @ColumnInfo(name = "end")
    private Timestamp course_end;
    @ColumnInfo(name = "status")
    private String course_status;
    @ColumnInfo(name = "note")
    private String course_note;

    public CourseEntity(int course_id, String course_title, Timestamp course_start, Timestamp course_end, String course_status, String course_note) {
        this.course_id = course_id;
        this.course_title = course_title;
        this.course_start = course_start;
        this.course_end = course_end;
        this.course_status = course_status;
        this.course_note = course_note;
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

    public Timestamp getCourse_start() {
        return course_start;
    }

    public void setCourse_start(Timestamp course_start) {
        this.course_start = course_start;
    }

    public Timestamp getCourse_end() {
        return course_end;
    }

    public void setCourse_end(Timestamp course_end) {
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

    @Override
    public String toString() {
        return "CourseEntity{" +
                "course_id=" + course_id +
                ", course_title='" + course_title + '\'' +
                ", course_start=" + course_start +
                ", course_end=" + course_end +
                ", course_status='" + course_status + '\'' +
                ", course_note='" + course_note + '\'' +
                '}';
    }
}
