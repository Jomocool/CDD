package com.example.cdd.Game.System;

import android.media.MediaParser;

import com.example.cdd.Game.Domain.CardGroup;
import com.example.cdd.Game.Domain.Cards;
import com.example.cdd.Game.Domain.Player;
import com.example.cdd.Game.Domain.PokerType;
import com.example.cdd.Game.Domain.RobotPlayer;
import com.example.cdd.Game.Rule.GameRule;

import java.util.ArrayList;
import java.util.Collections;

//游戏的主要运行过程
public class GameTurn {

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

    //记录上一位玩家的牌
    ArrayList<Integer> LastPlayerCards=new ArrayList<>();

    //返回上一位玩家的牌数组
    public ArrayList<Integer> getLastPlayerCardsArrayList()
    {
        return LastPlayerCards;
    }


    //构造函数，初始时为每位玩家发牌,并正常排序玩家的牌
    public GameTurn()
    {
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
    }

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
