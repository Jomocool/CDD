package com.example.cdd.Game.UI;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdd.Game.Domain.CardGroup;
import com.example.cdd.Game.Domain.Cards;
import com.example.cdd.Game.Domain.Player;
import com.example.cdd.Game.Rule.CDDGameRule;
import com.example.cdd.Game.System.GameTurn;
import com.example.cdd.GameOver.UI.Victory;
import com.example.cdd.R;

import java.util.ArrayList;

public class GamingInterfaceActivity extends AppCompatActivity {
    //游戏系统
    private GameTurn game_turn=new GameTurn();

    //记录出的牌
    private ArrayList<CardImage> selectedCardImage = new ArrayList<>();

    private ArrayList<CardImage> justPlayedCards=new ArrayList<>();


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

            //加入水平布局中
            addToHorizontalLinearLayout(card_image,layout);

            //设置牌的点击动作
            card_image.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //选中
                    if(!card_image.get_isSelected())
                    {
                        card_image.select();
                        card_image.offsetTopAndBottom(-20);
                        selectedCardImage.add(card_image);
                        game_turn.player1.getSelectedCardsArrayList().add(card_image.getSerial_number());
                    }
                    //取消选中
                    else
                    {
                        card_image.cancel_select();
                        card_image.offsetTopAndBottom(20);
                        selectedCardImage.add(card_image);
                        game_turn.player1.getSelectedCardsArrayList().remove((Integer) card_image.getSerial_number());
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

            //加入水平布局中
            addToHorizontalLinearLayout(card_image,layout);
        }
    }

    //将玩家2的牌加入布局中
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

    //点击出牌按钮
    public void click_play_cards(View view) {
        CardGroup cardGroup=new CardGroup(game_turn.player1.getSelectedCardsArrayList());
        boolean result=CDDGameRule.judge(cardGroup,new CardGroup(game_turn.getLastPlayerCardsArrayList()));
        //所选的牌不符合规则，重新选择
        if(false)//result==false
        {
            Toast.makeText(this, "所选牌不符合规则", Toast.LENGTH_LONG).show();
        }
        //所选的牌符合规则，出牌权跳到下一玩家
        else
        {
            LinearLayout linearLayout=findViewById(R.id.playerCardsContainer);
            LinearLayout justPlayedCardLayout=findViewById(R.id.JustPlayCardsContainer);

            //①先清空中间显示的牌
            for(int i=0;i<justPlayedCards.size();i++)
            {
                CardImage image=justPlayedCards.get(i);
                justPlayedCardLayout.removeView(image);
            }
            justPlayedCards.clear();

            //②清空数组并将玩家的出牌赋值给LastPlayerCardsArrayList
            game_turn.getLastPlayerCardsArrayList().clear();
            game_turn.getLastPlayerCardsArrayList().addAll(game_turn.player1.getSelectedCardsArrayList());


            //③将玩家所出的牌移除出serial_number数组
            for(int i=0;i<game_turn.player1.getSelectedCardsArrayList().size();i++)
            {
                Integer integer=game_turn.player1.getSelectedCardsArrayList().get(i);
                game_turn.player1.getArrayList().remove(integer);
            }

            //当玩家的牌数量等于0时，游戏结束
            if(game_turn.player1.getArrayList().size()==0)
            {
                startActivity(new Intent(this,Victory.class));
                return;
            }

            //清空玩家的selected_Cards数组
            game_turn.player1.getSelectedCardsArrayList().clear();


            //④制作玩家牌打出的安卓界面动画效果
            // 移除牌
            for (int i = 0; i<selectedCardImage.size();i++)
            {
                CardImage image = selectedCardImage.get(i);
                CardImage new_image=new CardImage(this,image.getSerial_number(),130);
                //从玩家牌池中移除牌
                linearLayout.removeView(image);
                //添加进入中间牌池显示
                justPlayedCards.add(new_image);
                addToHorizontalLinearLayout(new_image,justPlayedCardLayout);
            }
            if(linearLayout.getChildCount()!=0)
            {
                //设置第一张牌不偏移
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMarginStart(0);
                linearLayout.getChildAt(0).setLayoutParams(lp);
            }
            if(justPlayedCardLayout.getChildCount()!=0)
            {
                //设置第一张牌不偏移
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMarginStart(0);
                justPlayedCardLayout.getChildAt(0).setLayoutParams(lp);
            }

            selectedCardImage.clear();
        }
    }
}
