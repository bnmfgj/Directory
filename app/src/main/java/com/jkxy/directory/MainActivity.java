package com.jkxy.directory;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etTel;
    private ListView lv;

    private SimpleAdapter simpleAdapter;

    private List<Map<String,Object>> datalist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] arr = {R.id.listViewName, R.id.listViewTel};
        initView();
        //新建适配器
        //适配器加载数据源
        datalist=new ArrayList<>();
        simpleAdapter = new SimpleAdapter(this,datalist , R.layout.layou_listview, new String[]{"姓名", "电话"}, arr);

    }

    public void startDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("对话框");
        builder.setMessage("请点击");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void startListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择颜色");
        final String[] colours = {"蓝色", "红色", "黄色"};
        builder.setItems(colours, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, colours[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void startChoiceDialog() {
        AlertDialog.Builder bu = new AlertDialog.Builder(this);
        bu.setTitle("请选择性别");
        final String[] sex = {"男", "女"};
        bu.setSingleChoiceItems(sex, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, sex[which], Toast.LENGTH_SHORT).show();

            }
        });
        bu.show();
    }

    public void startViewDialog() {
        LayoutInflater li = LayoutInflater.from(this);
        View view = li.inflate(R.layout.layout_view, null);
        etName = (EditText) view.findViewById(R.id.name);
        etTel = (EditText) view.findViewById(R.id.tel);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.btnSD:
                startDialog();
                break;
            case R.id.btnList:
                startListDialog();
                break;
            case R.id.btnChoice:
                startChoiceDialog();
                break;*/
            case R.id.btnViewDialog:
                startViewDialog();
                break;
        }
    }

    public void initView() {
        findViewById(R.id.btnSD).setOnClickListener(this);
        findViewById(R.id.btnList).setOnClickListener(this);
        findViewById(R.id.btnChoice).setOnClickListener(this);
        findViewById(R.id.btnViewDialog).setOnClickListener(this);
        lv = (ListView) findViewById(R.id.lv);


    }

}
