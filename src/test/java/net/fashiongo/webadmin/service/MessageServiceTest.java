package net.fashiongo.webadmin.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.Message;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {
	
	@Autowired
	MessageService messageService;

	@Test
	public void testGetMessage() {
		GetMessageParameter parameters = new GetMessageParameter();
		parameters.setPagesize(20);
		parameters.setPagenum(0);
		parameters.setSendertypeid(1);
		
		GetMessageResponse result = messageService.getMessage(parameters);
		
		if(result != null) {
			for(Message msg : result.getMessagelist()) {
				assertNotNull(msg.getTitle());
			}
		}
	}

}