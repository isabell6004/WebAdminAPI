/**
 * 
 */
package net.fashiongo.webadmin.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.fashiongo.webadmin.model.pojo.parameter.*;
import net.fashiongo.webadmin.model.pojo.response.*;

/**
 * @author sanghyup
 *
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
public class SitemgmtServiceTest {
	

	@Autowired
	SitemgmtService sitemgmtService;
	
	/**
	 * @title Description Example
	 * @since 2018. 10. 1.
	 * @author sanghyup
	 * @return void
	 * @tags @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("SitemgmtServiceTest.setUpBeforeClass");
	}

	/**
	 * @title Description Example
	 * @since 2018. 10. 1.
	 * @author sanghyup
	 * @return void
	 * @tags @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("SitemgmtServiceTest.tearDownAfterClass");
	}

	/**
	 * @title Description Example
	 * @since 2018. 10. 1.
	 * @author sanghyup
	 * @return void
	 * @tags @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("SitemgmtServiceTest.setUp");
	}

	/**
	 * @title Description Example
	 * @since 2018. 10. 1.
	 * @author sanghyup
	 * @return void
	 * @tags @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("SitemgmtServiceTest.tearDown");
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtService#getCollectionCategoryList(net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters)}.
	 */
	@Test
	public void testGetCollectionCategoryList() {
//		fail("Not yet implemented");
		System.out.println("SitemgmtServiceTest.testGetCollectionCategoryList");

		// check collection category list
		GetCollectionCategoryListParameters parameters = new GetCollectionCategoryListParameters();
		parameters.setCategoryId(0);
		parameters.setExpandAll(1);
		GetCollectionCategoryListResponse result = sitemgmtService.getCollectionCategoryList(parameters);

		assertNull(result.getCollectionCategoryList());

		// check collection category detail
		parameters = new GetCollectionCategoryListParameters();
		parameters.setCategoryId(8);
		result = sitemgmtService.getCollectionCategoryList(parameters);

//		assertNull(result.getCollectionCategoryList());
}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtService#getCategoryList(net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters)}.
	 */
	@Test
	public void testGetCategoryList() {
//		fail("Not yet implemented");
		System.out.println("SitemgmtServiceTest.testGetCategoryList");

		// check category list
		GetCategoryListParameters parameters = new GetCategoryListParameters();
		parameters.setCategoryId(0);
		parameters.setExpandAll(0);
		GetCategoryListResponse result = sitemgmtService.getCategoryList(parameters);

//		assertNull(result.getCategorylist());
	}

	/**
	 * Test method for {@link net.fashiongo.webadmin.service.SitemgmtService#setCollectionCategoryListorder(net.fashiongo.webadmin.model.pojo.parameter.SetCollectionCategoryListorderParameters)}.
	 */
	@Test
	public void testSetCollectionCategoryListorder() {
//		fail("Not yet implemented");
		System.out.println("SitemgmtServiceTest.testSetCollectionCategoryListorder");

		// check category list
		SetCollectionCategoryListorderParameters parameters = new SetCollectionCategoryListorderParameters();
		parameters.setCategoryId(9);
		parameters.setListOrder(1);;
		parameters.setLvl(2);
		parameters.setParentCategoryId(1);
		SetCollectionCategoryListorderResponse result = sitemgmtService.setCollectionCategoryListorder(parameters);

//		assertNull(result.getCategoryCollectionlist());
	}

}
