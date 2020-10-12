package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.vendor.GetVendorListParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorListCSVResponse;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorWholeSalerEntityRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RenewalVendorServiceTest {
    @Autowired
    private VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;
    GetVendorListParameter parameter;

    @Before
    public void setUp() {
        parameter = GetVendorListParameter.builder()
                .pageNum(1)
                .pageSize(100)
                .country("ALL")
                .state("ALL")
                .status(0)
                .typeOfContract("0")
                .commission("0")
                .photoPlan("0")
                .chooseType("0")
                .build();

    }

    @Ignore
    @Test
    public void filter_companyname() {
        parameter = parameter.toBuilder().companyName("test").build();
        List<VendorListCSVResponse> list = vendorWholeSalerEntityRepository.getVendorListCSVWithCount(parameter);
        System.out.println(list);
    }

    @Ignore
    @Test
    public void filter_all() {
        List<VendorListCSVResponse> list = vendorWholeSalerEntityRepository.getVendorListCSVWithCount(parameter);
        System.out.println(list);
    }

    @Ignore
    @Test
    public void filter_vendorId_871() {
        parameter.setWholeSalerID(871);
        List<VendorListCSVResponse> list = vendorWholeSalerEntityRepository.getVendorListCSVWithCount(parameter);
        System.out.println(list);
    }
}
