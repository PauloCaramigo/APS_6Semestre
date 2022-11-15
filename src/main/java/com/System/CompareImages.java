package com.System;

import java.awt.image.BufferedImage;

public class CompareImages {
	BufferedImage testImagem;
	BufferedImage imagem2;
	
	public boolean compareImage(BufferedImage image1, BufferedImage image2){
		if (image1.getWidth()!= image2.getWidth()|| image1.getHeight() != image2.getHeight()){
			System.out.println("\nSão de tamanhos diferentes!");
			//JOptionPane.showMessageDialog(null, "São de tamanhos diferentes!");
			return false;
		}
	
		for (int x=0; x<image1.getWidth(); x++){
			for(int y=0; y<image1.getHeight(); y++){
				if(image1.getRGB(x,y)!=image2.getRGB(x,y)){
					System.out.println("\nAs imagens são diferentes!");
					//JOptionPane.showMessageDialog(null, "As imagens são diferentes!");
					return false;
				}
			}
		}
		System.out.println("\nAs imagens são iguais!");
		//JOptionPane.showMessageDialog(null, "As imagens são iguais!");
		return true;
	}
}

