package br.com.diebold.partsrequest.data.dao.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import android.util.Log;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    private final String TABLE;


    public ConexaoSQLite(Context context, String name,
                             SQLiteDatabase.CursorFactory factory, int version, String create,
                             DataBaseCreator databaseCreator, String table) {

        super(context, name , factory , version );

        this.TABLE = table;
        this.database = this.getDatabase();

        createTables(databaseCreator, this.database);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //databaseCreator.create(db);
    }

    private void createTables(DataBaseCreator databaseCreator, SQLiteDatabase db) {
        // Create Tables on DataBase
        try {
            databaseCreator.create(db);
        }catch (Exception e){
            Log.e( this.getClass().getSimpleName() +".createTables", e.getMessage(), e);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {


            Log.w(db.getClass().getName(),
                    "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ", which will destroy all old data");


            if (db.getPath().contains(DataBaseName.PARTSREQUESTDB.getValue()) && oldVersion < 0) {
                try {
                    //db.execSQL();
                } catch (Exception e) {
                    Log.w(this.getClass().getSimpleName() +".onUpgrade",  e);
                }
            }

        }
    }

    private SQLiteDatabase getDatabase() {
        if (database == null || !database.isOpen()) {
            database = this.getWritableDatabase();
        }
        return database;
    }

    public boolean hasRecord() {
        boolean ret = false;
        String sql = "SELECT * FROM " + TABLE;

        if (!database.isOpen() || database == null) {
            database = getDatabase();
        }

        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            ret = true;
        } else {
            ret = false;
        }

        return ret;
    }

    @Override
    public synchronized void close() {
        super.close();
        if (database.isOpen()) {
             database.close();
        }
    }


//    public Long insertWithOnConflict(String nullColumnHack,
//                                     ContentValues initialValues) {
//        return database.insertWithOnConflict(TABLE, null, initialValues, SQLiteDatabase.CONFLICT_REPLACE);
//    }


    public long inserirOuAtualizar(ContentValues values,
                                    String whereClause,
                                    String[] whereArgs) {

        long ia = 0;

        if(whereClause.contains("WHERE"))
            whereClause = whereClause.replace("WHERE", "");

        ia = database.update(TABLE, values, whereClause, whereArgs);
        if (ia == 0) {
            ia = database.insertWithOnConflict(TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }


        return ia;

    }


    public long inserir(ContentValues values) {

        long i = 0;

        i = database.insert(TABLE, null, values);

        return i;

    }

    public long atualizar(ContentValues values,
                                   String whereClause,
                                   String[] whereArgs) {

        long a = 0;

        if(whereClause.contains("WHERE"))
            whereClause = whereClause.replace("WHERE", "");

        a = database.update(TABLE, values, whereClause , whereArgs);

        return a;

    }


    public Cursor executarQuerySql(String sql, String[] selectionArgs) {

        Cursor cursor;

        cursor = database.rawQuery(sql, selectionArgs);

        return cursor;

    }


    public int deletar( String whereClause, String[] whereArgs) {


        int d = 0;

        if(whereClause.contains("WHERE"))
            whereClause = whereClause.replace("WHERE", "");

        d = database.delete(TABLE, whereClause , whereArgs);


        return d;

    }




}
