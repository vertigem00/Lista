package com.example.lista.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lista.dao.ItemDao;
import com.example.lista.models.ItemLista;

@Database(entities = {ItemLista.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
}
