package me.devilsen.imlist.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import me.devilsen.imchatlist.R;
import me.devilsen.imlist.ImageLoader;

/**
 * author : dongSen
 * dae : 2017/3/27
 * desc :
 */
public class ChatItemView extends ViewGroup {

    @IntDef({LEFT, RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
    }

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    private static final float WIDTH_SCALE = 0.8f;
    //the scale of the name if name is gone
    private static final float AVATAR_SCALE = 0.3F;

    @BindView(R.id.txt_chat_time)
    TextView time;

    @BindView(R.id.img_chat_avatar)
    ImageView avatar;

    @BindView(R.id.txt_chat_left_name)
    TextView name;

    @BindView(R.id.img_chat_content)
    ImageView contentBackground;

    @BindView(R.id.view_status)
    ChatStatusView status;

    View content;

    /**
     * item的方向，默认为左
     */
    @Direction
    int mDirection = LEFT;

    /**
     * 发送状态
     */
    @ChatStatusView.SendStatus
    private int mSendStatus;

    private ChatItemClickListener mItemClickListener;

    @OnClick(R.id.img_chat_avatar)
    void onClickAvatar() {
        if (mItemClickListener != null)
            mItemClickListener.onClickAvatar();
    }

    @OnLongClick(R.id.img_chat_avatar)
    boolean onLongClickAvatar() {
        if (mItemClickListener != null)
            mItemClickListener.onLongClickAvatar();
        return true;
    }

    @OnClick(R.id.img_chat_content)
    void onClickContent() {
        if (mItemClickListener != null)
            mItemClickListener.onClickContent();
    }

    @OnLongClick(R.id.img_chat_content)
    boolean onLongClickContent() {
        if (mItemClickListener != null)
            mItemClickListener.onLongClickContent();
        return true;
    }

    @OnClick(R.id.view_status)
    void onClickStatus() {
        if (mItemClickListener != null && mDirection == RIGHT && mSendStatus == ChatStatusView.FAIL)
            mItemClickListener.onClickFail();
    }

    public ChatItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_chat_holder_base, this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();

        if (childCount < 6) {
            throw new RuntimeException("there must have a child view");
        }

        if (childCount > 6) {
            throw new RuntimeException("there only have one child view");
        }

        content = getChildAt(5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthConstraints = getPaddingLeft() + getPaddingRight();
        int heightConstraints = getPaddingTop() + getPaddingBottom();

        int height = 0;
        int width = 0;

        // 1. Measure the time
        measureChildWithMargins(
                time,
                widthMeasureSpec,
                widthConstraints,
                heightMeasureSpec,
                heightConstraints);

        // 2. Update the constraints
        if (time.getVisibility() != GONE) {
            heightConstraints += time.getMeasuredHeight();
            height = Math.max(time.getMeasuredHeight(), height);
        }

        // 3. Measure the ProfilePhoto
        measureChildWithMargins(
                avatar,
                widthMeasureSpec,
                widthConstraints,
                heightMeasureSpec,
                heightConstraints);

        // 4. Update the constraints.
        widthConstraints += avatar.getMeasuredWidth();
        width += avatar.getMeasuredWidth();
        height = Math.max(
                getChildHeightWithMargins(avatar) + getChildHeightWithMargins(time),
                height);

        // 5. Measure the Menu.
        measureChildWithMargins(
                status,
                widthMeasureSpec,
                widthConstraints,
                heightMeasureSpec,
                heightConstraints);

        // 6. Update the constraints.
        widthConstraints += status.getMeasuredWidth();
        width += status.getMeasuredWidth();
        height = Math.max(status.getMeasuredHeight(), height);

        // 7. Prepare the vertical MeasureSpec.
        int verticalWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                (int) (MeasureSpec.getSize(widthMeasureSpec) * WIDTH_SCALE - widthConstraints),
                MeasureSpec.getMode(widthMeasureSpec));

        int verticalHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(heightMeasureSpec) - heightConstraints,
                MeasureSpec.getMode(heightMeasureSpec));

