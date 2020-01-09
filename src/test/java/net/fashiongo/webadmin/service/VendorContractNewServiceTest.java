package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.data.entity.primary.VendorContractDocumentEntity;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorContractDocumentEntityRepository;
import net.fashiongo.webadmin.service.vendor.VendorContractNewService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

/**
 * Created by jinwoo on 2019-12-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorContractNewServiceTest {

    @Autowired
    private VendorContractNewService vendorContractNewService;

    @Autowired
    private VendorContractDocumentEntityRepository vendorContractDocumentEntityRepository;

    @Test
    public void modify_vendor_contract_document_using_new_api() {

        SetVendorContractDocumentParameter request = new SetVendorContractDocumentParameter();
        request.setVendorContractDocumentID(2649);
        request.setVendorContractID(517);
        request.setDocumentTypeID(1);
        request.setFileName("test");
        request.setFileName2("test1");
        request.setFileName3("test2");
        request.setNote("test");
        request.setReceivedBy("developer");

        VendorContractDocumentEntity documentEntity = vendorContractDocumentEntityRepository.findOneByVendorContractDocumentID(request.getVendorContractDocumentID());
        Assert.assertNotNull(documentEntity);
        documentEntity.modifyEntity(request);

        Assert.assertEquals("test", documentEntity.getFileName());
        Assert.assertEquals("test1", documentEntity.getFileName2());
        Assert.assertEquals("test2", documentEntity.getFileName3());

//        vendorContractNewService.modifyVendorContractDocument(request);
    }

//    private void createAndModifyVendorContractHistory() {
//        vendorContractNewService.createAndModifyVendorContractHistory();
//    }
//
//    private void deleteVendorContractDocument() {
//        vendorContractNewService.deleteVendorContractDocument();
//    }
//
//    private void createAndModifyVendorContractHistory() {
//        vendorContractNewService.createAndModifyVendorContractHistory();
//    }
}
