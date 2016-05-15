package request;

import java.io.IOException;

import org.apache.http.client.HttpClient;

public interface MagAPI {
	public String doRequest(HttpClient client) throws IOException;

}
