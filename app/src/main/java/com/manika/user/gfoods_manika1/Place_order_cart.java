package com.manika.user.gfoods_manika1;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class Place_order_cart extends AppCompatActivity implements PaymentResultListener  {

    TextView place;
    ListView lv;
    ArrayList<String> a1 = ArrayListComman.getInstance().getList();
    String array[];
    ArrayAdapter<String> onj;
    String itemname;
    int qty = 0, price = 0;
    int total = 0;
    String qty2 = "", price2 = "";
    String date2 = "", items2 = "";
    String items_qty="";
    int total_final;
    int pos;
    String total_P;

    String url = "https://manika2mani.000webhostapp.com/setdata.php";
    Context cxt = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order_cart);
        lv = findViewById(R.id.list_cart);
        place = findViewById(R.id.place_order_button);

        if (a1.size() == 0) {
            place.setEnabled(false);
        } else {
            place.setEnabled(true);
        }

        Toast.makeText(this, "size is " + a1.size(), Toast.LENGTH_SHORT).show();
        onj = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, a1);
        lv.setAdapter(onj);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos=position;
                registerForContextMenu(lv);
            }
        });

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyy");
        String date = df.format(c);
        for (int i = 0; i < a1.size(); i++) {
            array = a1.get(i).split("\n");
            items2 = items2 + "," + array[0];
            qty = Integer.parseInt(array[1]);
            qty2 = qty2 + "," + qty;
            price = Integer.parseInt(array[2]);
            price2 = price2 + "," + price;
            total = total + (price * qty);
            date2 = date2 + "," + date;
            items_qty=items_qty+","+array[0]+"("+array[1]+")";

        }
        if(a1.size()!=0) {
            items_qty = items_qty.substring(1);
            place.setText("Place Order \nTotal Amount - " + total);
            total_final=total;
            total_P=String.valueOf(total_final);
        }
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startPayment();

            }
        });


    }
    public void startPayment() {
        /**
         * You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Payable Amount");
            //You can omit the image option to fetch the image from dashboard
            //options.put("image", "https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
            options.put("currency", "INR");

            String payment = total_P;

            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "aryavrat97@gmail.com");
            preFill.put("contact", "7417155939");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Toast.makeText(this, "Payment successfully done! " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        if(a1.size()!=0) {
            new DataProcess().execute();

        }DatabaseOperations db = new DatabaseOperations(cxt);
        db.putInformation(db, ArrayListComman.getInstance().getName(), ArrayListComman.getInstance().getContact(), items2, qty2, price2, String.valueOf(total), date2);
        qty = 0;
        price = 0;
        total = 0;
        qty2 = "";
        price2 = "";
        date2 = "";
        items2 = "";
        a1.clear();
        //Toast.makeText(cxt, "Ordered Placed Only Pay is left   "+items_qty+"   "+total_P, Toast.LENGTH_SHORT).show();
        //  a1.clear();
        onj.notifyDataSetChanged();
    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Delete");
      //  menu.add("Rename");

        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        String title=item.getTitle().toString();
        if(title.equals("Delete"))
        {
            a1.remove(lv.getItemAtPosition(pos).toString());
            Toast.makeText(Place_order_cart.this, "Item Removed", Toast.LENGTH_LONG).show();
            onj.notifyDataSetChanged();
        }
       /* else {

            final Dialog d=new Dialog(MainActivity.this);
            d.setContentView(R.layout.custom_dialog);
            d.show();
            final EditText e2=d.findViewById(R.id.edit1);
            Button b2=d.findViewById(R.id.button2);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data =e2.getText().toString();
                    a1.set(pos,data);
                    onj.notifyDataSetChanged();
                    d.dismiss();
                    Toast.makeText(MainActivity.this, "Item Renamed", Toast.LENGTH_LONG).show();
                }
            });



        }*/
        return super.onContextItemSelected(item);
    }



    class DataProcess extends AsyncTask<String, String, String> {
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(Place_order_cart.this);
            pd1.setMessage("Placing Order...");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", "XYZ"));
            params.add(new BasicNameValuePair("contact", ArrayListComman.getInstance().getContact()));
            params.add(new BasicNameValuePair("items_qty", items_qty));
            params.add(new BasicNameValuePair("price", total_P));
         //   params.add(new BasicNameValuePair("status","0"));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            // InputStream inputStream = null;
            // String result = null;
            // try {

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params));   //to send entity
                httpClient.execute(httpPost);  //to execute
                //HttpResponse httpResponse=httpClient.execute(httpPost);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(cxt, "Order placed", Toast.LENGTH_SHORT).show();
            pd1.dismiss();

            super.onPostExecute(s);
        }
    }
}







/*

 class DataProcess10 extends AsyncTask<String,String,String>
    {
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(Details_activity.this);
            pd1.setMessage("Wait...");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", id));
            params.add(new BasicNameValuePair("ratecurrent",String.valueOf(ratings)));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_rating);
            InputStream inputStream = null;
            String result = null;
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params));   //to send entity
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity entity = httpResponse.getEntity();

                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null) {

                    sb.append(line + "\n");
                }
                result = sb.toString().trim();

            } catch (Exception e)

            {

            } finally {

                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("result");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    //  String id1=obj.getString("id");
                    //String name1 = obj.getString("name");
                    int rate5=obj.getInt("rate5");
                    int rate1=obj.getInt("rate1");
                    int rate4=obj.getInt("rate4");
                    int rate3=obj.getInt("rate3");
                    int rate2=obj.getInt("rate2");
                    double avg=obj.getDouble("avgrate");
                    t7.setText(String.valueOf(avg));
                    t8.setText(String.valueOf("5 stars -" + rate5+ " users "));
                    t9.setText(String.valueOf("4 stars -" +rate4+ " users"));
                    t10.setText(String.valueOf("3 stars -"+ rate3+ " users"));
                    t11.setText(String.valueOf("2 stars -"+ rate2+ " users"));
                    t12.setText(String.valueOf("1 star -" +rate1+ " users"));
                    //String follow1=String.valueOf(follow);
                    //t1.setText(name1);


                }

                pd1.dismiss();

                super.onPostExecute(s);
            } catch (Exception e) {

            }
        }
    }


 */