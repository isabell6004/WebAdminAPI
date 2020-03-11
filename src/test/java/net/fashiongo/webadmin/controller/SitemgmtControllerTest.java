package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.controller.sitemgmt.SitemgmtController;
import net.fashiongo.webadmin.data.model.sitemgmt.response.GetSEOResponse;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetSEOParameter;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SitemgmtControllerTest {

    @Autowired
    private SitemgmtController sitemgmtController;

	@Test
	public void get_seo() {
        GetSEOParameter parameters = new GetSEOParameter();
        parameters.setPagesize(1);
        parameters.setPagenum(1);
        JsonResponse<GetSEOResponse> response = sitemgmtController.getSEO(parameters);
		log.debug(response.toString());
		log.debug("response data : {}",response.getData());

		GetSEOResponse data = response.getData();
		log.debug("cnt : {}", data.getRecCnt());
		log.debug("seo : {}", data.getSeo().get(0));
	}
}
