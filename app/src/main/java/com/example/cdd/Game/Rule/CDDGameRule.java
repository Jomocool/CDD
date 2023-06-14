package com.example.cdd.Game.Rule;

import com.example.cdd.Game.Domain.CardGroup;
import com.example.cdd.Game.Domain.PokerType;

public class CDDGameRule extends GameRule{
    //比较牌大小
    public static boolean judge(CardGroup playerCards, CardGroup deskCards){
        if (playerCards.getType() == PokerType.cError) return false;

        if (deskCards.getType() == PokerType.cDefault) return true;

        if (playerCards.getCards().size() != deskCards.getCards().size()) return false;
        if (playerCards.getType() == deskCards.getType()) {
            return playerCards.getMaxCard() > deskCards.getMaxCard();
        } else {
            if (playerCards.getType() == PokerType.c11111Five) return true;
            else if (playerCards.getType() == PokerType.c41) return deskCards.getType() != PokerType.c11111Five;
            else if (playerCards.getType() == PokerType.c32)
                return deskCards.getType() != PokerType.c11111Five && deskCards.getType() != PokerType.c41;
            else if (playerCards.getType() == PokerType.cFive) return deskCards.getType() == PokerType.c11111;
            else return false;
        }
    }
}
