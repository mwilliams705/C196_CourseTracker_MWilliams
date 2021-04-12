package edu.wgu.c196_coursetracker_mwilliams.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.Long;

@Entity(tableName = "assessments")
public class AssessmentEntity {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "title")
    private String assessment_title;
    @ColumnInfo(name = "isOA")
    private boolean isOA;
    @ColumnInfo(name = "end")
    private Long assessment_end;

    public AssessmentEntity(int id, String assessment_title, boolean isOA, Long assessment_end) {
        this.id = id;
        this.assessment_title = assessment_title;
        this.isOA = isOA;
        this.assessment_end = assessment_end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssessment_title() {
        return assessment_title;
    }

    public void setAssessment_title(String assessment_title) {
        this.assessment_title = assessment_title;
    }

    public boolean isOA() {
        return isOA;
    }

    public void setOA(boolean OA) {
        isOA = OA;
    }

    public Long getAssessment_end() {
        return assessment_end;
    }

    public void setAssessment_end(Long assessment_end) {
        this.assessment_end = assessment_end;
    }

    @Override
    public String  toString() {
        return "AssessmentEntity{" +
                "id=" + id +
                ", assessment_title='" + assessment_title + '\'' +
                ", isOA=" + isOA +
                ", assessment_end=" + assessment_end +
                '}';
    }
}
