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

public class CadastroActivity extends AppCompatActivity {
    private EditText edNome;
    private EditText edWhatsapp;
    private EditText edCpf;
    private EditText edUsuario;
    private EditText edSenha;
    private PessoaController pcontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        iniciaComponentes();
        pcontroller = new PessoaController();
    }

    public void btVoltarOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void btSalvarOnCick(View view) {
        Pessoa pessoa =
                new Pessoa(
                        PessoaController.getInstance().retornaProximoId(),
                        edNome.getText().toString(),
                        edCpf.getText().toString(),
                        edWhatsapp.getText().toString(),
                        edUsuario.getText().toString(),
                        edSenha.getText().toString()
                );
        if (pcontroller.insert(pessoa) != null)
            Toast.makeText(this, "Cadastro efetuado!", Toast.LENGTH_SHORT).show();
        else Log.e("CadastroActivity", "Erro ao efetuar cadastro!");
    }
    //TODO Acho q não vai precisar desse pq já fiz as validações na API mas tem q ver como trabalhar com as respostas dela;
//    private void validaCamposVazios() {
//        if (edNome.getText().toString().isEmpty()) {
//            Toast.makeText(this, "Campo nome é obrigatório", Toast.LENGTH_SHORT).show();
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

}