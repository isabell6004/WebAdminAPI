package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelVendorContractDocumentParameter {
    @JsonProperty(value = "DocumentHistoryIDs")
    private String documentHistoryIDs;
}
