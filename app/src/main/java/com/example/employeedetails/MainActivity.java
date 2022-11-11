package com.example.employeedetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.employeedetails.adapter.EmployeeAdapter;
import com.example.employeedetails.models.EmployeeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String SALARY = "salary";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<EmployeeModel> employeeData = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.employee_list_view);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://aamras.com/dummy/EmployeeDetails.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {

                    try {
                        JSONArray employeeArray = response.getJSONArray("employees");

                        int size = employeeArray.length();

                        for (int i = 0; i < size; i++) {

                            JSONObject currentEmployeeData = employeeArray.getJSONObject(i);

                            String name = (String) currentEmployeeData.get("name");
                            String age = String.valueOf(currentEmployeeData.get("age"));
                            String salary = String.valueOf(currentEmployeeData.get("salary"));
                            employeeData.add(new EmployeeModel(name, age, salary));
                        }

                        EmployeeAdapter adapter = new EmployeeAdapter(employeeData);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                        recyclerView.setHasFixedSize(true);
                        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                        recyclerView.addItemDecoration(decoration);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnClickListener(new EmployeeAdapter.MyOnClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Intent intent = new Intent(MainActivity.this, ShowEmployeeDetail.class);
                                intent.putExtra(NAME, employeeData.get(position).getName());
                                intent.putExtra(AGE, employeeData.get(position).getAge());
                                intent.putExtra(SALARY, employeeData.get(position).getSalary());
                                startActivity(intent);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                },
                error -> Log.d("volley_error", error + ""));

        requestQueue.add(jsonObjectRequest);

    }

}