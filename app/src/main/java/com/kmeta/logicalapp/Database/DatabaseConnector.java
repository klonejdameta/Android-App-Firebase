package com.kmeta.logicalapp.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.kmeta.logicalapp.Models.CustomerModel;
import com.kmeta.logicalapp.Models.DocumentsModel;
import com.kmeta.logicalapp.Models.UsersModel;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "logical.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME_users = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_name = "name";
    private static final String COLUMN_username = "username";
    private static final String COLUMN_email = "email";
    private static final String COLUMN_password = "password";
    private static final String TABLE_NAME_documents = "documents";
    private static final String COLUMN_document_number = "document_number";
    private static final String COLUMN_document_date = "document_date";
    private static final String COLUMN_amount = "amount";
    private static final String COLUMN_customer = "customer";
    private static final String TABLE_NAME_customers = "customers";
    private static final String COLUMN_customer_first_name = "first_name";
    private static final String COLUMN_customer_last_name = "last_name";
    private static final String COLUMN_customer_birth_date = "birth_date";
    private static final String COLUMN_customer_address = "address";
    private static final String COLUMN_customer_longitude = "longitude";
    private static final String COLUMN_customer_latitude = "latitude";
    private static final String COLUMN_customer_is_active = "is_active";
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseConnector(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase myDatabase) {
/*        String createTableUsers = "CREATE TABLE " + TABLE_NAME_users +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_name + " TEXT," +
                COLUMN_username + " TEXT, " +
                COLUMN_email + " TEXT," +
                COLUMN_password + " TEXT);";
        myDatabase.execSQL(createTableUsers);

        String createTableDocuments = "CREATE TABLE " + TABLE_NAME_documents +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_document_number + " TEXT," +
                COLUMN_document_date + " DATE, " +
                COLUMN_amount + " INT," +
                COLUMN_customer + " TEXT);";
        myDatabase.execSQL(createTableDocuments);

        String createTableCustomers = "CREATE TABLE " + TABLE_NAME_customers +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_customer_first_name + " TEXT," +
                COLUMN_customer_last_name + " TEXT, " +
                COLUMN_customer_birth_date + " DATE," +
                COLUMN_customer_address + " TEXT," +
                COLUMN_customer_longitude + " INT," +
                COLUMN_customer_latitude + " INT," +
                COLUMN_customer_is_active + " BOOLEAN DEFAULT 'true');";
        myDatabase.execSQL(createTableCustomers);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDatabase, int i, int i1) {
/*        myDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_users);
        myDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_documents);
        myDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_customers);
        onCreate(myDatabase);*/
    }

