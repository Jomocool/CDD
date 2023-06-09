package com.example.cdd.Game.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdd.Game.Domain.Player;
import com.example.cdd.Game.System.GameTurn;
import com.example.cdd.R;

public class GamingInterfaceActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaming_interface);
        //将玩家的牌加入布局中
        addPlayer1AllCards(game_turn.player1);
        addPlayer4AllCards(game_turn.player4);
        addPlayer2AllCards(game_turn.player2);
        addPlayer3AllCards(game_turn.player3);
    }

    //将一张牌水平地添加到线性布局中
    void addToHorizontalLinearLayout(CardImage imageView,LinearLayout layout)
    {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int Dp=0;
        if(layout.getChildCount()!=0)
            Dp=-35;
        lp.setMargins(dpTOpx(Dp), 0, 0, 0);

        imageView.setLayoutParams(lp);
        layout.addView(imageView);
    }

    //将一张牌竖直地添加到线性布局中
    void addToVerticalLinearLayout(CardImage imageView,LinearLayout layout)
    {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int Dp=0;
        if(layout.getChildCount()!=0)
            Dp=-60;
        lp.setMargins(0, dpTOpx(Dp), 0, 0);

        imageView.setLayoutParams(lp);
        layout.addView(imageView);
    }

    //将玩家1的所有牌添加到线性布局中
    void addPlayer1AllCards(Player player)
    {
        LinearLayout layout=findViewById(R.id.playerCardsContainer);
        //放入玩家的所有牌
        for(int i=0;i<player.getArrayList().size();i++)
        {
            //新建一张图片资源，并且记录牌号和花色对应的序号值
            CardImage card_image=new CardImage(this,player.getArrayList().get(i));

            //设置一张牌图片的属性
            card_image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            card_image.setMaxWidth(180);
            card_image.setAdjustViewBounds(true);

            //添加资源图片
            String image_name="card"+player.getArrayList().get(i);
            int resourceId =getResources().getIdentifier(image_name,"mipmap",getPackageName());
            card_image.setImageResource(resourceId);
            addToHorizontalLinearLayout(card_image,layout);

            //设置牌的点击动作
            card_image.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //没选中
                    if(!card_image.get_isSelected())
                    {
                        card_image.select();
                        card_image.offsetTopAndBottom(-20);
                    }
                    //选中
                    else
                    {
                        card_image.cancel_select();
                        card_image.offsetTopAndBottom(20);
                    }
                }
            });

        }
    }

    //将玩家4的牌加入布局中
    void addPlayer4AllCards(Player player4)
    {
        LinearLayout layout=findViewById(R.id.player4CardsContainer);
        //放入玩家的所有牌
        for(int i=0;i<player4.getArrayList().size();i++)
        {
            //新建一张图片资源，并且记录牌号和花色对应的序号值
            CardImage card_image=new CardImage(this,player4.getArrayList().get(i));

            //设置一张牌图片的属性
            card_image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            card_image.setMaxWidth(140);
            card_image.setAdjustViewBounds(true);

            //添加资源图片
            int resourceId =getResources().getIdentifier("poker_back","mipmap",getPackageName());
            card_image.setImageResource(resourceId);
            addToHorizontalLinearLayout(card_image,layout);
        }
    }

    //将玩家3的牌加入布局中
    void addPlayer2AllCards(Player player2)
    {
        LinearLayout layout=findViewById(R.id.player2CardsContainer);
        //放入玩家的所有牌
        for(int i=0;i<player2.getArrayList().size();i++)
        {
            //新建一张图片资源，并且记录牌号和花色对应的序号值
            CardImage card_image=new CardImage(this,player2.getArrayList().get(i));

            //设置一张牌图片的属性
            card_image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            card_image.setMaxHeight(200);
            card_image.setAdjustViewBounds(true);

            //添加资源图片
            int resourceId =getResources().getIdentifier("poker_back","mipmap",getPackageName());
            card_image.setImageResource(resourceId);
            addToVerticalLinearLayout(card_image,layout);
        }
    }

    //将玩家3的牌加入布局中
    void addPlayer3AllCards(Player player3)
    {
        LinearLayout layout=findViewById(R.id.player3CardsContainer);
        //放入玩家的所有牌
        for(int i=0;i<player3.getArrayList().size();i++)
        {
            //新建一张图片资源，并且记录牌号和花色对应的序号值
            CardImage card_image=new CardImage(this,player3.getArrayList().get(i));

            //设置一张牌图片的属性
            card_image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            card_image.setMaxHeight(200);
            card_image.setAdjustViewBounds(true);

            //添加资源图片
            int resourceId =getResources().getIdentifier("poker_back","mipmap",getPackageName());
            card_image.setImageResource(resourceId);
            addToVerticalLinearLayout(card_image,layout);
        }
    }

    //将dp转换成像素大小
    public int dpTOpx(int Dp)
    {
        float density = getResources().getDisplayMetrics().density;
        return (int) (Dp * density);
    }

    //游戏系统
    private GameTurn game_turn=new GameTurn();
}
