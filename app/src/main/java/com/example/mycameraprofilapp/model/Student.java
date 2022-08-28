package com.example.mycameraprofilapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity
public class Student implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name="photo")
    private String photo;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="classroom")
    private String classroom;
    @ColumnInfo(name="sport")
    private String sport;

    public Student(Integer id, String photo, String name, String classroom, String sport) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.classroom = classroom;
        this.sport = sport;
    }

    public Student(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
