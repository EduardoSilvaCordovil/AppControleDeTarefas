package com.example.appcontroledetarefas;

import java.io.Serializable;

public class Tarefa implements Serializable {
    int id;
    String nomeTarefa;
    String dataInicio;
    String dataFim;
    String horario;
    String status;
    String responsavel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Tarefa() {
    }
    public Tarefa(String nomeTarefa, String dataInicio, String dataFim, String horario, String status, String responsavel) {
        this.id = id;
        this.nomeTarefa = nomeTarefa;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horario = horario;
        this.status = status;
        this.responsavel = responsavel;
    }
    public Tarefa(int id, String nomeTarefa, String dataInicio, String dataFim, String horario, String status, String responsavel) {
        this.id = id;
        this.nomeTarefa = nomeTarefa;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horario = horario;
        this.status = status;
        this.responsavel = responsavel;
    }
}