package com.example.cdd.Game.UI;

import android.content.Context;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//本类是用来记录一张牌对应的图片资源以及它的各种属性
public class CardImage extends androidx.appcompat.widget.AppCompatImageView implements Cloneable {

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

    public CardImage(Context context,int s,int Max_width)
    {
        super(context);
        serial_number=s;
        isSelected=false;

        //设置布局方式
        setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        setMaxWidth(Max_width);
        setAdjustViewBounds(true);

        //添加资源图片
        int resId=getResources().getIdentifier("card"+Integer.toString(s),"mipmap",context.getPackageName());
        setImageResource(resId);
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

    //深拷贝
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    //属性
    private boolean isSelected;//是否被选择
    private int serial_number;//牌的序号
}
