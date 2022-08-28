package com.example.mycameraprofilapp.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycameraprofilapp.OnButtonDeleteClickedAction;
import com.example.mycameraprofilapp.OnMaterialCardClickedAction;
import com.example.mycameraprofilapp.R;
import com.example.mycameraprofilapp.model.Student;
import com.google.android.material.card.MaterialCardView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private TextView vhStudentName;
    private TextView vhStudentClassroom;
    private TextView vhStudentSport;
    private MaterialCardView vhMatCard;
    private ImageView vhDelete;

    public StudentViewHolder(@NonNull View view) {
        super(view);
        vhStudentName = view.findViewById(R.id.raw_name);
        vhStudentClassroom = view.findViewById(R.id.raw_classroom);
        vhStudentSport = view.findViewById(R.id.raw_sport);
        vhMatCard = view.findViewById(R.id.raw_matcard);
        vhDelete = view.findViewById(R.id.raw_delete);
    }

    public TextView getVhStudentName() {
        return vhStudentName;
    }

    public void setVhStudentName(TextView vhStudentName) {
        this.vhStudentName = vhStudentName;
    }

    public TextView getVhStudentClassroom() {
        return vhStudentClassroom;
    }

    public void setVhStudentClassroom(TextView vhStudentClassroom) {
        this.vhStudentClassroom = vhStudentClassroom;
    }

    public TextView getVhStudentSport() {
        return vhStudentSport;
    }

    public void setVhStudentSport(TextView vhStudentSport) {
        this.vhStudentSport = vhStudentSport;
    }

    public MaterialCardView getVhMatCard() {
        return vhMatCard;
    }

    public void setVhMatCard(MaterialCardView vhMatCard) {
        this.vhMatCard = vhMatCard;
    }

    public ImageView getVhDelete() {
        return vhDelete;
    }

    public void setVhDelete(ImageView vhDelete) {
        this.vhDelete = vhDelete;
    }

    public void bind(Student student, OnMaterialCardClickedAction onMaterialCardClickedAction, OnButtonDeleteClickedAction onButtonDeleteClickedAction) {
        vhStudentName.setText(student.getName());
        vhStudentClassroom.setText(student.getClassroom());
        vhStudentSport.setText(student.getSport());
        vhMatCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMaterialCardClickedAction.toDetail(student);
            }
        });
        vhDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonDeleteClickedAction.delete(student);
            }
        });
    }
}