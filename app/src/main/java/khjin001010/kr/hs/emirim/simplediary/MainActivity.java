package khjin001010.kr.hs.emirim.simplediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

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


        return null;
    };
}
