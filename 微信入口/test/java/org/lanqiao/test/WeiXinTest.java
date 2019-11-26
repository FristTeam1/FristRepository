package org.lanqiao.test;

import org.junit.Test;
import org.lanqiao.weixin.bean.AccessToken;
import org.lanqiao.weixin.utils.NetUtils;


import java.io.IOException;

public class WeiXinTest {
    @Test
    public void test1() throws IOException {
        AccessToken token = NetUtils.getAccessToken();
        System.out.println(token.getAccess_token());
        System.out.println(token.getExpires_in());
    }
    @Test
    public void test2() throws IOException {
        NetUtils.createMenu(NetUtils.getAccessToken());
    }
    @Test
    public void test4() throws IOException {
        NetUtils.deleteMenu(NetUtils.getAccessToken());

    }
}
