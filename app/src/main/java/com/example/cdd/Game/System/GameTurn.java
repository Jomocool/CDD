package com.example.cdd.Game.System;

import android.util.Log;
import android.view.View;

import com.example.cdd.Game.Domain.CardGroup;
import com.example.cdd.Game.Domain.Cards;
import com.example.cdd.Game.Domain.Player;
import com.example.cdd.Game.Domain.PokerType;
import com.example.cdd.Game.Domain.RobotPlayer;
import com.example.cdd.Game.Rule.GameRule;
import com.example.cdd.Game.UI.GamingInterfaceActivity;
import com.example.cdd.Game.UI.MyObserver;
import com.example.cdd.R;


import java.util.ArrayList;
import java.util.Collections;

//游戏的主要运行过程
public class GameTurn {
    //activity实例
    GamingInterfaceActivity activity;

    //被观察者实例，GameTurn对象就是被观察者
    public MyObservable myObservable=new MyObservable();

    //被观察者
    public class MyObservable{
        //观察者，Activity对象就是观察者
        private MyObserver observer;

        public void setObserver(MyObserver Observer)
        {
            observer=Observer;
        }

        //被观察者(GameTurn对象)通知观察者(Activity对象)
        public void notifyObservers()
        {
            observer.update();
        }
    }

    private int last_player=0;

    //出牌的次数
    private int play_cards_count=0;

    //是否游戏结束
    private boolean is_GameOver=false;

    //四位玩家
    public Player player1=new Player("玩家1");
    public RobotPlayer player2=new RobotPlayer("人机2");
    public RobotPlayer player3=new RobotPlayer("人机3");
    public RobotPlayer player4=new RobotPlayer("人机4");

    //player1得分
    int score1;

    //player2得分
    int score2;

    //player3得分
    int score3;

    //player4得分
    int score4;

    /*boolean player1_pass=false;
    boolean player2_pass=false;
    boolean player3_pass=false;
    boolean player4_pass=false;*/

    public int getLastPlayer()
    {
        return last_player;
    }

    public void setLastPlayer(int num)
    {
        last_player=num;
    }

    /*public void set_player1_pass(boolean b)
    {
        player1_pass=b;
    }

    public boolean get_player1_pass()
    {
        return player1_pass;
    }

    public void set_player2_pass(boolean b)
    {
        player2_pass=b;
    }

    public boolean get_player2_pass()
    {
        return player2_pass;
    }

    public void set_player3_pass(boolean b)
    {
        player3_pass=b;
    }

    public boolean get_player3_pass()
    {
        return player3_pass;
    }

    public void set_player4_pass(boolean b)
    {
        player4_pass=b;
    }

    public boolean get_player4_pass()
    {
        return player4_pass;
    }*/

    public int get_play_cards_count()
    {
        return play_cards_count;
    }

    public void play_cards_count_add_one()
    {
        play_cards_count++;
    }

    public boolean getIsGameOver()
    {
        return is_GameOver;
    }

    //记录上一位玩家的牌
    ArrayList<Integer> LastPlayerCards=new ArrayList<>();

    //设置游戏结束
    public void setGameOver()
    {
        is_GameOver=true;
    }

    //返回上一位玩家的牌数组
    public ArrayList<Integer> getLastPlayerCardsArrayList()
    {
        return LastPlayerCards;
    }

    //构造函数，初始时为每位玩家发牌,并正常排序玩家的牌
    public GameTurn(GamingInterfaceActivity Activity)
    {
        activity=Activity;

        score1=0;
        score2=0;
        score3=0;
        score4=0;
        shuffle_cards();;
        deal_cards();
        player1.normal_sort();
        player2.normal_sort();
        player3.normal_sort();
        player4.normal_sort();

        //初始化各玩家的出牌顺序
        if(player1.getArrayList().get(0)==1)
        {
            player1.setTurn(0);
            player2.setTurn(1);
            player4.setTurn(2);
            player3.setTurn(3);
        }
        else if(player2.getArrayList().get(0)==1)
        {
            player2.setTurn(0);
            player4.setTurn(1);
            player3.setTurn(2);
            player1.setTurn(3);
        }
        else if(player3.getArrayList().get(0)==1)
        {
            player3.setTurn(0);
            player1.setTurn(1);
            player2.setTurn(2);
            player4.setTurn(3);
        }
        else
        {
            player4.setTurn(0);
            player3.setTurn(1);
            player1.setTurn(2);
            player2.setTurn(3);
        }
    }

