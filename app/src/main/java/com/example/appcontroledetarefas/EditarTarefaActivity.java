package com.example.appcontroledetarefas;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditarTarefaActivity extends AppCompatActivity {
    private EditText editTextNomeTarefa;
    private EditText editTextDataInicio;
    private EditText editTextDataFim;
    private EditText editTextHorario;
    private EditText editTextStatus;
    private EditText editTextResponsavel;
    private Button btnSalvarEdicao;

    private BancoDeDados bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarefa);

        bancoDeDados = new BancoDeDados(this);

        editTextNomeTarefa = findViewById(R.id.editTextNomeTarefa);
        editTextDataInicio = findViewById(R.id.editTextDataInicio);
        editTextDataFim = findViewById(R.id.editTextDataFim);
        editTextHorario = findViewById(R.id.editTextHorario);
        editTextStatus = findViewById(R.id.editTextStatus);
        editTextResponsavel = findViewById(R.id.editTextResponsavel);
        btnSalvarEdicao = findViewById(R.id.btnSalvarEdicao);

        Tarefa tarefa = (Tarefa) getIntent().getSerializableExtra("Tarefa");

        if (tarefa != null) {
            editTextNomeTarefa.setText(tarefa.getNomeTarefa());
            editTextDataInicio.setText(tarefa.getDataInicio());
            editTextDataFim.setText(tarefa.getDataFim());
            editTextHorario.setText(tarefa.getHorario());
            editTextStatus.setText(tarefa.getStatus());
            editTextResponsavel.setText(tarefa.getResponsavel());
        }

        btnSalvarEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String novoNomeTarefa = editTextNomeTarefa.getText().toString();
                String novaDataInicio = editTextDataInicio.getText().toString();
                String novaDataFim = editTextDataFim.getText().toString();
                String novoHorario = editTextHorario.getText().toString();
                String novoStatus = editTextStatus.getText().toString();
                String novoResponsavel = editTextResponsavel.getText().toString();

                tarefa.setNomeTarefa(novoNomeTarefa);
                tarefa.setDataInicio(novaDataInicio);
                tarefa.setDataFim(novaDataFim);
                tarefa.setHorario(novoHorario);
                tarefa.setStatus(novoStatus);
                tarefa.setResponsavel(novoResponsavel);

                int rowsAffected = bancoDeDados.atualizaTarefa(tarefa);

                if (rowsAffected > 0) {
                    Toast.makeText(EditarTarefaActivity.this, "Tarefa editada com sucesso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.putExtra("TarefaAtualizada", tarefa);
                    setResult(RESULT_OK, intent);

                    finish();
                } else {
                    Toast.makeText(EditarTarefaActivity.this, "Falha ao editar a tarefa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
