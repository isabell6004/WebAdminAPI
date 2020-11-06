package net.fashiongo.webadmin.controller.push;

import net.fashiongo.webadmin.data.model.push.PushSendParameter;
import net.fashiongo.webadmin.service.PushService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("push/uids")
    public JsonResponse<Object> getUids(@RequestParam(name = "limit") long limit,
                                        @RequestParam(name = "offset") long offset) {
        List<String> uids = pushService.getPushUids(limit, offset);

        return new JsonResponse<>(true, "success", uids);
    }

    @GetMapping("push/user-permission")
    public JsonResponse<Object> getUserPermissions() {
        List<String> uids = pushService.getUidPermissions();

        return new JsonResponse<>(true, "success", uids);
    }
}
