package me.devilsen.imlist.models;

import android.content.Context;
import android.widget.ImageView;

import com.airbnb.epoxy.EpoxyModelClass;

import butterknife.BindView;
import me.devilsen.imchatlist.R;
import me.devilsen.imlist.ImageLoader;
import me.devilsen.imlist.message.UMessageImageContent;

/**
 * author : dongSen
 * date : 2017/3/30
 * desc :
 */
@EpoxyModelClass(layout = R.layout.chat_holder_img)
public abstract class ChatImgModel extends BaseChatModel<ChatImgModel.ImgHolder, UMessageImageContent> {

    public ChatImgModel(Context context) {
        super(context);
    }

    @Override
    public void bindHolder(ImgHolder holder) {
        content = (UMessageImageContent) message.getMessageContent();

        ImageLoader.getInstance().loadChatImg(context, holder.imageView, content);
    }

    static class ImgHolder extends BaseEpoxyHolder {
        @BindView(R.id.img_chat_content)
        ImageView imageView;
    }
}
