package com.example.a7learnstudentsmvvmrxjava.Model.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.a7learnstudentsmvvmrxjava.Model.Student;

@Database(version = 1 , entities = {Student.class} , exportSchema = false)
public abstract class AppDataBase extends RoomDatabase{

    private static AppDataBase appDataBase;

    public static AppDataBase getInstance(Context context){
        if (appDataBase == null){
            appDataBase = Room.databaseBuilder(context, AppDataBase.class , "db_students").build();
        }
        return appDataBase;
    }

    public abstract StudentDao getDao();

}
