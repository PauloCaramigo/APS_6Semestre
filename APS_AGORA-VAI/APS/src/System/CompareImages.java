package System;

import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class CompareImages {
	BufferedImage testImagem;
	BufferedImage imagem2;
	
	public boolean compareImage(BufferedImage image1, BufferedImage image2){
		if (image1.getWidth()!= image2.getWidth()|| image1.getHeight() != image2.getHeight()){
			JOptionPane.showMessageDialog(null, "São de tamanhos diferentes!");
			return false;
		}
	
		for (int x=0; x<image1.getWidth(); x++){
			for(int y=0; y<image1.getHeight(); y++){
				if(image1.getRGB(x,y)!=image2.getRGB(x,y)){
					JOptionPane.showMessageDialog(null, "As imagens são diferentes!");
					return false;
				}
			}
		}

		JOptionPane.showMessageDialog(null, "As imagens são iguais!");
		return true;
	}
}

