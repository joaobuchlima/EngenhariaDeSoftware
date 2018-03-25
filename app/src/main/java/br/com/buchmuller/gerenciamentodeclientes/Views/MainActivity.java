package br.com.buchmuller.gerenciamentodeclientes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText mnome_cliente, memail_cliente, mCPF_cliente, msenha_cliente, mendereco, mestado, mmunicipio, mtelefone;
    private Button mBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando a tela de edição de cliente
        mnome_cliente = (EditText) findViewById(R.id.nome_cliente);
        memail_cliente = (EditText) findViewById(R.id.email_cliente);
        mCPF_cliente = (EditText) findViewById(R.id.CPF_cliente);
        msenha_cliente = (EditText) findViewById(R.id.senha_cliente);
        mendereco = (EditText) findViewById(R.id.endereco);
        mestado = (EditText) findViewById(R.id.estado);
        mmunicipio = (EditText) findViewById(R.id.municipio);
        mtelefone = (EditText) findViewById(R.id.telefone);

        mBtnAdd = (Button) findViewById(R.id.btn_add);

    }

    // Cliando Cliente
    public void incluirCliente(View view){

        String nome_cliente = mnome_cliente.getText().toString();
        String email_cliente = memail_cliente.getText().toString();
        String CPF_cliente = mCPF_cliente.getText().toString();
        String senha_cliente = msenha_cliente.getText().toString();
        String endereco = mendereco.getText().toString();
        String estado = mestado.getText().toString();
        String municipio = mmunicipio.getText().toString();
        String telefone = mtelefone.getText().toString();


        HashMap<String, String> requestedParams = new HashMap<>();
        requestedParams.put("nome_cliente", nome_cliente);
        requestedParams.put("email_cliente", email_cliente);
        requestedParams.put("CPF_cliente", CPF_cliente);
        requestedParams.put("senha_cliente", senha_cliente);
        requestedParams.put("endereco", endereco);
        requestedParams.put("estado", estado);
        requestedParams.put("municipio", municipio);
        requestedParams.put("telefone", telefone);
        Log.d("HashMap", requestedParams.get("nome_cliente"));
        Toast.makeText(getApplicationContext(), "Cliente adicionado com sucesso: " + requestedParams.get("nome_cliente"), Toast.LENGTH_LONG).show();


        br.com.buchmuller.gerenciamentodeclientes.Requisicao requisicao = new br.com.buchmuller.gerenciamentodeclientes.Requisicao(br.com.buchmuller.gerenciamentodeclientes.Conexao.CREATE_URL, requestedParams);
        requisicao.execute();

        listaCliente(view);
    }

    public void listaCliente(View view) {
        Intent intent = new Intent(MainActivity.this, br.com.buchmuller.gerenciamentodeclientes.ViewActivity.class);
        startActivity(intent);
    }
}
