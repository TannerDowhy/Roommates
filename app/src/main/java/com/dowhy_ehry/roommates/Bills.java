package com.dowhy_ehry.roommates;

import android.widget.ImageView;

/**
 * Created by Peter on 2016-11-27.
 */

public class Bills {

    private String name;
    private float amount;
    private ImageView billPic;

    public Bills(String nName, float nAmount){
        setName(nName);
        setAmount(nAmount);
    }

    public Bills(String nName, float nAmount, ImageView nImage){
        setName(nName);
        setAmount(nAmount);
        setBillPic(nImage);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String nName){
        this.name = nName;
    }

    public float getAmount(){
        return this.amount;
    }

    public void setAmount(float nAmount){
        this.amount = nAmount;
    }

    public ImageView getBillPic(){
        return this.billPic;
    }

    public void setBillPic(ImageView nPic){
        this.billPic = nPic;
    }


}
