package utils;


import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBCUtils {
    private static DruidDataSource ds=new DruidDataSource();
    static {
        try {
            Properties prop=new Properties();
            InputStream a=JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
            prop.load(a);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            ds.setDriverClassName(driver);
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);
            ds.setInitialSize(10);
            ds.setMaxActive(20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static DataSource getDs(){
        return ds;
    }
}
