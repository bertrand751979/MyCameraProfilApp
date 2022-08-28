package com.example.mycameraprofilapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mycameraprofilapp.model.Student;


@Database(entities = {Student.class},version = 1)
public abstract class ApplicationDataBase extends RoomDatabase {
    private static ApplicationDataBase INSTANCE;
    public abstract StudentDao getStudentDao();
    public static synchronized ApplicationDataBase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ApplicationDataBase.class, "student_app").build();
        }
        return INSTANCE;
    }
}
