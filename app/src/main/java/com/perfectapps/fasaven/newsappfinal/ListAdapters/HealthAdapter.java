package com.perfectapps.fasaven.newsappfinal.ListAdapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.perfectapps.fasaven.newsappfinal.DataBaseHelper.DBContract;
import com.perfectapps.fasaven.newsappfinal.R;
import com.squareup.picasso.Picasso;


/**
 * Created by Salem on 09/06/2018.
 */


public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.HealthViewHolder> {
    public Cursor mCursor;
    public Context mContext;

    public HealthAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }


    @Override
    public HealthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.health_news_items, parent, false);

        return new HealthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HealthViewHolder holder, int position) {
        // ((ListViewHolder) holder).bindView(position);

        if (!mCursor.moveToPosition(position)) {
            return;
        }


        String title = mCursor.getString(mCursor.getColumnIndex(DBContract.HealthNewsTable.COLUMN_TITLE));
        String info = mCursor.getString(mCursor.getColumnIndex(DBContract.HealthNewsTable.COLUMN__INFOS));
        String image = mCursor.getString(mCursor.getColumnIndex(DBContract.HealthNewsTable.COLUMN_IMAGE));

        holder.mNewsTitleTV.setText(title);
        holder.mNewsInfoTV.setText(info);
        Picasso.get().load(image).into(holder.mNewsImage);

        //todo must finish this after I finish database steps.

    }

    @Override
    public int getItemCount() {

        return mCursor.getCount();
    }

    //TODO(2) make the whole statement for the recyclerview


    public class HealthViewHolder extends RecyclerView.ViewHolder {
        private TextView mNewsTitleTV;
        private TextView mNewsInfoTV;
        private ImageView mNewsImage;

        public HealthViewHolder(View itemView) {
            super(itemView);
            mNewsImage = itemView.findViewById(R.id.health_news_image);
            mNewsInfoTV = itemView.findViewById(R.id.health_news_info);
            mNewsTitleTV = itemView.findViewById(R.id.health_news_title);

        }


    }

}