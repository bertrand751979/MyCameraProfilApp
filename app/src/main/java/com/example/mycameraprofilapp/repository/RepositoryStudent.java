package com.example.mycameraprofilapp.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;

import com.example.mycameraprofilapp.ApplicationDataBase;
import com.example.mycameraprofilapp.model.Student;

import java.util.List;
import java.util.concurrent.Executors;

public class RepositoryStudent {
    private RepositoryStudent(){}
    public static RepositoryStudent INSTANCE = null;
    public static RepositoryStudent getInstance(){
        if(INSTANCE == null){
            INSTANCE = new RepositoryStudent();
        }
        return INSTANCE;
    }

    public void add(Student student, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDataBase.getInstance(context).getStudentDao().add(student);
            }
        });
    }

    public void delete (Student student, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDataBase.getInstance(context).getStudentDao().delete(student);
            }
        });
    }

    public void update (Student student, Context context){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ApplicationDataBase.getInstance(context).getStudentDao().update(student);
            }
        });
    }
    public LiveData<List<Student>> getStudentList (Context context){
        return ApplicationDataBase.getInstance(context).getStudentDao().getStudentList();
    }
}
