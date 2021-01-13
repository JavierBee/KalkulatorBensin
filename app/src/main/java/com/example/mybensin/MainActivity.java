package com.example.mybensin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity  {
    EditText diston,efisien;
    Button hitung;
    double harga;
    RadioGroup jenisbbm;
    TextView costfuel,neddfull;
    FloatingActionButton his,info;
    MyDatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hitung=findViewById(R.id.submit);
        //jarak=findViewById(R.id.jarak);
        jenisbbm=findViewById(R.id.jenisbensin);
        diston=findViewById(R.id.distance);
        efisien=findViewById(R.id.efisiensi);
        costfuel=findViewById(R.id.costed);
        neddfull=findViewById(R.id.fuelneed);
        his=findViewById(R.id.riwayat);
        info=findViewById(R.id.informasi);
        mydb=new MyDatabaseHelper(MainActivity.this);

        //nav to history
        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, History_activity.class);
               startActivity(intent);
            }
        });
        // nav to informasi
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(MainActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });


        //button ok
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb=new MyDatabaseHelper(MainActivity.this);
                int checkid =jenisbbm.getCheckedRadioButtonId();
                double dist;
                double efis;
                if(checkid == -1||diston.getText().toString().isEmpty()||efisien.getText().toString().isEmpty())
                {
                    Pesan.pesan(getApplicationContext(),"tolong diisi semua");
                }
                else
                {
                    dist=Double.parseDouble(diston.getText().toString());
                    efis=Double.parseDouble(efisien.getText().toString());
                    hitungall(dist,efis,checkid,mydb);
                    jenisbbm.clearCheck();
                    diston.getText().clear();
                    efisien.getText().clear();


                }


            }
        });

        //for perhitungan

    }

    private void hitungall(double dist, double efis, int checkid,MyDatabaseHelper mydb) {

        String bensin;
        double harga;
        double conv1;
        double conv2;
        double cost;
        double need;
        switch (checkid)
        {
            case R.id.pertamax:
                bensin="Pertamax";
                harga=10100;
                conv1=dist/100;
                conv2=conv1*efis;
                need=dist/efis;
                cost=conv2*harga;
                neddfull.setText(Double.toString(need));
                costfuel.setText(Double.toString(cost));
                mydb.addriwayat(dist,efis,bensin,cost,need);
                break;
            case R.id.pertalite:
                bensin="Pertalite";
                harga=8700;
                conv1=dist/100;
                conv2=conv1*efis;
                cost=conv2*harga;
                need=dist/efis;
                neddfull.setText(Double.toString(need));
                costfuel.setText(Double.toString(cost));
                mydb.addriwayat(dist,efis,bensin,cost,need);
                break;
            case R.id.pertamaxturbo:
                bensin="Pertamax Turbo";
                harga=10300;
                conv1=dist/100;
                conv2=conv1*efis;
                cost=conv2*harga;
                need=dist/efis;
                neddfull.setText(Double.toString(need));
                costfuel.setText(Double.toString(cost));
                mydb.addriwayat(dist,efis,bensin,cost,need);
                break;
        }


    }


}