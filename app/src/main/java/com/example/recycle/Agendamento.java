package com.example.recycle;

// Esta é uma classe Modelo (POJO) para guardar os dados de um agendamento
public class Agendamento {

    String titulo;
    String tag;
    String dataHora;
    String materiais;

    // Construtor
    public Agendamento(String titulo, String tag, String dataHora, String materiais) {
        this.titulo = titulo;
        this.tag = tag;
        this.dataHora = dataHora;
        this.materiais = materiais;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getTag() { return tag; }
    public String getDataHora() { return dataHora; }
    public String getMateriais() { return materiais; }
}