package edu.scut.emos.tsp.utils;

import java.util.UUID;

/**
 * UUIDGenerator可以生成一个UUID
 * 
 * @author emos
 *
 */
public class UUIDGenerator {

	public static String generate() {
		String UUIDStr = UUID.randomUUID().toString();
		String code = UUIDStr.substring(0, 8) + UUIDStr.substring(9, 13)
				+ UUIDStr.substring(14, 18) + UUIDStr.substring(19, 23)
				+ UUIDStr.substring(24);
		return code;
	}
}
