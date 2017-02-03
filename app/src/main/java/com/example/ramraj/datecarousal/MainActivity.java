package com.example.ramraj.datecarousal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.dateCarousalLayout)
    DateCarousal dateCarousal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dateCarousal.addClickListener(new DateCarousal.CarousalClickListener() {
            @Override
            public void handleClick(String desiredDate) {
                Toast.makeText(getApplicationContext(),desiredDate,Toast.LENGTH_SHORT).show();
            }
        });
        dateCarousal.post(new Runnable() {
            @Override
            public void run() {
                dateCarousal.setSmoothScrollingEnabled(true);
                dateCarousal.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        });
    }
}
