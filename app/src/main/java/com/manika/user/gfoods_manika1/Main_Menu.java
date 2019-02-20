package com.manika.user.gfoods_manika1;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Main_Menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
ListView lv;
Context context;
CustomAdapter2 ca;

    // Session Manager Class
    SessionManagement session;

//String name=ArrayListComman.getInstance().getName();
String url_get_info="https://manika2mani.000webhostapp.com/getfooditems.php";
    ArrayList<String> foodName=new ArrayList<String>();
    ArrayList<String> foodprice=new ArrayList<String>();
    ArrayAdapter<String> onj2;
  //  SharedPreferences pref;
   // SharedPreferences.Editor editor;
    public static int [] prgmImages={R.drawable.beverages,R.drawable.patties,R.drawable.parantha,R.drawable.maggi,R.drawable.egg_contains,R.drawable.south_indian,R.drawable.background};
    public static String [] prgmNameList={"Beverages","Patties","Paranthas","Maggi","Egg Contains","South Indian","Extras"};

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   Toast.makeText(context, "Phone : "+ArrayListComman.getInstance().getContact(), Toast.LENGTH_SHORT).show();

        // Session class instance
        session = new SessionManagement(getApplicationContext());


        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

       // pref = getApplicationContext().getSharedPreferences("AndroidPref",MODE_PRIVATE);
        //editor=pref.edit();
        //String gazab=pref.getString("phoneno",null);
       // Toast.makeText(context, ""+gazab, Toast.LENGTH_SHORT).show();
        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManagement.KEY_PHONE);

        // email
       // String email = user.get(SessionManager.KEY_EMAIL);

        // displaying user data
        //lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        //lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));



        new DataProcess4().execute();
       // fab= (FloatingActionButton) findViewById(R.id.fab);

        lv=findViewById(R.id.recycler_food);
        lv.setClickable(true);
        //lv2=findViewById(R.id.listView2);
        //lv2.setClickable(true);
        context=this;

        lv.setAdapter(new CustomAdapter(this, prgmNameList,prgmImages));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o=lv.getChildAt(1);
                ConstraintLayout cl=(ConstraintLayout) view;
                RelativeLayout rl=(RelativeLayout) cl.getChildAt(0);
                TextView tvName= (TextView) rl.getChildAt(1);
                Object o1=rl.getChildAt(1);
                Toast.makeText(context,  "1st mnsgdhfghdgshjgjgfkgskjgfxskhgk  ", Toast.LENGTH_SHORT).show();
         //       Intent i=new Intent();
            }
        });

//        lv2.setOnItemClickListener();

       // Utility.setListViewHeightBasedOnChildren(lv);
        //Utility.setListViewHeightBasedOnChildren(lv2);
        /*fab.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
                    	             //   textView.setText("Floating Action Button Clicked !!");

                    Toast.makeText(context, "You clicked cart", Toast.LENGTH_SHORT).show();
	            }
	        });
*/

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.cart1);
        fab.setBackgroundColor(R.color.colorbackgroundTEXT);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();
                Intent i =new Intent(Main_Menu.this,Place_order_cart.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu=navigationView.getMenu();
        MenuItem profile=menu.findItem(R.id.nav_camera);
        profile.setTitle("Profile");
        profile.setIcon(R.drawable.ic_profile);

        MenuItem history=menu.findItem(R.id.nav_gallery);
        history.setTitle("History");
        history.setIcon(R.drawable.ic_history);

        MenuItem logout=menu.findItem(R.id.nav_send);
        logout.setTitle("Logout");
        logout.setIcon(R.drawable.ic_history);

        View headerLayout=navigationView.getHeaderView(0);
       // TextView message=(TextView) navigationView.findViewById(R.id.textView);
//        message.setText("Manika Agrawal\n9557054160");
        ArrayListComman.getInstance().setName("XYZ");
      //  ArrayListComman.getInstance().setContact("9557054160");


        menu.findItem(R.id.nav_slideshow).setVisible(false);
        menu.findItem(R.id.nav_manage).setVisible(false);
       // menu.findItem(R.id.nav_share).setVisible(false);
     //   menu.findItem(R.id.nav_send).setVisible(false);


        navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

         if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }


            else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__menu, menu);
        return true;
    }
    class DataProcess4 extends AsyncTask<String,String,String>
    {
        ProgressDialog pd2;

        @Override
        protected void onPreExecute() {
            pd2=new ProgressDialog(Main_Menu.this);
            pd2.setMessage("Getting Food Details..");
            pd2.setCanceledOnTouchOutside(false);
            pd2.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {

            //ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            //params.add(new BasicNameValuePair("id", user_id));
            //params.add(new BasicNameValuePair("password",password));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_get_info);
            httpPost.setHeader("Content-Type","application/json");
            InputStream inputStream = null;
            String result = null;
            try {
               // httpPost.setEntity(new UrlEncodedFormEntity(params));   //to send entity
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

                    String name1 = obj.getString("fname");

                        String phone1 = obj.getString("fprice");
                        String email1 = obj.getString("fstatus");
                        String address1=obj.getString("fimg");

                        foodName.add(name1);
                        foodprice.add(phone1);

                        /*String city1=obj.getString("city");
                        String subject1=obj.getString("subjects");
                        Toast.makeText(getContext(), "" + name1 + phone1 + email1, Toast.LENGTH_LONG).show();
                        editor.putString("name_key2",name1);
                        editor.putString("phone_key2",phone1);
                        editor.putString("email_key2",email1);
                        editor.putString("address_key2",address1);
                        editor.putString("subject_key2",subject1);
                        editor.commit();
                        CommonData.getInstance().setName1(name1);
                        CommonData.getInstance().setPhone1(phone1);
                        CommonData.getInstance().setEmail(email1);

                        //  FirstFragment firstFragment=new FirstFragment();
                        // Bundle b=new Bundle();
                        fm=getFragmentManager();
                        ft=fm.beginTransaction();
                        ft.replace(R.id.tab3,new FirstFragment());   //ft.replace(R.id.main_page,new Second_Fragment()); jab koi object nhi banaya hua ho toh
                        ft.addToBackStack(null);   // to get back to the fragment
                        ft.commit();
*/
                    /*a1.add((i+1)+")\nName : "+name1+"\nPhone : "+phone1+"\nEmail : "+email1+"\n");
                    name.add(name1);
                    phone.add(phone1);
                    email.add(email1);
                    onj.notifyDataSetChanged();*/

                }
                ArrayListComman.getInstance().setListPrice(foodprice);
                ArrayListComman.getInstance().setListName(foodName);
         //       lv2.setAdapter(new CustomAdapter2(Main_Menu.this,foodName));
                //ca.SetList(foodName);
                //ca.notifyDataSetChanged();
                pd2.dismiss();

                // Toast.makeText(Main3Activity.this, "got done successfully", Toast.LENGTH_LONG).show();
                super.onPostExecute(s);
            } catch (Exception e) {

            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i =new Intent(Main_Menu.this,History.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            //Toast.makeText(context, "Logout Intent", Toast.LENGTH_SHORT).show();
            // Clear the session data
            // This will clear all session data and
            // redirect user to LoginActivity
            session.logoutUser();
            Intent de = new Intent(Main_Menu.this,Signin.class);
            startActivity(de);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
