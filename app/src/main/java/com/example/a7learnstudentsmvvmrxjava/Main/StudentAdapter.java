package com.example.a7learnstudentsmvvmrxjava.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a7learnstudentsmvvmrxjava.Model.Student;
import com.example.a7learnstudentsmvvmrxjava.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.item> {

    Context c;
    List<Student> students;

    public StudentAdapter(Context c, List<Student> students) {
        this.c = c;
        this.students = students;
    }

    @NonNull
    @Override
    public item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new item(LayoutInflater.from(c).inflate(R.layout.student_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull item holder, int position) {
        holder.bindStudents(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class item extends RecyclerView.ViewHolder{
        TextView nameTv, familyTv, courseTv, scoreTv, firstCharTv;
        public item(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.nameTv);
            familyTv = itemView.findViewById(R.id.familyTv);
            courseTv = itemView.findViewById(R.id.courseTv);
            scoreTv = itemView.findViewById(R.id.scoreTv);
            firstCharTv = itemView.findViewById(R.id.firstCharTv);
        }

        public void bindStudents(Student student){
            firstCharTv.setText(student.getFirst_name().substring(0,1));
            nameTv.setText(student.getFirst_name());
            familyTv.setText(student.getLast_name());
            courseTv.setText(student.getCourse());
            scoreTv.setText(student.getScore() + "");

        }

        public void addStudent(Student student){
            students.add(0,student);
            notifyItemInserted(0);
        }
    }
}
