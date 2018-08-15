package com.perfectapps.fasaven.newsappfinal.Fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.perfectapps.fasaven.newsappfinal.DataBaseHelper.DBContract;
import com.perfectapps.fasaven.newsappfinal.DataBaseHelper.NewsDBHelper;
import com.perfectapps.fasaven.newsappfinal.ListAdapters.HealthAdapter;
import com.perfectapps.fasaven.newsappfinal.ListAdapters.PoliticsAdapter;
import com.perfectapps.fasaven.newsappfinal.R;
import com.perfectapps.fasaven.newsappfinal.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.perfectapps.fasaven.newsappfinal.DataBaseHelper.DBContract.PoliticsNewsTable.COLUMN_IMAGE;
import static com.perfectapps.fasaven.newsappfinal.DataBaseHelper.DBContract.PoliticsNewsTable.COLUMN_TIMESTAMP;
import static com.perfectapps.fasaven.newsappfinal.DataBaseHelper.DBContract.PoliticsNewsTable.COLUMN_TITLE;
import static com.perfectapps.fasaven.newsappfinal.DataBaseHelper.DBContract.PoliticsNewsTable.COLUMN__INFOS;
import static com.perfectapps.fasaven.newsappfinal.DataBaseHelper.DBContract.PoliticsNewsTable.TABLE_NAME;

/**
 * Created by Salem on 05/06/2018.
 */

public class HealthNews extends android.support.v4.app.Fragment{
    RequestQueue mQueue;
    SQLiteDatabase mDataBase;



    //TODO (5) make the recyclerview get items from the political table from the database
    //TODO build volley withen here, load the items to Sqlite
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savceInstanceState){
        View view = inflater.inflate(R.layout.health_fragment,container, false);
        mQueue = Volley.newRequestQueue(getContext());
        jsonParse();
        RecyclerView recyclerView = view.findViewById(R.id.health_rv);

        HealthAdapter HealthAdapter = new HealthAdapter(getActivity(),getAllItems());
        recyclerView.setAdapter(HealthAdapter);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }


    private void jsonParse() {
        String url = "https://my.api.mockaroo.com/healthtest.json?key=d8625f80";
        NewsDBHelper dbHelper = new NewsDBHelper(getContext());
        final NewsModel model1 = new NewsModel();
        mDataBase = dbHelper.getWritableDatabase();
        //dbHelper.deleteAll();


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject getObject = response.getJSONObject(i);



                                String title =  getObject.getString("health_title");
                                String infos = getObject.getString("health_info");
                                String image = getObject.getString("health_image");

                                model1.setTitle(title);
                                model1.setInfos(infos);
                                model1.setImage(image);


                                ContentValues contentValues = new ContentValues();
                                contentValues.put(DBContract.HealthNewsTable.COLUMN_TITLE, String.valueOf(model1.getTitle()));
                                contentValues.put(DBContract.HealthNewsTable.COLUMN__INFOS, String.valueOf(model1.getInfos()));
                                contentValues.put(DBContract.HealthNewsTable.COLUMN_IMAGE, String.valueOf(model1.getImage()));
                                mDataBase.insert(DBContract.HealthNewsTable.TABLE_NAME, null, contentValues);







                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);

    }

    public Cursor mCursor;

    public void swapCursor(Cursor newCursor) {

        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;

    }
    public Cursor getAllItems(){
        NewsDBHelper newsDBHelper = new NewsDBHelper(getActivity());

        mDataBase = newsDBHelper.getReadableDatabase();
        return mDataBase.query(DBContract.HealthNewsTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DBContract.HealthNewsTable.COLUMN_TIMESTAMP + " DESC"

        );

    }



}