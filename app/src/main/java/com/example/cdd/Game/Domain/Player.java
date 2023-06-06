package com.example.cdd.Game.Domain;

import java.util.ArrayList;
import java.util.Collections;

//玩家类，代表玩家，每个玩家有13张牌
public class Player
{
    public Player(String Name)
    {
        name=Name;
    }

    //玩家姓名
    String name=new String();

    //存放玩家的13张牌序号，可通过Card的serial_number_TO_card找到对应的牌号和花色
    ArrayList<Integer> cards_list=new ArrayList<>();

    //正常排序
    public void normal_sort()
    {
        Collections.sort(cards_list);
    }

    //花色排序
    void suits_sort(ArrayList<Integer> list)
    {
        ArrayList<Integer> diamond=new ArrayList();
        ArrayList<Integer> plum=new ArrayList();
        ArrayList<Integer> hearts=new ArrayList();
        ArrayList<Integer> spade=new ArrayList();

        for(int i:list)
        {
            if(i%4==1)
                diamond.add(i);
            else if(i%4==2)
                plum.add(i);
            else if(i%4==3)
                hearts.add(i);
            else
                spade.add(i);
        }
        Collections.sort(diamond);
        Collections.sort(plum);
        Collections.sort(hearts);
        Collections.sort(spade);

        list.clear();
        list.addAll(diamond);
        list.addAll(plum);
        list.addAll(hearts);
        list.addAll(spade);
    }

    //打印玩家的牌
    public void show_player_card()
    {
        System.out.print(name+":");
        for(int num:cards_list)
        {
            String str=Cards.serial_number_TO_card.get(num);
            System.out.print(str+" ");
        }
        System.out.println();
    }

    String getName()
    {
        return name;
    }

    void setName(String Name)
    {
        name=Name;
    }

    public ArrayList<Integer> getArrayList()
    {
        return cards_list;
    }
}
