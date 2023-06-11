package com.example.cdd.Game.Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CardGroup {
    //当前牌组的牌
    ArrayList<Integer> cards;
    //牌型
    PokerType type;
    //最大单牌
    int maxCard;

    //默认构造函数
    public CardGroup(){
        cards=new ArrayList<Integer>();
        type=PokerType.cError;
        maxCard=0;
    }

    //构造函数，传入选中的牌，并判断牌型
    public CardGroup(ArrayList<Integer> selectedCards) {
        cards = selectedCards;
        //排序牌组，方便比大小
        Collections.sort(cards, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return integer.compareTo(t1);
            }
        });
        int n = cards.size();
        if (n == 1) {//单张
            type = PokerType.c1;
            maxCard=cards.get(0);
        } else if (n == 2) {//一对，如果两张牌点一样就是一对，如果不一样就是不符合规则的牌型
            //第一张牌的点数
            int card1 = 3 + (cards.get(0) - 1) / 4;
            //第二张牌的点数
            int card2 = 3 + (cards.get(1) - 1) / 4;
            if (card1 == card2){
                type = PokerType.c2;
                maxCard=cards.get(1);
            }
            else
                type = PokerType.cError;
        } else if (n == 3) {//三个：如果三张牌点一样就是三个，否则就是无效的牌型
            //第一张牌的点数
            int card1 = 3 + (cards.get(0) - 1) / 4;
            //第二张牌的点数
            int card2 = 3 + (cards.get(1) - 1) / 4;
            //第三张牌的点数
            int card3 = 3 + (cards.get(2) - 1) / 4;
            if (card1 == card2 && card1 == card3){
                type = PokerType.c3;
                maxCard=cards.get(2);
            }
            else
                type = PokerType.cError;
        } else if (n == 5) {//牌型可能是 同花顺>四个带单张>三个带一对>同花五>杂顺
            ArrayList<Integer> cardNum = new ArrayList<>();
            ArrayList<Integer> cardPattern = new ArrayList<>();
            for (int i = 0; i < cards.size(); i++) {
                cardNum.add(3 + (cards.get(i) - 1) / 4);
                cardPattern.add((cards.get(i) - 1) % 4);
            }

            boolean isSamePattern = true;//是否同花色
            boolean isContinuous = false;//是否顺子

            //判断是否同花色
            for (int i = 0; i < cardPattern.size() - 1; i++) {
                if (cardPattern.get(i) != cardPattern.get(i + 1)){
                    isSamePattern = false;
                    break;
                }
            }

            //判断是否顺子，先处理特殊情况：数组最后为A2或2的
            //处理A2345~23456，cardNum是345A2或34562
            if(is345A2(cardNum)){
                isContinuous=true;
                maxCard=cards.get(2);
            }else if(is34562(cardNum)){
                isContinuous=true;
                maxCard=cards.get(3);
            }
            else{
                //处理34567～10JQKA
                if(cardNum.get(0)>=3&&cardNum.get(0)<=10){
                    for(int i=0;i<cardNum.size()-1;i++){
                        if(cardNum.get(i)==cardNum.get(i+1)-1){
                            if(i==3)isContinuous=true;
                            continue;
                        }else{
                            break;
                        }
                    }
                    if(isContinuous){
                        maxCard=cards.get(4);
                    }
                }
            }

            if (isSamePattern) {
                if (isContinuous)//同花且顺子
                    type = PokerType.c11111Five;
                else//同花但不是顺子
                    type = PokerType.cFive;
            } else {
                if (isContinuous)//不同花但是顺子
                    type = PokerType.c11111;
                else {//既不是同花，也不是顺子，那有可能是四带一或者三带一对
                    if (is32(cardNum)) type = PokerType.c32;
                    else if (is41(cardNum)) type = PokerType.c41;
                        //既不是四带一，也不是三带一对，说明是无效牌型
                    else type = PokerType.cError;
                }
            }
        } else {//没有这么多牌的牌型，是无效的牌型
            type = PokerType.cError;
        }
    }

    public boolean is32(ArrayList<Integer> arr) {
        boolean front = true;
        boolean back = true;
        for (int i = 0; i < 2; i++) {
            if (arr.get(i) != arr.get(i + 1)){
                front = false;
                break;
            }
        }
        for (int i = arr.size() - 1; i > 2; i--) {
            if (arr.get(i) != arr.get(i - 1)){
                back = false;
                break;
            }
        }
        if (front) {
            maxCard=cards.get(2);
            return arr.get(3) == arr.get(4);
        }
        if (back) {
            maxCard=cards.get(4);
            return arr.get(0) == arr.get(1);
        }

        return false;
    }

    public boolean is41(ArrayList<Integer> arr) {
        boolean front = true;
        boolean back = true;
        for (int i = 0; i < 3; i++) {
            if (arr.get(i) != arr.get(i + 1)){
                front = false;
                break;
            }
        }
        for (int i = arr.size() - 1; i > 1; i--) {
            if (arr.get(i) != arr.get(i - 1)){
                back = false;
                break;
            }
        }

        if(front){
            maxCard=cards.get(3);
        }
        if(back){
            maxCard=cards.get(4);
        }

        return front || back;
    }

    public boolean is345A2(ArrayList<Integer>arr){
        return arr.get(0)==3&&arr.get(1)==4&&arr.get(2)==5&&arr.get(3)==14&&arr.get(4)==15;
    }

    public boolean is34562(ArrayList<Integer>arr){
        return arr.get(0)==3&&arr.get(1)==4&&arr.get(2)==5&&arr.get(3)==6&&arr.get(4)==15;
    }

    public ArrayList<Integer> getCards() {
        return cards;
    }

    public int getMaxCard() {
        return maxCard;
    }

    public PokerType getType() {
        return type;
    }

    public void setCards(ArrayList<Integer> cards) {
        this.cards = cards;
    }

    public void setMaxCard(int maxCard) {
        this.maxCard = maxCard;
    }

    public void setType(PokerType type) {
        this.type = type;
    }
}

