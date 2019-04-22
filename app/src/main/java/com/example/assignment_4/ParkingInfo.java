package com.example.assignment_4;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ParkingInfo extends AppCompatActivity {

    TextView tvName, tvAddress, tvSpot, tvCost, tvDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_info);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout_2);

        ParkingPojo parkingPojo = (ParkingPojo) getIntent().getParcelableExtra("parking");
        String address = getIntent().getStringExtra("address");
        String spots = "" + getIntent().getIntExtra("spot", 5);
        String distance = getIntent().getStringExtra("distance");
        //Toast.makeText(this, "" + distance, Toast.LENGTH_SHORT).show();

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

    }
}
