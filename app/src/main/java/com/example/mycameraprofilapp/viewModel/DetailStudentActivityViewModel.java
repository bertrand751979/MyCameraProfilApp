package com.example.mycameraprofilapp.viewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.mycameraprofilapp.model.Student;
import com.example.mycameraprofilapp.repository.RepositoryStudent;

public class DetailStudentActivityViewModel extends ViewModel {
    public void updateStudent(Student student, Context context){
        RepositoryStudent.getInstance().update(student,context);
    }

}
