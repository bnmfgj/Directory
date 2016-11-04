package com.jkxy.directory;


import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private EditText etName, etTel;
    private ListView lv;
    private ArrayAdapter<String> arr;
    private SimpleAdapter sa;
    private List<Map<String, Object>> datalist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        //新建适配器
        //适配器加载数据源
        String[] data = {"阿斯达wr", "qwe", "爱仕达", "公司", "叫我"};
        datalist = new ArrayList<>();
        arr = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        sa = new SimpleAdapter(this, datalist, R.layout.layou_listview, new String[]{"name", "Tel"}, new int[]{R.id.listViewName, R.id.listViewTel});
        lv.setAdapter(sa);


    }

    /*private List<Map<String, Object>> getdata() {
        for(int i=0;i<20;i++){
            Map<String,Object>map=new HashMap<>();
            map.put("name","zhangsan");
            map.put("Tel","32165498");
            datalist.add(map);
        }
        return datalist;
    }*/

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

    public void startListDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择操作");
        final String[] operation = {"打电话", "发短信"};

        final String tel = s;
        builder.setItems(operation, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Uri uri = Uri.parse("tel:" + tel);
                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_CALL);
                        i.setData(uri);
                        startActivity(i);
                        break;
                    case 1:
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("smsto:"+tel));
                        intent.putExtra("sms_body","");
                        startActivity(intent);
                        break;

                }
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
                Map<String, Object> map = new HashMap<>();
                map.put("name", etName.getText().toString());
                map.put("Tel", etTel.getText().toString());
                datalist.add(map);
                addContacts();


                /*ContentResolver cr = getContentResolver();
                ContentValues values = new ContentValues();
                Uri uri = cr.insert(ContactsContract.RawContacts.CONTENT_URI, values);
                Long raw = ContentUris.parseId(uri);
                values.clear();
                values.put(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID, raw);
                values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, etName.getText().toString());
                values.put(ContactsContract.CommonDataKinds.StructuredName.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                uri = cr.insert(ContactsContract.Data.CONTENT_URI, values);
                values.clear();
                values.put(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, raw);
                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,etTel.getText().toString());
                values.put(ContactsContract.CommonDataKinds.Phone.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                uri = cr.insert(ContactsContract.Data.CONTENT_URI, values);*/
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
/*        findViewById(R.id.btnSD).setOnClickListener(this);
        findViewById(R.id.btnList).setOnClickListener(this);
        findViewById(R.id.btnChoice).setOnClickListener(this);*/
        findViewById(R.id.btnViewDialog).setOnClickListener(this);


        lv = (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        lv.setOnScrollListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> tel = (HashMap<String, String>) parent.getItemAtPosition(position);
        //String n =tel.get("name");
        String s = tel.get("Tel");
        startListDialog(s);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public void addContacts() {
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver=getContentResolver();
        ContentValues values=new ContentValues();
        long contactId = ContentUris.parseId(resolver.insert(uri,values));

        uri =Uri.parse("content://com.android.contacts/data");
        values.put("raw_contact_id",contactId);
        values.put("mimetype","vnd.android.cursor.item/name");
        values.put("data2",etName.getText().toString());
        resolver.insert(uri,values);

        values.clear();
        values.put("raw_contact_id",contactId);
        values.put("mimetype", "vnd.android.cursor.item/phone_v2");
        values.put("data2", "2");
        values.put("data1", etTel.getText().toString());
        resolver.insert(uri, values);
    }
}
