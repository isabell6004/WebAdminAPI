package net.fashiongo.webadmin.service;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.VendorContractDocumentEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorContractEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.model.vendor.DelVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.data.repository.primary.VendorContractEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorContractDocumentEntityRepository;
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
    private VendorContractDocumentEntityRepository vendorContractDocumentEntityRepository;

    @Mock
    private VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;

    @Mock
    private VendorContractEntityRepository vendorContractEntityRepository;

    @Mock
    private CacheService cacheService;

    private VendorContractServiceImpl vendorContractService;

    @Before
    public void init() {
        vendorContractService = new VendorContractServiceImpl(
                vendorContractDocumentEntityRepository,
                vendorContractNewService,
                vendorWholeSalerEntityRepository,
                vendorContractEntityRepository,
                cacheService);
    }

    @Test
    public void delVendorContractDocument_when_normal_case() {
        String documentIds = "1,2";
        DelVendorContractDocumentParameter request = new DelVendorContractDocumentParameter();
        request.setDocumentHistoryIDs(documentIds);

        VendorContractDocumentEntity entity1 = VendorContractDocumentEntity.builder().vendorContractDocumentID(1).build();
        VendorContractDocumentEntity entity2 = VendorContractDocumentEntity.builder().vendorContractDocumentID(2).build();
        List<VendorContractDocumentEntity> documents = Arrays.asList(entity1, entity2);

        willDoNothing().given(vendorContractNewService).deleteVendorContractDocument(anyInt(), anyLong(), anyList());
        given(vendorContractDocumentEntityRepository.findAllById(anyList())).willReturn(documents);
        willDoNothing().given(vendorContractDocumentEntityRepository).deleteAll(documents);

        try {
            vendorContractService.delVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }


        verify(vendorContractNewService, atLeastOnce()).deleteVendorContractDocument(anyInt(), anyLong(), anyList());
        verify(vendorContractDocumentEntityRepository, atLeast(1)).findAllById(anyList());
        verify(vendorContractDocumentEntityRepository, atLeast(1)).deleteAll(anyList());
    }

    @Test
    public void delVendorContractDocument_when_throw_runtime_exception() {
        String documentIds = "222,333";
        DelVendorContractDocumentParameter request = new DelVendorContractDocumentParameter();
        request.setDocumentHistoryIDs(documentIds);

        given(vendorContractDocumentEntityRepository.findAllById(anyList())).willThrow(new RuntimeException("test"));

        try {
            vendorContractService.delVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractDocumentEntityRepository, atLeast(1)).findAllById(anyList());
    }

    @Test
    @Transactional
    public void delVendorContractDocument_when_not_exist_documentIds() {
        String documentIds = "99999,999999";
        DelVendorContractDocumentParameter request = new DelVendorContractDocumentParameter();
        request.setDocumentHistoryIDs(documentIds);

        willDoNothing().given(vendorContractNewService).deleteVendorContractDocument(anyInt(), anyLong(), anyList());
        given(vendorContractDocumentEntityRepository.findAllById(anyList())).willReturn(null);

        try {
            vendorContractService.delVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractNewService, atLeastOnce()).deleteVendorContractDocument(anyInt(), anyLong(), anyList());
    }

    @Test
    @Transactional
    public void delVendorContractDocument_when_null_request() {

        String documentIds = "";
        DelVendorContractDocumentParameter request = new DelVendorContractDocumentParameter();
        request.setDocumentHistoryIDs(documentIds);

        willDoNothing().given(vendorContractNewService).deleteVendorContractDocument(anyInt(), anyLong(), anyList());

        // do test
        try {
            vendorContractService.delVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }
        verify(vendorContractNewService, never()).deleteVendorContractDocument(anyInt(), anyLong(), anyList());
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

        VendorContractDocumentEntity returnEntity = VendorContractDocumentEntity.create(request, "testDeveloper");

        willDoNothing().given(vendorContractNewService).createVendorContractDocument(anyInt(), request);
        given(vendorContractDocumentEntityRepository.save(any())).willReturn(returnEntity);

        try {
            vendorContractService.setVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractNewService, atLeastOnce()).createVendorContractDocument(anyInt(), request);
        verify(vendorContractDocumentEntityRepository, atLeast(1)).save(any());
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

        willDoNothing().given(vendorContractNewService).createVendorContractDocument(anyInt(), request);
        given(vendorContractDocumentEntityRepository.save(any())).willThrow(new RuntimeException("test"));

        try {
            vendorContractService.setVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractNewService, never()).createVendorContractDocument(anyInt(), request);
        verify(vendorContractDocumentEntityRepository, atLeast(1)).save(any());
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

        VendorContractDocumentEntity returnEntity = VendorContractDocumentEntity.create(request, "testDeveloper");

        willDoNothing().given(vendorContractNewService).modifyVendorContractDocument(anyInt(), request);
        given(vendorContractDocumentEntityRepository.findOneByVendorContractDocumentID(request.getVendorContractDocumentID())).willReturn(returnEntity);
        given(vendorContractDocumentEntityRepository.save(any())).willReturn(returnEntity);

        try {
            vendorContractService.setVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractNewService, atLeastOnce()).modifyVendorContractDocument(anyInt(), request);
        verify(vendorContractDocumentEntityRepository, atLeast(1)).findOneByVendorContractDocumentID(any());
        verify(vendorContractDocumentEntityRepository, atLeast(1)).save(any());
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

        VendorContractDocumentEntity returnEntity = VendorContractDocumentEntity.create(request, "testDeveloper");

        willDoNothing().given(vendorContractNewService).modifyVendorContractDocument(anyInt(), request);
        given(vendorContractDocumentEntityRepository.findOneByVendorContractDocumentID(request.getVendorContractDocumentID())).willReturn(returnEntity);
        given(vendorContractDocumentEntityRepository.save(any())).willThrow(new RuntimeException("test"));

        try {
            vendorContractService.setVendorContractDocument(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorContractNewService, never()).modifyVendorContractDocument(anyInt(), request);
        verify(vendorContractDocumentEntityRepository, atLeast(1)).findOneByVendorContractDocumentID(any());
        verify(vendorContractDocumentEntityRepository, atLeast(1)).save(any());
    }

    @Test
    public void setVendorContract_when_normal_case_insert_contract_exist_origin_contract() {

        SetVendorContractParameter request = new SetVendorContractParameter();
        request.setWholeSalerID(2858);

        VendorContractEntity returnEntity = VendorContractEntity.create(request);

        given(vendorWholeSalerEntityRepository.findOneByID(anyInt())).willReturn(new WholeSalerEntity());
        given(vendorContractEntityRepository.findOneByWholeSalerID(anyInt())).willReturn(returnEntity);
        given(vendorContractEntityRepository.save(any())).willReturn(returnEntity);

        try {
            vendorContractService.setVendorContract(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorWholeSalerEntityRepository, atLeastOnce()).findOneByID(anyInt());
        verify(vendorContractEntityRepository, atLeastOnce()).findOneByWholeSalerID(anyInt());
        verify(vendorContractEntityRepository, atLeastOnce()).save(returnEntity);
    }

    @Test
    public void setVendorContract_when_normal_case_insert_contract_not_exist_origin_contract() {

        SetVendorContractParameter request = new SetVendorContractParameter();
        request.setWholeSalerID(2858);

        VendorContractEntity returnEntity = VendorContractEntity.create(request);

        given(vendorWholeSalerEntityRepository.findOneByID(anyInt())).willReturn(new WholeSalerEntity());
        given(vendorContractEntityRepository.findOneByWholeSalerID(anyInt())).willReturn(null);
        given(vendorContractEntityRepository.save(any())).willReturn(returnEntity);

        try {
            vendorContractService.setVendorContract(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorWholeSalerEntityRepository, atLeastOnce()).findOneByID(anyInt());
        verify(vendorContractEntityRepository, atLeastOnce()).findOneByWholeSalerID(anyInt());
        verify(vendorContractEntityRepository, atLeastOnce()).save(any());
    }

    @Test
    public void setVendorContract_when_normal_case_update_contract() {

        SetVendorContractParameter request = new SetVendorContractParameter();
        request.setWholeSalerID(2858);
        request.setVendorContractID(1111);
        request.setContractTypeID(5);

        VendorContractEntity returnEntity = VendorContractEntity.create(request);

        WholeSalerEntity dummyWholesaler = new WholeSalerEntity();

        given(vendorWholeSalerEntityRepository.findOneByID(anyInt())).willReturn(dummyWholesaler);
        given(vendorContractEntityRepository.findOneByVendorContractID(anyInt())).willReturn(returnEntity);
        given(vendorContractEntityRepository.save(any())).willReturn(returnEntity);
        given(vendorWholeSalerEntityRepository.save(any())).willReturn(dummyWholesaler);

        try {
            vendorContractService.setVendorContract(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorWholeSalerEntityRepository, atLeastOnce()).findOneByID(anyInt());
        verify(vendorContractEntityRepository, atLeastOnce()).findOneByVendorContractID(anyInt());
        verify(vendorContractEntityRepository, atLeastOnce()).save(returnEntity);
        verify(vendorWholeSalerEntityRepository, atLeastOnce()).save(dummyWholesaler);
    }

    @Test
    public void setVendorContract_when_normal_case_revise_contract() {

        SetVendorContractParameter request = new SetVendorContractParameter();
        request.setWholeSalerID(2858);
        request.setVendorContractID(1111);
        request.setContractTypeID(5);
        request.setVendorContractRowAdd(true);

        VendorContractEntity returnEntity = VendorContractEntity.create(request);

        WholeSalerEntity dummyWholesaler = new WholeSalerEntity();

        given(vendorWholeSalerEntityRepository.findOneByID(anyInt())).willReturn(dummyWholesaler);
        given(vendorContractEntityRepository.findOneByVendorContractID(anyInt())).willReturn(returnEntity);
        given(vendorContractEntityRepository.save(any())).willReturn(returnEntity);
        given(vendorWholeSalerEntityRepository.save(any())).willReturn(dummyWholesaler);

        try {
            vendorContractService.setVendorContract(request);
        } catch (Throwable t) {
            Assert.fail();
        }

        verify(vendorWholeSalerEntityRepository, atLeastOnce()).findOneByID(anyInt());
        verify(vendorContractEntityRepository, atLeastOnce()).findOneByVendorContractID(anyInt());
        verify(vendorContractEntityRepository, atLeast(2)).save(any());
        verify(vendorWholeSalerEntityRepository, atLeastOnce()).save(dummyWholesaler);
    }
}
