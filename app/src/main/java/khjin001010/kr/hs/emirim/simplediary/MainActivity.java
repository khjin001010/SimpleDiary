package khjin001010.kr.hs.emirim.simplediary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText edit;
    Button but; // 저장과 수정의 용도
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (DatePicker)findViewById(R.id.date_pick);
        edit = (EditText)findViewById(R.id.edit);
        but = (Button)findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fOut = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = edit.getText().toString();
                    fOut.write(str.getBytes());
                    fOut.close();
                    Toast.makeText(MainActivity.this, "정상적으로 "+fileName+"파일이 저장되었습니다.",Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Calendar cal = Calendar.getInstance(); //현재 객체의 참조값 반환, Calendar는 추상클래스이므로 객체 생성 불가능이라 이렇게 씀
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE); // == DayofMonth

        date.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                fileName = year+"_"+(month+1)+"_"+day+".txt";
                String readData = readDiary(fileName);
                edit.setText(readData);

                but.setEnabled(true); //수정과 새로작성이 가능하게끔 '활성화'!
            }
        });//(년,월,일,변경되었을 때 핸들러)
    }
    public String readDiary(String fileName){
        String diaryStr = null;
        FileInputStream fIn = null;
        byte[] buf = new byte[500]; //한번에 읽어오는 바이트크기
        try {
            fIn = openFileInput(fileName);
            fIn.read(buf);
            diaryStr = new String(buf).trim(); //앞 뒤의 공백만 제거, 중간은 trim()으로 제거 불가능
            but.setText("수정 하기");
        } catch (FileNotFoundException e) {
            edit.setText("일기가 존재하지 않습니다.");
            but.setText("새로 저장");
        } catch (IOException e) {
            edit.setText("");
        }

        try {
            fIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return diaryStr;
    };
}
