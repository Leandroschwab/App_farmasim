package br.com.farmasim.farmasim._functions;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import br.com.farmasim.farmasim._obj.Remedio;
import br.com.farmasim.farmasim._obj.Usuario;

public class Salvar {

    public static void  saveUserdata(Usuario userdata,Context context){
        try {
            Log.d("myTag","saveUserdata: started.");
            FileOutputStream fos = context.openFileOutput("userdata.ser",Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userdata);
            oos.close();
            fos.close();
            Log.d("myTag","saveUserdata: finished.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Usuario loadUsuario(Context context){
        Log.d("myTag","loadUsuario: started");
        Usuario userdata = new Usuario();
        try {

            FileInputStream fis = context.openFileInput("userdata.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userdata= (Usuario) ois.readObject();
            ois.close();
            fis.close();
            Log.d("myTag","loadUsuario: usuario resgatado com sucesso");
            return userdata;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("myTag","loadUsuario: File userdata nao encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userdata;
    }

    public static void  saveRemediosHashMap(HashMap<String, Remedio> remedio, Context context){
        try {
            Log.d("myTag","saveRemediosHashMap: started.");
            FileOutputStream fos = context.openFileOutput("remedios.ser",Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(remedio);
            oos.close();
            fos.close();
            Log.d("myTag","saveRemediosHashMap: finished.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static HashMap<String, Remedio> loadRemediosHashMap(Context context){
        Log.d("myTag","loadUsuario: started");
        HashMap<String, Remedio> remedio = new HashMap<String, Remedio>();
        try {

            FileInputStream fis = context.openFileInput("remedios.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            remedio= (HashMap<String, Remedio>) ois.readObject();
            ois.close();
            fis.close();
            Log.d("myTag","loadRemediosHashMap: remedio resgatado com sucesso");
            return remedio;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("myTag","loadRemediosHashMap: File remedio nao encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return remedio;
    }
    public static void clearData(Context context){
        Log.d("myTag","clearData: started");
        context.deleteFile("remedios.ser");
        context.deleteFile("userdata.ser");
    }
}
