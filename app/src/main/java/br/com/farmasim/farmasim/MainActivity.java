package br.com.farmasim.farmasim;

import android.content.Intent;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import java.util.*;

import br.com.farmasim.farmasim._obj.Remedio;
import br.com.farmasim.farmasim._obj.Usuario;


public class MainActivity extends AppCompatActivity {
    HashMap<String, String> minhaLista = new HashMap<String, String>();
    Usuario userData = new Usuario();
    HashMap<String, Remedio> remedio = new HashMap<String, Remedio>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void carregarLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);

        intent.putExtra("MinhaLista", minhaLista);
        intent.putExtra("UserData", userData);

        startActivity(intent);

    }
}
