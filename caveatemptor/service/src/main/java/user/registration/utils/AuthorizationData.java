package user.registration.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

import constants.Authorization;

public class AuthorizationData {

	private AuthorizationData() {
	}

	public static String getAuthorizationnKey(int size) {
		return new BigInteger(size * 5, new SecureRandom()).toString(32);
	}

	public static long getAuthorizationDate(long startingTime) {
		return startingTime + Authorization.KEY_EXPIRATION.getValue();
	}

}
