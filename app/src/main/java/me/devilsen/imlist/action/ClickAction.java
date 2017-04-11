package me.devilsen.imlist.action;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import me.devilsen.imlist.AvatarActivity;
import me.devilsen.imlist.message.UMessage;

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
        Toast.makeText(mContext, userId + name, Toast.LENGTH_SHORT).show();
    }

    public void longClickContent(UMessage message) {
        Toast.makeText(mContext, message.getMessageId() + "  ", Toast.LENGTH_SHORT).show();
    }

    public void clickFail(UMessage message) {
        Toast.makeText(mContext, message.getMessageId() + "重发", Toast.LENGTH_SHORT).show();
    }

}
