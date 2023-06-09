package com.example.cdd.Game.System;

import com.example.cdd.Game.Domain.Cards;
import com.example.cdd.Game.Domain.Player;

import java.util.ArrayList;
import java.util.Collections;

//游戏的主要运行过程
public class GameTurn {

    public Player player1=new Player("玩家1");
    public Player player2=new Player("玩家2");
    public Player player3=new Player("玩家3");
    public Player player4=new Player("玩家4");

    //构造函数，初始时为每位玩家发牌,并正常排序玩家的牌
    public GameTurn()
    {
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
}
