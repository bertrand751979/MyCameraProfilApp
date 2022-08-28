package com.example.mycameraprofilapp.viewModel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.mycameraprofilapp.model.Student;
import com.example.mycameraprofilapp.repository.RepositoryStudent;

public class RegisterStudentFragmentViewModel extends ViewModel {
    public void addCountry(Student student, Context context){
        RepositoryStudent.getInstance().add(student,context);
    }
}
