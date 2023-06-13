package com.example.cdd.Game.Domain;

import java.util.ArrayList;
import java.util.Collections;

//玩家类，代表玩家，每个玩家有13张牌
public class Player
{
    //玩家姓名
    String name=new String();

    //玩家的出排顺序
    int turn;

    //存放玩家的13张牌序号，可通过Card的serial_number_TO_card找到对应的牌号和花色
    ArrayList<Integer> cards_list=new ArrayList<>();

    //选中的牌,即将要出的牌，每次用完要清空
    ArrayList<Integer>selectedCards=new ArrayList<>();

    //选择操作 0:pass 1:出牌
    int operator;

    public void setTurn(int TURN)
    {
        turn=TURN;
    }

    public int getTurn()
    {
        return turn;
    }

    public Player(String Name)
    {
        name=Name;
    }

    public Player(){}

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

    //删除打出去的牌
    public void delete_SelectedCards(){
        for(int i=0;i<selectedCards.size();i++){
            cards_list.remove(selectedCards.get(i));
        }
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

    //选牌，鼠标点到哪张牌，就加入哪张牌，传入参数为下标
    public void select_Card(int card){
        selectedCards.add(card);
    }

    //删去所选牌
    public void delete_Card(int card){
        selectedCards.remove(card);
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator){
        this.operator=operator;
    }

    public ArrayList<Integer> getSelectedCardsArrayList() {
        return selectedCards;
    }

    public void setSelectedCardsArrayList(ArrayList<Integer> list)
    {
        selectedCards.clear();
        for(int i=0;i<list.size();i++)
        {
            selectedCards.add(list.get(i));
        }
    }
}