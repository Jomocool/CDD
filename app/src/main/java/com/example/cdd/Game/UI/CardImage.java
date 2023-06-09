package com.example.cdd.Game.UI;

import android.content.Context;
import android.widget.ImageView;

//本类是用来记录一张牌对应的图片资源以及它的各种属性
public class CardImage extends androidx.appcompat.widget.AppCompatImageView {

    public CardImage(Context context)
    {
        super(context);
        isSelected=false;
    }

    public CardImage(Context context,int s)
    {
        super(context);
        serial_number=s;
        isSelected=false;
    }

    //封装的接口
    void select()
    {
        isSelected=true;
    }

    void cancel_select()
    {
        isSelected=false;
    }

    boolean get_isSelected()
    {
        return isSelected;
    }

    void setSerial_number(int s)
    {
        serial_number=s;
    }

    int getSerial_number()
    {
        return serial_number;
    }

    //属性
    private boolean isSelected;//是否被选择
    private int serial_number;//牌的序号
}
