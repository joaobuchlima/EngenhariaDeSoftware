package br.com.buchmuller.gerenciamentodeclientes;

/**
 * Created by joaob on 3/24/2018.
 */

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Matriz_JSON {
    private static final String TAG = Matriz_JSON.class.getSimpleName();

    public String convertJson(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // lendo a resposta da conexão
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Exceção de formação URL: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "Exceção de protocolo: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "Exceção de IO: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exceção: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
