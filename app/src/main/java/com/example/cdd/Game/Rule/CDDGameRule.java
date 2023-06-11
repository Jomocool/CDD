package com.example.cdd.Game.Rule;

import com.example.cdd.Game.Domain.CardGroup;
import com.example.cdd.Game.Domain.PokerType;

public class CDDGameRule extends GameRule{
    //比较牌大小
    public static boolean judge(CardGroup playerCards, CardGroup deskCards){
        //不是相同数量的牌组无法比较，如果牌型无效也无法比较，由于已经打出的牌deskCards肯定是有效的，所以不用做额外判断
        return  playerCards.getType()!= PokerType.cError&&
                playerCards.getCards().size()==deskCards.getCards().size()&&
                playerCards.getMaxCard()>deskCards.getMaxCard();
    }
}
