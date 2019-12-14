package utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HTTPResponse implements Closeable{

	private int responseCode;

	private String responseMessage;

	private InputStream inputStream;

	public HTTPResponse(int responseCode, String responseMessage, InputStream inputStream) {
	        this.responseCode = responseCode;
	        this.responseMessage = responseMessage;
	        this.inputStream = inputStream;
	    }

	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String extractDataAsString() throws IOException {
		StringBuilder content = new StringBuilder();
		String line;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
		}

		return content.toString();
	}

	public void close() throws IOException {
		if (inputStream != null) {
			inputStream.close();
		}
	}

}
