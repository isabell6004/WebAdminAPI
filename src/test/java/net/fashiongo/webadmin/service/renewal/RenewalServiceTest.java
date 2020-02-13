package net.fashiongo.webadmin.service.renewal;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.kmm.GetKmmListParameter;
import net.fashiongo.webadmin.data.model.kmm.GetKmmListResponse;
import net.fashiongo.webadmin.data.model.kmm.KmmListDetail;
import net.fashiongo.webadmin.data.model.sitemgmt.SEO;
import net.fashiongo.webadmin.data.model.sitemgmt.response.GetSEOResponse;
import net.fashiongo.webadmin.model.pojo.ad.parameter.SetAddPageParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.SetAddSpotSettingParameter;
import net.fashiongo.webadmin.model.pojo.ad.response.GetADSettingResponse;
import net.fashiongo.webadmin.model.pojo.ad.response.GetSpotCheckResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetSEOParameter;
import net.fashiongo.webadmin.model.primary.CodeBodySize;
import net.fashiongo.webadmin.service.AdService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RenewalServiceTest {

	@Autowired
	private RenewalSitemgmtService renewalSitemgmtService;

	@Test
	public void get_seo() {

        GetSEOParameter parameter = new GetSEOParameter();
        parameter.setPagenum(1);
        parameter.setPagesize(10);

        GetSEOResponse response = renewalSitemgmtService.getSEO(parameter);

        Assert.assertNotNull(response);

        log.debug("response : {}, {} ", response.getRecCnt().size(), response.getSeo().size());
        log.debug("total : {}", response.getRecCnt());
        log.debug("response result : {}", response.getSeo());
	}
}
