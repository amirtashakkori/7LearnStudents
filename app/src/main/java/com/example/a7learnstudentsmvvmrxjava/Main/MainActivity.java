package com.example.a7learnstudentsmvvmrxjava.Main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a7learnstudentsmvvmrxjava.AddStudent.AddStudentActivity;
import com.example.a7learnstudentsmvvmrxjava.Factory;
import com.example.a7learnstudentsmvvmrxjava.Model.ApiService.ApiServiceProvider;
import com.example.a7learnstudentsmvvmrxjava.Model.DataBase.AppDataBase;
import com.example.a7learnstudentsmvvmrxjava.Model.StudentRepository;
import com.example.a7learnstudentsmvvmrxjava.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView studentsRv;
    ExtendedFloatingActionButton addBtn;
    String TAG = "mainActivity";

    StudentAdapter adapter;

    public void cast(){
        studentsRv = findViewById(R.id.studentsRv);
        addBtn = findViewById(R.id.addBtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cast();

        studentsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false));

        MainViewModel mainViewModel = new ViewModelProvider(this , new Factory(new StudentRepository(ApiServiceProvider.getApiService(), AppDataBase.getInstance(getApplicationContext()).getDao()))).get(MainViewModel.class);
        mainViewModel.getStudents().observe(this, students -> {

            Log.i(TAG, "Success");

            studentsRv.setLayoutManager(new LinearLayoutManager(MainActivity.this , LinearLayoutManager.VERTICAL , false));
            adapter = new StudentAdapter(MainActivity.this , students);
            studentsRv.setAdapter(adapter);
        });
        
        mainViewModel.getErrors().observe(this, error -> {
            Log.i(TAG, "onCreate: " + error);
            Toast.makeText(this,  "" + error, Toast.LENGTH_SHORT).show();
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });
        
    }
}