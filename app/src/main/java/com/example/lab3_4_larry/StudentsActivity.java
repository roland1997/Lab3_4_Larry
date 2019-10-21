package com.example.lab3_4_larry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentsActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private Button newStudent, viewStundets;
    private EditText fname,lname,course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        newStudent = (Button) findViewById(R.id.addStudentBut);
        viewStundets = (Button) findViewById(R.id.viewStudentsBut);
        fname = (EditText) findViewById(R.id.fNameLabel);
        lname = (EditText) findViewById(R.id.lNameLabel);
        course = (EditText) findViewById(R.id.courseLabel);
        mDatabaseHelper = new DatabaseHelper(this);


        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = fname.getText().toString();
                String text2 = lname.getText().toString();
                String text3 = course.getText().toString();
                if (fname.length() != 0) {
                    AddData(text1,text2,text3);
                    fname.setText("");
                    lname.setText("");
                    course.setText("");
                } else {
                    Toast.makeText(StudentsActivity.this,"You must put something in the text field!",Toast.LENGTH_LONG).show();

                }
            }
        });

        viewStundets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityList();
            }
        });
    }

    public void openActivityList(){
        Intent intent = new Intent(this, ListDataActivity.class);
        startActivity(intent);
    }

    public void AddData(String fname, String lname, String course) {
        boolean insertData = mDatabaseHelper.addData(fname,lname,course);

        if (insertData) {
            Toast.makeText(StudentsActivity.this,"Data Successfully Inserted!",Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(StudentsActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
        }
    }
}
