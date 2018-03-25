package br.com.buchmuller.gerenciamentodeclientes;

/**
 * Created by joaob on 3/24/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewActivity extends AppCompatActivity {
    private String TAG = br.com.buchmuller.gerenciamentodeclientes.MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    // Conexão com JSON

    ArrayList<HashMap<String, String>> contactList;

    private String codigo_cliente, nome_cliente, email_cliente, CPF_cliente, senha_cliente, endereco, estado, municipio, telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualiza);

        contactList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new Handler().execute();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ViewActivity.this, br.com.buchmuller.gerenciamentodeclientes.EditActivity.class);

                codigo_cliente = ((TextView) view.findViewById(R.id.codigo_cliente)).getText().toString();
                nome_cliente = ((TextView) view.findViewById(R.id.nome_cliente)).getText().toString();
                email_cliente = ((TextView) view.findViewById(R.id.email_cliente)).getText().toString();
                CPF_cliente = ((TextView) view.findViewById(R.id.CPF_cliente)).getText().toString();
                senha_cliente = ((TextView) view.findViewById(R.id.senha_cliente)).getText().toString();
                endereco = ((TextView) view.findViewById(R.id.endereco)).getText().toString();
                estado = ((TextView) view.findViewById(R.id.estado)).getText().toString();
                municipio = ((TextView) view.findViewById(R.id.municipio)).getText().toString();
                telefone = ((TextView) view.findViewById(R.id.telefone)).getText().toString();



                intent.putExtra("codigo_cliente", codigo_cliente);
                intent.putExtra("nome_cliente", nome_cliente);
                intent.putExtra("email_cliente", email_cliente);
                intent.putExtra("CPF_cliente", CPF_cliente);
                intent.putExtra("senha_cliente", senha_cliente);
                intent.putExtra("endereco", endereco);
                intent.putExtra("estado", estado);
                intent.putExtra("municipio", municipio);
                intent.putExtra("telefone", telefone);

                startActivity(intent);
            }
        });

    }

    public void adicionarCliente(View view) {
        Intent intent = new Intent(ViewActivity.this, br.com.buchmuller.gerenciamentodeclientes.MainActivity.class);
        startActivity(intent);
    }


    private class Handler extends AsyncTask<Void, Void, Void> {

        private ListAdapter adapter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(ViewActivity.this);
            pDialog.setMessage("Aguarde...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            br.com.buchmuller.gerenciamentodeclientes.Matriz_JSON sh = new br.com.buchmuller.gerenciamentodeclientes.Matriz_JSON();

            // Fazendo uma solicitado a URL and recebendo resposta
            String jsonStr = sh.convertJson(br.com.buchmuller.gerenciamentodeclientes.Conexao.READ);

            Log.e(TAG, "Resposta da URL: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Recebendo o array do JSON
                    JSONArray clienteArray = jsonObj.getJSONArray("result");

                    // Looping de todos os clientes
                    for (int i = 0; i < clienteArray.length(); i++) {
                        JSONObject c = clienteArray.getJSONObject(i);

                        String codigo_cliente = c.getString("codigo_cliente");
                        String nome_cliente = c.getString("nome_cliente");
                        String email_cliente = c.getString("email_cliente");
                        String CPF_cliente = c.getString("CPF_cliente");
                        String senha_cliente = c.getString("senha_cliente");
                        String endereco = c.getString("endereco");
                        String estado = c.getString("estado");
                        String municipio = c.getString("municipio");
                        String telefone = c.getString("telefone");


                        HashMap<String, String> cliente = new HashMap<>();


                        cliente.put("codigo_cliente", codigo_cliente);
                        cliente.put("nome_cliente", nome_cliente);
                        cliente.put("email_cliente", email_cliente);
                        cliente.put("CPF_cliente", CPF_cliente);
                        cliente.put("senha_cliente", senha_cliente);
                        cliente.put("endereco", endereco);
                        cliente.put("estado", estado);
                        cliente.put("municipio", municipio);
                        cliente.put("telefone", telefone);

                        // Adicionando cliente a lista de clientes
                        contactList.add(cliente);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Erro de análise de JSON: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Erro de análise de JSON: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Não foi possivel receber JSOn do servidor.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Não foi possivel receber JSOn do servidor.",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();

            // Atualizando dados JSON analisados em ListView

            ListAdapter adapter = new SimpleAdapter(
                    ViewActivity.this, contactList,
                    R.layout.listaclientes, new String[]{"codigo_cliente", "nome_cliente", "email_cliente", "CPF_cliente", "senha_cliente", "endereco", "estado", "municipio", "telefone"}, new int[]{R.id.codigo_cliente, R.id.nome_cliente, R.id.email_cliente, R.id.CPF_cliente, R.id.senha_cliente, R.id.endereco, R.id.estado, R.id.municipio, R.id.telefone});

            lv.setAdapter(adapter);


        }

    }


}