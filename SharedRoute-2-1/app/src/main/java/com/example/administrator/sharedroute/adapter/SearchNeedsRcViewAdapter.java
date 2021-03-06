package com.example.administrator.sharedroute.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.sharedroute.R;
import com.example.administrator.sharedroute.activity.BlurredActivity;
import com.example.administrator.sharedroute.entity.GoodsModel;
import com.example.administrator.sharedroute.entity.listItem;
import com.example.administrator.sharedroute.listener.OnBlurCompleteListener;
import com.example.administrator.sharedroute.widget.BlurBehind;

import java.util.ArrayList;

import static com.example.administrator.sharedroute.activity.SearchNeedsActivity.selectedItem;

/**
 * Created by luodian on 03/10/2017.
 */

public class SearchNeedsRcViewAdapter extends RecyclerView.Adapter<SearchNeedsRcViewAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<listItem> mDataset;
    private CallBackListener mCallBackListener;
    private Context mContext;
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        CardView mCardView;
        TextView kindsTextView;
        TextView priceTextView;
        TextView sendTimeTextView;
        TextView fetchTimeTextView;
        TextView sendLocTextView;
        TextView fetchLocTextView;
        ImageView mImageView;

        ViewHolder(final View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.searchNeeds_card_view);

            kindsTextView = (TextView) itemView.findViewById(R.id.searchNeeds_item_kinds);
            priceTextView = (TextView) itemView.findViewById(R.id.searchNeeds_item_price);
            sendTimeTextView = (TextView) itemView.findViewById(R.id.searchNeeds_send_time);
            fetchTimeTextView = (TextView) itemView.findViewById(R.id.searchNeeds_fetch_time);
            sendLocTextView = (TextView) itemView.findViewById(R.id.searchNeeds_send_loc);
            fetchLocTextView = (TextView) itemView.findViewById(R.id.searchNeeds_fetch_loc);

            mImageView = (ImageView) itemView.findViewById(R.id.trolley_icon);
        }

        public void updateUI(GoodsModel goods){
            if (goods != null
                    && goods.getmGoodsBitmap() != null
                    && mImageView != null)
                mImageView.setImageBitmap(goods.getmGoodsBitmap());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SearchNeedsRcViewAdapter(ArrayList<listItem> myDataset)
    {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
//    @Override
//    public SearchNeedsRcViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        // create a new view
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_search_needs, parent, false);
//        // set the view's size, margins, paddings and layout parameters
//        v.setOnClickListener(this);
//        return new ViewHolder(v);
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_search_needs,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.mCardView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                BlurBehind.getInstance().execute((Activity) mContext, new OnBlurCompleteListener() {
                    final int position = holder.getAdapterPosition();
                    @Override
                    public void onBlurComplete() {
                        Intent intent = new Intent(mContext, BlurredActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra("title_name","title"+Integer.toString(position+1));
                        intent.putExtra("select","release");
                        intent.putExtra("Activity","SearchNeeds");
                        mContext.startActivity(intent);
                    }
                });
                return true;
            }
        });
        return holder;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.kindsTextView.setText(mDataset.get(position).getExpressType());
        holder.fetchTimeTextView.setText(mDataset.get(position).getInTimeStamp());
        holder.sendTimeTextView.setText(mDataset.get(position).getOutTimeStamp());
        holder.fetchLocTextView.setText(mDataset.get(position).getInLocation());
        holder.sendLocTextView.setText(mDataset.get(position).getOutLocation());
        holder.priceTextView.setText(mDataset.get(position).getPrice());

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            //说实话，这样监听不太好，每次滑动getView的时候都要重新new一个监听，但是必须获取ChechView所在的那个Item的position，所以只能卸载getView函数内部
            @Override
            public void onClick(View v) {//等于说对于那10个左右的ChechBox,其绑定的监听在不断的改变
                if (holder.mImageView != null && mCallBackListener != null)
                    mCallBackListener.callBackImg(holder.mImageView);
                assert holder.mImageView != null;
                holder.mImageView.setClickable(false);
                holder.mImageView.setImageResource(R.drawable.trolley_pressed);
                listItem item = mDataset.get(position);
                selectedItem.add(item);
            }
        });
//        holder.mCardView.setOnLongClickListener(new View.OnLongClickListener(){
//            @Override
//            public boolean onLongClick(View v){
//                BlurBehind.getInstance().execute((Activity) mContext, new OnBlurCompleteListener() {
//                    @Override
//                    public void onBlurComplete() {
//                        Intent intent = new Intent(mContext, BlurredActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        mContext.startActivity(intent);
//                    }
//                });
//                return true;
//            }
//        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return mDataset == null ? 0 : mDataset.size();
    }

    public void setCallBackListener(CallBackListener mCallBackListener){
        this.mCallBackListener = mCallBackListener;
    }

    public interface CallBackListener{
        void callBackImg(ImageView goodsImg);
    }
}
