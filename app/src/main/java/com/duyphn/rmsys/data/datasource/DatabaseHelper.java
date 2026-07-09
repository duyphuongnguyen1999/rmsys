package com.duyphn.rmsys.data.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public final class DatabaseHelper extends SQLiteOpenHelper {

    private static volatile DatabaseHelper instance;

    public static final String DATABASE_NAME = "rmsys_store.db";
    private static final int DATABASE_VERSION = 1;

    // Product table
    private static final String TABLE_PRODUCT = "product";
    private static final String COL_PROD_ID = "id";
    private static final String COL_PROD_NAME = "name";
    private static final String COL_PROD_CAT_ID = "category_id";
    private static final String COL_PROD_SELL_PRICE = "selling_price";
    private static final String COL_PROD_IMP_PRICE = "import_price";
    private static final String COL_PROD_STOCK = "stock";
    private static final String COL_PROD_UNIT = "unit";
    private static final String COL_PROD_IMAGE_RES_ID = "image_res_id";
    private static final String COL_PROD_DESCRIPTION = "description";
    private static final String COL_PROD_UPDATED = "updated";
    private static final String COL_PROD_UPDATED_BY = "updated_by";
    private static final String COL_PROD_ACTIVE = "is_active";

    // Category table
    private static final String TABLE_CATEGORY = "category";
    private static final String COL_CATEGORY_ID = "id";
    private static final String COL_CATEGORY_NAME = "name";
    private static final String COL_CATEGORY_DESCRIPTION = "description";
    private static final String COL_CATEGORY_IMAGE_RES_ID = "image_res_id";
    private static final String COL_CATEGORY_UPDATED = "updated";
    private static final String COL_CATEGORY_UPDATED_BY = "updated_by";
    private static final String COL_CATEGORY_ACTIVE = "is_active";


    private DatabaseHelper(@Nullable Context context) {
        // Create database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Thread safe singleton pattern
    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        createProductTable(db);
        createCategoryTable(db);

        // Create indexes
        createProductTableIndexes(db);
        createCategoryTableIndexes(db);

        // Create triggers
        createProductTableTriggers(db);
        createCategoryTableTriggers(db);

        // Create views

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void createProductTable(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_PRODUCT + " " + " (" +
                        COL_PROD_ID + " TEXT PRIMARY KEY NOT NULL, " +
                        COL_PROD_NAME + " TEXT NOT NULL, " +
                        COL_PROD_CAT_ID + " TEXT, " +
                        COL_PROD_SELL_PRICE + " REAL NOT NULL, " +
                        COL_PROD_IMP_PRICE + " REAL, " +
                        COL_PROD_STOCK + " INTEGER DEFAULT 0, " +
                        COL_PROD_UNIT + " TEXT, " +
                        COL_PROD_IMAGE_RES_ID + " INTEGER, " +
                        COL_PROD_DESCRIPTION + " TEXT, " +
                        COL_PROD_UPDATED + " TEXT, " +
                        COL_PROD_UPDATED_BY + " TEXT, " +
                        COL_PROD_ACTIVE + " INTEGER DEFAULT 1, " +
                        "CONSTRAINT chk_stock CHECK (" + COL_PROD_STOCK + " >= 0)" +
                        ")";
        db.execSQL(query);
    }

    private static void createProductTableIndexes(SQLiteDatabase db) {
        String idxProductCategory =
            "CREATE INDEX idx_product_category ON " + TABLE_PRODUCT + " (" + COL_PROD_CAT_ID + ")";

        String idxProductName =
            "CREATE INDEX idx_product_name ON " + TABLE_PRODUCT + " (" + COL_PROD_NAME + ")";

        db.execSQL(idxProductCategory);
        db.execSQL(idxProductName);
    }

    private static void createProductTableTriggers(SQLiteDatabase db) {
        String createUpdateTrigger =
                "CREATE TRIGGER trg_product_updated " +
                "AFTER UPDATE ON " + TABLE_PRODUCT + " " +
                "BEGIN " +
                "   UPDATE " + TABLE_PRODUCT + " " +
                "   SET " + COL_PROD_UPDATED + " = datetime('now', 'localtime') " +
                "   WHERE " + COL_PROD_ID + " = NEW." + COL_PROD_ID + "; " +
                "END;";
        db.execSQL(createUpdateTrigger);
    }

    private static void createCategoryTable(SQLiteDatabase db) {
        String query =
            "CREATE TABLE " + TABLE_CATEGORY + " (" +
                COL_CATEGORY_ID + " TEXT PRIMARY KEY NOT NULL, " +
                COL_CATEGORY_NAME + " TEXT NOT NULL, " +
                COL_CATEGORY_DESCRIPTION + " TEXT, " +
                COL_CATEGORY_IMAGE_RES_ID + " INTEGER, " +
                COL_CATEGORY_UPDATED + " TEXT, " +
                COL_CATEGORY_UPDATED_BY + " TEXT, " +
                COL_CATEGORY_ACTIVE + " INTEGER DEFAULT 1)";
        db.execSQL(query);
    }

    private static void createCategoryTableIndexes(SQLiteDatabase db) {
        String idxCategoryName =
            "CREATE INDEX idx_category_name ON " + TABLE_CATEGORY + " (" + COL_CATEGORY_NAME + ")";

        db.execSQL(idxCategoryName);
    }

    private static void createCategoryTableTriggers(SQLiteDatabase db) {
        String createUpdateTrigger =
                "CREATE TRIGGER trg_category_updated " +
                        "AFTER UPDATE ON " + TABLE_CATEGORY + " " +
                        "BEGIN " +
                        "   UPDATE " + TABLE_CATEGORY + " " +
                        "   SET " + COL_CATEGORY_UPDATED + " = datetime('now', 'localtime') " +
                        "   WHERE " + COL_CATEGORY_ID + " = NEW." + COL_CATEGORY_ID + "; " +
                        "END;";
        db.execSQL(createUpdateTrigger);
    }

}
