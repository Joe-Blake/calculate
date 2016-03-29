package com.example.joe.calculate;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;

    Button btn_point;// 小数点
    Button btn_divide;// 除
    Button btn_multiply;// 乘
    Button btn_minus;// 减
    Button btn_pluse;// 加
    Button btn_equal;// 等于

    Button btn_clear;
    Button btn_del;

    EditText et_showView;

    boolean firstNum=true;
    boolean finish = false;

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);

        btn_point = (Button) findViewById(R.id.btn_point);// 小数点
        btn_divide = (Button) findViewById(R.id.btn_divide);// 除
        btn_multiply = (Button) findViewById(R.id.btn_multiply);// 乘
        btn_minus = (Button) findViewById(R.id.btn_minus);// 减
        btn_pluse = (Button) findViewById(R.id.btn_pluse);// 加
        btn_equal = (Button) findViewById(R.id.btn_equal);// 等于

        btn_clear = (Button) findViewById(R.id.btn_clear);//C
        btn_del = (Button) findViewById(R.id.btn_del);//Del

        et_showView = (EditText)findViewById(R.id.et_showView);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_point.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_pluse.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        str = et_showView.getText().toString();
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                finish = false;
                if(firstNum){
                    et_showView.setText("");
                    str = "";
                    firstNum = false;
                }
                et_showView.setText(str + ((Button) v).getText());
                break;
            case R.id.btn_pluse:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                finish = false;
                firstNum = false;
                str = et_showView.getText().toString();
                et_showView.setText(str +" "+((Button) v).getText()+" ");
                break;
            case R.id.btn_equal:
                getResult();
                firstNum = true;
                break;
            case R.id.btn_del:
                if (str != null && !str.equals("")) {
                    finish = false;
                    et_showView.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.btn_clear:
                finish = false;
                str = "";
                et_showView.setText("");
                break;
        }
    }

    public void getResult() {
        if(finish){
            et_showView.setText(str);
        }else {
            String exp = et_showView.getText().toString();
            double r = 0;
            int space = exp.indexOf(' ');//用于搜索空格位置
            String s1 = exp.substring(0, space);//s1用于保存第一个运算数
            String op = exp.substring(space + 1, space + 2);//op用于保存运算符
            String s2 = exp.substring(space + 3);//s2用于保存第二个运算数
            if(s1.substring(0,1).equals(".")){
                s1 = "0"+s1;
            }
            if(s2.substring(0,1).equals(".")){
                s2 = "0"+s2;
            }
            double arg1 = Double.parseDouble(s1);
            double arg2 = Double.parseDouble(s2);
            if(!s1.equals("")&&!s2.equals("")){
                if(op.equals("＋")){
                    r = arg1 + arg2;
                }else if(op.equals("－")){
                    r = arg1 - arg2;
                }else if(op.equals("×")){
                    r = arg1 * arg2;
                }else if(op.equals("÷")){
                    if (arg2 == 0)
                    {
                        r=0;
                    }
                    else
                    {
                        r = arg1 / arg2;
                    }
                }
                if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")){
                    int result = (int)r;
                    et_showView.setText(result+"");
                }else{
                    et_showView.setText(r+"");
                }
            } else if(!s1.equals("")&&s2.equals("")) {
                et_showView.setText(s1);
            }else if(s1.equals("")&&!s2.equals("")) {
                if (op.equals("＋")) {
                    r = arg1 + arg2;
                } else if (op.equals("－")) {
                    r = arg1 - arg2;
                } else if (op.equals("×")) {
                    r = 0;
                } else if (op.equals("÷")) {
                    r = 0;
                }
                if (!s2.contains(".") && !op.equals("÷")) {
                    int result = (int) r;
                    et_showView.setText(result + "");
                } else {
                    et_showView.setText(r+"");
                }
            }else{
                et_showView.setText("");
            }
            finish = true;
        }
    }
}
