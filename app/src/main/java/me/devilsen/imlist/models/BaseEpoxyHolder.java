package me.devilsen.imlist.models;

import android.support.annotation.CallSuper;
import android.view.View;

import com.airbnb.epoxy.EpoxyHolder;

import butterknife.ButterKnife;
import me.devilsen.imlist.view.ChatItemView;

/**
 * Creating a base holder class allows us to leverage ButterKnife's view binding for all subclasses.
 * This makes subclasses much cleaner, and is a highly recommended pattern.
 * <p>
 * holder layout must is {@link ChatItemView} as the parent view
 */
abstract class BaseEpoxyHolder extends EpoxyHolder {

    ChatItemView chatLayout;

    @CallSuper
    @Override
    protected void bindView(View itemView) {
        chatLayout = (ChatItemView) itemView;
        ButterKnife.bind(this, itemView);
    }
}
