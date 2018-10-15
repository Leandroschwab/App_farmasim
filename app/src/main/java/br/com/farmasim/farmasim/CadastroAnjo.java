package br.com.farmasim.farmasim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import br.com.farmasim.farmasim._functions.Salvar;
import br.com.farmasim.farmasim._obj.Remedio;
import br.com.farmasim.farmasim._obj.Usuario;

public class CadastroAnjo extends AppCompatActivity {
    Usuario userData;
    HashMap<String, Remedio> remedio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_anjo);
        this.userData = Salvar.loadUsuario(getApplicationContext());
        this.remedio = Salvar.loadRemediosHashMap(getApplicationContext());
    }

    void salvarAnjoButton(View view){
        Log.d("myTag","salvarAnjoButton: started.");
        EditText txtTelefone = (EditText) findViewById(R.id.telefone);
        userData.anjo = txtTelefone.getText().toString();
        Salvar.saveUserdata(userData,getApplicationContext());
        Log.d("myTag","userData.anjo: " + userData.anjo);
        finish();
    }
}
