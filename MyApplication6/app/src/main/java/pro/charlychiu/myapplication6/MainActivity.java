package pro.charlychiu.myapplication6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] listItem = new String[]{"必修科目", "選修科目"};
    private ListView listView1;
    static final int GET_REQUIRED = 1;
    static final int GET_ELECTIVE = 2;
    private TextView textView1;
    private TextView textView2;

    private Button button1;
    private Button button2;
    private EditText editText1;

    SharedPreferences preferences;


    String tmpString = "";
    String tmpString1 = "";
    String tmpString2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1 = (ListView)findViewById(R.id.listView1);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItem);

        listView1.setAdapter(adapter1);

        listView1.setOnItemClickListener(listViewClickListener);

        button1 = (Button)findViewById(R.id.clean);
        button2 = (Button)findViewById(R.id.save);
        editText1 = (EditText)findViewById(R.id.stuName);
        textView1 = (TextView)findViewById(R.id.required);
        textView2 = (TextView)findViewById(R.id.elective);

        button1.setOnClickListener(myBtnListener);
        button2.setOnClickListener(myBtnListener);

        preferences = getSharedPreferences("preFile",MODE_PRIVATE);
        tmpString = preferences.getString("name","");
        tmpString1 = preferences.getString("major","");
        tmpString2 = preferences.getString("ele","");

        if(!tmpString.equals(""))
        {
            editText1.setText(tmpString);
            textView1.setText(tmpString1);
            textView2.setText(tmpString2);
        }




    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        textView1 = (TextView)findViewById(R.id.required);
        textView2 = (TextView)findViewById(R.id.elective);
        if(requestCode == GET_REQUIRED)
        {
            if (resultCode == RESULT_OK) {
                textView1.setText("必修："+data.getCharSequenceExtra("TEXT"));
            }
        }
        if(requestCode == GET_ELECTIVE)
        {
            if(resultCode == RESULT_OK) {
                textView2.setText("選修："+data.getCharSequenceExtra("TEXT"));
            }
        }
    }

    private ListView.OnItemClickListener listViewClickListener = new ListView.OnItemClickListener(){
      @Override
        public void onItemClick(AdapterView<?> parent,View view,int position,long id){
          String sel = parent.getItemAtPosition(position).toString();
          if(sel == "必修科目")
          {
              Intent intent = new Intent(MainActivity.this,SecondActivity.class);
              startActivityForResult(intent,GET_REQUIRED);
          }
          if(sel == "選修科目")
          {
              Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
              startActivityForResult(intent,GET_ELECTIVE);
          }
      }

    };

    private Button.OnClickListener myBtnListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.save:
                    finish();

                    break;

                case R.id.clean:
                    preferences.edit()
                            .clear()
                            .commit();

                    break;
            }

        }
    };
    protected void onStop(){
        super.onStop();
        if(tmpString.equals("")){
            preferences.edit()
                    .putString("name",editText1.getText().toString())
                    .putString("major",textView1.getText().toString())
                    .putString("ele",textView2.getText().toString())
                    .commit();
        }
    }
}
