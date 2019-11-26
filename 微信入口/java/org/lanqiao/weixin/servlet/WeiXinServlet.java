package org.lanqiao.weixin.servlet;

import org.dom4j.DocumentException;
import org.lanqiao.weixin.utils.CheckSignature;
import org.lanqiao.weixin.utils.MessageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/WeiXinServlet")
public class WeiXinServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        try {
            //将微信传过来的xml转成map
            Map<String, String> map = MessageUtils.xml2Map(request);

            System.out.println(map);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");

            String massage = null;
            if(MessageUtils.MESSAGE_TEXT.equals(msgType)){
                //回复1
                String content = map.get("Content");
                if("1".equals(content)){
                    massage = MessageUtils.getMessage(fromUserName,toUserName,"天天中介");
                }else if("2".equals(content)){
                    massage = MessageUtils.getMessage(fromUserName,toUserName,"优质房源");
                }else if("3".equals(content)){
                    //回复图片消息
                    massage = MessageUtils.initImageMessage(fromUserName,toUserName);
                }
            }else if(MessageUtils.MESSAGE_EVENT.equals(msgType)){
                String event = map.get("Event");
                if(MessageUtils.MESSAGE_EVENT_SUBSCRIBE.equals(event)){
                    //给微信回复消息
                    massage = MessageUtils.getMessage(fromUserName,toUserName,MessageUtils.subscribeText());
                }
            }
            out.print(massage);

        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //将token、timestamp、nonce三个参数进行字典序排序
        String token = "loneliness";

        String signature1 = CheckSignature.checkSignature(token, timestamp, nonce);

        if(signature1.equals(signature)){
            response.getWriter().print(echostr);
        }
    }
}
