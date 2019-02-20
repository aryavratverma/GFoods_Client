package com.manika.user.gfoods_manika1;

import java.util.ArrayList;

/**
 * Created by user on 29-09-2018.
 */

public class ArrayListComman {


        private static ArrayListComman obj1;
        private ArrayList<String> arr;
        private ArrayList<String> price;
        private ArrayList<String> namef;
        private String user;

        private String contact;
        //private ArrayListCommon obj1;

        private ArrayListComman()
        {
            arr=new ArrayList<String>();
            price=new ArrayList<String>();
            namef=new ArrayList<String>();
            user="";
            contact="";

        }
        public static ArrayListComman getInstance()
        {
            if(obj1==null)
            {
                obj1=new ArrayListComman();

            }
            return  obj1;
        }
        public void setListPrice(ArrayList<String> a)
        {
            this.price = a;
        }
        public ArrayList<String> getListprice()
        {
            return this.price;
        }
    public void setListName(ArrayList<String> a1)
    {
        this.namef = a1;
    }
    public ArrayList<String> getListName()
    {
        return this.namef;
    }
        public ArrayList<String> getList(){
            return this.arr;
        }

        public void setName(String n)
        {
            this.user=n;
        }
        public String getName(){
        return this.user;
    }

    public void setContact(String n1)
    {
        this.contact=n1;
    }
    public String getContact(){
        return this.contact;
    }

    }


