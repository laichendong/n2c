package com.elf.n2c.spider;

import com.elf.n2c.dao.MongoUtil;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * User: laichendong
 * Date: 12-8-30
 * Time: 下午3:47
 */
public class Spider4Jd {

	static Logger logger = Logger.getLogger(Spider4Jd.class);

	public byte[] fetch(String url) {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				logger.error("Method failed: " + getMethod.getStatusLine());
			}
			return getMethod.getResponseBody();
			//处理内容
		} catch (HttpException e) {
			logger.warn("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			//发生网络异常
			e.printStackTrace();
		} finally {
			//释放连接
			getMethod.releaseConnection();
		}
		return null;
	}

	public void save(byte[] file, String url) {
		DB db = MongoUtil.getDb(MongoUtil.DB_360BUY);
		GridFS gridFS = new GridFS(db);
		GridFSInputFile gridFSInputFile = gridFS.createFile(file);
		gridFSInputFile.setFilename(url);
		gridFSInputFile.save();
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		String[] urls = {"http://www.360buy.com/product/520245.html",
				"http://www.360buy.com/product/570306.html",
				"http://www.360buy.com/product/584648.html",
				"http://www.360buy.com/product/584651.html",
				"http://www.360buy.com/product/632744.html"};
		Spider4Jd cf = new Spider4Jd();
		for (String url : urls) {
			logger.info("fetch the page:" + url);
			cf.save(cf.fetch(url), url);
		}
		logger.info("time spend:" + (System.currentTimeMillis() - t) + "ms");
	}
}
