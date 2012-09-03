package com.elf.n2c.dao;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: laichendong
 * Date: 12-8-31
 * Time: 下午4:46
 */
public class MongoUtil {
	private static Logger logger = Logger.getLogger(MongoUtil.class);
	private static Mongo m;
	private static String[][] servers = {{"localhost", "27017"}, {"localhost", "27017"}};
	/**
	 * 京东数据库名称
	 */
	public static final String DB_360BUY = "jd";
	/**
	 * 亚马逊数据库名称
	 */
	public static final String DB_AMAZON = "amazon";
	/**
	 * 当当数据库名称
	 */
	public static final String DB_DANGDANG = "dd";

	static {
		List<ServerAddress> addressList = new ArrayList<ServerAddress>();
		for (String[] server : servers) {
			try {
				addressList.add(new ServerAddress(server[0], Integer.parseInt(server[1])));
			} catch (UnknownHostException e) {
				logger.error("unknown server:" + Arrays.toString(servers), e);
			}
		}
		m = new Mongo(addressList);
	}

	/**
	 * 获取指定名称的DB
	 *
	 * @param dbName 数据库名称，使用常量
	 * @return DB 对象
	 */
	public static DB getDb(String dbName) {
		assert m != null;
		return m.getDB(dbName);
	}
}
