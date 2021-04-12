package edu.wgu.c196_coursetracker_mwilliams.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "instructors")
public class InstructorEntity {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "name")
    private String instructor_name;
    @ColumnInfo(name = "email")
    private String instructor_email;

    public InstructorEntity(int id, String instructor_name, String instructor_email) {
        this.id = id;
        this.instructor_name = instructor_name;
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

    public String getInstructor_email() {
        return instructor_email;
    }

    public void setInstructor_email(String instructor_email) {
        this.instructor_email = instructor_email;
    }

    @Override
    public String toString() {
        return "InstructorEntity{" +
                "id=" + id +
                ", instructor_name='" + instructor_name + '\'' +
                ", instructor_email='" + instructor_email + '\'' +
                '}';
    }
}
