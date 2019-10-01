package net.fashiongo.webadmin.data.repository.primary.vendor;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum VendorImageRequestOrderingType {
	APPROVED_ON("ApprovedOn"),
	REQUESTED_ON("RequestedOn"),
	COMPANY_NAME("CompanyName"),

	COMPANY_NAME_ASC("CompanyName asc"),
	COMPANY_NAME_DESC("CompanyName desc"),

	VENDOR_IMAGE_TYPE_ID_ASC("VendorImageTypeID asc"),
	VENDOR_IMAGE_TYPE_ID_DESC("VendorImageTypeID desc"),

	REQUESTED_ON_ASC("RequestedOn asc"),
	REQUESTED_ON_DESC("RequestedOn desc"),

	DECIDED_ON_ASC("DecidedOn asc"),
	DECIDED_ON_DESC("DecidedOn desc"),

	REJECT_REASON_ASC("RejectReason asc"),
	REJECT_REASON_DESC("RejectReason desc");

	private String stringValue;

	VendorImageRequestOrderingType(String stringValue) {
		this.stringValue = stringValue;
	}

	public static VendorImageRequestOrderingType getType(String value) {
		if (StringUtils.isEmpty(value)) {
			return APPROVED_ON;
		}

		return Arrays.stream(VendorImageRequestOrderingType.values())
				.filter(t -> Objects.equals(t.getStringValue(), value))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
