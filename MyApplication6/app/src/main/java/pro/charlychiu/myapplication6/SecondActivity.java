package pro.charlychiu.myapplication6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SecondActivity extends AppCompatActivity {
    static final int GET_REQUIRED = 1;
    String[] listItem = new String[]{"計算機組織", "線性代數","程式設計","物件導向"};
    private ListView listView2;
    private Button button2;
    CharSequence sel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listView2 = (ListView)findViewById(R.id.listView2);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItem);

        listView2.setAdapter(adapter1);

        listView2.setOnItemClickListener(listViewClickListener);
        
        button2 = (Button)findViewById(R.id.backbtn2);

        button2.setOnClickListener(btnOnClickListener);
    }

    private ListView.OnItemClickListener listViewClickListener = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            sel = parent.getItemAtPosition(position).toString();
        }
    };

    private Button.OnClickListener btnOnClickListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("TEXT",sel);
            //startActivityForResult(intent,GET_ELECTIVE);
            setResult(RESULT_OK,intent);
            finish();
        }
    };




}
