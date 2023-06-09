package com.example.cdd.Game.UI;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdd.Game.Domain.Player;
import com.example.cdd.Game.System.GameTurn;
import com.example.cdd.R;

public class GamingInterfaceActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaming_interface);
        addCard(game_turn.player1);
    }

    //将牌添加到线性布局中
    void addToLinearLayout(ImageView imageView,LinearLayout layout)
    {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int Dp=0;
        if(layout.getChildCount()!=0)
        {
            Dp=-35;
        }
        lp.setMargins(dpTOpx(Dp), 0, 0, 0);

        imageView.setLayoutParams(lp);
        layout.addView(imageView);
    }

    //将玩家的所有牌添加到线性布局中
    void addCard(Player player)
    {
        LinearLayout layout=findViewById(R.id.playerCardsContainer);
        //放入玩家的所有牌
        for(int i=0;i<player.getArrayList().size();i++)
        {
            ImageView imageView=new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setMaxWidth(180);
            imageView.setAdjustViewBounds(true);
            imageView.setImageResource(R.mipmap.club10);
            addToLinearLayout(imageView,layout);
        }
    }

    public int dpTOpx(int Dp)
    {
        float density = getResources().getDisplayMetrics().density;
        return (int) (Dp * density);
    }

    //游戏系统
    private GameTurn game_turn=new GameTurn();
}
