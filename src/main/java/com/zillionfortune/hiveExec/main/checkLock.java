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
 * ClassName:checkLock <br/>
 * Function: check hive table lock_table exists data. <br/>
 * Reason: if have data, then do DW ETL
 * 
 * @author zhujisong
 * @version
 * @since JDK 1.6
 * @see
 */
public class checkLock {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	private static Connection con;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(checkLock.class);
	static {
		try {
			con = DriverManager.getConnection("jdbc:hive2://192.168.210.66:10000/default", "root	", "zbjf@123");
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) throws SQLException, ParseException {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		Statement stmt = con.createStatement();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date now=new Date();
		String sql = "select * from staging.lock where locktime='"+myFmt.format(now)+"'";
		System.out.println(sql);
		ResultSet res = stmt.executeQuery(sql);
		System.out.println("checklock1111111111111111111"+myFmt2.format(new Date()));
		if(!res.next()){
			System.out.println("0");
			System.exit(1);
		}else{
				 System.out.println("success");
		}
	}
}
