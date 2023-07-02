package com.example.cdd.Game.Domain;

import com.example.cdd.Game.Rule.CDDGameRule;

import java.util.ArrayList;
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
                return findMinC11111Five(LastPlayerCards);
            case c41:
                tmp=findMinC41(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                return findMinC11111Five(LastPlayerCards);
            case c32:
                tmp=findMinC32(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinC41(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                return findMinC11111Five(LastPlayerCards);
            case cFive:
                tmp=findMinCFive(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinC32(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinC41(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                return findMinC11111Five(LastPlayerCards);
            case c11111:
                tmp=findMinC11111(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinCFive(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinC32(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                tmp=findMinC41(LastPlayerCards);
                if(tmp.size()>0)return tmp;
                return findMinC11111Five(LastPlayerCards);
        }
        return tmp;
    }

    //传入的是当前需要压的牌组
    //假设当前需要压的牌型是单牌，就找比其大且是机器人所有牌中最小的单牌；后面找其他牌型也类似
    //如果要得起，返回存有牌的数组并更新牌组；如果要不起，返回空数组
    public ArrayList<Integer> findMinC1(ArrayList<Integer>LastPlayerCards){
        ArrayList<Integer>curPlayerCards=new ArrayList<>();
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);

        ArrayList<Integer>cards=new ArrayList<>(cards_list);

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

        ArrayList<Integer>cards=new ArrayList<>(cards_list);

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

        ArrayList<Integer>cards=new ArrayList<>(cards_list);

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

        ArrayList<Integer>cards=new ArrayList<>(cards_list);

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
                            if(isContinuous(tmp)&& CDDGameRule.judge(group,LastPlayerGroup)){
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

        ArrayList<Integer>cards=new ArrayList<>(cards_list);
        suits_sort(cards);

        int index=0;
        while(index<cards.size()-4){
            int cur=index;
            ArrayList<Integer>tmp=new ArrayList<>();
            while(cur+1<cards.size()&&transferToPattern(cards.get(cur))==transferToPattern(cards.get(cur+1))){
                tmp.add(cards.get(cur++));
            }
            tmp.add(cards.get(cur));

            if(tmp.size()>=5){
                for(int i=0;i<tmp.size()-4;i++){
                    List<Integer>list=tmp.subList(i,i+5);
                    ArrayList<Integer>tmpList=new ArrayList<Integer>(list);
                    CardGroup group=new CardGroup(tmpList);
                    if(CDDGameRule.judge(group,LastPlayerGroup)){
                        cards_list.removeAll(tmpList);
                        return tmpList;
                    }
                }
            }

            index=cur+1;
        }

        return new ArrayList<>();
    }

    public ArrayList<Integer> findMinC11111Five(ArrayList<Integer>LastPlayerCards){
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);

        ArrayList<Integer>cards=new ArrayList<>(cards_list);
        suits_sort(cards);

        int index=0;
        while(index<cards.size()-4){
            int cur=index;
            ArrayList<Integer>tmp=new ArrayList<>();
            while(cur+1<cards.size()&&transferToPattern(cards.get(cur))==transferToPattern(cards.get(cur+1))){
                tmp.add(cards.get(cur++));
            }
            tmp.add(cards.get(cur));

            if(tmp.size()>=5){
                for(int i=0;i<tmp.size()-4;i++){
                    List<Integer>list=tmp.subList(i,i+5);
                    ArrayList<Integer>tmpList=new ArrayList<Integer>(list);
                    if(isContinuous(tmpList)){
                        CardGroup group=new CardGroup(tmpList);
                        if(CDDGameRule.judge(group,LastPlayerGroup)){
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

        ArrayList<Integer>cards=new ArrayList<>(cards_list);

        if(cards.get(cards.size()-1)<LastPlayerGroup.getMaxCard()){
            return curPlayerCards;
        }

        int index=0;
        while(index<cards.size()-2){
            int card1=transferToNum(cards.get(index));
            int card2=transferToNum(cards.get(index+1));
            int card3=transferToNum(cards.get(index+2));
            if(card1==card2&&card2==card3){
                ArrayList<Integer>tmp_Arr=new ArrayList<>(cards);
                tmp_Arr.remove(cards.get(index));
                tmp_Arr.remove(cards.get(index+1));
                tmp_Arr.remove(cards.get(index+2));

                for(int i=0;i<tmp_Arr.size()-1;i++){
                    if(transferToNum(tmp_Arr.get(i))==transferToNum((tmp_Arr.get(i+1)))){
                        curPlayerCards.add(cards.get(index));
                        curPlayerCards.add(cards.get(index+1));
                        curPlayerCards.add(cards.get(index+2));
                        curPlayerCards.add(tmp_Arr.get(i));
                        curPlayerCards.add(tmp_Arr.get(i+1));
                        break;
                    }
                }

                CardGroup group=new CardGroup(curPlayerCards);
                if(CDDGameRule.judge(group,LastPlayerGroup)){
                    cards_list.removeAll(curPlayerCards);
                    return curPlayerCards;
                }
                curPlayerCards.clear();
            }
            index++;
        }

        return new ArrayList<>();
    }

    public ArrayList<Integer> findMinC41(ArrayList<Integer>LastPlayerCards){
        CardGroup LastPlayerGroup=new CardGroup(LastPlayerCards);
        ArrayList<Integer>curPlayerCards=new ArrayList<>();

        ArrayList<Integer>cards=new ArrayList<>(cards_list);

        int index=0;
        ArrayList<Integer>c4=new ArrayList<>();
        while(index<cards.size()-3){
            int card1=transferToNum(cards.get(index));
            int card2=transferToNum(cards.get(index+1));
            int card3=transferToNum(cards.get(index+2));
            int card4=transferToNum(cards.get(index+3));
            if(card1==card2&&card2==card3&&card3==card4){
                c4.add(cards.get(index));
                c4.add(cards.get(index+1));
                c4.add(cards.get(index+2));
                c4.add(cards.get(index+3));
                break;
            }
            index++;
        }

        if(c4.size()>0){
            cards.removeAll(c4);
            curPlayerCards.addAll(c4);
            curPlayerCards.add(cards.get(0));

            CardGroup group=new CardGroup(curPlayerCards);
            if(CDDGameRule.judge(group,LastPlayerGroup)){
                cards_list.removeAll(curPlayerCards);
                return curPlayerCards;
            }
        }

        return new ArrayList<>();
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

    public ArrayList<Integer> firstPlay(){
        ArrayList<Integer> containDiamond3=new ArrayList<>();
        ArrayList<Integer>defaultArr=new ArrayList<>();
        defaultArr.add(0);

        containDiamond3=findMinC11111(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        else if(containDiamond3.size()>0&&!containDiamond3.contains(1))cards_list.addAll(containDiamond3);

        containDiamond3=findMinCFive(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        else if(containDiamond3.size()>0&&!containDiamond3.contains(1))cards_list.addAll(containDiamond3);

        containDiamond3=findMinC32(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        else if(containDiamond3.size()>0&&!containDiamond3.contains(1))cards_list.addAll(containDiamond3);

        containDiamond3=findMinC41(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        else if(containDiamond3.size()>0&&!containDiamond3.contains(1))cards_list.addAll(containDiamond3);

        containDiamond3= findMinC11111Five(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        else if(containDiamond3.size()>0&&!containDiamond3.contains(1))cards_list.addAll(containDiamond3);

        containDiamond3=findMinC3(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        else if(containDiamond3.size()>0&&!containDiamond3.contains(1))cards_list.addAll(containDiamond3);

        containDiamond3=findMinC2(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        else if(containDiamond3.size()>0&&!containDiamond3.contains(1))cards_list.addAll(containDiamond3);

        containDiamond3=findMinC1(defaultArr);
        if(containDiamond3.size()>0&&containDiamond3.contains(1))return containDiamond3;
        else if(containDiamond3.size()>0&&!containDiamond3.contains(1))cards_list.addAll(containDiamond3);

        return new ArrayList<>();
    }

    public ArrayList<Integer> maxPlayer(){
        ArrayList<Integer> maxNumCards=new ArrayList<>();
        ArrayList<Integer>defaultArr=new ArrayList<>();
        defaultArr.add(0);

        maxNumCards=findMinC11111(defaultArr);
        if(maxNumCards.size()>0)return maxNumCards;
        maxNumCards=findMinCFive(defaultArr);
        if(maxNumCards.size()>0)return maxNumCards;
        maxNumCards=findMinC32(defaultArr);
        if(maxNumCards.size()>0)return maxNumCards;
        maxNumCards=findMinC41(defaultArr);
        if(maxNumCards.size()>0)return maxNumCards;
        maxNumCards= findMinC11111Five(defaultArr);
        if(maxNumCards.size()>0)return maxNumCards;
        maxNumCards=findMinC3(defaultArr);
        if(maxNumCards.size()>0)return maxNumCards;
        maxNumCards=findMinC2(defaultArr);
        if(maxNumCards.size()>0)return maxNumCards;
        maxNumCards=findMinC1(defaultArr);
        if(maxNumCards.size()>0)return maxNumCards;

        return maxNumCards;
    }
}
