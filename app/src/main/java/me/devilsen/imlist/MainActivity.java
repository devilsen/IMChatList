package me.devilsen.imlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.devilsen.imchatlist.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mChatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ChatAdapter chatAdapter = new ChatAdapter(this);
//        TestAdapter chatAdapter = new TestAdapter();

        mChatList.setAdapter(chatAdapter);
        mChatList.setLayoutManager(new LinearLayoutManager(this));

//        for (int i = 0; i < 100; i++) {
//            chatAdapter.addData("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD1234567890" + i);
//        }
//        chatAdapter.notifyDataSetChanged();

//        mChatList.getRecycledViewPool().setMaxRecycledViews(R.layout.model_color, 50);

    }
}
