package br.com.farmasim.farmasim;

import android.content.Intent;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import java.util.*;

import br.com.farmasim.farmasim._functions.Salvar;
import br.com.farmasim.farmasim._obj.Remedio;
import br.com.farmasim.farmasim._obj.Usuario;


public class MainActivity extends AppCompatActivity {
    Usuario userData;
    HashMap<String, Remedio> remedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.userData = Salvar.loadUsuario(getApplicationContext());
        this.remedio = Salvar.loadRemediosHashMap(getApplicationContext());
        Log.d("myTag","loadUsuario: finished");
        if (userData.user!=null){
            carregarLogado();
        }
    }

    public void carregarLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
    public void carregarLogado() {
        Intent intent = new Intent(this, LogadoActivity.class);
        startActivity(intent);

    }
    public void LoadDataButton(View view) {
        userData = Salvar.loadUsuario(getApplicationContext());
        Log.d("myTag","loadUsuario: finished");

    }
}
