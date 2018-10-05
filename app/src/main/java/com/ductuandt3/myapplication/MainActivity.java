package com.ductuandt3.myapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.ductuandt3.myapplication.adapter.CardAdapter;
import com.ductuandt3.myapplication.entity.CardEntity;
import com.ductuandt3.myapplication.sqlLite.DBCard;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<CardEntity> cardEntityArrayList;
    private CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValue();
        DBCard dbCard = new DBCard(this);
//        dbCard.deleteTable();
        dbCard.createTable();
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        cardEntityArrayList = dbCard.getAllCardList();
        Log.d("LDT", "cardEntityArrayList.size : "  + cardEntityArrayList.size());
        setCardAdapter();
    }

    private void initValue(){
        mRecyclerView = findViewById(R.id.addCardActivity_listView);
        cardEntityArrayList = new ArrayList<CardEntity>();
    }

    private void setCardAdapter(){
        adapter = new CardAdapter(getApplicationContext(), cardEntityArrayList);
        adapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(adapter);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.material_light_theme_form:
                startActivity(new Intent(this, LightThemeActivity.class));
                break;
            default:
                break;
        }
    }
}