/*    public Boolean insertDataUsers(String name, String username, String email, String password) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_name, name);
        contentValues.put(COLUMN_username, username);
        contentValues.put(COLUMN_email, email);
        String hashedPassword = hashPassword(password);
        contentValues.put(COLUMN_password, hashedPassword);
        long result = myDatabase.insert(TABLE_NAME_users, null, contentValues);
        return result != -1;
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }

    public Boolean insertDataDocuments(String documentNo, String documentDate, String amount, String customer) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_document_number, documentNo);
        contentValues.put(COLUMN_document_date, documentDate);
        contentValues.put(COLUMN_amount, amount);
        contentValues.put(COLUMN_customer, customer);
        long result = myDatabase.insert(TABLE_NAME_documents, null, contentValues);
        return result != -1;
    }

    public Boolean insertDataCustomers(String customerFirstName, String customerLastName, String birthDate,
                                       String address, String latitude, String longitude) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_customer_first_name, customerFirstName);
        contentValues.put(COLUMN_customer_last_name, customerLastName);
        contentValues.put(COLUMN_customer_birth_date, birthDate);
        contentValues.put(COLUMN_customer_address, address);
        contentValues.put(COLUMN_customer_longitude, latitude);
        contentValues.put(COLUMN_customer_latitude, longitude);
        long result = myDatabase.insert(TABLE_NAME_customers, null, contentValues);
        return result != -1;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_users + " WHERE " + COLUMN_email + " = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            @SuppressLint("Range") String hashedPassword = cursor.getString(cursor.getColumnIndex(COLUMN_password));
            return BCrypt.checkpw(password, hashedPassword);
        } else {
            return false;
        }
    }

    public String getUserName(String email) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        String userName = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            userName = cursor.getString(2);
        }
        cursor.close();
        myDatabase.close();
        return userName;
    }

    public void addCustomer(CustomerModel customerModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConnector.COLUMN_customer_first_name, customerModel.getFirstName());
        contentValues.put(DatabaseConnector.COLUMN_customer_last_name, customerModel.getLastName());
        contentValues.put(DatabaseConnector.COLUMN_customer_birth_date, customerModel.getBirthDate());
        contentValues.put(DatabaseConnector.COLUMN_customer_address, customerModel.getAddress());
        contentValues.put(DatabaseConnector.COLUMN_customer_latitude, customerModel.getLatitude());
        contentValues.put(DatabaseConnector.COLUMN_customer_longitude, customerModel.getLongitude());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseConnector.TABLE_NAME_customers, null, contentValues);
    }

    public void updateCustomer(CustomerModel customerModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConnector.COLUMN_customer_first_name, customerModel.getFirstName());
        contentValues.put(DatabaseConnector.COLUMN_customer_last_name, customerModel.getLastName());
        contentValues.put(DatabaseConnector.COLUMN_customer_birth_date, customerModel.getBirthDate());
        contentValues.put(DatabaseConnector.COLUMN_customer_address, customerModel.getAddress());
        contentValues.put(DatabaseConnector.COLUMN_customer_latitude, customerModel.getLatitude());
        contentValues.put(DatabaseConnector.COLUMN_customer_longitude, customerModel.getLongitude());
        contentValues.put(DatabaseConnector.COLUMN_customer_is_active, customerModel.getIsActive());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME_customers, contentValues, COLUMN_ID + " = ?", new String[]
                {String.valueOf(customerModel.getId())});
    }

    public void deleteCustomer(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME_customers, COLUMN_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

    public void addDocument(DocumentsModel documentsModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConnector.COLUMN_document_number, documentsModel.getDocumentNumber());
        contentValues.put(DatabaseConnector.COLUMN_document_date, documentsModel.getDocumentDate());
        contentValues.put(DatabaseConnector.COLUMN_amount, documentsModel.getAmount());
        contentValues.put(DatabaseConnector.COLUMN_customer, documentsModel.getCustomer());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseConnector.TABLE_NAME_documents, null, contentValues);
    }

    public void updateDocument(DocumentsModel documentsModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConnector.COLUMN_document_number, documentsModel.getDocumentNumber());
        contentValues.put(DatabaseConnector.COLUMN_document_date, documentsModel.getDocumentDate());
        contentValues.put(DatabaseConnector.COLUMN_amount, documentsModel.getAmount());
        contentValues.put(DatabaseConnector.COLUMN_customer, documentsModel.getCustomer());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME_documents, contentValues, COLUMN_ID + " = ?", new String[]
                {String.valueOf(documentsModel.getId())});
    }

    public void deleteDocument(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME_documents, COLUMN_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

    public List<CustomerModel> getCustomerList() {
        String sql = "select * from " + TABLE_NAME_customers;
        sqLiteDatabase = this.getReadableDatabase();
        List<CustomerModel> storeCustomer = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String firstName = cursor.getString(1);
                String lastName = cursor.getString(2);
                String birthDate = cursor.getString(3);
                String address = cursor.getString(4);
                String longitude = cursor.getString(5);
                String latitude = cursor.getString(6);
                String isActive = cursor.getString(7);
                storeCustomer.add(new CustomerModel(id, firstName, lastName,
                        birthDate, address, longitude, latitude, isActive));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeCustomer;
    }

    public List<DocumentsModel> getDocumentsList() {
        String sql = "select * from " + TABLE_NAME_documents;
        sqLiteDatabase = this.getReadableDatabase();
        List<DocumentsModel> storeDocument = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String documentNumber = cursor.getString(1);
                String documentDate = cursor.getString(2);
                String documentAmount = cursor.getString(3);
                String documentCustomer = cursor.getString(4);
                storeDocument.add(new DocumentsModel(id, documentNumber, documentDate,
                        documentAmount, documentCustomer));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeDocument;
    }
    public List<UsersModel> getUsersList() {
        String sql = "select * from " + TABLE_NAME_users;
        sqLiteDatabase = this.getReadableDatabase();
        List<UsersModel> storeDocument = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String userName = cursor.getString(2);
                String email = cursor.getString(3);
                storeDocument.add(new UsersModel(id, name, userName,
                        email));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeDocument;
    }
    public void updateUsers(UsersModel usersModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConnector.COLUMN_name, usersModel.getName());
        contentValues.put(DatabaseConnector.COLUMN_username, usersModel.getUserName());
        contentValues.put(DatabaseConnector.COLUMN_email, usersModel.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME_users, contentValues, COLUMN_ID + " = ?", new String[]
                {String.valueOf(usersModel.getId())});
    }

    public void deleteUsers(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME_users, COLUMN_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }*/
}