package br.com.buchmuller.gerenciamentodeclientes;

/**
 * Created by joaob on 3/24/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class EditActivity extends AppCompatActivity {

    private EditText mcodigo_cliente, mnome_cliente, memail_cliente, mCPF_cliente, msenha_cliente, mendereco, mestado, mmunicipio, mtelefone;
    private Button mBtnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edita);

        // Inicializando a tela de ediação de cliente
        mcodigo_cliente = (EditText) findViewById(R.id.codigo_cliente);
        mnome_cliente = (EditText) findViewById(R.id.nome_cliente);
        memail_cliente = (EditText) findViewById(R.id.email_cliente);
        mCPF_cliente = (EditText) findViewById(R.id.CPF_cliente);
        msenha_cliente = (EditText) findViewById(R.id.senha_cliente);
        mendereco = (EditText) findViewById(R.id.endereco);
        mestado = (EditText) findViewById(R.id.estado);
        mmunicipio = (EditText) findViewById(R.id.municipio);
        mtelefone = (EditText) findViewById(R.id.telefone);
        mBtnAdd = (Button) findViewById(R.id.btn_add);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            mcodigo_cliente.setText(bundle.getString("codigo_cliente"));
            mnome_cliente.setText(bundle.getString("nome_cliente"));
            memail_cliente.setText(bundle.getString("email_cliente"));
            mCPF_cliente.setText(bundle.getString("CPF_cliente"));
            msenha_cliente.setText(bundle.getString("senha_cliente"));
            mendereco.setText(bundle.getString("endereco"));
            mestado.setText(bundle.getString("estado"));
            mmunicipio.setText(bundle.getString("municipio"));
            mtelefone.setText(bundle.getString("telefone"));
        }

    }


    public void atualizarCliente(View view) {
        String codigo_cliente = mcodigo_cliente.getText().toString();
        String nome_cliente = mnome_cliente.getText().toString();
        String email_cliente = memail_cliente.getText().toString();
        String CPF_cliente = mCPF_cliente.getText().toString();
        String senha_cliente = msenha_cliente.getText().toString();
        String endereco = mendereco.getText().toString();
        String estado = mestado.getText().toString();
        String municipio = mmunicipio.getText().toString();
        String telefone = mtelefone.getText().toString();

        HashMap<String, String> requestedParams = new HashMap<>();
        requestedParams.put("codigo_cliente", codigo_cliente);
        requestedParams.put("nome_cliente", nome_cliente);
        requestedParams.put("email_cliente", email_cliente);
        requestedParams.put("CPF_cliente", CPF_cliente);
        requestedParams.put("senha_cliente", senha_cliente);
        requestedParams.put("endereco", endereco);
        requestedParams.put("estado", estado);
        requestedParams.put("municipio", municipio);
        requestedParams.put("telefone", telefone);
        Log.d("HashMap", requestedParams.get("codigo_cliente"));
        Toast.makeText(getApplicationContext(), "Cliente atualizado com sucesso: " + requestedParams.get("nome_cliente"), Toast.LENGTH_LONG).show();

        br.com.buchmuller.gerenciamentodeclientes.Requisicao postRequestHandler = new br.com.buchmuller.gerenciamentodeclientes.Requisicao(br.com.buchmuller.gerenciamentodeclientes.Conexao.UPDATE, requestedParams);
        postRequestHandler.execute();

        listarClientes(view);
    }

    public void excluirCliente(View view) {
        String codigo_cliente = mcodigo_cliente.getText().toString();


        HashMap<String, String> requestedParams = new HashMap<>();
        requestedParams.put("codigo_cliente", codigo_cliente);

        Log.d("HashMap", requestedParams.get("codigo_cliente"));
        Toast.makeText(getApplicationContext(), "Cliente excluido com sucesso " + requestedParams.get("nome_cliente"), Toast.LENGTH_LONG).show();


        br.com.buchmuller.gerenciamentodeclientes.Requisicao postRequestHandler = new br.com.buchmuller.gerenciamentodeclientes.Requisicao(br.com.buchmuller.gerenciamentodeclientes.Conexao.DELETE, requestedParams);
        postRequestHandler.execute();

        listarClientes(view);
    }

    public void listarClientes(View view) {
        Intent intent = new Intent(EditActivity.this, br.com.buchmuller.gerenciamentodeclientes.ViewActivity.class);
        startActivity(intent);
    }
}
