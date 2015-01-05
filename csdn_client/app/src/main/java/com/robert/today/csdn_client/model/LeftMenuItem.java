package com.robert.today.csdn_client.model;

import java.io.Serializable;

/**
 * Created by chenjun06 on 2014/12/26.
 */
public class LeftMenuItem implements Serializable {
    private String textName;
    private int drawableRes;
    private int pos;

    public LeftMenuItem() {
    }

    public LeftMenuItem(String textName) {
        this.textName = textName;
        this.drawableRes = -1;
        this.pos = -1;
    }

    public LeftMenuItem(String textName, int drawableRes, int pos) {
        this.textName = textName;
        this.drawableRes = drawableRes;
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }
}
