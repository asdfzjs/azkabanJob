/**
 * Project Name:HiveExec
 * File Name:unlock.java
 * Package Name:com.zillionfortune.hiveExec.main
 * Date:2016年12月19日下午3:09:22
*/

package com.zillionfortune.hiveExec.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;

/**
 * ClassName:unlock <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年12月19日 下午3:09:22 <br/>
 * 
 * @author zhujisong
 * @version
 * @since JDK 1.6
 * @see
 */
public class unlock {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	private static Connection con;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(unlock.class);
	static {
		try {
			con = DriverManager.getConnection("jdbc:hive2://192.168.210.66:10000/default", "root", "zbjf@123");
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) throws SQLException, ParseException {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Statement stmt = con.createStatement();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd"); 
		Date now=new Date();
		String sql = "insert into table staging.unlock values (\'"+myFmt.format(now)+"\')";
		stmt.execute(sql);
		System.out.println("success");
	}
}
