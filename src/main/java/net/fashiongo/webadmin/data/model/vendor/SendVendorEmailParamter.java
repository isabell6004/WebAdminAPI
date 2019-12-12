package net.fashiongo.webadmin.data.model.vendor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendVendorEmailParamter<T> {
    private String senderName;
    private String senderEmailAddress;
    private String recipientName;
    private String recipientEmailAddress;
    private String subject;
    private String message;
    private T additionalInfo;
}
