package com.example.lista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.lista.dao.ItemDao;
import com.example.lista.database.AppDatabase;
import com.example.lista.models.ItemLista;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvItens;
    Button btnNew;


    List<ItemLista> itemBd;
    ItemDao itemDao;
    ArrayList<Item> itemList = new ArrayList<Item>();
    ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnNew = findViewById(R.id.btn_new);

        rvItens = findViewById(R.id.rv_itens);
        itemArrayAdapter = new ItemArrayAdapter(R.layout.item, itemList);

        rvItens = (RecyclerView) findViewById(R.id.rv_itens);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());

        rvItens.addItemDecoration(dividerItemDecoration);

        rvItens.setAdapter(itemArrayAdapter);

        AppDatabase appDatabase = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "db_its")
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();


        itemDao = appDatabase.itemDao();
        itemBd = itemDao.getallItemLista();
        for(ItemLista p : itemBd){
            itemList.add(new Item(p.titulo, p.descricao));
        }

        swipeToDelete();

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(MainActivity.this, SecondaryActivity.class);
                startActivity(call);
            }
        });
    }


    private void swipeToDelete(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // get position item in recyclerview
                int position = viewHolder.getAdapterPosition();

                ItemLista pessoa = itemBd.get(position);

                itemDao.delete(pessoa);

                itemBd.remove(position);

                itemList.remove(position);


                itemArrayAdapter.notifyItemRemoved(position);
                itemArrayAdapter.notifyItemRangeChanged(position, itemList.size());
            }
        }).attachToRecyclerView(rvItens);
    }

}