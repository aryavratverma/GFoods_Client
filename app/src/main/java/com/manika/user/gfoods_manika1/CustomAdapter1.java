package com.manika.user.gfoods_manika1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 26-09-2018.
 */

public class CustomAdapter1 extends BaseAdapter {
    String[] result;
    private String answer;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;

    public CustomAdapter1(Internal_Menu mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = mainActivity;
        imageId = prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public String getanswer()
    {
        return answer;
    }
    public void SetAnswer(String a)
    {
        answer=a;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.food_item, null);
        holder.tv = (TextView) rowView.findViewById(R.id.food_name);
        holder.img = (ImageView) rowView.findViewById(R.id.food_image);
        holder.img.setFocusable(false);
        holder.img.setClickable(false);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                answer=result[position];
                Toast.makeText(context, "Now You Clicked " + result[position], Toast.LENGTH_LONG).show();
                Intent i=new Intent(context,Details_Food.class);
                i.putExtra("food_name",result[position]);
                context.startActivity(i);

            }
        });
        return rowView;
    }
}

