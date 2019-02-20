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

public class Details_Food2 extends AppCompatActivity {

    ElegantNumberButton quantity2;
    Button add_to_cart2;
    TextView name12, amt2;
    ImageView img2;
    ArrayList<String> arr=ArrayListComman.getInstance().getList();
    ArrayList<String> name_food = ArrayListComman.getInstance().getListName();
    ArrayList<String> price_food = ArrayListComman.getInstance().getListprice();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__food2);
        quantity2 = findViewById(R.id.quantity_button2);
        add_to_cart2 = findViewById(R.id.button_addtocart2);
        // name=findViewById(R.id.food_name_detail);
        name12 = findViewById(R.id.text_name2);
        amt2 = findViewById(R.id.text_amount2);
        img2 = findViewById(R.id.food_image_detail2);


        Bundle b = getIntent().getExtras();
        String food = b.getString("food_name2");
final int index=name_food.indexOf(food);
img2.setImageResource(R.drawable.background);
name12.setText(food);
amt2.setText("Rs "+price_food.get(index)+" /-");



        add_to_cart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity2.getNumber().isEmpty()||quantity2.getNumber().equals("0"))
                {
                    add_to_cart2.setEnabled(false);
                    Intent i=getIntent();
                    finish();
                    startActivity(i);

                }
                else
                {
                    add_to_cart2.setEnabled(true);
                    arr.add(name12.getText().toString() + "\n" + quantity2.getNumber().toString() + "\n"+price_food.get(index));
                    Toast.makeText(Details_Food2.this, "Product Added To Cart", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
