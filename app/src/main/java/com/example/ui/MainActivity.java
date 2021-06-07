package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> expiry = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        textView = findViewById(R.id.count);

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("users");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject userData = jsonArray.getJSONObject(i);
                name.add(userData.getString("name"));
                date.add(userData.getString("date"));
                expiry.add(userData.getString("expires"));
                title.add(userData.getString("title"));
                String count = "(" + jsonArray.length() + ")";
                textView.setText(count);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HelperAdapter adapter = new HelperAdapter(MainActivity.this,title,name,date,expiry,MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private String JsonDataFromAsset() {

        String json = null;

        try {

            InputStream inputStream = getAssets().open("user.json");
            int size = inputStream.available();
            byte[] bufferData = new byte[size];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData,"UTF-8");


        }catch (Exception e){
            e.printStackTrace();
        }

        return json;
    }
}