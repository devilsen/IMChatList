package me.devilsen.imlist;

import android.util.Log;

import com.airbnb.epoxy.EpoxyAdapter;

import me.devilsen.imlist.models.ChatImgModel_;
import me.devilsen.imlist.models.ChatTxtModel_;
import me.devilsen.imlist.view.ChatItemView;
import me.devilsen.imlist.view.ChatStatusView;

/**
 * author : dongSen
 * date : 2017/3/28
 * desc :
 */
public class ChatAdapter extends EpoxyAdapter {

    private ChatTxtModel_ chatTxtModel = new ChatTxtModel_();

    ChatAdapter() {
        enableDiffing();

        long time = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0)
                addModels(new ChatTxtModel_()
                        .text("aaaa" + i)
                        .direction(ChatItemView.RIGHT)
                        .status(ChatStatusView.LOADING));
            else
                addModels(new ChatTxtModel_()
                        .text("bbbb" + i)
                        .direction(ChatItemView.LEFT)
                        .status(ChatStatusView.SUCCESS));
        }

        for (int i = 0; i < 10000; i++) {
            if (i % 2 == 0)
                addModels(new ChatImgModel_()
                        .direction(ChatItemView.RIGHT)
                        .status(ChatStatusView.LOADING));
            else
                addModels(new ChatImgModel_()
                        .direction(ChatItemView.LEFT)
                        .status(ChatStatusView.SUCCESS));
        }


        Log.e("time", System.currentTimeMillis() - time + "  ");

//        addModels(list);
    }

    public void setData() {
        notifyModelsChanged();
    }


}
