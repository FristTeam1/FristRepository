package Dao;

import bean.House;
import org.apache.commons.dbutils.QueryRunner;
import utils.JDBCUtils;

import java.sql.SQLException;

public class HouseDao {
   public static QueryRunner qr=new QueryRunner(JDBCUtils.getDs());

    public static int add(House house) throws SQLException {
        String sql="insert into houses VALUES(NULL,?,?,?,?,?,?,?,?,?);";
        String[] params={house.getFaddress(),house.getFx(),house.getFarea(),house.getPicture1(),house.getPicture2(),house.getPicture3(),house.getPicture4(),house.getThing(),house.getPric()};
        int a=qr.update(sql,params);
        System.out.println(a);
        return a;
    }
}
