package net.fashiongo.webadmin.data.model.vendor.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.ContractPlansResponse;

import java.util.List;

@Getter
public class GetContractPlansResponse {

    @JsonProperty("contents")
    private List<ContractPlansResponse> contractPlansResponseList;

    @JsonProperty("totalCount")
    private Long total;

    public GetContractPlansResponse() {}

    @Builder
    public GetContractPlansResponse(List<ContractPlansResponse> contractPlansResponseList, Long total) {
        this.contractPlansResponseList = contractPlansResponseList;
        this.total = total;
    }
}
