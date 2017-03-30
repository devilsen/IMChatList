package me.devilsen.imlist.models;

import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelClass;

import butterknife.BindView;
import me.devilsen.imchatlist.R;

/**
 * author : dongSen
 * date : 2017/3/28
 * desc : 聊天文字item
 */
@EpoxyModelClass(layout = R.layout.chat_holder_txt)
public abstract class ChatTxtModel extends BaseChatModel<ChatTxtModel.TxtHolder> {
    @EpoxyAttribute
    String text;

    @Override
    public void bindHolder(TxtHolder holder) {
        holder.textView.setText(text);
    }

    static class TxtHolder extends BaseEpoxyHolder {
        @BindView(R.id.txt_chat_content)
        TextView textView;
    }
}
