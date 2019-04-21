package com.zl.androidstudy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhangling
 * date 2019-04-19 23:15
 * description: 悬停列表适配器
 */
public class SuspensionAdapter extends RecyclerView.Adapter<SuspensionAdapter.ViewHolder> {
    private List<Integer> imgList;
    private Context context;

    SuspensionAdapter(List<Integer> imgList, Context context) {
        this.imgList = imgList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_suspension, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).asDrawable().load(imgList.get(i)).into(viewHolder.image);
        viewHolder.suspensionText.setText(String.valueOf(i));
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.suspension_text)
        TextView suspensionText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}