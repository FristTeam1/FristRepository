package Service;

import Dao.HouseDao;
import bean.House;

import java.sql.SQLException;

public class HouseService {
    public static int add(House house) throws SQLException {
        return HouseDao.add(house);
    }
}
