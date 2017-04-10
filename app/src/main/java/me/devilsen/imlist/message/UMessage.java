package me.devilsen.imlist.message;

/**
 * author : dongSen
 * date : 2017/4/10
 * desc :
 */
public class UMessage {

    private String messageId;

    private UMessageContent messageContent;

    public UMessage(UMessageContent messageContent) {
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


    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (messageId != null ? messageId.hashCode() : 0);
        result = 31 * result + (messageContent != null ? messageContent.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UMessage)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UMessage that = (UMessage) o;
        if (messageId != null ? !messageId.equals(that.messageId) : that.messageId != null) {
            return false;
        }

        if (messageContent != null ? !messageContent.equals(that.messageContent) : that.messageContent != null) {
            return false;
        }
        return true;
    }

}
