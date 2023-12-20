package com.example.appcontroledetarefas;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;

public class TarefaAdapter extends BaseAdapter {

    private List<Tarefa> listaTarefas;
    private Context context;
    private BancoDeDados bancoDeDados;

    public TarefaAdapter(Context context, List<Tarefa> listaTarefas, BancoDeDados bancoDeDados) {
        this.context = context;
        this.listaTarefas = listaTarefas;
        this.bancoDeDados = bancoDeDados;
    }

    public void setListaTarefas(List<Tarefa> newList) {
        this.listaTarefas = newList;
    }

    public TarefaAdapter(SearchView.OnQueryTextListener onQueryTextListener, List<Tarefa> listaTarefas, BancoDeDados bancoDeDados) {
    }

    @Override
    public int getCount() {
        return listaTarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_tarefa, null);
        }

        TextView textViewItem = view.findViewById(R.id.textViewItem);
        Button btnEditar = view.findViewById(R.id.btnEditar);
        Button btnExcluir = view.findViewById(R.id.btnExcluir);

        final Tarefa tarefa = (Tarefa) getItem(position);

        textViewItem.setText(String.format("ID: %d%nNome da Tarefa: %s%nData de Início: %s%nData de Fim: %s%nHorário: %s%nStatus: %s%nResponsável: %s", tarefa.getId(), tarefa.getNomeTarefa(), tarefa.getDataInicio(), tarefa.getDataFim(), tarefa.getHorario(), tarefa.getStatus(), tarefa.getResponsavel()));

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarefa tarefaSelecionada = (Tarefa) getItem(position);
                bancoDeDados.deletaTarefas(tarefaSelecionada);
                listaTarefas.remove(position);
                notifyDataSetChanged();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditarTarefaActivity.class);
                intent.putExtra("Tarefa", tarefa);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
