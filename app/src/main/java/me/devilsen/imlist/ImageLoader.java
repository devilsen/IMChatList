package me.devilsen.imlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import me.devilsen.imlist.message.UMessageImageContent;

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


    public void loadAvatar(@NonNull Context context,
                           @NonNull ImageView imageView,
                           @NonNull String avatarPath) {
        Glide.with(context)
                .load(avatarPath)
                .fitCenter()
                .into(imageView);
    }

    public void loadChatImg(@NonNull Context context,
                            @NonNull ImageView imageView,
                            @NonNull UMessageImageContent imageContent) {
        Glide.with(context)
                .load(imageContent.getPath())
                .centerCrop()
                .override(400, 400)
                .into(imageView);

    }

}
