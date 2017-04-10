package me.devilsen.imlist.message;

/**
 * author : dongSen
 * date : 2017/4/10
 * desc :
 */
public class UMessageImageContent extends UMessageContent {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? "" : path;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UMessageImageContent)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UMessageImageContent that = (UMessageImageContent) o;
        if (path != null ? !path.equals(that.path) : that.path != null) {
            return false;
        }
        return true;
    }
}
