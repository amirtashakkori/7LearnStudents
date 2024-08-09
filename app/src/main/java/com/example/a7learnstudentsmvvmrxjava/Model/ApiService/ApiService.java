package com.example.a7learnstudentsmvvmrxjava.Model.ApiService;

import com.example.a7learnstudentsmvvmrxjava.Model.Student;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("experts/student")
    Single<List<Student>> getStudents();

    @POST("experts/student")
    Single<Student> saveStudent(@Body JsonObject jsonObject);

}
