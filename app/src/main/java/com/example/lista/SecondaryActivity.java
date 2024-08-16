package com.example.lista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class SecondaryActivity extends AppCompatActivity {

    ItemDao itemDao;
    Button btnSave;
    EditText titulo;
    EditText descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_activity);

        btnSave = findViewById(R.id.btn_save);
        titulo = findViewById(R.id.txt_titulo);
        descricao = findViewById(R.id.txt_descricao);


        AppDatabase appDatabase = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "db_its")
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        itemDao = appDatabase.itemDao();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloS = titulo.getText().toString().trim();
                String descricaoS = descricao.getText().toString().trim();

                if(tituloS.isEmpty() || descricaoS.isEmpty()){
                    Toast.makeText(v.getContext(), "Preencha todos os campos para inserir uma tarefa", Toast.LENGTH_SHORT).show();

                }else{
                    ItemLista item = new ItemLista(tituloS, descricaoS);

                    itemDao.insertAll(item);

                    Intent call = new Intent(SecondaryActivity.this, MainActivity.class);
                    startActivity(call);
                }
            }
        });
    }
}
