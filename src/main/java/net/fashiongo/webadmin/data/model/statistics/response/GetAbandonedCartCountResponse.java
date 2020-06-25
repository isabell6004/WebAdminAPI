package net.fashiongo.webadmin.data.model.statistics.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetAbandonedCartCountResponse {

    private long recipientCount;
    private long unsubscribeCount;

    public GetAbandonedCartCountResponse() {
    }

    @Builder
    public GetAbandonedCartCountResponse(long recipientCount, long unsubscribeCount) {
        this.recipientCount = recipientCount;
        this.unsubscribeCount = unsubscribeCount;
    }
}
