package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.data.model.push.PushSendParameter;

import java.util.List;

public interface PushService {
    void sendPushNotification(PushSendParameter parameter);

    List<String> getPushUids(long limit, long offset);

    List<String> getUidPermissions();
}
