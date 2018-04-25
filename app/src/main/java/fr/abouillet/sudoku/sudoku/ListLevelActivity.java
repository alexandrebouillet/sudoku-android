package fr.abouillet.sudoku.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.abouillet.sudoku.sudoku.dao.GridDao;

public class ListLevelActivity extends AppCompatActivity {

  GridDao gridDao = new GridDao();
  ArrayAdapter<String> adapter;
  public int level;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_level);

    level = getIntent().getIntExtra("level", 1);
    ListView  lv = (ListView) findViewById(R.id.lv);

    adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gridDao.getListGridByLevel(level));

    lv.setAdapter(adapter);

    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ListLevelActivity.this, GameActivity.class);
        intent.putExtra("grid", position);
        intent.putExtra("level", level);
        startActivity(intent);
      }
    });

  }


}
