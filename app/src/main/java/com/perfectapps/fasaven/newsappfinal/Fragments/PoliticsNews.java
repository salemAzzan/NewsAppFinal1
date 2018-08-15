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
import com.perfectapps.fasaven.newsappfinal.ListAdapters.PoliticsAdapter;
import com.perfectapps.fasaven.newsappfinal.R;
import static com.perfectapps.fasaven.newsappfinal.DataBaseHelper.DBContract.PoliticsNewsTable.*;
import com.perfectapps.fasaven.newsappfinal.DataBaseHelper.NewsDBHelper;
import com.perfectapps.fasaven.newsappfinal.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Salem on 05/06/2018.
 */

public class PoliticsNews extends android.support.v4.app.Fragment{
    RequestQueue mQueue;
    SQLiteDatabase mDataBase;



    //TODO (5) make the recyclerview get items from the political table from the database
    //TODO build volley withen here, load the items to Sqlite
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savceInstanceState){
        View view = inflater.inflate(R.layout.politics_fragment,container, false);
        mQueue =Volley.newRequestQueue(getContext());
        jsonParse();
        RecyclerView recyclerView = view.findViewById(R.id.politic_rv);

        PoliticsAdapter politicsAdapter = new PoliticsAdapter(getActivity(),getAllItems());
        recyclerView.setAdapter(politicsAdapter);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }


    private void jsonParse() {
        String url = "https://my.api.mockaroo.com/news_test.json?key=d8625f80";
        NewsDBHelper dbHelper = new NewsDBHelper(getContext());
        final NewsModel model = new NewsModel();
        mDataBase = dbHelper.getWritableDatabase();
        //dbHelper.deleteAll();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject getObject = response.getJSONObject(i);



                                String title =  getObject.getString("news_title");
                                String infos = getObject.getString("news_info");
                                String image = getObject.getString("news_image");
                                model.setTitle(title);
                                model.setInfos(infos);
                                model.setImage(image);


                                ContentValues contentValues = new ContentValues();
                                contentValues.put(COLUMN_TITLE, String.valueOf(model.getTitle()));
                                contentValues.put(COLUMN__INFOS, String.valueOf(model.getInfos()));
                                contentValues.put(COLUMN_IMAGE, String.valueOf(model.getImage()));
                                mDataBase.insert(TABLE_NAME, null, contentValues);
                                swapCursor(getAllItems());





                             /*   newsInfosList.add(new NewsInfos (name,infos,image));
                                newsAdapter = new NewsAdapter(MainActivity.this, newsInfosList);
                                recyclerView.setAdapter(newsAdapter);
                                newsAdapter.setOnItemClickListener(MainActivity.this);*/

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
        return mDataBase.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_TIMESTAMP + " DESC"

        );

    }



}
