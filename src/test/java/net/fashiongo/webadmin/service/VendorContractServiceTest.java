package net.fashiongo.webadmin.service;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.VendorContractHistoryDocumentEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.model.vendor.DelVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.data.repository.primary.VendorContractHistoryDocumentEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.VendorContractHistoryEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorWholeSalerEntityRepository;
import net.fashiongo.webadmin.service.vendor.VendorContractNewService;
import net.fashiongo.webadmin.service.vendor.VendorInfoNewService;
import net.fashiongo.webadmin.service.vendor.impl.VendorContractServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class VendorContractServiceTest {

    @Mock
    private VendorInfoNewService vendorInfoNewService;

    @Mock
    private VendorContractNewService vendorContractNewService;

    @Mock
    private VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;

    @Mock
    private VendorContractHistoryEntityRepository vendorContractHistoryEntityRepository;

    @Mock
    private VendorContractHistoryDocumentEntityRepository vendorContractHistoryDocumentEntityRepository;

    @Mock
    private CacheService cacheService;

    private VendorContractServiceImpl vendorContractService;

    @Before
    public void init() {
        vendorContractService = new VendorContractServiceImpl(
                vendorContractNewService,
                vendorWholeSalerEntityRepository,
                vendorContractHistoryEntityRepository,
                vendorContractHistoryDocumentEntityRepository,
                cacheService);
    }

    @Test
    public void delVendorContractDocument_when_normal_case() {
        String documentIds = "1,2";
        DelVendorContractDocumentParameter request = new DelVendorContractDocumentParameter();
        request.setDocumentHistoryIDs(documentIds);

        VendorContractHistoryDocumentEntity entity1 = new VendorContractHistoryDocumentEntity();
        VendorContractHistoryDocumentEntity entity2 = new VendorContractHistoryDocumentEntity();
        List<VendorContractHistoryDocumentEntity> documents = Arrays.asList(entity1, entity2);

        willDoNothing().given(vendorContractNewService).deleteVendorContractDocument(anyLong(), anyLong(), anyList());
        given(vendorContractHistoryDocumentEntityRepository.findAllById(anyList())).willReturn(documents);
        willDoNothing().given(vendorContractHistoryDocumentEntityRepository).deleteAll(documents);

        try {
            vendorContractService.delVendorContractDocument(request);
        } catch (Throwable t) {
            log.debug(t.getMessage(), t);
            Assert.fail();
        }


        verify(vendorContractNewService, atLeastOnce()).deleteVendorContractDocument(anyLong(), anyLong(), anyList());
        verify(vendorContractHistoryDocumentEntityRepository, atLeast(1)).findAllById(anyList());
        verify(vendorContractHistoryDocumentEntityRepository, atLeast(1)).deleteAll(anyList());
    }

    @Test
    public void delVendorContractDocument_when_throw_runtime_exception() {
        String documentIds = "222,333";
        DelVendorContractDocumentParameter request = new DelVendorContractDocumentParameter();
        request.setDocumentHistoryIDs(documentIds);

        try {
            vendorContractService.delVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }
    }

    @Test
    @Transactional
    public void delVendorContractDocument_when_not_exist_documentIds() {
        String documentIds = "99999,999999";
        DelVendorContractDocumentParameter request = new DelVendorContractDocumentParameter();
        request.setDocumentHistoryIDs(documentIds);

        willDoNothing().given(vendorContractNewService).deleteVendorContractDocument(anyLong(), anyLong(), anyList());

        try {
            vendorContractService.delVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractNewService, atLeastOnce()).deleteVendorContractDocument(anyLong(), anyLong(), anyList());
    }

    @Test
    @Transactional
    public void delVendorContractDocument_when_null_request() {

        String documentIds = "";
        DelVendorContractDocumentParameter request = new DelVendorContractDocumentParameter();
        request.setDocumentHistoryIDs(documentIds);

        willDoNothing().given(vendorContractNewService).deleteVendorContractDocument(anyLong(), anyLong(), anyList());

        // do test
        try {
            vendorContractService.delVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }
        verify(vendorContractNewService, never()).deleteVendorContractDocument(anyLong(), anyLong(), anyList());
    }

    @Test
    public void setVendorContractDocument_when_normal_case_insert() {

        SetVendorContractDocumentParameter request = new SetVendorContractDocumentParameter();
        request.setVendorContractID(1);
        request.setDocumentTypeID(1);
        request.setFileName("test");
        request.setFileName2("test1");
        request.setFileName3("test2");
        request.setNote("test");
        request.setReceivedBy("developer");

        willDoNothing().given(vendorContractNewService).createVendorContractDocument(anyLong(), request);

        try {
            vendorContractService.setVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractNewService, atLeastOnce()).createVendorContractDocument(anyLong(), request);
    }

    @Test
    public void setVendorContractDocument_when_normal_case_insert_exception() {

        SetVendorContractDocumentParameter request = new SetVendorContractDocumentParameter();
        request.setVendorContractID(1);
        request.setDocumentTypeID(1);
        request.setFileName("test");
        request.setFileName2("test1");
        request.setFileName3("test2");
        request.setNote("test");
        request.setReceivedBy("developer");

        willDoNothing().given(vendorContractNewService).createVendorContractDocument(anyLong(), request);

        try {
            vendorContractService.setVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractNewService, never()).createVendorContractDocument(anyLong(), request);
    }

    @Test
    public void setVendorContractDocument_when_normal_case_update() {

        SetVendorContractDocumentParameter request = new SetVendorContractDocumentParameter();
        request.setVendorContractDocumentID(1);
        request.setVendorContractID(1);
        request.setDocumentTypeID(1);
        request.setFileName("test");
        request.setFileName2("test1");
        request.setFileName3("test2");
        request.setNote("test");
        request.setReceivedBy("developer");

        willDoNothing().given(vendorContractNewService).modifyVendorContractDocument(anyLong(), request);

        try {
            vendorContractService.setVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractNewService, atLeastOnce()).modifyVendorContractDocument(anyLong(), request);
    }

    @Test
    public void setVendorContractDocument_when_normal_case_update_exception() {

        SetVendorContractDocumentParameter request = new SetVendorContractDocumentParameter();
        request.setVendorContractDocumentID(1);
        request.setVendorContractID(1);
        request.setDocumentTypeID(1);
        request.setFileName("test");
        request.setFileName2("test1");
        request.setFileName3("test2");
        request.setNote("test");
        request.setReceivedBy("developer");

        willDoNothing().given(vendorContractNewService).modifyVendorContractDocument(anyLong(), request);

        try {
            vendorContractService.setVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractNewService, never()).modifyVendorContractDocument(anyLong(), request);
    }

    @Test
    public void setVendorContract_when_normal_case_insert_contract_exist_origin_contract() {

        SetVendorContractParameter request = new SetVendorContractParameter();
        request.setWholeSalerID(2858);

        given(vendorWholeSalerEntityRepository.findOneByID(anyInt())).willReturn(new WholeSalerEntity());

        try {
            vendorContractService.setVendorContract(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorWholeSalerEntityRepository, atLeastOnce()).findOneByID(anyInt());
    }

    @Test
    public void setVendorContract_when_normal_case_insert_contract_not_exist_origin_contract() {

        SetVendorContractParameter request = new SetVendorContractParameter();
        request.setWholeSalerID(2858);

        given(vendorWholeSalerEntityRepository.findOneByID(anyInt())).willReturn(new WholeSalerEntity());

        try {
            vendorContractService.setVendorContract(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorWholeSalerEntityRepository, atLeastOnce()).findOneByID(anyInt());
    }

    @Test
    public void setVendorContract_when_normal_case_update_contract() {

        SetVendorContractParameter request = new SetVendorContractParameter();
        request.setWholeSalerID(2858);
        request.setVendorContractID(1111);
        request.setContractTypeID(5);

        WholeSalerEntity dummyWholesaler = new WholeSalerEntity();

        given(vendorWholeSalerEntityRepository.findOneByID(anyInt())).willReturn(dummyWholesaler);
        given(vendorWholeSalerEntityRepository.save(any())).willReturn(dummyWholesaler);

        try {
            vendorContractService.setVendorContract(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorWholeSalerEntityRepository, atLeastOnce()).findOneByID(anyInt());
        verify(vendorWholeSalerEntityRepository, atLeastOnce()).save(dummyWholesaler);
    }

    @Test
    public void setVendorContract_when_normal_case_revise_contract() {

        SetVendorContractParameter request = new SetVendorContractParameter();
        request.setWholeSalerID(2858);
        request.setVendorContractID(1111);
        request.setContractTypeID(5);
        request.setVendorContractRowAdd(true);

        WholeSalerEntity dummyWholesaler = new WholeSalerEntity();

        given(vendorWholeSalerEntityRepository.findOneByID(anyInt())).willReturn(dummyWholesaler);
        given(vendorWholeSalerEntityRepository.save(any())).willReturn(dummyWholesaler);

        try {
            vendorContractService.setVendorContract(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorWholeSalerEntityRepository, atLeastOnce()).findOneByID(anyInt());
        verify(vendorWholeSalerEntityRepository, atLeastOnce()).save(dummyWholesaler);
    }
}
