package br.com.diebold.partsrequest.data.dao.dbHelper;

import android.content.ContentValues;

import br.com.diebold.partsrequest.data.dao.model.IModel;

public interface ICrud {

//    long insert(ContentValues values);
//
//    long update(ContentValues values, String whereClause,
//                String[] whereArgs);
//
//    long delete(String whereClause, String[] whereArgs);

    IModel build(android.database.Cursor cursor);

}
