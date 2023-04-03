import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenerator {
	public void create(InputStream inputStream, String fileName) throws IOException {
		// leitura da imagem
		BufferedImage originalImage = ImageIO.read(inputStream);

		// Criar nova imagem em memoria com transparencia e tamanho novo
		int resizedWidth = 512;
		int resizedHeight = 512;
		int newHeight = resizedHeight + 200;
		BufferedImage newImage = new BufferedImage(resizedWidth, newHeight, BufferedImage.TRANSLUCENT);

		// Copiar a imagem original para a nova imagem (em memoria)
		Graphics2D graphics = (Graphics2D) newImage.getGraphics();
		graphics.drawImage(originalImage, 0, 0, 512, 512, null);

		// Configurar a fonte
		var font = new Font("Comic Sans MS", Font.BOLD, 32);
		graphics.setFont(font);
		
		// Escrever uma frase na nova imagem
		String text = "TOPZERA";
		int xFont = (resizedWidth / 2) - (graphics.getFontMetrics().stringWidth(text) / 2);
		int yFont = newHeight - 100;
		graphics.setColor(Color.CYAN);
		graphics.drawString(text, xFont, yFont);

		// Escrever a nova imagem em um arquivo
		ImageIO.write(newImage, "png", new File(fileName));
	}
}
