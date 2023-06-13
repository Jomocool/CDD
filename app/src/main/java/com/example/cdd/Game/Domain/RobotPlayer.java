package com.example.cdd.Game.Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RobotPlayer extends Player{
    public RobotPlayer(String name){
        super(name);
    }

    public ArrayList<Integer> getCards(ArrayList<Integer>LastPlayerCards){
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);
        ArrayList<Integer>tmp=new ArrayList<>();

        switch (LastPlayerGroup.getType()){
            case c1:
                return findMinC1(LastPlayerCards);
            case c2:
                return findMinC2(LastPlayerCards);
            case c3:
                return findMinC3(LastPlayerCards);
            case c11111Five:
                return findC11111Five(LastPlayerCards);
            case c41:
                tmp=findMinC41(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                return findC11111Five(LastPlayerCards);
            case c32:
                tmp=findMinC32(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinC41(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                return findC11111Five(LastPlayerCards);
            case cFive:
                tmp=findMinCFive(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinC32(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinC41(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                return findC11111Five(LastPlayerCards);
            case c11111:
                tmp=findMinC11111(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinCFive(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinC32(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinC41(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                return findC11111Five(LastPlayerCards);
        }
        return tmp;
    }

    //传入的是当前需要压的牌组
    //假设当前需要压的牌型是单牌，就找比其大且是机器人所有牌中最小的单牌；后面找其他牌型也类似
    //如果要得起，返回存有牌的数组并更新牌组；如果要不起，返回空数组
    public ArrayList<Integer> findMinC1(ArrayList<Integer>LastPlayerCards){
        ArrayList<Integer>curPlayerCards=new ArrayList<>();
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);

        ArrayList<Integer>cards=this.cards_list;
        Collections.sort(cards);//按牌点从小到大排序

        //如果当前玩家的最大单牌都没有上一位出牌玩家的出的牌大，那就要不起，返回空数组
        if(cards.get(cards.size()-1)<LastPlayerGroup.getMaxCard()){
            return curPlayerCards;
        }

        for(int i=0;i<cards.size();i++){
            if(cards.get(i)>LastPlayerGroup.getMaxCard()){
                curPlayerCards.add(cards.get(i));
                break;
            }
        }

        cards_list.removeAll(curPlayerCards);
        return curPlayerCards;
    }

    public ArrayList<Integer> findMinC2(ArrayList<Integer>LastPlayerCards){
        ArrayList<Integer>curPlayerCards=new ArrayList<>();
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);

        ArrayList<Integer>cards=this.cards_list;
        Collections.sort(cards);//按牌点从小到大排序

        if(cards.get(cards.size()-1)<LastPlayerGroup.getMaxCard()){
            return curPlayerCards;
        }

        int index=0;
        while(index<cards.size()-1){
            int card1=transferToNum(cards.get(index));
            int card2=transferToNum(cards.get(index+1));
            if(card1==card2&&cards.get(index+1)>LastPlayerGroup.getMaxCard()){
                curPlayerCards.add(cards.get(index));
                curPlayerCards.add(cards.get(index+1));
                break;
            }
            index++;
        }

        cards_list.removeAll(curPlayerCards);
        return curPlayerCards;
    }

    public ArrayList<Integer> findMinC3(ArrayList<Integer>LastPlayerCards){
        ArrayList<Integer>curPlayerCards=new ArrayList<>();
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);

        ArrayList<Integer>cards=this.cards_list;
        Collections.sort(cards);//按牌点从小到大排序

        if(cards.get(cards.size()-1)<LastPlayerGroup.getMaxCard()){
            return curPlayerCards;
        }

        int index=0;
        while(index<cards.size()-2){
            int card1=transferToNum(cards.get(index));
            int card2=transferToNum(cards.get(index+1));
            int card3=transferToNum(cards.get(index+2));
            if(card1==card2&&card2==card3&&cards.get(index+2)>LastPlayerGroup.getMaxCard()){
                curPlayerCards.add(cards.get(index));
                curPlayerCards.add(cards.get(index+1));
                curPlayerCards.add(cards.get(index+2));
                break;
            }
            index++;
        }

        cards_list.removeAll(curPlayerCards);
        return curPlayerCards;
    }

    public ArrayList<Integer> findMinC11111(ArrayList<Integer>LastPlayerCards){
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);

        ArrayList<Integer>cards=this.cards_list;
        Collections.sort(cards);//按牌点从小到大排序

        for(int i=0;i<cards.size()-4;i++){
            for(int j=i+1;j<cards.size()-3;j++){
                for(int k=j+1;k<cards.size()-2;k++){
                    for(int l=k+1;l<cards.size()-1;l++){
                        for(int m=l+1;m<cards.size();m++){
                            ArrayList<Integer>tmp=new ArrayList<>();
                            tmp.add(cards.get(i));
                            tmp.add(cards.get(j));
                            tmp.add(cards.get(k));
                            tmp.add(cards.get(l));
                            tmp.add(cards.get(m));
                            CardGroup group=new CardGroup(tmp);
                            if(isContinuous(tmp)&&group.getMaxCard()>LastPlayerGroup.getMaxCard()){
                                cards_list.removeAll(tmp);
                                return tmp;
                            }
                        }
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    public ArrayList<Integer> findMinCFive(ArrayList<Integer>LastPlayerCards){
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);

        int index=0;
        while(index<cards_list.size()-4){
            int cur=index;
            ArrayList<Integer>tmp=new ArrayList<>();
            while(cur+1<cards_list.size()&&transferToPattern(cards_list.get(cur))==transferToPattern(cards_list.get(cur+1))){
                tmp.add(cards_list.get(cur++));
            }
            tmp.add(cards_list.get(cur));

            if(tmp.size()>=5){
                for(int i=0;i<tmp.size()-4;i++){
                    List<Integer>list=tmp.subList(index+i,index+i+5);
                    ArrayList<Integer>tmpList=new ArrayList<Integer>(list);
                    CardGroup group=new CardGroup(tmpList);
                    if(group.getMaxCard()>LastPlayerGroup.getMaxCard()){
                        cards_list.removeAll(tmpList);
                        return tmpList;
                    }
                }
            }

            index=cur+1;
        }

        return new ArrayList<>();
    }

    public ArrayList<Integer> findC11111Five(ArrayList<Integer>LastPlayerCards){
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);

        int index=0;
        while(index<cards_list.size()-4){
            int cur=index;
            ArrayList<Integer>tmp=new ArrayList<>();
            while(cur+1<cards_list.size()&&transferToPattern(cards_list.get(cur))==transferToPattern(cards_list.get(cur+1))){
                tmp.add(cards_list.get(cur++));
            }
            tmp.add(cards_list.get(cur));

            if(tmp.size()>=5){
                for(int i=0;i<tmp.size()-4;i++){
                    List<Integer>list=tmp.subList(index+i,index+i+5);
                    ArrayList<Integer>tmpList=new ArrayList<Integer>(list);
                    if(isContinuous(tmpList)){
                        CardGroup group=new CardGroup(tmpList);
                        if(group.getMaxCard()>LastPlayerGroup.getMaxCard()){
                            cards_list.removeAll(tmpList);
                            return tmpList;
                        }
                    }
                }
            }

            index=cur+1;
        }

        return new ArrayList<>();
    }

    public ArrayList<Integer> findMinC32(ArrayList<Integer>LastPlayerCards){
        ArrayList<Integer>curPlayerCards=new ArrayList<>();
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);

        ArrayList<Integer>c3Cards= findMinC3(LastPlayerCards);

        if(c3Cards.size()==0)return curPlayerCards;

        ArrayList<Integer>cards=cards_list;
        Collections.sort(cards);

        cards.remove(c3Cards.get(0));
        cards.remove(c3Cards.get(1));
        cards.remove(c3Cards.get(2));

        int index=0;
        ArrayList<Integer>c2Cards=new ArrayList<>();
        while(index<cards.size()-1){
            int card1=transferToNum(cards.get(index));
            int card2=transferToNum(cards.get(index+1));
            if(card1==card2){
                c2Cards.add(cards.get(index));
                c2Cards.add(cards.get(index+1));
                break;
            }
            index++;
        }

        if(c2Cards.size()>0){
            curPlayerCards.addAll(c3Cards);
            curPlayerCards.addAll(c2Cards);
            cards_list.removeAll(c3Cards);
            cards_list.removeAll(c2Cards);
        }

        return curPlayerCards;
    }

    public ArrayList<Integer> findMinC41(ArrayList<Integer>LastPlayerCards){
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);
        ArrayList<Integer>curPlayerCards=new ArrayList<>();

        ArrayList<Integer>cards=cards_list;
        Collections.sort(cards);

        int index=0;
        ArrayList<Integer>c4=new ArrayList<>();
        while(index<cards.size()-3){
            int card1=transferToNum(cards.get(index));
            int card2=transferToNum(cards.get(index+1));
            int card3=transferToNum(cards.get(index+2));
            int card4=transferToNum(cards.get(index+3));
            if(card1==card2&&card2==card3&&card3==card4&&cards.get(index+3)>LastPlayerGroup.getMaxCard()){
                c4.add(cards.get(index));
                c4.add(cards.get(index+1));
                c4.add(cards.get(index+2));
                c4.add(cards.get(index+3));
                break;
            }
        }

        if(c4.size()>0){
            cards.removeAll(c4);
            curPlayerCards.addAll(c4);
            curPlayerCards.add(cards.get(0));
            cards_list.removeAll(curPlayerCards);
        }

        return curPlayerCards;
    }

    public int transferToNum(int n){
        return 3+(n-1)/4;
    }

    //0：方块
    //1：梅花
    //2：红心
    //3：黑心
    public int transferToPattern(int n){
        return (n-1)%4;
    }

    public boolean is345A2(ArrayList<Integer>arr){
        return arr.get(0)==3&&arr.get(1)==4&&arr.get(2)==5&&arr.get(3)==14&&arr.get(4)==15;
    }

    public boolean is34562(ArrayList<Integer>arr){
        return arr.get(0)==3&&arr.get(1)==4&&arr.get(2)==5&&arr.get(3)==6&&arr.get(4)==15;
    }

    public boolean isContinuous(ArrayList<Integer>arr){
        ArrayList<Integer>cardNum=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            cardNum.add(transferToNum(arr.get(i)));
        }

        if(is34562(cardNum)||is345A2(cardNum))return true;

        boolean isContinuous=false;
        for(int i=0;i<cardNum.size()-1;i++){
            if(cardNum.get(i)==cardNum.get(i+1)-1){
                if(i==3)isContinuous=true;
            }else{
                break;
            }
        }

        return isContinuous;
    }

    public boolean isSamePattern(ArrayList<Integer>arr){
        ArrayList<Integer>cardPattern=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            cardPattern.add(transferToPattern(arr.get(i)));
        }

        boolean isSamePattern=true;

        //判断是否同花色
        for (int i = 0; i < cardPattern.size() - 1; i++) {
            if (cardPattern.get(i) != cardPattern.get(i + 1)){
                isSamePattern = false;
                break;
            }
        }

        return isSamePattern;
    }

    public ArrayList<Integer> firstPlay(){
        ArrayList<Integer> containDiamond3=new ArrayList<>();
        ArrayList<Integer>defaultArr=new ArrayList<>();
        defaultArr.add(0);

        containDiamond3=findMinC11111(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        containDiamond3=findMinCFive(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        containDiamond3=findMinC32(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        containDiamond3=findMinC41(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        containDiamond3=findC11111Five(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        containDiamond3=findMinC3(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        containDiamond3=findMinC2(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        containDiamond3=findMinC1(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;

        return containDiamond3;
    }

    public ArrayList<Integer> maxPlayer(){
        ArrayList<Integer> containDiamond3=new ArrayList<>();
        ArrayList<Integer>defaultArr=new ArrayList<>();
        defaultArr.add(0);

        containDiamond3=findMinC11111(defaultArr);
        if(containDiamond3.size()>0)return containDiamond3;
        containDiamond3=findMinCFive(defaultArr);
        if(containDiamond3.size()>0)return containDiamond3;
        containDiamond3=findMinC32(defaultArr);
        if(containDiamond3.size()>0)return containDiamond3;
        containDiamond3=findMinC41(defaultArr);
        if(containDiamond3.size()>0)return containDiamond3;
        containDiamond3=findC11111Five(defaultArr);
        if(containDiamond3.size()>0)return containDiamond3;
        containDiamond3=findMinC3(defaultArr);
        if(containDiamond3.size()>0)return containDiamond3;
        containDiamond3=findMinC2(defaultArr);
        if(containDiamond3.size()>0)return containDiamond3;
        containDiamond3=findMinC1(defaultArr);
        if(containDiamond3.size()>0)return containDiamond3;

        return containDiamond3;
    }
}
