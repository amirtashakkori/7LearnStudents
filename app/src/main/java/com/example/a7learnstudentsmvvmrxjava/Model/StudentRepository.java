package com.example.a7learnstudentsmvvmrxjava.Model;

import androidx.lifecycle.LiveData;

import com.example.a7learnstudentsmvvmrxjava.Model.ApiService.ApiService;
import com.example.a7learnstudentsmvvmrxjava.Model.DataBase.StudentDao;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class StudentRepository {

    ApiService apiService;
    StudentDao dao;


    public StudentRepository(ApiService apiService, StudentDao dao) {
        this.apiService = apiService;
        this.dao = dao;
    }

    public Completable refreshStudents(){
        return apiService.getStudents().doOnSuccess(students -> dao.addStudents(students)).ignoreElement();
    }

    public LiveData<List<Student>> getStudents(){
        return dao.getStudents();
    }

    public Single<Student> saveStudent(String name, String family, String course, long score){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("first_name" , name);
        jsonObject.addProperty("last_name" , family);
        jsonObject.addProperty("course" , course);
        jsonObject.addProperty("score" , score);
        return apiService.saveStudent(jsonObject).doOnSuccess(student -> dao.addStudent(student));
    }

}
