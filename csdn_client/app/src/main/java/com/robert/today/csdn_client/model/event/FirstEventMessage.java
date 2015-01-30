package com.robert.today.csdn_client.model.event;

/**
 * Created by chenjun06 on 2015/1/30.
 */
public class FirstEventMessage {

    private boolean isShowImage = true;

    public FirstEventMessage() {}

    public FirstEventMessage(boolean isShowImage) {
        this.isShowImage = isShowImage;
    }

    public boolean isShowImage() {
        return this.isShowImage;
    }

    public void setShowImage(boolean isShowImage) {
        this.isShowImage = isShowImage;
    }
}
