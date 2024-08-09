package com.example.a7learnstudentsmvvmrxjava.AddStudent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.a7learnstudentsmvvmrxjava.Factory;
import com.example.a7learnstudentsmvvmrxjava.Model.ApiService.ApiServiceProvider;
import com.example.a7learnstudentsmvvmrxjava.Model.DataBase.AppDataBase;
import com.example.a7learnstudentsmvvmrxjava.Model.StudentRepository;
import com.example.a7learnstudentsmvvmrxjava.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddStudentActivity extends AppCompatActivity {

    TextInputEditText nameEdt, familyEdt, courseEdt, scoreEdt;
    Button saveBtn;

    Disposable disposable;

    public void cast(){
        nameEdt = findViewById(R.id.nameEdt);
        familyEdt = findViewById(R.id.familyEdt);
        courseEdt = findViewById(R.id.courseEdt);
        scoreEdt = findViewById(R.id.scoreEdt);
        saveBtn = findViewById(R.id.saveBtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cast();
        AddStudentViewModel viewModel = new ViewModelProvider(this, new Factory(new StudentRepository(ApiServiceProvider.getApiService(), AppDataBase.getInstance(getApplicationContext()).getDao()))).get(AddStudentViewModel.class);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Objects.requireNonNull(nameEdt.getText()).toString();
                String family = Objects.requireNonNull(familyEdt.getText()).toString();
                String course = Objects.requireNonNull(courseEdt.getText()).toString();
                String score = Objects.requireNonNull(scoreEdt.getText()).toString();

                if (!name.isEmpty() && !family.isEmpty() && !course.isEmpty() && !score.isEmpty()){
                    viewModel.saveStudent(name, family, course, Long.parseLong(score))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onComplete() {
                            Toast.makeText(AddStudentActivity.this, "Successfully Added!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(AddStudentActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}