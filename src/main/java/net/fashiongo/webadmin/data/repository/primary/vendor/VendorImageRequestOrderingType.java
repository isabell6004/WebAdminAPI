package net.fashiongo.webadmin.data.repository.primary.vendor;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum VendorImageRequestOrderingType {
	APPROVED_ON("ApprovedOn"),
	REQUESTED_ON("requestDate"),
	COMPANY_NAME("companyName"),

	COMPANY_NAME_ASC("companyName asc"),
	COMPANY_NAME_DESC("companyName desc"),

	VENDOR_IMAGE_TYPE_ID_ASC("bannerTypeId asc"),
	VENDOR_IMAGE_TYPE_ID_DESC("bannerTypeId desc"),

	REQUESTED_ON_ASC("requestDate asc"),
	REQUESTED_ON_DESC("requestDate desc"),

	DECIDED_ON_ASC("decidedDate asc"),
	DECIDED_ON_DESC("decidedDate desc"),

	REJECT_REASON_ASC("rejectReason asc"),
	REJECT_REASON_DESC("rejectReason desc");

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
