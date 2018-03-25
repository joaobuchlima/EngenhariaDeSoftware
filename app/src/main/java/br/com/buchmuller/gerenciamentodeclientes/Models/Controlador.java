package br.com.buchmuller.gerenciamentodeclientes;

/**
 * Created by joaob on 3/24/2018.
 */

import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class Controlador {
    // Criando um controlador de solicitação POST
    public String requisicao(String requestUrl, HashMap<String, String> requestedDataParams) throws UnsupportedEncodingException {

        // Definir um URL vazio no sistema
        URL url;


        // Definindo um construtor de string para armazenar o resultado
        StringBuilder stringBuilder = new StringBuilder();

        try {
            // Inicializando a URL
            url = new URL(requestUrl);

            // Cliando uma conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Definindo Método
            connection.setRequestMethod(br.com.buchmuller.gerenciamentodeclientes.Conexao.POST_METHOD);
            // Defininco conexão
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            // Definindo input e output
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Criando uma url como String com parâmetros
            StringBuilder url_string = new StringBuilder();

            boolean ampersand = false;
            for (Map.Entry<String, String> params : requestedDataParams.entrySet() ){
                if (ampersand)
                    url_string.append("&");
                else
                    ampersand = true;

                url_string.append(URLEncoder.encode(params.getKey(), "UTF-8"));
                url_string.append("=");
                url_string.append(URLEncoder.encode(params.getValue(), "UTF-8"));
            }
            Log.d("Final Url===", url_string.toString());



            //Criando um fluxo de saída
            OutputStream outputStream = connection.getOutputStream();

            // Escrevendo um fluxo de saída
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(url_string.toString());

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();


            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));


                String result;
                while ((result = bufferedReader.readLine()) != null) {
                    stringBuilder.append(result);
                }


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    // Obtendo solicitação
    public String getRequestHandler(String requestUrl){

        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(requestUrl);
            // Abrindo conexão
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));


            String result;
            while ((result = bufferedReader.readLine()) != null) {
                stringBuilder.append(result + "\n");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
