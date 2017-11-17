package edu.scut.emos.tsp.utils;
/**
 * @author Qinzheng
 */

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 维护一个单例SqlSessionFactory
 * 
 * @author Qinzheng
 *
 */
public class MybatisSqlSession {

	/**
	 * 定义资源文件位置
	 */
	private static String resource = "mybatis-config.xml";

	/**
	 * 单例SqlSessionFactory
	 */
	private static SqlSessionFactory sqlSessionFactory;

	/**
	 * 获取单例SqlSessionFactory
	 * 
	 * @return SqlSessionFactory
	 */
	public static synchronized SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sqlSessionFactory;
	}

	/**
	 * 获取openSession
	 * 
	 * @return SqlSession
	 */
	public static SqlSession openSession() {
		return getSqlSessionFactory().openSession();
	}
}
