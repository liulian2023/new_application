package com.example.new_application.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.new_application.R;
import com.example.new_application.bean.BaseUrlEntity;

import java.util.List;

public class BaseUrlAdapter extends BaseQuickAdapter<BaseUrlEntity, BaseViewHolder> {
    public BaseUrlAdapter(int layoutResId, @Nullable List<BaseUrlEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseUrlEntity item) {
        helper.setText(R.id.line_num_tv,"线路"+(helper.getAdapterPosition()+1));
        helper.setText(R.id.url_tv,item.getDomain());
       ImageView selector_iv= helper.getView(R.id.selector_iv);
       if(item.isCheck()){
           selector_iv.setVisibility(View.VISIBLE);
       }else {
           selector_iv.setVisibility(View.GONE);
       }
        TextView speed_tv =helper.getView(R.id.speed_tv);
        boolean success = item.isSuccess();
        if(success){
            long endTime = item.getEndTime();
            if(endTime == 0){
                return;
            }
            long startTime = item.getStartTime();
            long differenceTime = endTime - startTime;
            if(differenceTime>=500){
                speed_tv.setTextColor(ContextCompat.getColor(getContext(),R.color.red));
            }else {
                speed_tv.setTextColor(ContextCompat.getColor(getContext(),R.color.green));
            }
            speed_tv.setText("延迟:"+differenceTime+"ms");
        }else {
            speed_tv.setText("网络状况不佳");
            speed_tv.setTextColor(ContextCompat.getColor(getContext(),R.color.red));
        }

    }

}
