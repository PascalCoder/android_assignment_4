package com.example.assignment_4;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

import java.util.Calendar;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback { //FragmentActivity

    private static String TAG = MapsActivity.class.getSimpleName();
    public static final float INITIAL_ZOOM = 12f;
    private GoogleMap mMap;

    private static final int REQUEST_LOCATION_PERMISSION = 1;

    public EditText etDate, etTime;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    int currentMonth, currentDayOfMonth, currentYear;
    TimePickerDialog timePickerDialog;
    int currentHour, currentMinute;
    public SeekBar seekBar;
    public TextView tvTime;
    ImageView ivSearch;
    public LinearLayout linearLayout;

    boolean hiddenToolbar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps); //activity_mapssliding_panel


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        etDate = findViewById(R.id.et_date);
        etTime = findViewById(R.id.et_time);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();

                currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                currentMonth = calendar.get(Calendar.MONTH);
                currentYear = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(MapsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText(month + 1 + "/" + dayOfMonth + "/" + year);
                    }
                }, currentMonth, currentDayOfMonth, currentYear);
                datePickerDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(MapsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String mHours = "";
                        String mMinutes = "";
                        if(hourOfDay < 10){mHours = "0" + hourOfDay;}else mHours = "" + hourOfDay;
                        if(minute < 10){mMinutes = "0" + minute;}else mMinutes = "" + minute;
                        etTime.setText(mHours + ": " + mMinutes);
                    }
                },currentHour,currentMinute,false);
                timePickerDialog.show();
            }
        });

        linearLayout = findViewById(R.id.ll_container);
        linearLayout.setBackgroundColor(0x00000000);

        seekBar = findViewById(R.id.sb_time);
        seekBar.setMax(120);
        tvTime = findViewById(R.id.tv_minute);
        ivSearch = findViewById(R.id.iv_search);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvTime.setText("" + progress + " min");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        final Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        final Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        ivSearch.setOnClickListener(new ImageView.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!hiddenToolbar){
                    linearLayout.setBackgroundColor(Color.GREEN);
                    //linearLayout.startAnimation(slideUp);
                    //v.startAnimation(slideUp);
                    //v.animate().translationY(v.getHeight());
                    slideUp(linearLayout);
                    slideUp(ivSearch);
                    Toast.makeText(MapsActivity.this, "HiddenToolbar: " + hiddenToolbar, Toast.LENGTH_SHORT).show();
                    hiddenToolbar = true;
                    Toast.makeText(MapsActivity.this, "HiddenToolbar: " + hiddenToolbar, Toast.LENGTH_SHORT).show();
                }else if(v.getVisibility() == View.INVISIBLE){
                    v.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onClick: Visibility changed");

                }
                else{
                    linearLayout.setBackgroundColor(Color.BLUE);
                    //linearLayout.startAnimation(slideDown);
                    //v.startAnimation(slideDown);
                    slideDown(linearLayout);
                    slideDown(ivSearch);
                    hiddenToolbar = false;
                    Toast.makeText(MapsActivity.this, "HiddenToolbar: " + hiddenToolbar, Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mapFragment).commit();
        mapFragment.getMapAsync(this);
    }

    public void slideUp(View view){
        //Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        int distance = linearLayout.getHeight();
        TranslateAnimation animate = new TranslateAnimation(0,0,
                                                            0,
                                                            -distance + 30);
        animate.setDuration(1000);
        animate.setFillAfter(false);
        view.startAnimation(animate);
    }

    public void slideDown(View view){
        //Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        int distance = linearLayout.getHeight();
        TranslateAnimation animate = new TranslateAnimation(0,0,
                -distance + 30,
                0);
        animate.setDuration(1000);
        animate.setFillAfter(false);
        view.startAnimation(animate);
    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng office = new LatLng(33.756898,-84.392066);
        mMap.addMarker(new MarkerOptions().position(office).title("Centennial Tower"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(office, INITIAL_ZOOM));

        setMapLongClick(mMap);
        setPoiClick(mMap);

        enableMyLocation();

        setInfoWindowClickToPanorama(mMap);
    }

    private void setMapLongClick(final GoogleMap map) {
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                String snippet = String.format(Locale.getDefault(),
                        "Lat: %1$.5f, Long: %2$.5f",
                        latLng.latitude, latLng.longitude);

                map.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(getString(R.string.dropped_pin))
                            .snippet(snippet));
            }
        });
    }

    private void setPoiClick(final GoogleMap map) {
        map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest poi) {
                Marker poiMarker = mMap.addMarker(new MarkerOptions()
                        .position(poi.latLng)
                        .title(poi.name));
                poiMarker.setTag("poi");
                poiMarker.showInfoWindow();
            }
        });
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }

    private void setInfoWindowClickToPanorama(GoogleMap map) {
        map.setOnInfoWindowClickListener(
                new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        if (marker.getTag() == "poi") {
                            StreetViewPanoramaOptions options =
                                    new StreetViewPanoramaOptions().position(
                                            marker.getPosition());
                            SupportStreetViewPanoramaFragment streetViewFragment
                                    = SupportStreetViewPanoramaFragment
                                    .newInstance(options);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container,
                                            streetViewFragment)
                                    .addToBackStack(null).commit();
                        }
                    }
                });
    }

}
