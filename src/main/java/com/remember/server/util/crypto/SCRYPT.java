package com.remember.server.util.crypto;

import com.lambdaworks.crypto.SCryptUtil;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */
public class SCRYPT {

	private static final int N = 16384;
	private static final int r = 8;
	private static final int p = 1;


	public static String encrypt(String password) {

		return SCryptUtil.scrypt(password, N, r, p);

	}

	public static boolean check(String password, String encrypted) {
		return SCryptUtil.check(password, encrypted);
	}

}
