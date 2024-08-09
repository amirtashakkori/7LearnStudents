package com.example.a7learnstudentsmvvmrxjava.AddStudent;

import androidx.lifecycle.ViewModel;

import com.example.a7learnstudentsmvvmrxjava.Model.StudentRepository;

import io.reactivex.Completable;

public class AddStudentViewModel extends ViewModel {

    StudentRepository repo;

    public AddStudentViewModel(StudentRepository repo) {
        this.repo = repo;
    }

    public Completable saveStudent(String name, String family, String course, long score){
        return repo.saveStudent(name, family, course, score).ignoreElement();
    }

}
