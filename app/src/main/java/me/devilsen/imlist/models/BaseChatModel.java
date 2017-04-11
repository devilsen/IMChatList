package me.devilsen.imlist.models;

import android.content.Context;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;

import me.devilsen.imlist.action.ClickAction;
import me.devilsen.imlist.action.ClickActionContent;
import me.devilsen.imlist.message.UMessage;
import me.devilsen.imlist.message.UMessageContent;
import me.devilsen.imlist.message.UserInfo;
import me.devilsen.imlist.view.ChatItemClickListener;
import me.devilsen.imlist.view.ChatItemView;
import me.devilsen.imlist.view.ChatStatusView;

/**
 * author : dongSen
 * date : 2017/3/30
 * desc : base model
 */
abstract class BaseChatModel<T extends BaseEpoxyHolder, C extends UMessageContent> extends EpoxyModelWithHolder<T>
        implements ChatItemClickListener, ClickContentListener {

    @EpoxyAttribute
    @ChatItemView.Direction
    int direction;

    @EpoxyAttribute
    @ChatStatusView.SendStatus
    int status = ChatStatusView.SUCCESS;

    @EpoxyAttribute
    String userId;

    @EpoxyAttribute
    String time;

    @EpoxyAttribute
    String name;

    @EpoxyAttribute
    String avatar;

    @EpoxyAttribute
    UserInfo userInfo;

    @EpoxyAttribute
    UMessage message;

    protected Context context;

    protected C content;

    private ClickAction action;

    ClickActionContent clickActionContent;

    public BaseChatModel(Context context) {
        this.context = context;
        action = new ClickAction(context);
        clickActionContent = new ClickActionContent(context);
    }

    abstract void bindHolder(T holder);

    @Override
    public void bind(T holder) {
        setUserInfo();

        setMessageInfo();

        setSingleInfo(holder);

        holder.chatLayout.setOnChatItemClickListener(this);

        bindHolder(holder);
    }

    /**
     * set user info
     */
    private void setUserInfo() {
        if (userInfo != null) {
            if (userInfo.getUserId() != null)
                userId = userInfo.getUserId();

            if (userInfo.getName() != null)
                name = userInfo.getName();

            if (userInfo.getAvatar() != null)
                avatar = userInfo.getAvatar();
        }
    }

    /**
     * set message info
     */
    private void setMessageInfo() {
        if (message != null) {
            if (message.getTime() != null)
                time = message.getTime();

            if (message.getDirection() != ChatItemView.LEFT)
                direction = message.getDirection();

            if (message.getSendStatus() != ChatStatusView.LOADING)
                status = message.getSendStatus();
        }
    }

    /**
     * if set single info, set it
     */
    private void setSingleInfo(T holder) {
        holder.chatLayout.setName(name);
        holder.chatLayout.setAvatar(avatar);
        holder.chatLayout.setTime(time);
        holder.chatLayout.setDirection(direction);
        holder.chatLayout.setStatus(status);
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
    public void onClickContentView() {
        onClickContent();
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
