package net.fashiongo.webadmin.support;

public class FileNameUtils {

	private static final String DELIMITER = ":";

	public static String parseNotificationSavedFile(String originFileNameFromDB) {
		String[] split = originFileNameFromDB.split(DELIMITER);
		if(split.length == 1) {
			return split[0];
		}

		return split[1];
	}

	public static String updateOBSPrefixNotificationSavedFile(String originFileName) {
		return "TC-OBS" + DELIMITER + originFileName;
	}
}
