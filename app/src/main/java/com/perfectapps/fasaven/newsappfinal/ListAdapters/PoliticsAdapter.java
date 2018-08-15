package com.perfectapps.fasaven.newsappfinal.ListAdapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.perfectapps.fasaven.newsappfinal.DataBaseHelper.DBContract.PoliticsNewsTable.*;

import com.perfectapps.fasaven.newsappfinal.R;
import com.squareup.picasso.Picasso;


/**
 * Created by Salem on 05/06/2018.
 */

public class PoliticsAdapter extends RecyclerView.Adapter<PoliticsAdapter.ListViewHolder>{
    public Cursor mCursor;
    public Context mContext;
    public PoliticsAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }
    private OnItemClickListener mListener;

  public interface OnItemClickListener{
        void onItemClick(int position);
  }
  public void setOnItemClickListener(OnItemClickListener listener){
      mListener = listener;
  }




    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.politics_news_items,parent,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
       // ((ListViewHolder) holder).bindView(position);

        if(!mCursor.moveToPosition(position)){return;}


        String title =mCursor.getString(mCursor.getColumnIndex(COLUMN_TITLE));
        String info =mCursor.getString(mCursor.getColumnIndex(COLUMN__INFOS));
        String image = mCursor.getString(mCursor.getColumnIndex(COLUMN_IMAGE));

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


   public class ListViewHolder extends RecyclerView.ViewHolder{
        private TextView mNewsTitleTV;
        private TextView mNewsInfoTV;
        private ImageView mNewsImage;
        public ListViewHolder(View itemView){
            super(itemView);
            mNewsImage = itemView.findViewById(R.id.news_image);
            mNewsInfoTV = itemView.findViewById(R.id.news_info);
            mNewsTitleTV = itemView.findViewById(R.id.news_title);

        }

   }


}
