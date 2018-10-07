package br.com.farmasim.farmasim;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.*;


public class MainActivity extends AppCompatActivity {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void carregarLogin(View view) {

        hashMap.put("key", new String("value"));
        Intent intent = new Intent(this, LoginActivity.class);

        intent.putExtra("map", hashMap);
        startActivity(intent);

    }
}
