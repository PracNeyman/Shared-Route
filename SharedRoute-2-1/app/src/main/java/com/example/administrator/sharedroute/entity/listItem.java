package com.example.administrator.sharedroute.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.LinearLayout;

/**
 * Created by 王烨臻 on 2017/10/1.
 */

public class listItem implements Parcelable{
    private String expressType;
    private String expressSize;
    private String inTimeStamp;//取件时间
    private String inLocation;//取件地点
    private String outLocation;//送件地点
    private String outTimeStamp;//送件时间
    private double price;//价格
    private boolean isCheckBoxElected;
    public LinearLayout item_bg;

    public boolean isCheckBoxElected() {
        return isCheckBoxElected;
    }

    public void setCheckBoxElected(boolean checkBoxElected) {
        isCheckBoxElected = checkBoxElected;
    }


    public listItem(String expressType, String expressSize, String inTimeStamp, String outTimeStamp, String inLocation, String outLocation, double price){
        this.expressSize=expressSize;
        this.expressType=expressType;
        this.inTimeStamp=inTimeStamp;
        this.outTimeStamp=outTimeStamp;
        this.inLocation=inLocation;
        this.outLocation=outLocation;
        this.price=price;
    }
    public listItem(String expressType, String expressSize, String inTimeStamp, String outTimeStamp, String inLocation, String outLocation, double price, Boolean radioButton){
        this.expressSize=expressSize;
        this.expressType=expressType;
        this.inTimeStamp=inTimeStamp;
        this.outTimeStamp=outTimeStamp;
        this.inLocation=inLocation;
        this.outLocation=outLocation;
        this.price=price;
        this.isCheckBoxElected = radioButton;
    }

    public listItem(listItem item){
        this.expressSize=item.getExpressSize();
        this.expressType=item.getExpressType();
        this.inTimeStamp=item.getInTimeStamp();
        this.outTimeStamp=item.getOutTimeStamp();
        this.inLocation=item.getInLocation();
        this.outLocation=item.getOutLocation();
        this.price=item.findPrice();
        this.isCheckBoxElected = item.isCheckBoxElected();
    }
    public listItem(){

    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public String getExpressSize() {
        return expressSize;
    }

    public void setExpressSize(String expressSize) {
        this.expressSize = expressSize;
    }

    public String getInTimeStamp() {
        return inTimeStamp;
    }

    public void setInTimeStamp(String inTimeStamp) {
        this.inTimeStamp = inTimeStamp;
    }

    public String getOutTimeStamp() {
        return outTimeStamp;
    }

    public void setOutTimeStamp(String outTimeStamp) {
        this.outTimeStamp = outTimeStamp;
    }

    public String getInLocation() {
        return inLocation;
    }

    public void setInLocation(String inLocation) {
        this.inLocation = inLocation;
    }

    public String getOutLocation() {
        return outLocation;
    }

    public void setOutLocation(String outLocation) {
        this.outLocation = outLocation;
    }

    public String getPrice() {
        return String.valueOf(price)+"元";
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double findPrice(){
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(expressType);
            dest.writeString(expressSize);
            dest.writeString(inTimeStamp);
            dest.writeString(inLocation);
            dest.writeString(outLocation);
            dest.writeString(outTimeStamp);
            dest.writeDouble(price);
    }
    public static final Creator<listItem> CREATOR = new Creator(){
        @Override
        public Object createFromParcel(Parcel source) {
            listItem item = new listItem();
            item.setExpressType(source.readString());
            item.setExpressSize(source.readString());
            item.setInTimeStamp(source.readString());
            item.setInLocation(source.readString());
            item.setOutLocation(source.readString());
            item.setOutTimeStamp(source.readString());
            item.setPrice(source.readDouble());
            return item;
        }

        @Override
        public listItem[] newArray(int size) {
            return new listItem[size];
        }
    };

    }