    //牌局进行中
    public void PlayingGame()
    {
        /*Log.e("inf",""+player1.getTurn());
        Log.e("inf",""+player2.getTurn());
        Log.e("inf",""+player3.getTurn());
        Log.e("inf",""+player4.getTurn());*/

        if(is_GameOver==true)
        {
            return;
        }

        //人类玩家
        if((play_cards_count%4)==player1.getTurn())
        {
            //设置玩家按键按钮可视化
            activity.set_selection_visible();

            //设置玩家过的文本不可见
            activity.findViewById(R.id.pass1).setVisibility(View.INVISIBLE);
        }

        //人机2
        else if((play_cards_count%4)==player2.getTurn())
        {
            //根据人机算法自动获取要出的牌
            if(getLastPlayer()!=2)
            {
                if(play_cards_count==0)
                    player2.setSelectedCardsArrayList(player2.firstPlay());
                else
                    player2.setSelectedCardsArrayList(player2.getCards(LastPlayerCards));
            }
            else
                player2.setSelectedCardsArrayList(player2.maxPlayer());

            for(int i=0;i<player2.getSelectedCardsArrayList().size();i++)
            {
                Log.e("人机玩家2最开始选择的牌：",""+player2.getSelectedCardsArrayList().get(i));
            }
            Log.e("人机玩家2剩下牌的个数",""+player2.getArrayList().size());

            //出牌动画
            activity.player2_plays_cards_with_delay();
        }

        //人机3
        else if((play_cards_count%4)==player3.getTurn())
        {
            //根据人机算法自动获取要出的牌
            if(getLastPlayer()!=3)
            {
                if(play_cards_count==0)
                    player3.setSelectedCardsArrayList(player3.firstPlay());
                else
                    player3.setSelectedCardsArrayList(player3.getCards(LastPlayerCards));
            }
            else
                player3.setSelectedCardsArrayList(player3.maxPlayer());


            for(int i=0;i<player3.getSelectedCardsArrayList().size();i++)
            {
                Log.e("人机玩家3最开始选择的牌：",""+player3.getSelectedCardsArrayList().get(i));
            }
            Log.e("人机玩家3剩下牌的个数",""+player3.getArrayList().size());

            //出牌动画
            activity.player3_plays_cards_with_delay();
        }

        //人机4
        else if((play_cards_count%4)==player4.getTurn())
        {
            //根据人机算法自动获取要出的牌
            if(getLastPlayer()!=4)
            {
                if(play_cards_count==0)
                    player4.setSelectedCardsArrayList(player4.firstPlay());
                else
                    player4.setSelectedCardsArrayList(player4.getCards(LastPlayerCards));
            }
            else
                player4.setSelectedCardsArrayList(player4.maxPlayer());

            for(int i=0;i<player4.getSelectedCardsArrayList().size();i++)
            {
                Log.e("人机玩家4最开始选择的牌：",""+player4.getSelectedCardsArrayList().get(i));
            }
            Log.e("人机玩家4剩下牌的个数",""+player4.getArrayList().size());

            //出牌动画
            activity.player4_plays_cards_with_delay();
        }
    }

    /*public void playing_game()
    {
        while(is_GameOver==false)
        {
            if(play_cards_count%4==player1.getTurn())
            {

            }

            if(play_cards_count%4==player2.getTurn())
            {
                myObservable.notifyObservers();
                play_cards_count++;
            }

            if(play_cards_count%4==player3.getTurn())
            {
                myObservable.notifyObservers();
                play_cards_count++;
            }

            if(play_cards_count%4==player4.getTurn())
            {
                myObservable.notifyObservers();
                play_cards_count++;
            }
        }

    }*/

    //洗牌
    void shuffle_cards()
    {
        Collections.shuffle(Cards.serial_number);
    }

    //发牌
    void deal_cards()
    {
        //发牌
        for(int i=0;i<Cards.serial_number.size();i++)
        {
            if(i%4==0)
                player1.getArrayList().add(Cards.serial_number.get(i));
            else if(i%4==1)
                player2.getArrayList().add(Cards.serial_number.get(i));
            else if(i%4==2)
                player3.getArrayList().add(Cards.serial_number.get(i));
            else
                player4.getArrayList().add(Cards.serial_number.get(i));
        }
    }

    void show_player_cards()
    {
        player1.show_player_card();
        player2.show_player_card();
        player3.show_player_card();
        player4.show_player_card();
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public int getScore3() {
        return score3;
    }

    public int getScore4() {
        return score4;
    }

    //计算最终得分
    public void computeScore(){
        int cardScore1=computeCardScore(player1.getArrayList().size());
        int cardScore2=computeCardScore(player2.getArrayList().size());
        int cardScore3=computeCardScore(player3.getArrayList().size());
        int cardScore4=computeCardScore(player4.getArrayList().size());

        int sum=score1+score2+score3+score4;

        score1=sum-4*score1;
        score2=sum-4*score2;
        score3=sum-4*score3;
        score4=sum-4*score4;
    }

    //传入剩余牌数，计算牌分
    public int computeCardScore(int n){
        if(n<8)return n;
        else if(n>=8&&n<10)return n*2;
        else if(n>=10&&n<13)return n*3;
        else return n*4;
    }
}
