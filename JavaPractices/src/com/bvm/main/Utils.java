package com.bvm.main;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.http.Part;
import javax.ws.rs.core.Response;


public class Utils {
	private static char[] VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879"
			.toCharArray();

	public static String csRandomAlphaNumericString(int numChars) {

		try {
			SecureRandom srand = SecureRandom.getInstance("SHA1PRNG");
			char[] buff = new char[numChars];
			for (int i = 0; i < numChars; ++i) {
				// reseed rand once you've used up all available entropy bits
				if ((i % 10) == 0) {
					srand.setSeed(srand.nextLong()); // 64 bits of random!
				}
				buff[i] = VALID_CHARACTERS[srand.nextInt(VALID_CHARACTERS.length)];
			}
			return new String(buff);

		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static Error getError(Integer status) {
		Error result = new Error();
		result.setStatus(status);
		return result;
	}

	public static String getKey() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		return uuid;
	}

	public static Response inputValidationError() {
		Error e = new Error();
		e.setMessage("Input validation error");
		e.setStatus(400);
		return Response.status(400).entity(e).build();
	}

	public static Response c4cGatewayError() {
		Error e = new Error();
		e.setMessage("Unable to get C4C entity metadata");
		e.setStatus(502);
		return Response.status(502).entity(e).build();
	}

	public static Response serverError(String error) {
		Error e = new Error();
		e.setMessage(error);
		e.setStatus(502);
		return Response.status(502).entity(e).build();
	}

	public static Response fileFormatError(Exception ex) {
		Error e = new Error();
		e.setMessage("Error processing the CSV file => " + ex.getLocalizedMessage());
		e.setStatus(400);
		return Response.status(400).entity(e).build();
	}

	public static Response fileFormatError() {
		Error e = new Error();
		e.setMessage("Error processing the CSV file ");
		e.setStatus(400);
		return Response.status(400).entity(e).build();
	}

	public static String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE
																													// fix.
			}
		}
		return null;
	}

	public static <T> boolean IsNullOrEmpty(Collection<T> list) {
		return list == null || list.isEmpty();
	}
	
	public static String utf8Encoding(){
		return StandardCharsets.UTF_8.name();
	}
}