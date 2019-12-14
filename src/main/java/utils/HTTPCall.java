package utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HTTPCall {

	public enum Method {
		GET, POST;
	}

	private String userAgent;

	private String url;

	private Map<String, String> params = new HashMap<>();

	public HTTPCall(String url) {
		this.url = url;
	}

	public HTTPCall addParam(String name, String value) {
		params.put(name, value);
		return this;
	}

	public HTTPResponse execute(Method method) throws IOException {
		StringBuilder paramsStr = null;
		if (params.size() > 0) {

			paramsStr = new StringBuilder();

			boolean first = true;

			for (Map.Entry<String, String> param : params.entrySet()) {
				if (!first) {
					paramsStr.append("&");
				}

				paramsStr.append(param.getKey()).append("=").append(URLEncoder.encode(param.getValue(), "UTF-8"));
				first = false;
			}
		}
		URL urlObj = null;

		if (method == Method.GET) {

			urlObj = new URL(url + "?" + paramsStr);
		} else {
			urlObj = new URL(url);
		}

		HttpURLConnection urlConn = (HttpURLConnection) urlObj.openConnection();

		urlConn.setRequestMethod(method.toString());

		if (userAgent != null) {

			urlConn.setRequestProperty("User-Agent", userAgent);
		}

		if (method == Method.POST && paramsStr != null) {

			urlConn.setDoOutput(true);

			try (DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream())) {
				wr.writeBytes(paramsStr.toString());
			}
		}

		int responseCode = urlConn.getResponseCode();
		String responseMessage = urlConn.getResponseMessage();
		InputStream in = urlConn.getInputStream();

		return new HTTPResponse(responseCode, responseMessage, in);
	}

}
