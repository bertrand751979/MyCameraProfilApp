package com.example.mycameraprofilapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycameraprofilapp.OnButtonDeleteClickedAction;
import com.example.mycameraprofilapp.OnMaterialCardClickedAction;
import com.example.mycameraprofilapp.R;
import com.example.mycameraprofilapp.model.Student;
import com.example.mycameraprofilapp.viewHolder.StudentViewHolder;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    private ArrayList<Student> listStudentAdapter = new ArrayList<>();
    private OnMaterialCardClickedAction onMaterialCardClickedAction;
    private OnButtonDeleteClickedAction onButtonDeleteClickedAction;

    public StudentAdapter(OnMaterialCardClickedAction onMaterialCardClickedAction, OnButtonDeleteClickedAction onButtonDeleteClickedAction) {
        this.onMaterialCardClickedAction = onMaterialCardClickedAction;
        this.onButtonDeleteClickedAction = onButtonDeleteClickedAction;
    }

    public void setListStudentAdapter(ArrayList<Student> listStudentAdapter) {
        this.listStudentAdapter = listStudentAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_student,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bind(listStudentAdapter.get(position),onMaterialCardClickedAction,onButtonDeleteClickedAction );
    }

    @Override
    public int getItemCount() {
        return listStudentAdapter.size();
    }
}
