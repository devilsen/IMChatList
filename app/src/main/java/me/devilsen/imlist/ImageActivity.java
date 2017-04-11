package me.devilsen.imlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.devilsen.imchatlist.R;

/**
 * author : dongSen
 * date : 2017/4/11
 * desc :
 */
public class ImageActivity extends AppCompatActivity {

    @BindView(R.id.img_activity_preview)
    ImageView previewImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ButterKnife.bind(this);

        String image = getIntent().getStringExtra("image");

        ImageLoader.getInstance().loadChatImg(this, previewImg, image);
    }
}
