package edu.scut.emos.tsp;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import edu.scut.emos.tsp.dao.mysql.DWindowCommitResultMapper;
import edu.scut.emos.tsp.model.DWindowCommitResult;
import edu.scut.emos.tsp.utils.MybatisSqlSession;

/**
 * @author emos
 *
 */
public class JunitTest {
	private SqlSession s;
	
	@Before
	public void before() {
		s = MybatisSqlSession.openSession();
	}
	
	@Test
	public void test1() {
		System.out.println("this is test1");
		
		byte a;
		boolean b = true;
		a = (byte) (b ? 1 : 0);
		System.out.println(a);
	}
	
	@Test
	public void test2() {
		System.out.println("this is test2");
		
		DWindowCommitResultMapper d = s.getMapper(DWindowCommitResultMapper.class);
		
		DWindowCommitResult dcr = d.selectByPrimaryKey("test");
		
		System.out.println(dcr.getCommitresultid() + dcr.getCommittime());
	}
}
