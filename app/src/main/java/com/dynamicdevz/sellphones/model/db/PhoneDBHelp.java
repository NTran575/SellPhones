package com.dynamicdevz.sellphones.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.dynamicdevz.sellphones.model.data.Phone;

import java.util.ArrayList;
import java.util.List;

import static com.dynamicdevz.sellphones.util.Logger.logDebug;

public class PhoneDBHelp extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "phone.db";
    public static int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "phones";
    public static final String COLUMN_YEAR = "year_made";
    public static final String COLUMN_MODEL = "Model";
    public static final String COLUMN_PRICE = "Price";
    public static final String COLUMN_MANUFACTURER = "Manufacturer";
    public static final String COLUMN_PHONE_ID = "phone_id";

    public PhoneDBHelp(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCommand = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_PHONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_YEAR + " INTEGER, " +
                COLUMN_MODEL + " TEXT, " +
                COLUMN_PRICE + " DECIMAL(4,2), "+
                COLUMN_MANUFACTURER + " TEXT)";
        sqLiteDatabase.execSQL(createCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String upgrade = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(upgrade);
        onCreate(sqLiteDatabase);
    }

    public void insertPhone(Phone phone) {
        logDebug("Inserting new phone: "+ phone.getModel());
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MANUFACTURER, phone.getManufacturer().name());
        contentValues.put(COLUMN_MODEL, phone.getModel());
        contentValues.put(COLUMN_PRICE, phone.getPrice());
        contentValues.put(COLUMN_YEAR, phone.getYear());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
    }
    public List<Phone> getAllPhones() {
        logDebug("Reading from database.");
        List<Phone> phones = new ArrayList<>();
        String getQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = getReadableDatabase().rawQuery(getQuery, null);
        cursor.move(-1);

        //Phone constructor
        while (cursor.moveToNext()) {
            Phone phone = new Phone(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PHONE_ID)),
                    Phone.Manufacturer.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_MANUFACTURER))),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MODEL)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR))
            );
            phones.add(phone);
        }
        return phones;
    }
    public void deletePhone(Phone phone){
        String deleteQuery = TABLE_NAME + " WHERE "+COLUMN_PHONE_ID + " = "+phone.getPhoneid();
        getWritableDatabase().delete(deleteQuery, null, null);
    }




}
