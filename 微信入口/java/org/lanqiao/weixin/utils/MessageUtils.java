package org.lanqiao.weixin.utils;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lanqiao.bean.Image;
import org.lanqiao.bean.ImageMessage;
import org.lanqiao.bean.TextMessage;
import sun.plugin2.message.Message;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtils {
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_EVENT_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_IMAGE = "image";

    public static final String MESSAGE_EVENT_VIEW= "view";
    public static final String MESSAGE_EVENT_CLICK= "click";

    public static final String MESSAGE_EVENT_SCANCODE_PUSH= "scancode_push";
    public static final String MESSAGE_EVENT_LOCATION_SELECT= "location_select";
    public static final String MESSAGE_EVENT_LOCATION= "location";

    public static Map<String , String> xml2Map(HttpServletRequest request) throws IOException, DocumentException {
        Map<String , String> map = new HashMap<>();
        ServletInputStream inputStream = request.getInputStream();
        //创建saxreader对象解析xml
        SAXReader reader = new SAXReader();
        //读取xml文件
        Document document = reader.read(inputStream);
        //获取根节点---xml
        Element root = document.getRootElement();
        //获取根节点里的子节点
        List<Element> elements = root.elements();

        for(Element e : elements){
            map.put(e.getName() , e.getText());
        }
        return map;
    }

    public static String text2XML(TextMessage message){
        XStream xStream = new XStream();
        xStream.alias("xml",TextMessage.class);
        return xStream.toXML(message);
    }

    /*
    订阅是回复用户的内容
     */
    public static String subscribeText(){
        StringBuilder sb = new StringBuilder();
        sb.append("欢迎来到天天二手房中介!\n");


        return sb.toString();
    }

    public static String getMessage(String fromUserName , String toUserName , String content){
        TextMessage message = new TextMessage();

        message.setToUserName(fromUserName);
        message.setFromUserName(toUserName);
        message.setCreateTime(new Date().getTime()+"");
        message.setMsgType(MessageUtils.MESSAGE_TEXT);
        message.setContent(content);

        return MessageUtils.text2XML(message);
    }

    //qX31vWCSLgrGq4ftqZ6B95KoYata2pLziceI8vrb7h95JR7LRTkqJkHmDIqL0IYQ

    /*
    将图片消息转成xml
     */
    public static String image2XML(ImageMessage message){
        XStream xStream = new XStream();
        xStream.alias("xml",ImageMessage.class);
        xStream.alias("Image", Image.class);

        return xStream.toXML(message);
    }

    public static String initImageMessage(String fromUserName , String toUserName){
        Image image = new Image();
        image.setMediaId("qX31vWCSLgrGq4ftqZ6B95KoYata2pLziceI8vrb7h95JR7LRTkqJkHmDIqL0IYQ");

        ImageMessage message = new ImageMessage();
        message.setFromUserName(toUserName);
        message.setToUserName(fromUserName);
        message.setImage(image);
        message.setCreateTime(new Date().getTime()+"");
        message.setMsgType(MESSAGE_IMAGE);

        return image2XML(message);
    }
}
