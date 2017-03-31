package me.devilsen.imlist.view;

/**
 * author : dongSen
 * date : 2017/3/31
 * desc : click item listener adapter
 */
public abstract class ChatItemAdapterClickListener implements ChatItemClickListener {


    public abstract void onClickAvatar();

    public abstract void onClickContent();

    public abstract void onLongClickContent();

    @Override
    public void onLongClickAvatar() {

    }

    @Override
    public void onClickFail() {

    }
}
