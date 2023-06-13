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
import com.example.cdd.GameOver.UI.Defeat;
import com.example.cdd.GameOver.UI.Victory;
import com.example.cdd.R;

import java.sql.Array;
import java.util.ArrayList;

public class GamingInterfaceActivity extends AppCompatActivity {
    //游戏系统
    private GameTurn game_turn=new GameTurn(this);

    //记录要出的牌
    private ArrayList<CardImage> selectedCardImage = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaming_interface);
        //将玩家的牌加入布局中
        addPlayer1AllCards(game_turn.player1);
        addPlayer4AllCards(game_turn.player4);
        addPlayer2AllCards(game_turn.player2);
        addPlayer3AllCards(game_turn.player3);

        game_turn.PlayingGame();
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
                        //已实现在出牌按钮点击时加入到player的SelectedCardsArrayList，这里不用加入，每次出牌时加入更方便
                        //game_turn.player1.getSelectedCardsArrayList().add(card_image.getSerial_number());
                    }
                    //取消选中
                    else
                    {
                        card_image.cancel_select();
                        card_image.offsetTopAndBottom(20);
                        selectedCardImage.add(card_image);
                        //已实现在出牌按钮点击时加入到player的SelectedCardsArrayList，这里不用加入，每次出牌时加入更方便
                        //game_turn.player1.getSelectedCardsArrayList().remove((Integer) card_image.getSerial_number());
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

    //点击出牌按钮(人类玩家打牌)
    public void click_play_cards(View view) {
        //布局文件
        LinearLayout linearLayout=findViewById(R.id.playerCardsContainer);
        LinearLayout justPlayedCardLayout=findViewById(R.id.JustPlayCardsContainer);

        //点击出牌，会将点击的牌加入到player1的selectedCardsArrayList数组中
        for(int i=0;i<linearLayout.getChildCount();i++)
        {
            CardImage image=(CardImage)linearLayout.getChildAt(i);
            if(image.get_isSelected()==true)
            {
                game_turn.player1.getSelectedCardsArrayList().add((Integer) image.getSerial_number());
            }
        }

        CardGroup cardGroup=new CardGroup(game_turn.player1.getSelectedCardsArrayList());
        boolean result=CDDGameRule.judge(cardGroup,new CardGroup(game_turn.getLastPlayerCardsArrayList()));

        //所选的牌不符合规则，重新选择
        if(result==false)
        {
            game_turn.player1.getSelectedCardsArrayList().clear();
            Toast.makeText(this, "所选牌不符合规则", Toast.LENGTH_LONG).show();
        }
        //所选的牌符合规则，出牌权跳到下一玩家
        else
        {
            //①先清空中间显示的牌
            middle_layout_clear(justPlayedCardLayout);

            //②清空原先的上一玩家出牌数组并将该玩家的出牌赋值给LastPlayerCardsArrayList
            game_turn.getLastPlayerCardsArrayList().clear();
            //人类玩家出牌，不存在不出的情况，所以不用判断game_turn.player1.getSelectedCardsArrayList()是否为0
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
                game_turn.setGameOver();
                startActivity(new Intent(this,Victory.class));
                return;
            }

            //④制作玩家牌打出的安卓界面动画效果
            // 移除牌
            for (int i = 0; i<selectedCardImage.size();i++)
            {
                CardImage image = selectedCardImage.get(i);
                CardImage new_image=new CardImage(this,image.getSerial_number(),130);
                //从玩家牌池中移除牌
                linearLayout.removeView(image);
                //添加进入中间牌池显示
                addToHorizontalLinearLayout(new_image,justPlayedCardLayout);
            }
            set_first_card_no_offset(linearLayout);
            set_first_card_no_offset(justPlayedCardLayout);

            //清空玩家的selected_Cards数组
            game_turn.player1.getSelectedCardsArrayList().clear();

            //清空玩家选择出了的牌
            selectedCardImage.clear();
        }
    }

    //机器人2号打牌
    //机器人将牌这一轮要出的牌传给它的selectedCardsArrayList中
    public void player2_plays_cards()
    {
        ArrayList<Integer> list=game_turn.player2.getSelectedCardsArrayList();

        LinearLayout player2_cards_layout=findViewById(R.id.player2CardsContainer);
        LinearLayout just_played_cards_layout=findViewById(R.id.JustPlayCardsContainer);

        if(list.size()==0)//该玩家选择过
        {
            //在玩家处显示过的图标，并在下次删除

        }
        else
        {
            //①先清空中间显示的牌
            middle_layout_clear(just_played_cards_layout);

            //②清空原先的上一玩家出牌数组并将该电脑玩家的出牌赋值给LastPlayerCardsArrayList
            game_turn.getLastPlayerCardsArrayList().clear();
            game_turn.getLastPlayerCardsArrayList().addAll(list);

            //③将电脑玩家所出的牌移除出它的serial_number数组
            for(int i=0;i<list.size();i++)
            {
                game_turn.player2.getArrayList().remove((Integer)list.get(i));
            }
            //当玩家的牌数量等于0时，游戏结束
            if(game_turn.player2.getArrayList().size()==0)
            {
                game_turn.setGameOver();
                startActivity(new Intent(this, Defeat.class));
                return;
            }

            //④制作电脑玩家牌打出的安卓界面动画效果
            //移除牌需要通过倒序的方式移除，如果通过正序移除会每次牌池的数量会减少，然后getChildAt(i)就会超出范围,所以j从结尾到开头
            //要想结果牌的结果从左到右正确显示，i就得从头遍历数组
            for(int i=list.size()-1,j=0;i>=0;i--,j++)
            {
                //移除玩家手中特定数量的牌 移除前几张
                CardImage cardImage=(CardImage)player2_cards_layout.getChildAt(i);
                player2_cards_layout.removeView(cardImage);

                //添加到中间牌池
                CardImage new_card_image=new CardImage(this,list.get(j),130);
                addToHorizontalLinearLayout(new_card_image,just_played_cards_layout);
            }
            set_first_card_no_offset(player2_cards_layout);
            set_first_card_no_offset(just_played_cards_layout);

            //清空玩家的selected_Cards数组
            game_turn.player2.getSelectedCardsArrayList().clear();
        }
    }


    //机器人3号打牌
    //机器人将牌这一轮要出的牌传给它的selectedCardsArrayList中
    public void player3_plays_cards()
    {
        ArrayList<Integer> list=game_turn.player3.getSelectedCardsArrayList();

        LinearLayout player3_cards_layout=findViewById(R.id.player3CardsContainer);
        LinearLayout just_played_cards_layout=findViewById(R.id.JustPlayCardsContainer);

        if(list.size()==0)//该玩家选择过
        {
            //在玩家处显示过的图标，并在下次删除

        }
        else
        {
            //①先清空中间显示的牌
            middle_layout_clear(just_played_cards_layout);

            //②清空原先的上一玩家出牌数组并将该电脑玩家的出牌赋值给LastPlayerCardsArrayList
            game_turn.getLastPlayerCardsArrayList().clear();
            game_turn.getLastPlayerCardsArrayList().addAll(list);

            //③将电脑玩家所出的牌移除出它的serial_number数组
            for(int i=0;i<list.size();i++)
            {
                game_turn.player3.getArrayList().remove((Integer)list.get(i));
            }
            //当玩家的牌数量等于0时，游戏结束
            if(game_turn.player3.getArrayList().size()==0)
            {
                game_turn.setGameOver();
                startActivity(new Intent(this, Defeat.class));
                return;
            }

            //④制作电脑玩家牌打出的安卓界面动画效果
            //移除牌需要通过倒序的方式移除，如果通过正序移除会每次牌池的数量会减少，然后getChildAt(i)就会超出范围,所以j从结尾到开头
            //要想结果牌的结果从左到右正确显示，i就得从头遍历数组
            for(int i=list.size()-1,j=0;i>=0;i--,j++)
            {
                //移除玩家手中特定数量的牌 移除前几张
                CardImage cardImage=(CardImage)player3_cards_layout.getChildAt(i);
                player3_cards_layout.removeView(cardImage);

                //添加到中间牌池
                CardImage new_card_image=new CardImage(this,list.get(j),130);
                addToHorizontalLinearLayout(new_card_image,just_played_cards_layout);
            }
            set_first_card_no_offset(player3_cards_layout);
            set_first_card_no_offset(just_played_cards_layout);

            //清空玩家的selected_Cards数组
            game_turn.player3.getSelectedCardsArrayList().clear();
        }
    }

    //机器人4号打牌
    //机器人将牌这一轮要出的牌传给它的selectedCardsArrayList中
    public void player4_plays_cards()
    {
        ArrayList<Integer> list=game_turn.player4.getSelectedCardsArrayList();

        LinearLayout player4_cards_layout=findViewById(R.id.player4CardsContainer);
        LinearLayout just_played_cards_layout=findViewById(R.id.JustPlayCardsContainer);

        if(list.size()==0)//该玩家选择过
        {
            //在玩家处显示过的图标，并在下次删除

        }
        else
        {
            //①先清空中间显示的牌
            middle_layout_clear(just_played_cards_layout);

            //②清空原先的上一玩家出牌数组并将该电脑玩家的出牌赋值给LastPlayerCardsArrayList
            game_turn.getLastPlayerCardsArrayList().clear();
            game_turn.getLastPlayerCardsArrayList().addAll(list);

            //③将电脑玩家所出的牌移除出它的serial_number数组
            for(int i=0;i<list.size();i++)
            {
                game_turn.player4.getArrayList().remove((Integer)list.get(i));
            }
            //当玩家的牌数量等于0时，游戏结束
            if(game_turn.player4.getArrayList().size()==0)
            {
                game_turn.setGameOver();
                startActivity(new Intent(this, Defeat.class));
                return;
            }

            //④制作电脑玩家牌打出的安卓界面动画效果
            //移除牌需要通过倒序的方式移除，如果通过正序移除会每次牌池的数量会减少，然后getChildAt(i)就会超出范围,所以j从结尾到开头
            //要想结果牌的结果从左到右正确显示，i就得从头遍历数组
            for(int i=list.size()-1,j=0;i>=0;i--,j++)
            {
                //移除玩家手中特定数量的牌 移除前几张
                CardImage cardImage=(CardImage)player4_cards_layout.getChildAt(i);
                player4_cards_layout.removeView(cardImage);

                //添加到中间牌池
                CardImage new_card_image=new CardImage(this,list.get(j),130);
                addToHorizontalLinearLayout(new_card_image,just_played_cards_layout);
            }
            set_first_card_no_offset(player4_cards_layout);
            set_first_card_no_offset(just_played_cards_layout);

            //清空玩家的selected_Cards数组
            game_turn.player4.getSelectedCardsArrayList().clear();
        }
    }

    //清空中间的牌
    void middle_layout_clear(LinearLayout layout)
    {
        for(int i=layout.getChildCount()-1;i>=0;i--)
        {
            CardImage image=(CardImage) layout.getChildAt(i);
            layout.removeView(image);
        }
    }

    //设置第一张牌不偏移
    void set_first_card_no_offset(LinearLayout layout)
    {
        if(layout.getChildCount()!=0)
        {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMarginStart(0);
            layout.getChildAt(0).setLayoutParams(lp);
        }
    }
}
