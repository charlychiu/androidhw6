package pro.charlychiu.myapplication6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ThirdActivity extends AppCompatActivity {
    static final int GET_ELECTIVE = 2;
    String[] listItem = new String[]{"數位訊號", "軟體工程","網頁程式設計","資訊安全"};
    private ListView listView3;
    private Button button3;
    CharSequence sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        listView3 = (ListView)findViewById(R.id.listView3);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItem);

        listView3.setAdapter(adapter1);

        listView3.setOnItemClickListener(listViewClickListener);

        button3 = (Button)findViewById(R.id.backbtn3);

        button3.setOnClickListener(btnOnClickListener);
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
