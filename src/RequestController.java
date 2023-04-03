import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class RequestController {
	public String send(String url) {
		try {
			URI endereco = URI.create(url);
			var client = HttpClient.newHttpClient();
			var request = HttpRequest.newBuilder(endereco).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			return response.body();
		} catch (Exception e) {
			throw new Error("Erro ao realização a requisição");
		}
	}
}
