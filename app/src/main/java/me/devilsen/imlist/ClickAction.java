package me.devilsen.imlist;

import android.content.Context;
import android.content.Intent;

import me.devilsen.imlist.message.UMessage;
import me.devilsen.imlist.message.UMessageContent;

/**
 * author : dongSen
 * date : 2017/4/1
 * desc : 统一处理点击事件
 */
public class ClickAction {

    private Context mContext;

    public ClickAction(Context context) {
        this.mContext = context;
    }

    public void clickAvatar(String userId) {
        Intent intent = new Intent(mContext, AvatarActivity.class);
        intent.putExtra("userId", userId);
        mContext.startActivity(intent);
    }


    public void longClickAvatar(String userId, String name) {

    }

    public void longClickContent(UMessage message) {

    }

    public void clickFail(UMessage message) {

    }

    public void clickContent(UMessageContent content) {

    }
}
