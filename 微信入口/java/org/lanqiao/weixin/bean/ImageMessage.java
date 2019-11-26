package org.lanqiao.weixin.bean;

import org.lanqiao.bean.BaseMessage;
import org.lanqiao.bean.Image;

public class ImageMessage extends BaseMessage {
    private org.lanqiao.bean.Image Image;

    public org.lanqiao.bean.Image getImage() {
        return Image;
    }

    public void setImage(org.lanqiao.bean.Image image) {
        Image = image;
    }
}
