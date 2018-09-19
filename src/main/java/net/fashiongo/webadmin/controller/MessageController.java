/**
 * 
 */
package net.fashiongo.webadmin.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.parameter.GetMessageParameters;
import net.fashiongo.webadmin.service.MessageService;

/**
 * @author Incheol Jung
 */
@RestController
@RequestMapping(value="/message", produces = "application/json")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="getmessage", method=RequestMethod.POST)
	public JsonResponse<JSONObject> GetMessage(@RequestBody GetMessageParameters parameters) {
		JsonResponse<JSONObject> results = new JsonResponse<JSONObject>(false, null, null);
		
		JSONObject result = messageService.GetMessage(parameters);
		results.setData(result);
		results.setSuccess(true);
		
		return results;
	}
}