package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.data.model.push.PushSendParameter;

public interface PushService {
    void sendPushNotification(PushSendParameter parameter);
}
