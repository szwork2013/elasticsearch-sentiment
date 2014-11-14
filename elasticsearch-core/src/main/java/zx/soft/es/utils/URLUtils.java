package zx.soft.es.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLUtils {

	public static String getDecoderURL(String url, String code) {
		try {
			String result = URLDecoder.decode(url, code);
			return result;
		} catch (UnsupportedEncodingException e) {
			return url;
		}

	}
}
