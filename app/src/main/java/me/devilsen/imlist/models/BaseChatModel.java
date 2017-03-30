package me.devilsen.imlist.models;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;

import me.devilsen.imlist.view.ChatItemView;
import me.devilsen.imlist.view.ChatStatusView;


/**
 * author : dongSen
 * date : 2017/3/30
 * desc :
 */
abstract class BaseChatModel<T extends BaseEpoxyHolder> extends EpoxyModelWithHolder<T> {

    @EpoxyAttribute
    @ChatItemView.Direction
    int direction;

    @EpoxyAttribute
    @ChatStatusView.SendStatus
    int status;

    @Override
    public void bind(T holder) {
        holder.chatLayout.setDirection(direction);
        holder.chatLayout.setStatus(status);

        bindHolder(holder);
    }

    abstract void bindHolder(T holder);
}
