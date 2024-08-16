package com.example.lista.dao;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lista.models.ItemLista;

@Dao
public interface ItemDao {
    @Insert
    void insertAll(ItemLista... itemlista);

    @Delete
    void delete(ItemLista itemlista);

    @Query("SELECT * FROM itemlista")
    List<ItemLista> getallItemLista();

    @Query("DELETE FROM itemlista")
    void deleteAll();
}