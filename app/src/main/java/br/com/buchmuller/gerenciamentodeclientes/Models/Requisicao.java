package br.com.buchmuller.gerenciamentodeclientes;

/**
 * Created by joaob on 3/24/2018.
 */

import android.os.AsyncTask;
import java.io.UnsupportedEncodingException;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Requisicao extends AsyncTask<Void, Void, String> {
    // Requisição de URL
    String url;

    HashMap<String, String> requestedParams;

    Requisicao(String url, HashMap<String, String> params){
        this.url = url;
        this.requestedParams = params;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(Void... voids) {

        // Enviando uma requisição de POST
        br.com.buchmuller.gerenciamentodeclientes.Controlador controlador = new br.com.buchmuller.gerenciamentodeclientes.Controlador();
        try {
            String s = controlador.requisicao(url, requestedParams);

            return s.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}

