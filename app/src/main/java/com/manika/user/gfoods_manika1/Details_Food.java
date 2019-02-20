package com.manika.user.gfoods_manika1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;

public class Details_Food extends AppCompatActivity {
ElegantNumberButton quantity;
Button add_to_cart;
TextView name1,amt;
ImageView img;
ArrayList<String> arr=ArrayListComman.getInstance().getList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__food);
        quantity=findViewById(R.id.quantity_button);
        add_to_cart=findViewById(R.id.button_addtocart);
       // name=findViewById(R.id.food_name_detail);
        name1=findViewById(R.id.text_name);
        amt=findViewById(R.id.text_amount);
        img=findViewById(R.id.food_image_detail);



        Bundle b=getIntent().getExtras();
        String food=b.getString("food_name");

        if(food.equals("Aloo Patties"))
        {
            img.setImageResource(R.drawable.aloo_patties);
         //   name.setText("Aloo Patties");
            name1.setText("Aloo Patties");
            amt.setText("Rs 20 /-");

        }
        else  if(food.equals("Paneer Patties"))
        {
            img.setImageResource(R.drawable.paneer_patties);
           // name.setText("Paneer Patties");
            name1.setText("Paneer Patties");
            amt.setText("Rs 20 /-");

        }
        else  if(food.equals("Cheese Patties"))
        {
            img.setImageResource(R.drawable.cheese_patties);
           // name.setText("Cheese Patties");
            name1.setText("Cheese Patties");
            amt.setText("Rs 20 /-");

        }
        else  if(food.equals("Ragda Patties"))
        {
            img.setImageResource(R.drawable.radga_patties);
           // name.setText("Ragda Patties");
            name1.setText("Ragda Patties");
            amt.setText("Rs 20 /-");

        }

        else  if(food.equals("Masala Tea"))
        {
            img.setImageResource(R.drawable.tea);
            //name.setText("Masala Tea");
            name1.setText("Masala Tea");
            amt.setText("Rs 10 /-");

        }
        else  if(food.equals("Cappuccino"))
        {
            img.setImageResource(R.drawable.capacino);
            //name.setText("Cappuccino");
            name1.setText("Cappuccino");
            amt.setText("Rs 10 /-");

        }
        else  if(food.equals("Cold Coffee"))
        {
            img.setImageResource(R.drawable.cold_coffee);
            //name.setText("Cold Coffee");
            name1.setText("Cold Coffee");
            amt.setText("Rs 25 /-");

        }

        else  if(food.equals("Tea(Tea bag)"))
        {
            img.setImageResource(R.drawable.tea_bag);
            //name.setText("Cold Coffee");
            name1.setText("Tea(Tea Bag)");
            amt.setText("Rs 07 /-");

        }

        else  if(food.equals("Iced Tea"))
        {
            img.setImageResource(R.drawable.ice_tea);
            //name.setText("Cold Coffee");
            name1.setText("Iced Tea");
            amt.setText("Rs 15 /-");

        }
        else  if(food.equals("Cold Drinks"))
        {
            img.setImageResource(R.drawable.cold_drink);
            //name.setText("Cold Coffee");
            name1.setText("Cold Drinks");
            amt.setText("On MRP");

        }

        else  if(food.equals("Aloo Pyaz Parantha"))
        {
            img.setImageResource(R.drawable.aloo_parantha);
            //name.setText("Cold Coffee");
            name1.setText("Aloo Pyaz Parantha");
            amt.setText("Rs 30 /-");

        }

        else  if(food.equals("Paneer Parantha"))
        {
            img.setImageResource(R.drawable.paneer_parantha);
            //name.setText("Cold Coffee");
            name1.setText("Paneer Parantha");
            amt.setText("Rs 50 /-");

        }

        else  if(food.equals("Plain Maggi"))
        {
            img.setImageResource(R.drawable.plain_maggi);
            //name.setText("Cold Coffee");
            name1.setText("Plain Maggi");
            amt.setText("Rs 20 /-");

        }

        else  if(food.equals("Veg Maggi"))
        {
            img.setImageResource(R.drawable.veg_maggi);
            //name.setText("Cold Coffee");
            name1.setText("Veg Maggi");
            amt.setText("Rs 30 /-");

        }

        else  if(food.equals("Butter Maggi"))
        {
            img.setImageResource(R.drawable.butter_maggi);
            //name.setText("Cold Coffee");
            name1.setText("Butter Maggi");
            amt.setText("Rs 25 /-");

        }

        else  if(food.equals("Veg Butter Maggi"))
        {
            img.setImageResource(R.drawable.veg_butter_maggi);
            //name.setText("Cold Coffee");
            name1.setText("Veg Butter Maggi");
            amt.setText("Rs 35 /-");

        }

        else  if(food.equals("Jeera Maggi"))
        {
            img.setImageResource(R.drawable.jerra_maggi);
            //name.setText("Cold Coffee");
            name1.setText("Jerra Maggi");
            amt.setText("Rs 35 /-");

        }

        else  if(food.equals("Fried Maggi"))
        {
            img.setImageResource(R.drawable.fried_maggi);
            //name.setText("Cold Coffee");
            name1.setText("Fried Maggi");
            amt.setText("Rs 35 /-");

        }

        else  if(food.equals("Omelette"))
        {
            img.setImageResource(R.drawable.omlette);
            //name.setText("Cold Coffee");
            name1.setText("Omelette");
            amt.setText("Rs 35 /-");

        }

        else  if(food.equals("Bread Omelette"))
        {
            img.setImageResource(R.drawable.bread_omelette);
            //name.setText("Cold Coffee");
            name1.setText("Bread Omelette");
            amt.setText("Rs 40 /-");

        }

        else  if(food.equals("Egg Curry with Naan"))
        {
            img.setImageResource(R.drawable.egg_curry);
            //name.setText("Cold Coffee");
            name1.setText("Egg Curry with Naan");
            amt.setText("Rs 70 /-");

        }

        else  if(food.equals("Egg Roll"))
        {
            img.setImageResource(R.drawable.egg_roll);
            //name.setText("Cold Coffee");
            name1.setText("Egg Roll");
            amt.setText("Rs 40 /-");

        }

        else  if(food.equals("Egg Chowmein"))
        {
            img.setImageResource(R.drawable.egg_chowmein);
            //name.setText("Cold Coffee");
            name1.setText("Egg Chowmein");
            amt.setText("Rs 60 /-");

        }

        else  if(food.equals("Egg Fried Rice"))
        {
            img.setImageResource(R.drawable.egg_fried_rice);
            //name.setText("Cold Coffee");
            name1.setText("Egg Fried Rice");
            amt.setText("Rs 60 /-");

        }

        else  if(food.equals("Idli Sambar"))
        {
            img.setImageResource(R.drawable.idli_sambar);
            //name.setText("Cold Coffee");
            name1.setText("Idli Sambar");
            amt.setText("Rs 30 /-");

        }

        else  if(food.equals("Masala Dosa"))
        {
            img.setImageResource(R.drawable.dosa);
            //name.setText("Cold Coffee");
            name1.setText("Masala Dosa");
            amt.setText("Rs 40 /-");

        }

        else  if(food.equals("Special Dosa"))
        {
            img.setImageResource(R.drawable.special_dosa);
            //name.setText("Cold Coffee");
            name1.setText("Special Dosa");
            amt.setText("Rs 60 /-");

        }

        else  if(food.equals("Uttapam"))
        {
            img.setImageResource(R.drawable.uttapam);
            //name.setText("Cold Coffee");
            name1.setText("Uttapam");
            amt.setText("Rs 40 /-");

        }

        else  if(food.equals("Paneer Uttapam"))
        {
            img.setImageResource(R.drawable.paneer_uttapam);
            //name.setText("Cold Coffee");
            name1.setText("Paneer Uttapam");
            amt.setText("Rs 60 /-");

        }

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(quantity.getNumber().isEmpty()||quantity.getNumber().equals("0"))
                {
                    add_to_cart.setEnabled(false);
                    Intent i=getIntent();
                    finish();
                    startActivity(i);

                }
                else {
                    add_to_cart.setEnabled(true);


                    if (name1.getText().toString().equals("Cold Coffee")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n25");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Aloo Patties")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n20");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Cold Drinks")) {

                        //  arr.add(name1.getText().toString()+"\n"+quantity.getNumber().toString()+"\n20");
                        Toast.makeText(Details_Food.this, "Go Grab from Canteen", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Tea(Tea Bag)")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n7");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Masala Tea")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n10");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Cappuccino")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n10");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Iced Tea")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n15");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Paneer Patties")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n20");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Cheese Patties")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n20");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Ragda Patties")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n20");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Aloo Pyaz Parantha")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n30");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Paneer Parantha")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n50");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Plain Maggi")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n20");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Veg Maggi")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n30");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Butter Maggi")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n25");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Veg Butter Maggi")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n30");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Jerra Maggi")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n35");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Fried Maggi")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n35");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Omelette")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n35");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Bread Omelette")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n40");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Egg Curry with Naan")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n70");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Egg Chowmein")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n60");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Egg Fried Rice")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n60");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Egg Roll")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n40");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Idli Sambar")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n30");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Masala Dosa")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n40");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Special Dosa")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n60");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Uttapam")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n40");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    } else if (name1.getText().toString().equals("Paneer Uttapam")) {

                        arr.add(name1.getText().toString() + "\n" + quantity.getNumber().toString() + "\n60");
                        Toast.makeText(Details_Food.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });

    }
}
