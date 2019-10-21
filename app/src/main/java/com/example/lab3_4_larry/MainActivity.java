package com.example.lab3_4_larry;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name,email,pass;
    TextView datepicked;
    Button login, pickdate;
    CheckBox remember;
    String n,e,ph,date;
    int year,month,day;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";
    public static final String Date = "dateKey";

    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.emailAdress);
        pass = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.logIn);
        remember = (CheckBox)findViewById(R.id.rememberMe);
        pickdate = (Button)findViewById(R.id.pickADate);
        datepicked = (TextView)findViewById(R.id.datepicked);

        Log.i("date","create");
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        loadData();
        updateData();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remember.isChecked()){
                    saveData();
                }
                Toast.makeText(MainActivity.this,"Thanks",Toast.LENGTH_LONG).show();
            }
        });


        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("date","click");

                Calendar calendar = Calendar.getInstance();
                 year = calendar.get(Calendar.YEAR);
                 month = calendar.get(Calendar.MONTH);
                 day = calendar.get(Calendar.DAY_OF_MONTH);
                Log.i("date",year+"/"+month+"/" +day);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                     date= dayOfMonth + "/" + month + "/" + year;
                     datepicked.setText(date);
                    }
                }, year, month, day);

                 datePickerDialog.show();
                
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityStudents();
            }
        });


    }
    public void openActivityStudents(){
        Intent intent = new Intent(this, StudentsActivity.class);
        startActivity(intent);
    }
    public  void saveData(){
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        n  = name.getText().toString();
        ph  = pass.getText().toString();
        e  = email.getText().toString();


        editor.putString(Name, n);
        editor.putString(Password, ph);
        editor.putString(Email, e);
        editor.putString(Date,date);
        editor.apply();

    }
    public void loadData(){
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        n = sharedpreferences.getString("nameKey", "");
        e = sharedpreferences.getString("emailKey", "");
        ph = sharedpreferences.getString("passwordKey", "");
        date = sharedpreferences.getString("dateKey","");


    }

    public void updateData(){

        name.setText(n);
        email.setText(e);
        pass.setText(ph);
        datepicked.setText(date);

    }


}
