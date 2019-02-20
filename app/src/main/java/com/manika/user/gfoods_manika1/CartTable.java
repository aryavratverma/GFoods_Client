package com.manika.user.gfoods_manika1;

import android.provider.BaseColumns;

/**
 * Created by user on 29-09-2018.
 */

public class CartTable {

    public CartTable()
    {

    }
    public static abstract class TableInfo implements BaseColumns
    {
        public static final String user_name="name_key";
        public static final String user_phone="phone_key";
        public static final String user_items="items_key";
        public static final String user_qty="qty_key";
        public static final String user_price="price_key";
        public static final String user_total="total_key";
        public static final String user_date="date_key";
        public static final String database_name="user_info";
        public static final String table_name="cart_info";
    }
}
