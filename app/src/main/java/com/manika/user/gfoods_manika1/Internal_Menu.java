package com.manika.user.gfoods_manika1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class Internal_Menu extends AppCompatActivity {


    ListView lv;
    Context context;



    ArrayList prgmName;
   // public static int [] prgmImages;
    //public static String [] prgmNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal__menu);


        lv=findViewById(R.id.internal_food);
        Bundle b=getIntent().getExtras();
        assert b != null;
        String name=b.getString("menu_name");
        context=this;


        if(name.equals("Patties"))
        {
           // Toast.makeText(context, "Wait for Jiffy", Toast.LENGTH_SHORT).show();
            int [] prgmImages={R.drawable.aloo_patties, R.drawable.paneer_patties, R.drawable.cheese_patties, R.drawable.radga_patties};
            String [] prgmNameList={"Aloo Patties", "Paneer Patties", "Cheese Patties", "Ragda Patties"};


            lv.setClickable(true);

            lv.setAdapter(new CustomAdapter1(this, prgmNameList,prgmImages));

        }

        else if(Objects.equals(name, "Beverages"))
        {

            int [] prgmImages={R.drawable.tea_bag,R.drawable.tea, R.drawable.capacino, R.drawable.cold_coffee,R.drawable.ice_tea,R.drawable.cold_drink};
            String [] prgmNameList={"Tea(Tea bag)","Masala Tea", "Cappuccino", "Cold Coffee","Iced Tea","Cold Drinks"};


            lv.setClickable(true);
            context=this;

            lv.setAdapter(new CustomAdapter1(this, prgmNameList,prgmImages));

        }

        else  if(name.equals("Paranthas"))
        {
            // Toast.makeText(context, "Wait for Jiffy", Toast.LENGTH_SHORT).show();
            int [] prgmImages={R.drawable.aloo_parantha, R.drawable.paneer_parantha};
            String [] prgmNameList={"Aloo Pyaz Parantha", "Paneer Parantha"};


            lv.setClickable(true);

            lv.setAdapter(new CustomAdapter1(this, prgmNameList,prgmImages));

        }

        else  if(name.equals("Maggi"))
        {
            // Toast.makeText(context, "Wait for Jiffy", Toast.LENGTH_SHORT).show();
            int [] prgmImages={R.drawable.plain_maggi, R.drawable.veg_maggi, R.drawable.butter_maggi, R.drawable.veg_butter_maggi,R.drawable.jerra_maggi,R.drawable.fried_maggi};
            String [] prgmNameList={"Plain Maggi", "Veg Maggi", "Butter Maggi", "Veg Butter Maggi","Jeera Maggi","Fried Maggi"};


            lv.setClickable(true);

            lv.setAdapter(new CustomAdapter1(this, prgmNameList,prgmImages));

        }

        else  if(name.equals("Egg Contains"))
        {
            // Toast.makeText(context, "Wait for Jiffy", Toast.LENGTH_SHORT).show();
            int [] prgmImages={R.drawable.omlette, R.drawable.bread_omelette, R.drawable.egg_curry, R.drawable.egg_roll,R.drawable.egg_chowmein,R.drawable.egg_fried_rice};
            String [] prgmNameList={"Omelette", "Bread Omelette", "Egg Curry with Naan", "Egg Roll","Egg Chowmein","Egg Fried Rice"};


            lv.setClickable(true);

            lv.setAdapter(new CustomAdapter1(this, prgmNameList,prgmImages));

        }

        else  if(name.equals("South Indian"))
        {
            // Toast.makeText(context, "Wait for Jiffy", Toast.LENGTH_SHORT).show();
            int [] prgmImages={R.drawable.idli_sambar, R.drawable.dosa, R.drawable.special_dosa, R.drawable.uttapam,R.drawable.paneer_uttapam};
            String [] prgmNameList={"Idli Sambar", "Masala Dosa", "Special Dosa", "Uttapam","Paneer Uttapam"};


            lv.setClickable(true);

            lv.setAdapter(new CustomAdapter1(this, prgmNameList,prgmImages));

        }
        else if(name.equals("Extras"))
        {
            ArrayList<String> arrayList = ArrayListComman.getInstance().getListName();
            lv.setClickable(true);
            lv.setAdapter(new CustomAdapter2(this,arrayList));
        }

    }
}
