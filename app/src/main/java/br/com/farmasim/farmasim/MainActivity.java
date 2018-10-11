package br.com.farmasim.farmasim;

import android.content.Intent;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import java.util.*;
import android.os.Handler;

import br.com.farmasim.farmasim._obj.Remedio;
import br.com.farmasim.farmasim._obj.Usuario;


public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();

    HashMap<String, String> minhaLista = new HashMap<String, String>();
    Usuario userData = new Usuario();
    HashMap<String, Remedio> remedio = new HashMap<String, Remedio>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Runnable r = new Runnable() {
            public void run() {
                Log.d("myTag", "loop Main: user: " + userData.user + " senha: "+ userData.senha);

                handler.postDelayed(this, 500);
            }
        };
        handler.postDelayed(r, 500);
    }

    public void carregarLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);

        intent.putExtra("MinhaLista", minhaLista);
        intent.putExtra("UserData", userData);

        startActivity(intent);

    }
}
