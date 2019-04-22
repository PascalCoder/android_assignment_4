package com.example.assignment_4;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

public class ParkingInfo extends AppCompatActivity {

    TextView tvName, tvAddress, tvSpot, tvCost, tvDistance;

    EditText etDateInfo, etTimeInfo;
    TextView tvMinuteInfo, tvPrice;
    SeekBar sbTime;

    Calendar calendar;
    DatePickerDialog datePickerDialog;
    int currentMonth, currentDayOfMonth, currentYear;
    TimePickerDialog timePickerDialog;
    int currentHour, currentMinute;

    double price = 0.0;
    double cost = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_info);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout_2);

        final ParkingPojo parkingPojo = (ParkingPojo) getIntent().getParcelableExtra("parking");
        String address = getIntent().getStringExtra("address");
        String spots = "" + getIntent().getIntExtra("spot", 5);
        String distance = getIntent().getStringExtra("distance");
        cost = Double.parseDouble(getIntent().getStringExtra("cost"));
        //Toast.makeText(this, "" + cost, Toast.LENGTH_SHORT).show();

        tvName = findViewById(R.id.tv_name_info);
        tvName.setText(parkingPojo.getName());

        tvAddress = findViewById(R.id.tv_address_info);
        tvAddress.setText(address);

        tvSpot = findViewById(R.id.tv_spot_info);
        tvSpot.setText(spots);

        tvCost = findViewById(R.id.tv_cost_info);
        tvCost.setText(parkingPojo.getCostPerMinute());

        tvDistance = findViewById(R.id.tv_distance_info);
        tvDistance.setText(new DecimalFormat("##.##").format(Double.parseDouble(distance)) + " mile");


        etDateInfo = findViewById(R.id.et_date_info);
        etTimeInfo = findViewById(R.id.et_time_info);

        calendar = Calendar.getInstance();

        currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        etDateInfo.setText(currentMonth + 1 + "/" + currentDayOfMonth + "/" + currentYear);

        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);
        etTimeInfo.setText(currentHour + ":" + currentMinute);

        tvMinuteInfo = findViewById(R.id.tv_minute_info);
        sbTime = findViewById(R.id.sb_time_info);
        sbTime.setMax(120);

        tvPrice = findViewById(R.id.tv_price);

        sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvMinuteInfo.setText(" " + progress + " min");
                price = progress * cost;

                tvPrice.setText("$" + price);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}
