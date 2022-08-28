package com.example.mycameraprofilapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mycameraprofilapp.model.Student;

import java.util.List;


@Dao
public interface StudentDao {
    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getStudentList();

    @Insert
    void add(Student student);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);
}
