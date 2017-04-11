package me.devilsen.imlist.message;

import android.support.annotation.NonNull;

/**
 * author : dongSen
 * date : 2017/4/11
 * desc : user info for UI
 */
public class UserInfo {

    private String userId;

    private String name;

    private String avatar;

    public UserInfo(@NonNull String userId, String name, String avatar) {
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (userId != null ? !userId.equals(userInfo.userId) : userInfo.userId != null)
            return false;
        if (name != null ? !name.equals(userInfo.name) : userInfo.name != null) return false;
        return avatar != null ? avatar.equals(userInfo.avatar) : userInfo.avatar == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }
}
