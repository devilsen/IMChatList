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
abstract class BaseChatModel<T extends BaseEpoxyHolder> extends EpoxyModelWithHolder<T> {

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

    @EpoxyAttribute(hash = false)
    ChatItemClickListener itemClickListener;

    @Override
    public void bind(T holder) {
        holder.chatLayout.setDirection(direction);
        holder.chatLayout.setTime(time);
        holder.chatLayout.setName(name);
        holder.chatLayout.setAvatar(avatar);
        holder.chatLayout.setStatus(status);
        holder.chatLayout.setOnChatItemClickListener(itemClickListener);

        bindHolder(holder);
    }

    abstract void bindHolder(T holder);
}
