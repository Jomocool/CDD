package com.example.cdd.Game.System;

import com.example.cdd.Game.Domain.CardGroup;
import com.example.cdd.Game.Domain.Cards;
import com.example.cdd.Game.Domain.Player;
import com.example.cdd.Game.Domain.PokerType;
import com.example.cdd.Game.Rule.GameRule;

import java.util.ArrayList;
import java.util.Collections;

//游戏的主要运行过程
public class GameTurn {

    //四位玩家
    public Player player1=new Player("玩家1");
    public Player player2=new Player("玩家2");
    public Player player3=new Player("玩家3");
    public Player player4=new Player("玩家4");

    //当前出牌玩家
    public int currentPlayer;

    //当前局面最大的牌
    public CardGroup currentGroup=new CardGroup();

    //当前局面最大的牌是谁打出来的
    public int controller;

    //当前打到第几副牌
    public int currentRoute;

    //当前过人次数
    public int currentCount;

    //游戏规则
    GameRule rule;

    //构造函数，初始时为每位玩家发牌,并正常排序玩家的牌
    public GameTurn()
    {
        shuffle_cards();;
        deal_cards();
        player1.normal_sort();
        player2.normal_sort();
        player3.normal_sort();
        player4.normal_sort();

        //初始化游戏规则
        rule=new GameRule();

        //第一次开始游戏，用的是第一副牌
        currentRoute=0;

        //找到有方块3的玩家
        currentPlayer=findDiamond3();

        gaming();
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

    //游戏过程，已经提前设置好第一个出牌的人
    public void gaming(){
        currentCount=0;
        while(player1.getArrayList().size()!=0&&player2.getArrayList().size()!=0&&player3.getArrayList().size()!=0&&player4.getArrayList().size()!=0){
            //player1 turn
            if(currentPlayer==0){
                //第一副牌第一轮的第一个人需要先打出带有方块3的牌型
                if(currentRoute==0&&currentCount==0){

                }
            }
            //player2 turn
            else if(currentPlayer==1){

            }
            //player3 turn
            else if(currentPlayer==2){

            }
            //player4 turn
            else{

            }
        }
        currentRoute++;
    }

    public int findDiamond3(){
        //找到第一个出牌的人，谁有方块3谁先出牌
        int res=0;
        res=player1.getArrayList().get(0)==1?0:res;
        res=player2.getArrayList().get(0)==1?1:res;
        res=player3.getArrayList().get(0)==1?2:res;
        res=player4.getArrayList().get(0)==1?3:res;

        return res;
    }
}
