package info.androidhive.slidingmenu;
import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class DatabaseHandler extends SQLiteOpenHelper 
{
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "Event";
 
    // Contacts table name
    private static final String TABLE_CONTACTS = "eventtable";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CATEGORY = "meaning";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_ALARM_ID = "alarm_id";
    private static final String KEY_START_DATE = "start_date";
    private static final String KEY_START_TIME = "start_time";
 
    public DatabaseHandler(Context context) 
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) 
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_CATEGORY + " TEXT," +  KEY_DATE + " TEXT,"+ KEY_TIME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_ALARM_ID + " INTEGER," +   KEY_START_DATE + " TEXT," + KEY_START_TIME + " TEXT"
                + ")";
        
        //db.execSQL("delete from" + DATABASE_NAME);
        Log.e("redoy", "trunk hoise");
        
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    void addContact(Contact contact) 
    {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact name
        values.put(KEY_CATEGORY, contact.getCategory()); // Contact category
        values.put(KEY_DATE, contact.getDate()); // Contact date
        values.put(KEY_TIME, contact.getTime()); // Contact time
        values.put(KEY_DESCRIPTION, contact.getDescription()); // Contact description
        values.put(KEY_ALARM_ID, contact.getAlarm_id());
        values.put(KEY_START_DATE, contact.getStart_date()); // Contact date
        values.put(KEY_START_TIME, contact.getStart_time());
 
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    Contact getContact(int id) 
    {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                KEY_NAME, KEY_CATEGORY ,KEY_DATE, KEY_TIME, KEY_DESCRIPTION,KEY_ALARM_ID,KEY_START_DATE,KEY_START_TIME}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), 
                cursor.getString(5), cursor.getInt(6),cursor.getString(7),cursor.getString(8));
        
        // return contact
        return contact;
    }
 
    // Getting All Contacts
    public List<Contact> getAllContacts() 
    {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) 
        {
            do 
            {
                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setCategory(cursor.getString(2));
                contact.setDate(cursor.getString(3));
                contact.setTime(cursor.getString(4));
                contact.setDescription(cursor.getString(5));
                contact.setAlarm_id(cursor.getInt(6));
                contact.setStart_date(cursor.getString(7));
                contact.setStart_time(cursor.getString(8));
                
                // Adding contact to list
                contactList.add(contact);
            }
            while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
 
    // Updating single contact
    public int updateContact(Contact contact) 
    {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_CATEGORY, contact.getCategory());
        values.put(KEY_DATE, contact.getDate());
        values.put(KEY_TIME, contact.getTime());
        values.put(KEY_DESCRIPTION, contact.getDescription());
        values.put(KEY_ALARM_ID, contact.getAlarm_id());
        values.put(KEY_START_DATE, contact.getStart_date()); // Contact date
        values.put(KEY_START_TIME, contact.getStart_time());
 
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
    }
 
    // Deleting single contact
    public void deleteContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
        db.close();
    }
 
    // Getting contacts Count
    public int getContactsCount() 
    {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
}