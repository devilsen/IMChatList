package me.devilsen.imlist.models;

import android.support.annotation.CallSuper;
import android.view.View;

import com.airbnb.epoxy.EpoxyHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.devilsen.imchatlist.R;
import me.devilsen.imlist.view.ChatItemView;

/**
 * Creating a base holder class allows us to leverage ButterKnife's view binding for all subclasses.
 * This makes subclasses much cleaner, and is a highly recommended pattern.
 */
abstract class BaseEpoxyHolder extends EpoxyHolder {

    @BindView(R.id.chat_item_content)
    ChatItemView chatLayout;

    @CallSuper
    @Override
    protected void bindView(View itemView) {
        ButterKnife.bind(this, itemView);
    }
}
