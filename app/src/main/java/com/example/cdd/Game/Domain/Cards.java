package com.example.cdd.Game.Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//卡牌类，一共有52张卡牌
public class Cards {

    //number_TO_card用于存放牌的大小序号对应的牌的花色数字
    //例如：“♦3”对应的序号是1，因为“♦3”在锄大地中最小
    //例如：“♠2”对应的序号是52，因为“♠2”在锄大地中最大
    static HashMap<Integer,String> serial_number_TO_card=new HashMap<>();
    //最终结果：
    // 1=♦3, 2=♣3, 3=♥3, 4=♠3,
    // 5=♦4, 6=♣4, 7=♥4, 8=♠4,
    // 9=♦5, 10=♣5, 11=♥5, 12=♠5,
    // 13=♦6, 14=♣6, 15=♥6, 16=♠6,
    // 17=♦7, 18=♣7, 19=♥7, 20=♠7,
    // 21=♦8, 22=♣8, 23=♥8, 24=♠8,
    // 25=♦9, 26=♣9, 27=♥9, 28=♠9,
    // 29=♦10, 30=♣10, 31=♥10, 32=♠10,
    // 33=♦J, 34=♣J, 35=♥J, 36=♠J,
    // 37=♦Q, 38=♣Q, 39=♥Q, 40=♠Q,
    // 41=♦k, 42=♣k, 43=♥k, 44=♠k,
    // 45=♦A, 46=♣A, 47=♥A, 48=♠A,
    // 49=♦2, 50=♣2, 51=♥2, 52=♠2

    //存放牌打乱之后的序号
    public static ArrayList<Integer> serial_number=new ArrayList<>();

    //准备牌
    //静态模块，实际上实现了构造函数的功能
    static
    {
        String[] color={"♦","♣","♥","♠"};
        String[] number={"3","4","5","6","7","8","9","10","J","Q","k","A","2"};

        int num=1;
        for(String n : number)
        {
            for (String c:color)
            {
                serial_number_TO_card.put(num,c+n);
                serial_number.add(num);
                num++;
            }
        }
    }

    Cards()
    {


    }

    void show_map()
    {
        System.out.println(serial_number_TO_card);
    }
    void show_serial_number(){System.out.println(serial_number);}
}

