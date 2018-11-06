package com.example.development.sakaiclient20.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.development.sakaiclient20.R;
import com.example.development.sakaiclient20.networking.services.AssignmentsService;
import com.example.development.sakaiclient20.networking.services.ServiceFactory;
import com.example.development.sakaiclient20.persistence.SakaiDatabase;
import com.example.development.sakaiclient20.persistence.access.AssignmentDao;
import com.example.development.sakaiclient20.persistence.entities.Assignment;
import com.example.development.sakaiclient20.repositories.AssignmentRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssignmentsService service = ServiceFactory.getService(this, AssignmentsService.class);
        AssignmentDao dao = SakaiDatabase.getInstance(this).assignmentDao;
        AssignmentRepository repo = new AssignmentRepository(dao, service);

        repo.getAllAssignments(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        assignments -> {
                            for(Assignment assignment : assignments) {
                                Log.d("Assignment", assignment.title);
                            }
                        },
                        error -> error.printStackTrace()
                );
    }
}
