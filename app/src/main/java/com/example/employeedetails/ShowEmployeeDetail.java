package com.example.employeedetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowEmployeeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee_detail);

        TextView name = findViewById(R.id.name);
        TextView age = findViewById(R.id.age);
        TextView salary = findViewById(R.id.salary);

        Intent intent = getIntent();

        String ageText = intent.getStringExtra(MainActivity.AGE);
        String salaryText = intent.getStringExtra(MainActivity.SALARY);

        name.setText(intent.getStringExtra(MainActivity.NAME));
        age.setText(ageText);
        salary.setText(salaryText);

    }
}