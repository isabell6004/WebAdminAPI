package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.TotalRateTotal;
import net.fashiongo.webadmin.model.pojo.VisitorStatic;
import net.fashiongo.webadmin.model.pojo.response.GetDashboardResponse;
/**
 * 
 * @author Incheol Jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StaticServiceTest {

	@Autowired
	StaticService staticService;
	
	/**
	 * 
	 * Test GetDashboard
	 * 
	 * @since 2018. 10. 22.
	 * @author Incheol Jung
	 */
	@Test
	public void TestGetDashboard() {
		GetDashboardResponse result = this.staticService.getDashboard();
		
		VisitorStatic visitors = result.getVisitors();
		if(visitors != null) {
			TotalRateTotal totalRate = visitors.getTotalVisitors();
			if(totalRate.getTotal() != null) {
				assertNotNull(totalRate.getTotal());
			}
		}
	}
}