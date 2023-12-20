package com.example.appcontroledetarefas;

import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO_DADOS = 1;
    private static final String NOME_BANCO_DADOS = "gerenciaTarefas";
    private static final String TABELA_TAREFAS = "TAREFAS";
    private static final String CAMPO_ID = "id";
    private static final String CAMPO_NOME_TAREFA = "nomeTarefa";
    private static final String CAMPO_DATA_INICIO = "dataInicio";
    private static final String CAMPO_DATA_FIM = "dataFim";
    private static final String CAMPO_HORARIO = "horario";
    private static final String CAMPO_STATUS = "status";
    private static final String CAMPO_NOME_AUTOR = "nomeAutor";

    public BancoDeDados(Context context) {
        super(context, NOME_BANCO_DADOS, null, VERSAO_BANCO_DADOS);
    }

    public void onCreate(SQLiteDatabase banco) {
        String criaTabelaTarefas = "CREATE TABLE " + TABELA_TAREFAS + "(" +
                CAMPO_ID + " INTEGER PRIMARY KEY, " +
                CAMPO_NOME_TAREFA + " TEXT, " +
                CAMPO_DATA_INICIO + " TEXT, " +
                CAMPO_DATA_FIM + " TEXT, " +
                CAMPO_HORARIO + " TEXT, " +
                CAMPO_STATUS + " TEXT, " +
                CAMPO_NOME_AUTOR + " TEXT" + ")";
        banco.execSQL(criaTabelaTarefas);
    }

    public void onUpgrade(SQLiteDatabase banco, int i, int i1) {
        banco.execSQL("DROP TABLE IF EXISTS " + TABELA_TAREFAS);
        onCreate(banco);
    }

    public void deletar(SQLiteDatabase banco) {
        banco.execSQL("DROP TABLE IF EXISTS " + TABELA_TAREFAS);
    }

    public void insereTarefa(Tarefa tarefa) {
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOME_TAREFA, tarefa.getNomeTarefa());
        values.put(CAMPO_DATA_INICIO, tarefa.getDataInicio());
        values.put(CAMPO_DATA_FIM, tarefa.getDataFim());
        values.put(CAMPO_HORARIO, tarefa.getHorario());
        values.put(CAMPO_STATUS, tarefa.getStatus());
        values.put(CAMPO_NOME_AUTOR, tarefa.getResponsavel());
        banco.insert(TABELA_TAREFAS, null, values);
        banco.close();
    }

    public List<Tarefa> consultaTarefa(int id) {
        List<Tarefa> listaTarefas = new ArrayList<>();
        SQLiteDatabase banco = this.getReadableDatabase();
        Cursor cursor = banco.query(TABELA_TAREFAS, new String[]{CAMPO_ID, CAMPO_NOME_TAREFA, CAMPO_DATA_INICIO, CAMPO_DATA_FIM, CAMPO_HORARIO, CAMPO_STATUS, CAMPO_NOME_AUTOR},
                CAMPO_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Tarefa tarefa = new Tarefa(Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6));
                    listaTarefas.add(tarefa);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return listaTarefas;
    }

    public List<Tarefa> listaTodasTarefas() {
        List<Tarefa> listaTarefas = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABELA_TAREFAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(Integer.parseInt(cursor.getString(0)));
                tarefa.setNomeTarefa(cursor.getString(1));
                tarefa.setDataInicio(cursor.getString(2));
                tarefa.setDataFim(cursor.getString(3));
                tarefa.setHorario(cursor.getString(4));
                tarefa.setStatus(cursor.getString(5));
                tarefa.setResponsavel(cursor.getString(6));
                listaTarefas.add(tarefa);
            } while (cursor.moveToNext());
        }
        return listaTarefas;
    }

    public int atualizaTarefa(Tarefa tarefa) {
        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOME_TAREFA, tarefa.getNomeTarefa());
        values.put(CAMPO_DATA_INICIO, tarefa.getDataInicio());
        values.put(CAMPO_DATA_FIM, tarefa.getDataFim());
        values.put(CAMPO_HORARIO, tarefa.getHorario());
        values.put(CAMPO_STATUS, tarefa.getStatus());
        values.put(CAMPO_NOME_AUTOR, tarefa.getResponsavel());
        return banco.update(TABELA_TAREFAS, values, CAMPO_ID + "=?", new String[]{String.valueOf(tarefa.getId())});
    }

    public void deletaTarefas(Tarefa tarefa) {
        SQLiteDatabase banco = this.getWritableDatabase();
        banco.delete(TABELA_TAREFAS, CAMPO_ID + "=?", new String[]{String.valueOf(tarefa.getId())});
        banco.close();
    }

    public int consultaQuantidadeTarefas() {
        String countQuery = "SELECT * FROM " + TABELA_TAREFAS;
        SQLiteDatabase banco = this.getReadableDatabase();
        Cursor cursor = banco.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
