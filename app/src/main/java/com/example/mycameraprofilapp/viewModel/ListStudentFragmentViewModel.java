package com.example.mycameraprofilapp.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mycameraprofilapp.model.Student;
import com.example.mycameraprofilapp.repository.RepositoryStudent;

import java.util.List;

public class ListStudentFragmentViewModel extends ViewModel {
    public LiveData<List<Student>> getDisplayList (Context context){
        return RepositoryStudent.getInstance().getStudentList(context);
    }

    public void deleteCountry(Student student, Context context){
        RepositoryStudent.getInstance().delete(student,context);
    }


}