        // 8. Measure the Title.
        measureChildWithMargins(
                name,
                verticalWidthMeasureSpec,
                0,
                verticalHeightMeasureSpec,
                0);

        // 9. Measure the content
        measureChildWithMargins(
                content,
                verticalWidthMeasureSpec,
                0,
                verticalHeightMeasureSpec,
                name.getMeasuredHeight());

        // 10. Update the verticalWidthMeasureSpec
        verticalWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                content.getMeasuredWidth(),
                MeasureSpec.getMode(widthMeasureSpec));

        // 11. Measure contentBackground
        measureChildWithMargins(
                contentBackground,
                verticalWidthMeasureSpec,
                0,
                verticalHeightMeasureSpec,
                name.getMeasuredHeight());

        // 12. Update the sizes.
        MarginLayoutParams contentBackgroundParams = (MarginLayoutParams) contentBackground.getLayoutParams();

        width += Math.max(avatar.getMeasuredWidth(), content.getMeasuredWidth());
        height = Math.max(getChildHeightWithMargins(time) +
                getChildHeightWithMargins(name) +
                getChildHeightWithMargins(content) +
                contentBackgroundParams.topMargin + contentBackgroundParams.bottomMargin, height);

        // 13. Set the dimension for this ViewGroup.
        setMeasuredDimension(
                resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));


    }


    @Override
    protected void measureChildWithMargins(
            View child,
            int parentWidthMeasureSpec,
            int widthUsed,
            int parentHeightMeasureSpec,
            int heightUsed) {
        if (child.getVisibility() == GONE)
            return;

        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        int childWidthMeasureSpec = getChildMeasureSpec(
                parentWidthMeasureSpec,
                widthUsed + lp.leftMargin + lp.rightMargin,
                lp.width);

        int childHeightMeasureSpec = getChildMeasureSpec(
                parentHeightMeasureSpec,
                heightUsed + lp.topMargin + lp.bottomMargin,
                lp.height);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    /**
     * 重写onLayout方法，请勿改动里面的内容，如有修改，请直接改动xml里面的数值
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (LEFT == mDirection) {
            onLeftLayout(r);
        } else {
            onRightLayout(r);
        }
    }

    /**
     * layout the view if direction is LEFT
     *
     * @param totalWidth total width
     */
    private void onLeftLayout(int totalWidth) {

        int widthConstraints = getPaddingLeft() + getPaddingRight();
        int heightConstraints = getPaddingTop() + getPaddingBottom();

        // 1. Layout time
        if (time.getVisibility() != GONE) {
            MarginLayoutParams timeParams = (MarginLayoutParams) time.getLayoutParams();
            time.layout(
                    (totalWidth - time.getMeasuredWidth()) / 2,
                    timeParams.topMargin,
                    (totalWidth + time.getMeasuredWidth()) / 2,
                    timeParams.topMargin + time.getMeasuredHeight());

            // 2. Update the height heightConstraints if time is visible
            heightConstraints += getChildHeightWithMargins(time);
        }

        // 3. Layout the avatar
        layoutChildWithMargins(
                LEFT,
                avatar,
                widthConstraints,
                heightConstraints
        );

        // 4. Update the widthConstraints
        widthConstraints += getChildWidthWithMargins(avatar);

        // 5. Layout the name
        layoutChildWithMargins(
                LEFT,
                name,
                widthConstraints,
                heightConstraints
        );

        // 6. Update the height Constraints
        if (name.getVisibility() == GONE) {//avoid content too high
            heightConstraints += getChildHeightWithMargins(avatar) * AVATAR_SCALE;
        } else {
            heightConstraints += getChildHeightWithMargins(name);
        }

        // 7. Get status margin
        MarginLayoutParams statusParams = (MarginLayoutParams) status.getLayoutParams();
        MarginLayoutParams contentParams = (MarginLayoutParams) content.getLayoutParams();
        MarginLayoutParams contentBackgroundParams = (MarginLayoutParams) contentBackground.getLayoutParams();

        int statusWidthMargin = widthConstraints + contentParams.rightMargin + contentBackgroundParams.rightMargin +
                content.getMeasuredWidth() + statusParams.leftMargin;

        int statusHeightMargin = heightConstraints + contentParams.topMargin + contentBackgroundParams.topMargin;

        // 8. Update the width constraints
        widthConstraints += contentParams.leftMargin + contentBackgroundParams.leftMargin;

        // 9. Layout the status
        status.layout(statusWidthMargin,
                statusHeightMargin + (content.getMeasuredHeight() - status.getMeasuredHeight()) / 2,
                statusWidthMargin + status.getMeasuredWidth(),
                statusHeightMargin + (content.getMeasuredHeight() + status.getMeasuredHeight()) / 2);

        // 10. Layout the content
        int heightMargin = contentParams.topMargin + contentBackgroundParams.topMargin;

        content.layout(widthConstraints,
                heightConstraints + heightMargin,
                widthConstraints + content.getMeasuredWidth(),
                heightConstraints + content.getMeasuredHeight() + heightMargin);

        // 9. Layout the background
        contentBackground.layout(widthConstraints,
                heightConstraints + heightMargin,
                widthConstraints + content.getMeasuredWidth(),
                heightConstraints + content.getMeasuredHeight() + heightMargin);


    }

    /**
     * layout the view if direction is RIGHT
     *
     * @param totalWidth total width
     */
    private void onRightLayout(int totalWidth) {
        int widthConstraints = totalWidth - getPaddingLeft() + getPaddingRight();
        int heightConstraints = getPaddingTop() + getPaddingBottom();

        // 1. Layout time
        if (time.getVisibility() != GONE) {
            MarginLayoutParams timeParams = (MarginLayoutParams) time.getLayoutParams();
            time.layout(
                    (totalWidth - time.getMeasuredWidth()) / 2,
                    timeParams.topMargin,
                    (totalWidth + time.getMeasuredWidth()) / 2,
                    timeParams.topMargin + time.getMeasuredHeight());

            // 2. Update the height heightConstraints if time is visible
            heightConstraints += getChildHeightWithMargins(time);
        }

        // 3. Layout the avatar
        layoutChildWithMargins(
                RIGHT,
                avatar,
                widthConstraints,
                heightConstraints
        );

        // 4. Update the widthConstraints
        widthConstraints -= getChildWidthWithMargins(avatar);

        // 5. Layout the name
        layoutChildWithMargins(
                RIGHT,
                name,
                widthConstraints,
                heightConstraints
        );

        // 6. Update the height Constraints
        if (name.getVisibility() == GONE) {//avoid content too high
            heightConstraints += getChildHeightWithMargins(avatar) * AVATAR_SCALE;
        } else {
            heightConstraints += getChildHeightWithMargins(name);
        }

        // 7. Get status margin
        MarginLayoutParams statusParams = (MarginLayoutParams) status.getLayoutParams();
        MarginLayoutParams contentParams = (MarginLayoutParams) content.getLayoutParams();
        MarginLayoutParams contentBackgroundParams = (MarginLayoutParams) contentBackground.getLayoutParams();

        int statusWidthMargin = widthConstraints - contentParams.rightMargin - contentBackgroundParams.rightMargin -
                content.getMeasuredWidth() - statusParams.leftMargin;

        int statusHeightMargin = heightConstraints + contentParams.topMargin + contentBackgroundParams.topMargin;

        // 8. Update the width constraints
        widthConstraints -= contentParams.leftMargin + contentBackgroundParams.leftMargin;

        // 9. Layout the status
        status.layout(statusWidthMargin - status.getMeasuredWidth(),
                statusHeightMargin + (content.getMeasuredHeight() - status.getMeasuredHeight()) / 2,
                statusWidthMargin,
                statusHeightMargin + (content.getMeasuredHeight() + status.getMeasuredHeight()) / 2);

        // 10. Layout the content
        int heightMargin = contentParams.topMargin + contentBackgroundParams.topMargin;

        content.layout(widthConstraints - content.getMeasuredWidth(),
                heightConstraints + heightMargin,
                widthConstraints,
                heightConstraints + content.getMeasuredHeight() + heightMargin);

        // 9. Layout the background
        contentBackground.layout(widthConstraints - content.getMeasuredWidth(),
                heightConstraints + heightMargin,
                widthConstraints,
                heightConstraints + content.getMeasuredHeight() + heightMargin);


    }

    /**
     * layout the child view
     *
     * @param direction         in IM there will have two direction view, left and right
     * @param child             childView
     * @param widthConstraints  the constraints of the width
     * @param heightConstraints the constraints of the height
     */
    private void layoutChildWithMargins(
            @Direction int direction,
            View child,
            int widthConstraints,
            int heightConstraints) {

        if (child.getVisibility() == GONE)
            return;

        MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();

        if (direction == LEFT) {
            layoutChildLeft(child, params, widthConstraints, heightConstraints);
        } else {
            layoutChildRight(child, params, widthConstraints, heightConstraints);
        }
    }

    private void layoutChildLeft(View child,
                                 MarginLayoutParams params,
                                 int widthConstraints,
                                 int heightConstraints) {

        int left = widthConstraints + params.leftMargin;

        int top = heightConstraints + params.topMargin;

        int right = left + child.getMeasuredWidth();

        int bottom = top + child.getMeasuredHeight();

        child.layout(left, top, right, bottom);
    }

    private void layoutChildRight(View child,
                                  MarginLayoutParams params,
                                  int widthConstraints,
                                  int heightConstraints) {
        widthConstraints -= params.leftMargin;

        int left = widthConstraints - child.getMeasuredWidth();

        int top = heightConstraints + params.topMargin;

        int right = widthConstraints;

        int bottom = top + child.getMeasuredHeight();

        child.layout(left, top, right, bottom);
    }

    /**
     * get the width with margins
     *
     * @return total width
     */
    private int getChildWidthWithMargins(View child) {
        if (child.getVisibility() == GONE)
            return 0;

        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        return child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
    }

    /**
     * get the height with margins
     *
     * @return total height
     */
    private int getChildHeightWithMargins(View child) {
        if (child.getVisibility() == GONE)
            return 0;

        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

        return child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    /**
     * set chat item background
     *
     * @param background the res of the background
     */
    public void setContentBackground(@DrawableRes int background) {
        contentBackground.setImageResource(background);
    }

    /**
     * set the name of chat
     *
     * @param nameString if name is null, hide the view.
     *                   if direction is right, hide the view.
     */
    public void setName(@Nullable String nameString) {
        if (nameString == null) {
            if (name.getVisibility() == VISIBLE)
                name.setVisibility(GONE);

        } else if (mDirection == LEFT) {
            if (!name.isShown())
                name.setVisibility(VISIBLE);

            name.setText(nameString);
        }
    }

    /**
     * set avatar
     *
     * @param avatarPath avatar path
     */
    public void setAvatar(@NonNull String avatarPath) {
        ImageLoader.getInstance().loadAvatar(avatar, avatarPath);
    }

    /**
     * set send status
     *
     * @param sendStatus send status
     */
    public void setStatus(@ChatStatusView.SendStatus int sendStatus) {
        mSendStatus = sendStatus;
        status.setSendStatus(sendStatus);
    }

    /**
     * set time
     *
     * @param timeString if timeString is null, hide the view
     */
    public void setTime(@Nullable String timeString) {
        if (timeString == null) {
            if (time.getVisibility() == VISIBLE)
                setTimeVisibility(GONE);
        } else {
            if (!time.isShown())
                setTimeVisibility(VISIBLE);

            time.setText(timeString);
        }
    }

    /**
     * 设置时间是否可见
     */
    public void setTimeVisibility(int visibility) {
        time.setVisibility(visibility);
    }

    /**
     * 设置item的方向
     *
     * @param direction 0为左，1为右
     */
    public void setDirection(@Direction int direction) {
        this.mDirection = direction;
        if (direction == LEFT) {
            setContentBackground(R.drawable.bg_chat_left);
        } else {
            name.setVisibility(GONE);
            setContentBackground(R.drawable.bg_chat_right);
        }
    }

    /**
     * set item click listener
     *
     * @param itemClickListener click listener
     */
    public void setOnChatItemClickListener(@NonNull ChatItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

}
