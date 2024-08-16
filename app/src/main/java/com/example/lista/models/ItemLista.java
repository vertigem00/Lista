package com.example.lista.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "itemlista")
public class ItemLista {
    // : Id, Nome, Curso e Idade
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "titulo")
    public String titulo;
    @ColumnInfo(name = "descricao")
    public String descricao;


    public ItemLista(String titulo, String descricao){
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @NonNull
    @Override
    public String toString() {
        String nomeRetorno = this.uid + " | " + this.titulo + " | " + this.descricao;
        return nomeRetorno ;
    }
}
