package com.example.a7learnstudentsmvvmrxjava;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.a7learnstudentsmvvmrxjava.AddStudent.AddStudentViewModel;
import com.example.a7learnstudentsmvvmrxjava.Main.MainViewModel;
import com.example.a7learnstudentsmvvmrxjava.Model.Student;
import com.example.a7learnstudentsmvvmrxjava.Model.StudentRepository;

public class Factory implements ViewModelProvider.Factory {

    StudentRepository repo;

    public Factory(StudentRepository repo) {
        this.repo = repo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass, @NonNull CreationExtras extras) {
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel(repo);

        else if (modelClass.isAssignableFrom(AddStudentViewModel.class)) {
            return (T) new AddStudentViewModel(repo);
        } else
            throw new IllegalArgumentException("ViewModel is not valid!");

    }
}
