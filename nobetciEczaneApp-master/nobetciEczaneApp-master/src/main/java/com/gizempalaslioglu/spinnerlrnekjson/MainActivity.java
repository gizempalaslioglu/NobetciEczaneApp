package com.gizempalaslioglu.spinnerlrnekjson;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gizempalaslioglu.spinnerlrnekjson.JSONTaker.*;

import androidx.appcompat.app.AppCompatActivity;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String ilceler[] = {"Adalar", "Arnavutköy", "Ataşehir", "Avcılar", "Bağcılar", "Bahçelievler", "Bakırköy", "Başakşehir",
                "Bayrampaşa", "Beşiktaş", "Beykoz", "Beylikdüzü", "Beyoğlu", "Büyükçekmece", "Çatalca", "Çekmeköy", "Esenler", "Esenyurt",
                "Eyüp", "Fatih", "Gaziosmanpaşa", "Güngören", "Kadıköy", "Kağıthane", "Kartal", "Küçükçekmece", "Maltepe", "Pendik", "Sancaktepe",
                "Sarıyer", "Şile", "Silivri", "Şişli", "Sultanbeyli", "Sultangazi", "Tuzla", "Ümraniye", "Üsküdar", "Zeytinburnu"};

        final Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ilceler);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        Button listeleButon = findViewById(R.id.listeleButon);
        listeleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = spinner.getSelectedItem().toString();
                getEczane(item);
            }
        });
    }

    public void getEczane(String ilce) {
        JSONTaker js_tk = new JSONTaker();
        try {
            ArrayList<HashMap<String, String>> userList = new ArrayList<>();
            ListView listview = findViewById(R.id.pharmacy);

            JSONObject jsObj = new JSONObject(js_tk.getListData("İstanbul", ilce));

            JSONArray jsArray = jsObj.getJSONArray("result");
            for (int i = 0; i < jsArray.length(); i++) {
                HashMap<String, String> stu = new HashMap<>();

                JSONObject obj = jsArray.getJSONObject(i);
                stu.put("name", obj.getString("name"));
                stu.put("address", obj.getString("address"));
                userList.add(stu);//arraylist e ekle
            }
            ListAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, userList, R.layout.list, new String[]{"name", "address"},
                    new int[]{R.id.name, R.id.address});
            listview.setAdapter(simpleAdapter);
        } catch (JSONException | UnirestException ex) {
            Log.e("JsonParser ", "Exception", ex);
        }
    }


}