/**
 * 
 */
package net.fashiongo.webadmin.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.primary.VendorCompany;

/**
 * @author roy
 *
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class VendorServiceTest {
	
	@Autowired
	VendorService vendorService;

	@Test
	public void testGetVendorList() {
		List<VendorCompany> vendorList = vendorService.getVendorList();
		
		assertTrue(vendorList.size() > 0);
		
		VendorCompany vendorCompany = vendorList.get(0);
		
		assertNotNull(vendorCompany);
		assertTrue(vendorCompany.getWholeSalerId() > 0);
		assertTrue(vendorCompany.getCompanyName().length() > 0);
	}

}
