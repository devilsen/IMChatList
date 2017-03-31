package me.devilsen.imlist;

import android.widget.ImageView;

/**
 * author : dongSen
 * date : 2017/3/31
 * desc :
 */
public class ImageLoader {

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static final ImageLoader instance = new ImageLoader();
    }


    public void loadAvatar(ImageView imageView, String avatarPath) {

    }

}
