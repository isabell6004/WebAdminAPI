package net.fashiongo.webadmin.data.repository.primary.vendor;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum VendorImageRequestApprovalType {
	APPROVED("Approved"),
	DENIED("Denied"),
	PENDING("Pending");

	private String stringValue;

	VendorImageRequestApprovalType(String stringValue) {
		this.stringValue = stringValue;
	}

	public static VendorImageRequestApprovalType getType(String stringValue) {
		if (StringUtils.isEmpty(stringValue)) {
			return null;
		}
		return Arrays.stream(VendorImageRequestApprovalType.values())
				.filter(t -> Objects.equals(t.getStringValue(), stringValue))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
