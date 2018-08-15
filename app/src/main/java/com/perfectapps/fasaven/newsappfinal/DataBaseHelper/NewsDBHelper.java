package com.perfectapps.fasaven.newsappfinal.DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.perfectapps.fasaven.newsappfinal.DataBaseHelper.DBContract.HealthNewsTable.TABLE_NAME;

/**
 * Created by Salem on 05/06/2018.
 */

public class NewsDBHelper extends SQLiteOpenHelper {
    private static final String DATA_BASE_NAME = "news.db";
    private static final int DATA_BASE_VERSION = 2;

    final static String SQL_CREATE_HEALTH_NEWS_TABLE = "CREATE TABLE "+ TABLE_NAME +
            " ( "+ _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DBContract.HealthNewsTable.COLUMN_TITLE+ " TEXT NOT NULL, "+
            DBContract.HealthNewsTable.COLUMN__INFOS+ " TEXT NOT NULL, "+
            DBContract.HealthNewsTable.COLUMN_IMAGE+" TEXT NOT NULL, "+
            DBContract.HealthNewsTable.COLUMN_TIMESTAMP+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";


    public NewsDBHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*final String SQL_CREATE_NEWS_TABLE = "CREATE TABLE "+TABLE_NAME +
                " ( "+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT NOT NULL, "+
                COLUMN__INFOS + " TEXT NOT NULL,"+
                COLUMN_TIMESTAMP+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";

        */final String SQL_CREATE_POLITICS_NEWS_TABLE = "CREATE TABLE "+ DBContract.PoliticsNewsTable.TABLE_NAME +
                " ( "+ _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DBContract.PoliticsNewsTable.COLUMN_TITLE+ " TEXT NOT NULL, "+
                DBContract.PoliticsNewsTable.COLUMN__INFOS+ " TEXT NOT NULL, "+
                DBContract.PoliticsNewsTable.COLUMN_IMAGE+" TEXT NOT NULL, "+
                DBContract.PoliticsNewsTable.COLUMN_TIMESTAMP+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";


         final  String SQL_CREATE_ECONOMIC_NEWS_TABLE = "CREATE TABLE "+ DBContract.EconomicNewsTable.TABLE_NAME +
                 " ( "+ _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                 DBContract.EconomicNewsTable.COLUMN_TITLE+ " TEXT NOT NULL, "+
                 DBContract.EconomicNewsTable.COLUMN__INFOS+ " TEXT NOT NULL, "+
                 DBContract.EconomicNewsTable.COLUMN_IMAGE+" TEXT NOT NULL, "+
                 DBContract.EconomicNewsTable.COLUMN_TIMESTAMP+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
         final  String SQL_CREATE_SPORT_NEWS_TABLE = "CREATE TABLE "+ DBContract.SportNewsTable.TABLE_NAME +
                 " ( "+ _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DBContract.SportNewsTable.COLUMN_TITLE+ " TEXT NOT NULL, "+
                DBContract.SportNewsTable.COLUMN__INFOS+ " TEXT NOT NULL, "+
                DBContract.SportNewsTable.COLUMN_IMAGE+" TEXT NOT NULL, "+
                DBContract.SportNewsTable.COLUMN_TIMESTAMP+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
         final String SQL_CREATE_ACTORS_NEWS_TABLE = "CREATE TABLE "+ DBContract.ActorsNewsTable.TABLE_NAME +
                 " ( "+ _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                 DBContract.ActorsNewsTable.COLUMN_TITLE+ " TEXT NOT NULL, "+
                 DBContract.ActorsNewsTable.COLUMN__INFOS+ " TEXT NOT NULL, "+
                 DBContract.ActorsNewsTable.COLUMN_IMAGE+" TEXT NOT NULL, "+
                 DBContract.ActorsNewsTable.COLUMN_TIMESTAMP+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";

        final String SQL_CREATE_URGENT_NEWS_TABLE = "CREATE TABLE "+ DBContract.UrgentNewsTable.TABLE_NAME +
                " ( "+ _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
               DBContract.UrgentNewsTable.COLUMN_TITLE+ " TEXT NOT NULL, "+
               DBContract.UrgentNewsTable.COLUMN__INFOS+ " TEXT NOT NULL, "+
               DBContract.UrgentNewsTable.COLUMN_IMAGE+" TEXT NOT NULL, "+
               DBContract.UrgentNewsTable.COLUMN_TIMESTAMP+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";

        final String SQL_CREATE_TECHNOLOGY_NEWS_TABLE  = "CREATE TABLE "+ DBContract.TechnologyNewsTable.TABLE_NAME +
                " ( "+ _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DBContract.TechnologyNewsTable.COLUMN_TITLE+ " TEXT NOT NULL, "+
                DBContract.TechnologyNewsTable.COLUMN__INFOS+ " TEXT NOT NULL, "+
                DBContract.TechnologyNewsTable.COLUMN_IMAGE+" TEXT NOT NULL, "+
                DBContract.TechnologyNewsTable.COLUMN_TIMESTAMP+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";
        db.execSQL(SQL_CREATE_POLITICS_NEWS_TABLE);
        db.execSQL(SQL_CREATE_ACTORS_NEWS_TABLE);

        db.execSQL(SQL_CREATE_ECONOMIC_NEWS_TABLE);
        db.execSQL(SQL_CREATE_SPORT_NEWS_TABLE);
        db.execSQL(SQL_CREATE_TECHNOLOGY_NEWS_TABLE);
        db.execSQL(SQL_CREATE_URGENT_NEWS_TABLE);
        db.execSQL(SQL_CREATE_HEALTH_NEWS_TABLE);









        //   technology

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL(SQL_CREATE_HEALTH_NEWS_TABLE);

        db.execSQL("DROP TABLE IF EXISTS  "+ DBContract.HealthNewsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DBContract.PoliticsNewsTable.TABLE_NAME);

    onCreate(db);

    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBContract.HealthNewsTable.TABLE_NAME,null,null);
        db.delete(DBContract.PoliticsNewsTable.TABLE_NAME,null,null);

        db.execSQL("delete  from " + DBContract.HealthNewsTable.TABLE_NAME);
        db.execSQL("delete  from " + DBContract.PoliticsNewsTable.TABLE_NAME);
        db.close();
    }

    //TODO (4) make an appropriate database open helper.
}
