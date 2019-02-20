package com.manika.user.gfoods_manika1;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    ListView lv;
    String name,contact,items,qty,price,total,date;
    String items2[],qty2[],price2[];
    ArrayList<String> a1=new ArrayList<String>();
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        lv=findViewById(R.id.history_list);
pref=getApplicationContext().getSharedPreferences("AndroidPref",MODE_PRIVATE);
        DatabaseOperations db=new DatabaseOperations(this);
       Cursor cr= db.getInformation(db);

        cr.moveToFirst();
        do {
            name=cr.getString(0);
            contact=cr.getString(1);
            items=cr.getString(2);
            qty=cr.getString(3);
            price=cr.getString(4);
            total=cr.getString(5);
            date=cr.getString(6);
//      pref.getString("phoneno",null)
if(contact.equals(ArrayListComman.getInstance().getContact())) {
    //  String phone=cr.getString(1);
    // Toast.makeText(cxt, ""+name, Toast.LENGTH_SHORT).show();
    a1.add("NAME-- " + name + "\nCONTACT-- " + contact + "\nITEMS-- " + items.substring(1) + "\nQUANTITY-- " + qty.substring(1) + "\nPRICES PER-- " + price.substring(1) + "\nTOTAL AMT-- " + total + "\nDATE-- " + date.substring(1, 12) + "\n\n");
}      }while(cr.moveToNext());

        ArrayAdapter<String> onj=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a1);
        lv.setAdapter(onj);


    }
}
