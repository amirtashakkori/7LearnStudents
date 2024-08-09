package com.example.a7learnstudentsmvvmrxjava.Model.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Dao;

import com.example.a7learnstudentsmvvmrxjava.Model.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Query("Select * from table_student")
    LiveData<List<Student>> getStudents();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addStudents(List<Student> students);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addStudent(Student student);

}
