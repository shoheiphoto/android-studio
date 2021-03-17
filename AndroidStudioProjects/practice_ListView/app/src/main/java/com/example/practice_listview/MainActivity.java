package com.example.practice_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);

        /* String型のArrayListを作成 */
        List<String> items = new ArrayList<String>();

        /* リストの0-19まで"item_数字"という値を設定 */
        for (int i = 0; i < 20 ; i++) {
            items.add("item_" + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ListView listview = (ListView)findViewById(R.id.mylist);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                // parent（つまりListView）をListViewクラスにキャスト
                ListView listView = (ListView)parent;

                // 引数のpos（選択項目が何番目か）から項目を取得
                String item = (String)listView.getItemAtPosition(pos);

                // Toastで取得した項目を表示
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });
    }
}