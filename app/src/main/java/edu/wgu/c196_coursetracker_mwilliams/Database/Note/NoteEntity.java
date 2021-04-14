package edu.wgu.c196_coursetracker_mwilliams.Database.Note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import edu.wgu.c196_coursetracker_mwilliams.Database.Assessment.AssessmentEntity;

@Entity(tableName = "notes",
        indices = {@Index(value = "note_id",unique = true),@Index(value = "assessment_id",unique = true)},
        foreignKeys = @ForeignKey(entity = AssessmentEntity.class,
                parentColumns = "assessment_id",
                childColumns = "assessment_id",onDelete = ForeignKey.CASCADE))
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private int id;
    @ColumnInfo(name = "title")
    private String note_title;
    @ColumnInfo(name = "content")
    private String note_content;
    @ColumnInfo(name = "assessment_id")
    private int assessment_id;

    public NoteEntity(String note_title, String note_content, int assessment_id) {
        this.note_title = note_title;
        this.note_content = note_content;
        this.assessment_id = assessment_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public int getAssessment_id() {
        return assessment_id;
    }

    public void setAssessment_id(int assessment_id) {
        this.assessment_id = assessment_id;
    }
}
