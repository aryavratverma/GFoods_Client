package com.manika.user.gfoods_manika1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {

    EditText eu,ee,ep,name,pswd,confirm;
    TextView click;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText)findViewById(R.id.name);
        b=(Button)findViewById(R.id.btn_register);
        click = (TextView)findViewById(R.id.click);
        eu=(EditText)findViewById(R.id.roll);
        ee=(EditText)findViewById(R.id.email);
        ep=(EditText)findViewById(R.id.mob);
        pswd=(EditText)findViewById(R.id.pswd);
        confirm=(EditText)findViewById(R.id.cnfrmpswd);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(),"Please enter your name.",Toast.LENGTH_LONG).show();
                }


                else if (eu.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter university roll number.",Toast.LENGTH_LONG).show();
                }
                else if (ee.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter email id",Toast.LENGTH_LONG).show();
                }
                else if (ep.getText().toString().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter mobile number",Toast.LENGTH_LONG).show();
                }
                else if(!(pswd.getText().toString()).equals(confirm.getText().toString())){
                    Toast.makeText(Signup.this, "Password not Matching!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(isValidMail(ee.getText().toString()) && isValidMobile(ep.getText().toString())) {

                        new DataProcess().execute();
                    }//Toast.makeText(getApplicationContext(), "Successfully Registered!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Signin.class);
                startActivity(intent);
            }
        });

    }
    private boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() < 10 || phone.length() > 10) {
                // if(phone.length() != 10) {
                check = false;
                ep.setError("Not Valid Number");
            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }
    private boolean isValidMail(String email) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();

        if(!check) {
            ee.setError("Not Valid Email");
        }
        return check;
    }
    class DataProcess extends AsyncTask<String, String, String> {
        ProgressDialog pd1;

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(Signup.this);
            pd1.setMessage("Registering you...");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name.getText().toString()));
            params.add(new BasicNameValuePair("unirollno", eu.getText().toString()));
            params.add(new BasicNameValuePair("email", ee.getText().toString()));
            params.add(new BasicNameValuePair("phone", ep.getText().toString()));
            params.add(new BasicNameValuePair("password", pswd.getText().toString()));

            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost("https://manika2mani.000webhostapp.com/setUserData.php");
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

                    String name1 = object.getString("unirollno");
                    if(name1.equals("0")){
                        String login_always="";
                        //
                        //
                        Toast.makeText(Signup.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //String unirollno1 = object.getString("eu");
                        //String email1 = object.getString("ee");
                        //String  phone1 = object.getString("mob");
                        Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
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
