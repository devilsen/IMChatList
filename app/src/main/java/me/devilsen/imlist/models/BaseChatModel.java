package me.devilsen.imlist.models;

import android.content.Context;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;

import me.devilsen.imlist.ClickAction;
import me.devilsen.imlist.message.UMessage;
import me.devilsen.imlist.message.UMessageContent;
import me.devilsen.imlist.view.ChatItemClickListener;
import me.devilsen.imlist.view.ChatItemView;
import me.devilsen.imlist.view.ChatStatusView;

/**
 * author : dongSen
 * date : 2017/3/30
 * desc : base model
 */
abstract class BaseChatModel<T extends BaseEpoxyHolder, C extends UMessageContent> extends EpoxyModelWithHolder<T>
        implements ChatItemClickListener {

    @EpoxyAttribute
    @ChatItemView.Direction
    int direction;

    @EpoxyAttribute
    @ChatStatusView.SendStatus
    int status;

    @EpoxyAttribute
    String userId;

    @EpoxyAttribute
    String time;

    @EpoxyAttribute
    String name;

    @EpoxyAttribute
    String avatar;

    @EpoxyAttribute
    UMessage message;

    protected Context context;

    protected C content;

    private ClickAction action;

    public BaseChatModel(Context context) {
        this.context = context;
        action = new ClickAction(context);
    }

    abstract void bindHolder(T holder);

    @Override
    public void bind(T holder) {
        holder.chatLayout.setDirection(direction);
        holder.chatLayout.setTime(time);
        holder.chatLayout.setName(name);
        holder.chatLayout.setAvatar(avatar);
        holder.chatLayout.setStatus(status);
        holder.chatLayout.setOnChatItemClickListener(this);

        bindHolder(holder);
    }

    @Override
    public void onClickAvatar() {
        action.clickAvatar(userId);
    }

    @Override
    public void onLongClickAvatar() {
        action.longClickAvatar(userId, name);
    }

    @Override
    public void onClickContent() {
        action.clickContent(message.getMessageContent());
    }

    @Override
    public void onLongClickContent() {
        action.longClickContent(message);
    }

    @Override
    public void onClickFail() {
        action.clickFail(message);
    }
}
