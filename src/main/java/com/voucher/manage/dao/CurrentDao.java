package com.voucher.manage.dao;

import java.sql.SQLException;
import java.util.Map;

public interface CurrentDao {

   Map selectTable(Object object) throws ClassNotFoundException, SQLException;

    Map selectTable(Object[] objects, String[][] joinParameters) throws ClassNotFoundException, SQLException;

    Integer insertTable(Object object, String val) throws ClassNotFoundException;

    Integer updateTable(String tableName, String line_uuid, String value);

}
