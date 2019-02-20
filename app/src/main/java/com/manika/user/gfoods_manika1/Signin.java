package com.manika.user.gfoods_manika1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Signin extends AppCompatActivity {

    Button signup, signin;
    EditText mobile, passwrd;
    CheckBox c;

    // Session Manager Class
    SessionManagement session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Session Manager
        session = new SessionManagement(getApplicationContext());



        signin=(Button)findViewById(R.id.btn_signin);
        signup = (Button)findViewById(R.id.btn_signup);
        mobile = (EditText)findViewById(R.id.mobile);
        passwrd = (EditText)findViewById(R.id.passwrd);
        c = (CheckBox) findViewById(R.id.chk);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!c.isChecked()) {
                    passwrd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    passwrd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Signin.this, Signup.class);
                startActivity(in);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mobile.getText().toString().length()==0)
                {
                    Toast.makeText(Signin.this,"Please enter your mobile number.",Toast.LENGTH_SHORT).show();
                }
                else if(passwrd.getText().toString().length()==0){
                    Toast.makeText(Signin.this, "Please enter password.", Toast.LENGTH_SHORT).show();
                }
                else{
                    new Signin.DataProcess1().execute();
                   // Intent q=new Intent(Signin.this,Main_Menu.class);//
                    //q.putExtra("phone",mobile.getText().toString());
                    //startActivity(q);
                    // Creating user login session

                    session.createLoginSession(mobile.getText().toString());

                }
            }
        });


    }
    class DataProcess1 extends AsyncTask<String, String, String> {
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(Signin.this);
            pd1.setMessage("Logging you...");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("phone", mobile.getText().toString()));
            params.add(new BasicNameValuePair("password", passwrd.getText().toString()));

            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost("https://manika2mani.000webhostapp.com/login.php");
            InputStream inputStream = null;
            String result = null;

            try{
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity entity = httpResponse.getEntity();
                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while((line = reader.readLine()) != null){
                    sb.append(line +"\n");
                }
                result = sb.toString().trim();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {

                if(inputStream!=null)
                {
                    try{
                        inputStream.close();
                    }
                    catch (Exception e)
                    {

                    }
                }

            }
            return result;

        }

        @Override
        protected void onPostExecute(String s) {

            try{
                JSONObject obj = new JSONObject(s);
                JSONArray array = obj.getJSONArray("result");
                for(int i=0;i<array.length(); i++){
                    JSONObject object = array.getJSONObject(i);

                    String name1 = object.getString("phone");
                    if(name1.equals("0")){
                        String login_always="";
                        //
                        //
                        Toast.makeText(Signin.this, "Login failed.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //String unirollno1 = object.getString("eu");
                        //String email1 = object.getString("ee");
                        //String  phone1 = object.getString("mob");
                        ArrayListComman.getInstance().setContact(mobile.getText().toString());
                        Toast.makeText(Signin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent q=new Intent(Signin.this,Main_Menu.class);
                        //q.putExtra("phone",mobile.getText().toString());
                        startActivity(q);

                        //UserSessionManager userSessionManager=new UserSessionManager(Signin.this);
                        //userSessionManager.setPhn(mobile.getText().toString());
                    }
                }
            }
            catch (Exception e){

            }
            //Toast.makeText(Signup.this, "Registered Sucessfully!", Toast.LENGTH_SHORT).show();
            pd1.dismiss();

            super.onPostExecute(s);
        }
    }
}


