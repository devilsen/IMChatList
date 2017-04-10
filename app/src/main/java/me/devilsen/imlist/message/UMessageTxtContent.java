package me.devilsen.imlist.message;

/**
 * author : dongSen
 * date : 2017/4/10
 * desc :
 */
public class UMessageTxtContent extends UMessageContent {

    private String content;

    public UMessageTxtContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UMessageTxtContent)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UMessageTxtContent that = (UMessageTxtContent) o;
        if (content != null ? !content.equals(that.content) : that.content != null) {
            return false;
        }
        return true;
    }
}
