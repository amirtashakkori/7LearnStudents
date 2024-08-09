package com.example.a7learnstudentsmvvmrxjava.Main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.a7learnstudentsmvvmrxjava.Model.Student;
import com.example.a7learnstudentsmvvmrxjava.Model.StudentRepository;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    StudentRepository repo;
    MutableLiveData<String> errors = new MutableLiveData<>();
    Disposable disposable;

    public MainViewModel(StudentRepository repo) {
        this.repo = repo;
        repo.refreshStudents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        errors.postValue(e + "");
                    }
                });
    }

    public LiveData<List<Student>> getStudents(){
        return repo.getStudents();
    }

    public MutableLiveData<String> getErrors(){
        return errors;
    }

    @Override
    protected void onCleared() {
        disposable.dispose();
        super.onCleared();
    }
}
