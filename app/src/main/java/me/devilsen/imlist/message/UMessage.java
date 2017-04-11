package me.devilsen.imlist.message;

import android.support.annotation.NonNull;

import me.devilsen.imlist.view.ChatItemView;
import me.devilsen.imlist.view.ChatStatusView;

/**
 * author : dongSen
 * date : 2017/4/10
 * desc : message for UI
 */
public class UMessage {

    private String messageId;

    private String time;

    @ChatItemView.Direction
    private int direction;

    @ChatStatusView.SendStatus
    private int sendStatus;

    private UserInfo userInfo;

    private UMessageContent messageContent;


    public UMessage(@NonNull UMessageContent messageContent) {
        this.messageContent = messageContent;
    }

    public UMessage(@NonNull String messageId, String time, UMessageContent messageContent) {
        this.messageId = messageId;
        this.time = time;
        this.messageContent = messageContent;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public UMessageContent getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(UMessageContent messageContent) {
        this.messageContent = messageContent;
    }

    @ChatStatusView.SendStatus
    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(@ChatStatusView.SendStatus int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @ChatItemView.Direction
    public int getDirection() {
        return direction;
    }

    public void setDirection(@ChatItemView.Direction int direction) {
        this.direction = direction;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UMessage uMessage = (UMessage) o;

        if (direction != uMessage.direction) return false;
        if (sendStatus != uMessage.sendStatus) return false;
        if (messageId != null ? !messageId.equals(uMessage.messageId) : uMessage.messageId != null)
            return false;
        if (time != null ? !time.equals(uMessage.time) : uMessage.time != null) return false;
        if (userInfo != null ? !userInfo.equals(uMessage.userInfo) : uMessage.userInfo != null)
            return false;
        return messageContent != null ? messageContent.equals(uMessage.messageContent) : uMessage.messageContent == null;

    }

    @Override
    public int hashCode() {
        int result = messageId != null ? messageId.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + direction;
        result = 31 * result + sendStatus;
        result = 31 * result + (userInfo != null ? userInfo.hashCode() : 0);
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        return result;
    }
}
