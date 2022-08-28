package com.example.mycameraprofilapp.fragment;

import static com.example.mycameraprofilapp.activities.MainActivity.STUDENT_EXTRA;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mycameraprofilapp.OnButtonDeleteClickedAction;
import com.example.mycameraprofilapp.OnMaterialCardClickedAction;
import com.example.mycameraprofilapp.R;
import com.example.mycameraprofilapp.activities.DetailStudentActivity;
import com.example.mycameraprofilapp.adapter.StudentAdapter;
import com.example.mycameraprofilapp.model.Student;
import com.example.mycameraprofilapp.viewModel.ListStudentFragmentViewModel;
import java.util.ArrayList;
import java.util.List;

public class ListStudentFragment extends Fragment {
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private ListStudentFragmentViewModel listStudentFragmentViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listStudentFragmentViewModel = new ViewModelProvider(this).get(ListStudentFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_student_list);
        setViewCountry();
    }
    private  void setViewCountry(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        OnMaterialCardClickedAction onMaterialCardClickedAction = new OnMaterialCardClickedAction() {
            @Override
            public void toDetail(Student student) {
                Intent intent = new Intent(ListStudentFragment.this.getContext(), DetailStudentActivity.class);
                intent.putExtra(STUDENT_EXTRA, student);
                startActivity(intent);
            }
        };
        OnButtonDeleteClickedAction onButtonDeleteClickedAction = new OnButtonDeleteClickedAction() {
            @Override
            public void delete(Student student) {
                listStudentFragmentViewModel.deleteCountry(student,getContext());
            }
        };
        studentAdapter = new StudentAdapter(onMaterialCardClickedAction, onButtonDeleteClickedAction );
        recyclerView.setAdapter(studentAdapter);
        listStudentFragmentViewModel.getDisplayList(getContext()).observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentAdapter.setListStudentAdapter(new ArrayList<>(students));
            }
        });
    }
}
