package edu.wgu.c196_coursetracker_mwilliams.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "assessments",
        indices = {@Index(value = "assessment_id",unique = true),@Index(value = "course_id",unique = true)},
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                parentColumns = "course_id",
                childColumns = "course_id",onDelete = ForeignKey.CASCADE))
public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "assessment_id")
    private int id;
    @ColumnInfo(name = "title")
    private String assessment_title;
    @ColumnInfo(name = "isOA")
    private boolean isOA;
    @ColumnInfo(name = "date")
    private String assessment_date;
    @ColumnInfo(name = "course_id")
    private int course_id;

    public AssessmentEntity(String assessment_title, boolean isOA, String assessment_date, int course_id) {
        this.assessment_title = assessment_title;
        this.isOA = isOA;
        this.assessment_date = assessment_date;
        this.course_id = course_id;
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

    public String getAssessment_date() {
        return assessment_date;
    }

    public void setAssessment_date(String assessment_date) {
        this.assessment_date = assessment_date;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
