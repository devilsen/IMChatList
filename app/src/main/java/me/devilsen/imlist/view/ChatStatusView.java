package me.devilsen.imlist.view;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.devilsen.imchatlist.R;

/**
 * author : dongSen
 * date : 2017/3/27
 * desc :
 */
public class ChatStatusView extends FrameLayout {

    @IntDef({LOADING, SUCCESS, FAIL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SendStatus {
    }

    public static final int LOADING = 0;
    public static final int SUCCESS = 1;
    public static final int FAIL = 2;

    @BindView(R.id.img_chat_status_retry)
    ImageView fail;
    @BindView(R.id.pb_chat_status_loading)
    ProgressBar loading;

    public ChatStatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_chat_status, this);
        ButterKnife.bind(this);
    }

    public void setSendStatus(@SendStatus int sendStatus) {
        if (sendStatus == LOADING) {
            showLoading();
        } else if (sendStatus == FAIL) {
            showFail();
        } else {
            hide();
        }
    }

    private void showFail() {
        if (!isShown())
            setVisibility(VISIBLE);

        if (!fail.isShown())
            fail.setVisibility(VISIBLE);

        if (loading.isShown())
            loading.setVisibility(GONE);
    }

    private void showLoading() {
        if (!isShown())
            setVisibility(VISIBLE);

        if (!loading.isShown())
            loading.setVisibility(VISIBLE);

        if (fail.isShown()) {
            fail.setVisibility(GONE);
        }
    }

    private void hide() {
        if (getVisibility() == VISIBLE)
            setVisibility(GONE);

        if (loading.isShown())
            loading.setVisibility(GONE);

        if (fail.isShown()) {
            fail.setVisibility(GONE);
        }
    }


}
