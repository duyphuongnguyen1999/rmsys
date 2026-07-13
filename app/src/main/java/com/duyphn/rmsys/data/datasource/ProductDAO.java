package com.duyphn.rmsys.data.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.duyphn.rmsys.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final DatabaseHelper dbHelper;

    public ProductDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public boolean insert(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_PROD_ID, product.getId());
        values.put(DatabaseHelper.COL_PROD_NAME, product.getName());
        values.put(DatabaseHelper.COL_PROD_CAT_ID, product.getCategoryId());
        values.put(DatabaseHelper.COL_PROD_SELL_PRICE, product.getSellingPrice());
        values.put(DatabaseHelper.COL_PROD_IMP_PRICE, product.getImportPrice());
        values.put(DatabaseHelper.COL_PROD_STOCK, product.getStock());
        values.put(DatabaseHelper.COL_PROD_UNIT, product.getUnit());
        values.put(DatabaseHelper.COL_PROD_IMAGE_RES_ID, product.getImageResId());
        values.put(DatabaseHelper.COL_PROD_DESCRIPTION, product.getDescription());

        long result = db.insert("product", null, values);
        db.close();
        return result != -1;
    }

    public List<Product> findByActiveState(boolean active) {
        List<Product> productList = new ArrayList<>();
        String selectQuery =
            "SELECT " +
                DatabaseHelper.COL_PROD_ID + ", " +
                DatabaseHelper.COL_PROD_NAME + ", " +
                DatabaseHelper.COL_PROD_CAT_ID + ", " +
                DatabaseHelper.COL_PROD_SELL_PRICE + ", " +
                DatabaseHelper.COL_PROD_IMP_PRICE + ", " +
                DatabaseHelper.COL_PROD_STOCK + ", " +
                DatabaseHelper.COL_PROD_UNIT + ", " +
                DatabaseHelper.COL_PROD_IMAGE_RES_ID + ", " +
                DatabaseHelper.COL_PROD_DESCRIPTION + " " +
            "FROM " + DatabaseHelper.TABLE_PRODUCT + " " +
            "WHERE " + DatabaseHelper.COL_PROD_ACTIVE + "= ?";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String activeValue = active ? "1" : "0";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{ activeValue });

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getString(0));
                product.setName(cursor.getString(1));
                product.setCategoryId(cursor.getString(2));
                product.setSellingPrice(cursor.getDouble(3));
                product.setImportPrice(cursor.getDouble(4));
                product.setStock(cursor.getInt(5));
                product.setUnit(cursor.getString(6));
                product.setImageResId(cursor.getInt(7));
                product.setDescription(cursor.getString(8));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productList;
    }

    public Product findById(@NonNull String id) {
        Product product = new Product();
        String selectQuery =
                "SELECT " +
                        DatabaseHelper.COL_PROD_ID + ", " +
                        DatabaseHelper.COL_PROD_NAME + ", " +
                        DatabaseHelper.COL_PROD_CAT_ID + ", " +
                        DatabaseHelper.COL_PROD_SELL_PRICE + ", " +
                        DatabaseHelper.COL_PROD_IMP_PRICE + ", " +
                        DatabaseHelper.COL_PROD_STOCK + ", " +
                        DatabaseHelper.COL_PROD_UNIT + ", " +
                        DatabaseHelper.COL_PROD_IMAGE_RES_ID + ", " +
                        DatabaseHelper.COL_PROD_DESCRIPTION + " " +
                "FROM " + DatabaseHelper.TABLE_PRODUCT + " " +
                "WHERE " + DatabaseHelper.COL_PROD_ID + " = ? " +
                        "AND " + DatabaseHelper.COL_PROD_ACTIVE + " = 1"; // Find all active product

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] { id });

        if (cursor.moveToFirst()) {
            do {
                product.setId(cursor.getString(0));
                product.setName(cursor.getString(1));
                product.setCategoryId(cursor.getString(2));
                product.setSellingPrice(cursor.getDouble(3));
                product.setImportPrice(cursor.getDouble(4));
                product.setStock(cursor.getInt(5));
                product.setUnit(cursor.getString(6));
                product.setImageResId(cursor.getInt(7));
                product.setDescription(cursor.getString(8));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return product;
    }

    public Product findById(@NonNull String id, boolean active) {
        Product product = new Product();
        String selectQuery =
                "SELECT " +
                        DatabaseHelper.COL_PROD_ID + ", " +
                        DatabaseHelper.COL_PROD_NAME + ", " +
                        DatabaseHelper.COL_PROD_CAT_ID + ", " +
                        DatabaseHelper.COL_PROD_SELL_PRICE + ", " +
                        DatabaseHelper.COL_PROD_IMP_PRICE + ", " +
                        DatabaseHelper.COL_PROD_STOCK + ", " +
                        DatabaseHelper.COL_PROD_UNIT + ", " +
                        DatabaseHelper.COL_PROD_IMAGE_RES_ID + ", " +
                        DatabaseHelper.COL_PROD_DESCRIPTION + " " +
                "FROM " + DatabaseHelper.TABLE_PRODUCT + " " +
                "WHERE " + DatabaseHelper.COL_PROD_ID + " = ? " +
                        "AND " + DatabaseHelper.COL_PROD_ACTIVE + " = ?";

        String activeValue = active ? "1" : "0";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] { id, activeValue });

        if (cursor.moveToFirst()) {
            do {
                product.setId(cursor.getString(0));
                product.setName(cursor.getString(1));
                product.setCategoryId(cursor.getString(2));
                product.setSellingPrice(cursor.getDouble(3));
                product.setImportPrice(cursor.getDouble(4));
                product.setStock(cursor.getInt(5));
                product.setUnit(cursor.getString(6));
                product.setImageResId(cursor.getInt(7));
                product.setDescription(cursor.getString(8));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return product;
    }

    public List<Product> findByName(String name) {
        List<Product> productList = new ArrayList<>();
        String selectQuery =
                "SELECT " +
                        DatabaseHelper.COL_PROD_ID + ", " +
                        DatabaseHelper.COL_PROD_NAME + ", " +
                        DatabaseHelper.COL_PROD_CAT_ID + ", " +
                        DatabaseHelper.COL_PROD_SELL_PRICE + ", " +
                        DatabaseHelper.COL_PROD_IMP_PRICE + ", " +
                        DatabaseHelper.COL_PROD_STOCK + ", " +
                        DatabaseHelper.COL_PROD_UNIT + ", " +
                        DatabaseHelper.COL_PROD_IMAGE_RES_ID + ", " +
                        DatabaseHelper.COL_PROD_DESCRIPTION + " " +
                "FROM " + DatabaseHelper.TABLE_PRODUCT + " " +
                "WHERE " + DatabaseHelper.COL_PROD_ID + "= '" + name + "'";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getString(0));
                product.setName(cursor.getString(1));
                product.setCategoryId(cursor.getString(2));
                product.setSellingPrice(cursor.getDouble(3));
                product.setImportPrice(cursor.getDouble(4));
                product.setStock(cursor.getInt(5));
                product.setUnit(cursor.getString(6));
                product.setImageResId(cursor.getInt(7));
                product.setDescription(cursor.getString(8));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productList;
    }

    public List<Product> findByCategoryId(String cat_id) {
        List<Product> productList = new ArrayList<>();
        String selectQuery =
                "SELECT " +
                        DatabaseHelper.COL_PROD_ID + ", " +
                        DatabaseHelper.COL_PROD_NAME + ", " +
                        DatabaseHelper.COL_PROD_CAT_ID + ", " +
                        DatabaseHelper.COL_PROD_SELL_PRICE + ", " +
                        DatabaseHelper.COL_PROD_IMP_PRICE + ", " +
                        DatabaseHelper.COL_PROD_STOCK + ", " +
                        DatabaseHelper.COL_PROD_UNIT + ", " +
                        DatabaseHelper.COL_PROD_IMAGE_RES_ID + ", " +
                        DatabaseHelper.COL_PROD_DESCRIPTION + " " +
                "FROM " + DatabaseHelper.TABLE_PRODUCT + " " +
                "WHERE " + DatabaseHelper.COL_PROD_ID + "= '" + cat_id + "'";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getString(0));
                product.setName(cursor.getString(1));
                product.setCategoryId(cursor.getString(2));
                product.setSellingPrice(cursor.getDouble(3));
                product.setImportPrice(cursor.getDouble(4));
                product.setStock(cursor.getInt(5));
                product.setUnit(cursor.getString(6));
                product.setImageResId(cursor.getInt(7));
                product.setDescription(cursor.getString(8));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productList;
    }




}
