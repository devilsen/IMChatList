package me.devilsen.imlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.devilsen.imchatlist.R;

/**
 * author : dongSen
 * date : 2017/4/10
 * desc :
 */
public class AvatarActivity extends AppCompatActivity {

    @BindView(R.id.txt_avatar_id)
    TextView userIdTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        String userId = intent.getStringExtra("userId");

        userIdTxt.setText(userId);
    }
}
