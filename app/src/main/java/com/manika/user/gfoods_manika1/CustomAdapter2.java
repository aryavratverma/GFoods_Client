package com.manika.user.gfoods_manika1;

/**
 * Created by user on 17-11-2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by user on 26-09-2018.
 */

class CustomAdapter2 extends BaseAdapter {
    ArrayList<String> result;
    ArrayList<String> result1;
    String answer;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;

    public CustomAdapter2(Internal_Menu mainActivity, ArrayList<String> foodName) {
        // TODO Auto-generated constructor stub
        result = foodName;
        context = mainActivity;
        //imageId = prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public ArrayList<String> getanswer()
    {
        return result1;
    }
    public void SetList(ArrayList<String> a)
    {
        result1=a;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.food_item, null);
        holder.tv = (TextView) rowView.findViewById(R.id.food_name);
        holder.img = (ImageView) rowView.findViewById(R.id.food_image);
        holder.img.setFocusable(false);
        holder.img.setClickable(false);
        //holder.tv.setText(result[position]);
        holder.img.setImageResource(R.drawable.background);
        holder.tv.setText(result.get(position));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                answer=result.get(position);
                Toast.makeText(context, "You Clicked " + result.get(position), Toast.LENGTH_LONG).show();
                Intent i=new Intent(context,Details_Food2.class);
                i.putExtra("food_name2",result.get(position));
                context.startActivity(i);

            }
        });
        return rowView;
    }
}

