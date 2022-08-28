package com.example.mycameraprofilapp.activities;
import static com.example.mycameraprofilapp.activities.MainActivity.STUDENT_EXTRA;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycameraprofilapp.viewModel.DetailStudentActivityViewModel;
import com.example.mycameraprofilapp.R;
import com.example.mycameraprofilapp.model.Student;

public class DetailStudentActivity extends AppCompatActivity {
    private EditText detailStudentName;
    private EditText detailStudentClassroom;
    private TextView detailStudentSport;
    private Button detailBtnUpdate;
    private Button detailBtnCancel;
    private Student student;
    private DetailStudentActivityViewModel detailStudentActivityViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        student = (Student) getIntent().getSerializableExtra(STUDENT_EXTRA);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Description");
        detailStudentActivityViewModel = new ViewModelProvider(this).get(DetailStudentActivityViewModel.class);
        detailStudentName= findViewById(R.id.desc_student_name);
        detailStudentClassroom = findViewById(R.id.desc_student_classroom);
        detailStudentSport = findViewById(R.id.desc_student_sport);
        detailBtnUpdate = findViewById(R.id.desc_btn_update);
        detailBtnCancel = findViewById(R.id.desc_btn_cancel);
        detailStudentName.setText(student.getName());
        detailStudentClassroom.setText(student.getClassroom());
        detailStudentSport.setText(student.getSport());
        detailBtnUpdate = findViewById(R.id.desc_btn_update);
        detailBtnCancel = findViewById(R.id.desc_btn_cancel);
        detailBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student.setName(detailStudentName.getText().toString());
                student.setClassroom(detailStudentClassroom.getText().toString());
                student.setSport(detailStudentSport.getText().toString());
                detailStudentActivityViewModel.updateStudent(student,DetailStudentActivity.this);
            }
        });
        detailBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
