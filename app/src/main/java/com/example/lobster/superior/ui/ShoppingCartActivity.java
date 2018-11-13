package com.example.lobster.superior.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lobster.superior.R;
import com.example.lobster.superior.adapter.ShoppingCartAdapter;
import com.example.lobster.superior.db.NoteLitepal;
import com.example.lobster.superior.model.Goods;
import com.example.lobster.superior.model.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements ShoppingCartAdapter.OnRecyclerViewItemDeleteClickListener, ShoppingCartAdapter.OnRecyclerViewItemAddClickListener,
        ShoppingCartAdapter.OnRecyclerViewItemReduceClickListener {
    public static List<Goods> goodsList = new ArrayList<Goods>();
    private EditText name;
    private TextView Sub, Tax, Total;
    private ShoppingCartAdapter adapter;
    private Button confirm;

    public static String date2string(Date date) {
        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        strDate = sdf.format(date);
        return strDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        initGoods();
        name = (EditText) findViewById(R.id.note_name);
        List<Note> noteList = NoteLitepal.queryNoteAll();
        if (noteList != null && noteList.size() != 0) {
            for (int i = 0; i < noteList.size(); i++) {
                Log.d("litepal", noteList.get(i).getName());
                Log.d("litepal", noteList.get(i).getTime());
                Log.d("litepal", noteList.get(i).getMoney());
            }
        }

        name.setText("Receipt " + (NoteLitepal.queryNoteAll().size() + 1));
        Sub = (TextView) findViewById(R.id.Sub);
        Tax = (TextView) findViewById(R.id.Tax);
        Total = (TextView) findViewById(R.id.Total);
        confirm = (Button) findViewById(R.id.confirm);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ShoppingCartAdapter(this, goodsList, this, this, this);
        recyclerView.setAdapter(adapter);
        calculation();
    }

    public void confirm(View v) {
        NoteLitepal.createNewNote(new Note(name.getText().toString(), date2string(new Date()), Total.getText().toString()));
        Intent intent = new Intent(ShoppingCartActivity.this, RecordActivity.class);
        startActivity(intent);
        finish();
    }

    public void calculation() {
        double sub = 0;
        double tax = 0;
        double total = 0;
        for (int i = 0; i < goodsList.size(); i++) {
            sub = sub + Integer.valueOf(goodsList.get(i).getPrice()) * Integer.valueOf(goodsList.get(i).getNumber());
        }
        tax = sub * 0.09;
        total = sub + tax;
        Sub.setText("Sub Total: " + String.valueOf(sub) + "$");
        Tax.setText("Tax: " + String.valueOf(tax) + "$");
        Total.setText("Total: " + String.valueOf(total) + "$");
    }

    @Override
    public void onItemDeleteClick(View view, Goods goods) {
        goodsList.remove(goods);
        calculation();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemAddClick(View view, int position) {
        goodsList.get(position).setNumber((Integer.parseInt(goodsList.get(position).getNumber()) + 1) + "");
        calculation();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemReduceClick(View view, int position) {
        goodsList.get(position).setNumber((Integer.parseInt(goodsList.get(position).getNumber()) - 1) + "");
        calculation();
        adapter.notifyDataSetChanged();
    }

    private void initGoods() {
        for (int i = 0; i < 2; i++) {
            Goods apple = new Goods("apple", "1", "1", "market1");
            goodsList.add(apple);
            Goods banana = new Goods("banana", "1", "2", "market2");
            goodsList.add(banana);
            Goods orange = new Goods("orange", "1", "3", "market3");
            goodsList.add(orange);
            Goods watermelon = new Goods("watermelon", "1", "4", "market4");
            goodsList.add(watermelon);
            Goods pear = new Goods("pear", "1", "5", "market5");
            goodsList.add(pear);
            Goods grape = new Goods("grape", "1", "6", "market6");
            goodsList.add(grape);
            Goods pineapple = new Goods("pineapple", "1", "7", "market7");
            goodsList.add(pineapple);
            Goods strawberry = new Goods("strawberry", "1", "8", "market8");
            goodsList.add(strawberry);
            Goods cherry = new Goods("cherry", "1", "9", "market9");
            goodsList.add(cherry);
            Goods mango = new Goods("mango", "1", "10", "market10");
            goodsList.add(mango);
        }
    }
}
