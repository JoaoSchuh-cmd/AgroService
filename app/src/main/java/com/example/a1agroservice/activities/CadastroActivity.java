package com.example.a1agroservice.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a1agroservice.R;
import com.example.a1agroservice.controllers.PessoaController;
import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.singleton.Login;

public class CadastroActivity extends AppCompatActivity {
    private EditText edNome;
    private EditText edWhatsapp;
    private EditText edCpf;
    private EditText edUsuario;
    private EditText edSenha;
    private PessoaController pessoaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        iniciaComponentes();
        pessoaController = new PessoaController(this);
    }

    public void btVoltarOnClick(View view) {
        abrirLoginPage();
    }

    public void btSalvarOnCick(View view) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(PessoaController.getInstance(this).retornaProximoId());
        pessoa.setNome(edNome.getText().toString());
        pessoa.setCpf(edCpf.getText().toString());
        pessoa.setUsuario(edUsuario.getText().toString());
        pessoa.setSenha(edSenha.getText().toString());
        pessoa.setCelular(edWhatsapp.getText().toString());

        pessoaController.insert(this, pessoa);

//        if (pessoaController.getByUsuario(edUsuario.getText().toString()) != null) {
//            limpaCampos(); // TODO Quando tiver a HomePage, pode excluir isso
////            abrirHomePage();
//        } else {
            limpaCampos();
            abrirLoginPage();
//        };
    }

    public void abrirLoginPage() {
        Login.getUsuarioLogado(edUsuario.getText().toString(), edSenha.getText().toString());

        Intent loginPage = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(loginPage);
    }

    public void abrirHomePage() {
//        Intent homePage = new Intent(getApplicationContext(), HomeActivity.class);
//        startActivity(homePage);
    }

    //TODO Acho q n??o vai precisar desse pq j?? fiz as valida????es na API mas tem q ver como trabalhar com as respostas dela;
//    private void validaCamposVazios() {
//        if (edNome.getText().toString().isEmpty()) {
//            Toast.makeText(this, "Campo nome ?? obrigat??rio", Toast.LENGTH_SHORT).show();
//            return;
//        }
//    }
    private void iniciaComponentes() {
        edNome = findViewById(R.id.edNome);
        edWhatsapp = findViewById(R.id.edWhatsapp);
        edCpf = findViewById(R.id.edCpf);
        edUsuario = findViewById(R.id.edUsuario);
        edSenha = findViewById(R.id.edSenha);
    }

    private void limpaCampos() {
        edNome.setText("");
        edWhatsapp.setText("");
        edCpf.setText("");
        edUsuario.setText("");
        edSenha.setText("");
    }
}