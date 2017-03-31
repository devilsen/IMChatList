package me.devilsen.imlist.models;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;

import me.devilsen.imlist.view.ChatItemClickListener;
import me.devilsen.imlist.view.ChatItemView;
import me.devilsen.imlist.view.ChatStatusView;


/**
 * author : dongSen
 * date : 2017/3/30
 * desc : base model
 */
abstract class BaseChatModel<T extends BaseEpoxyHolder> extends EpoxyModelWithHolder<T>
        implements ChatItemClickListener {

    @EpoxyAttribute
    @ChatItemView.Direction
    int direction;

    @EpoxyAttribute
    @ChatStatusView.SendStatus
    int status;

    @EpoxyAttribute
    String time;

    @EpoxyAttribute
    String name;

    @EpoxyAttribute
    String avatar;

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
    }

    @Override
    public void onLongClickAvatar() {

    }

    @Override
    public void onClickContent() {

    }

    @Override
    public void onLongClickContent() {

    }

    @Override
    public void onClickFail() {

    }
}
