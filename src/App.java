import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {		
		RequestController controller = new RequestController();
		String body = controller.send("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json");

		var parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parser(body);
		
		var generator = new StickerGenerator();
		var directory = new File("stickers/");
		directory.mkdir();
		
		for (Map<String,String> filme : listaDeFilmes) {
			
			String urlImage = filme.get("image");
			String title = filme.get("title");

			InputStream inputStream = new URL(urlImage).openStream();
			String fileName = "stickers/" + title.replace(":", " -") + ".png"; 
			
			generator.create(inputStream, fileName);

			System.out.println("Titulo: " + title);
			System.out.println();
		}
	}
}
