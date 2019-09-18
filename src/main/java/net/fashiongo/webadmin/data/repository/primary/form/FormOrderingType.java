package net.fashiongo.webadmin.data.repository.primary.form;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Getter
public enum FormOrderingType {
	FASHION_GO_FORM_ID_ASC("FashionGoFormID asc"),
	FASHION_GO_FORM_ID_DESC("FashionGoFormID desc"),
	FORM_NAME_ASC("FormName asc"),
	FORM_NAME_DESC("FormName desc"),
	ATTACHMENT_ASC("Attachment asc"),
	ATTACHMENT_DESC("Attachment desc"),
	MEMO_ASC("Memo asc"),
	MEMO_DESC("Memo desc"),
	MODIFIED_ON_ASC("ModifiedOn asc"),
	MODIFIED_ON_DESC("ModifiedOn desc");

	private String stringValue;

	FormOrderingType(String stringValue) {
		this.stringValue = stringValue;
	}

	public static FormOrderingType getFromStringValue(String stringValue) {
		if (StringUtils.isEmpty(stringValue)) {
			return FASHION_GO_FORM_ID_DESC;
		}

		return Arrays.stream(FormOrderingType.values())
				.filter(t -> t.getStringValue().equals(stringValue))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
