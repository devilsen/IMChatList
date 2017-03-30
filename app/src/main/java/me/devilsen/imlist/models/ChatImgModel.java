package me.devilsen.imlist.models;

import android.widget.ImageView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;

import butterknife.BindView;
import me.devilsen.imchatlist.R;

/**
 * author : dongSen
 * date : 2017/3/30
 * desc :
 */
@EpoxyModelClass(layout = R.layout.chat_holder_img)
public abstract class ChatImgModel extends BaseChatModel<ChatImgModel.ImgHolder> {

    @EpoxyAttribute
    String path;

    @Override
    public void bindHolder(ImgHolder holder) {
//        holder.imageView.
    }

    static class ImgHolder extends BaseEpoxyHolder {
        @BindView(R.id.img_chat_content)
        ImageView imageView;
    }
}
