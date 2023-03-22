package com.zasa.superduper.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zasa.superduper.Adapters.AttendanceAdapter;
import com.zasa.superduper.Adapters.Routes_Adapter;
import com.zasa.superduper.Models.AttendanceModel;
import com.zasa.superduper.Models.Routes_Model;
import com.zasa.superduper.R;

import java.util.ArrayList;

public class DailyAttendenceActivity extends AppCompatActivity {
    ImageView iv_back;
    ArrayList<AttendanceModel> attendanceList = new ArrayList<>();
    RecyclerView rcv_attendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_attendence);

        iv_back = findViewById(R.id.atten_back_btn);
        rcv_attendance = findViewById(R.id.rv_attendance);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getAttendance();

    }

    private void getAttendance() {
        attendanceList.add(new AttendanceModel("8/3/2023","08:57"));
        attendanceList.add(new AttendanceModel("9/3/2023","08:57"));
        attendanceList.add(new AttendanceModel("10/3/2023","08:59"));
        attendanceList.add(new AttendanceModel("11/3/2023","09:00"));
        attendanceList.add(new AttendanceModel("12/3/2023","09:02"));
        attendanceList.add(new AttendanceModel("13/3/2023","09:00"));
        attendanceList.add(new AttendanceModel("14/3/2023","08:58"));
        attendanceList.add(new AttendanceModel("15/3/2023","08:55"));

        AttendanceAdapter attendanceAdapter = new AttendanceAdapter(attendanceList,this);
        rcv_attendance.setAdapter(attendanceAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv_attendance.setLayoutManager(layoutManager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}