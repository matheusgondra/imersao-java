import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {	
		String url = "https://api.nasa.gov/planetary/apod?api_key=MaDCVISd4aa2iAsJ37QMnp5FunZZ0aTP8dbBjxz4&start_date=2022-06-12&end_date=2022-06-14";
		String urlIMDB = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

		RequestController controller = new RequestController();
		String json = controller.send(url);
		String jsonIMDB = controller.send(urlIMDB);

		ContentExtractor extractor = new ContentNasaExtractor();
		List<Content> contents = extractor.extractContent(json);
		
		// ContentExtractor extractor = new ContentIMDBExtractor();
		// List<Content> contents = extractor.extractContent(jsonIMDB);

		var generator = new StickerGenerator();
		var directory = new File("stickers/");
		directory.mkdir();
		
		for (int i = 0; i < contents.size(); i++) {
			Content content = contents.get(i);

			String urlImage = content.getUrlImage();
			String title = content.getTitle();

			InputStream inputStream = new URL(urlImage).openStream();
			String fileName = "stickers/" + title.replace(":", " -") + ".png"; 
			
			generator.create(inputStream, fileName);

			System.out.println("Titulo: " + title);
			System.out.println();
		}
	}
}
