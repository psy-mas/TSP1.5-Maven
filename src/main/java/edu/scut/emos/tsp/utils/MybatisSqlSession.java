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

	private static String resource = "mybatis-config.xml";

	private static SqlSessionFactory sqlSessionFactory;

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

	public static synchronized SqlSession openSession() {
		return getSqlSessionFactory().openSession();
	}
}
