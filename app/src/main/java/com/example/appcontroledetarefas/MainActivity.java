package com.example.appcontroledetarefas;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private BancoDeDados bancoDeDados;
    private TarefaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bancoDeDados = new BancoDeDados(this);
        List<Tarefa> listaTarefas = bancoDeDados.listaTodasTarefas();
        adapter = new TarefaAdapter(this, listaTarefas, bancoDeDados);
        ListView listViewTarefas = findViewById(R.id.listViewTarefas);
        listViewTarefas.setAdapter(adapter);

        final EditText editTextNomeTarefa = findViewById(R.id.editTextNomeTarefa);
        final EditText editTextDataInicio = findViewById(R.id.editTextDataInicio);
        final EditText editTextDataFim = findViewById(R.id.editTextDataFim);
        final EditText editTextHorario = findViewById(R.id.editTextHorario);
        final EditText editTextStatus = findViewById(R.id.editTextStatus);
        final EditText editTextResponsavel = findViewById(R.id.editTextResponsavel);

        Button btnAdicionar = findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeTarefa = editTextNomeTarefa.getText().toString();
                String dataInicio = editTextDataInicio.getText().toString();
                String dataFim = editTextDataFim.getText().toString();
                String horario = editTextHorario.getText().toString();
                String status = editTextStatus.getText().toString();
                String responsavel = editTextResponsavel.getText().toString();

                if (!nomeTarefa.isEmpty() && !dataInicio.isEmpty() && !dataFim.isEmpty() &&
                        !horario.isEmpty() && !status.isEmpty() && !responsavel.isEmpty()) {
                    Tarefa novaTarefa = new Tarefa(nomeTarefa, dataInicio, dataFim, horario, status, responsavel);
                    bancoDeDados.insereTarefa(novaTarefa);

                    editTextNomeTarefa.setText("");
                    editTextDataInicio.setText("");
                    editTextDataFim.setText("");
                    editTextHorario.setText("");
                    editTextStatus.setText("");
                    editTextResponsavel.setText("");

                    listaTarefas.clear();
                    listaTarefas.addAll(bancoDeDados.listaTodasTarefas());
                    adapter.setListaTarefas(listaTarefas);
                    adapter.notifyDataSetChanged();
                } else {
                }
            }
        });

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    int idPesquisa = Integer.parseInt(newText);
                    List<Tarefa> tarefasFiltradas = bancoDeDados.consultaTarefa(idPesquisa);
                    adapter.setListaTarefas(tarefasFiltradas);
                    adapter.notifyDataSetChanged();
                } catch (NumberFormatException e) {
                }
                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                List<Tarefa> listaTarefas = bancoDeDados.listaTodasTarefas();
                adapter.setListaTarefas(listaTarefas);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
}
