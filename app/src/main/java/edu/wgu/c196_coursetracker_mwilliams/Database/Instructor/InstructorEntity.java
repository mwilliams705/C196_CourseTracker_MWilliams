package edu.wgu.c196_coursetracker_mwilliams.Database.Instructor;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import edu.wgu.c196_coursetracker_mwilliams.Database.Course.CourseEntity;

@Entity(tableName = "instructors",
        indices = {@Index(value = "instructor_id",unique = true)})
public class InstructorEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "instructor_id")
    private int id;
    @ColumnInfo(name = "name")
    private String instructor_name;
    @ColumnInfo(name = "phone")
    private String instructor_phone;
    @ColumnInfo(name = "email")
    private String instructor_email;

    public InstructorEntity(String instructor_name, String instructor_phone, String instructor_email) {
        this.instructor_name = instructor_name;
        this.instructor_phone = instructor_phone;
        this.instructor_email = instructor_email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstructor_name() {
        return instructor_name;
    }

    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }

    public String getInstructor_phone() {
        return instructor_phone;
    }

    public void setInstructor_phone(String instructor_phone) {
        this.instructor_phone = instructor_phone;
    }

    public String getInstructor_email() {
        return instructor_email;
    }

    public void setInstructor_email(String instructor_email) {
        this.instructor_email = instructor_email;
    }

}
