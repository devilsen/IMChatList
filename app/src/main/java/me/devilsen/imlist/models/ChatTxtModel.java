package me.devilsen.imlist.models;

import android.content.Context;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyModelClass;

import butterknife.BindView;
import me.devilsen.imchatlist.R;
import me.devilsen.imlist.message.UMessageTxtContent;

/**
 * author : dongSen
 * date : 2017/3/28
 * desc : 聊天文字item
 */
@EpoxyModelClass(layout = R.layout.chat_holder_txt)
public abstract class ChatTxtModel extends BaseChatModel<ChatTxtModel.TxtHolder, UMessageTxtContent> {

    public ChatTxtModel(Context context) {
        super(context);
    }

    @Override
    public void bindHolder(TxtHolder holder) {
        content = (UMessageTxtContent) message.getMessageContent();

        holder.textView.setText(content.getContent());
    }

    static class TxtHolder extends BaseEpoxyHolder {
        @BindView(R.id.txt_chat_content)
        TextView textView;
    }
}
