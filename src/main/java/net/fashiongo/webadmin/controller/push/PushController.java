package net.fashiongo.webadmin.controller.push;

import net.fashiongo.webadmin.data.model.push.PushSendParameter;
import net.fashiongo.webadmin.service.PushService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushController {

    private final PushService pushService;

    @Autowired
    public PushController(PushService pushService) {
        this.pushService = pushService;
    }

    @PostMapping(value = "push")
    public JsonResponse<Object> sendPushNotification(@RequestBody PushSendParameter parameter) {
        pushService.sendPushNotification(parameter);

        return new JsonResponse<>(true, "success", null);
    }
}
