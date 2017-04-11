package me.devilsen.imlist.action;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import me.devilsen.imlist.ImageActivity;
import me.devilsen.imlist.message.UMessageImageContent;
import me.devilsen.imlist.message.UMessageTxtContent;

/**
 * author : dongSen
 * date : 2017/4/11
 * desc : 点击内容处理
 */
public class ClickActionContent {

    private Context mContext;

    public ClickActionContent(Context mContext) {
        this.mContext = mContext;
    }

    public void clickTxtContent(@NonNull UMessageTxtContent content) {
        Toast.makeText(mContext, content.getContent(), Toast.LENGTH_SHORT).show();
    }


    public void clickImageContent(@NonNull UMessageImageContent content) {
        Intent intent = new Intent(mContext, ImageActivity.class);
        intent.putExtra("image", content.getPath());
        mContext.startActivity(intent);
    }
}
