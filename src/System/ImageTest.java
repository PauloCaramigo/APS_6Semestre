package System;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ImageTest extends JFrame {
	BufferedImage testImagem;
	BufferedImage imagem2;
	
	public ImageTest() {
		Container c = getContentPane();
	    c.setLayout(new FlowLayout());
		
	    JButton btn = new JButton("Carregar Imagem");
	    btn.addActionListener(
	      new ActionListener(){
	        public void actionPerformed(ActionEvent e){
	        testImagem = null;
	          imagem2 = null;
	           
	          try{
	        	  testImagem = ImageIO.read(
	              new File("C:/Users/PC/Desktop/APS_6Semestre/src/images/Não existe.jpg"));
	            
	            imagem2= ImageIO.read(
	  	              new File("C:/Users/PC/Desktop/APS_6Semestre/src/images/Suh.jpg"));
	          }
	          catch(IOException exc){
	            JOptionPane.showMessageDialog(null, 
	              "Erro ao carregar a imagem: " + 
	              exc.getMessage());
	          }
	 
	          if(testImagem != null) {
	            JOptionPane.showMessageDialog(null, 
	              "Imagem carregada com sucesso.");
	            }
	        }
	      }
	    );
	    
	    JButton btn2 = new JButton("Comparar");
	    btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		          compareImage(testImagem, imagem2);				
			}
		});
	    
	    c.add(btn);
	    c.add(btn2);
	    setSize(400, 300);
	    setVisible(true);
	}
	    
	    public static boolean compareImage(BufferedImage image1, BufferedImage image2){
	        if (image1.getWidth()!= image2.getWidth()|| image1.getHeight() != image2.getHeight()){
	        	JOptionPane.showMessageDialog(null, "São de tamanhos diferentes!");
	            return(false);
	        }
	    
	        for (int x=0; x<image1.getWidth(); x++){
	            for(int y=0; y<image1.getHeight(); y++){
	                if(image1.getRGB(x,y)!=image2.getRGB(x,y)){
	                	JOptionPane.showMessageDialog(null, "As imagens são diferentes!");
	                    return(false);
	                }
	            }
	        }
	        JOptionPane.showMessageDialog(null, "As imagens são iguais!");
	        return(true);
	    }
	
	public static void main(String args[]) {
		ImageTest ii = new ImageTest();
	}
}
