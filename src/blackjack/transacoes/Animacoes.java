package blackjack.transacoes;

import blackjack.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Animacoes{

    public static void blackjackStart(JFrame jf) throws Exception{
        JLabel jl = new JLabel();
        
        ImagesTransitions imgTr = new ImagesTransitions("imgs/start_blackjack/photo (#).png", 1, 120);
        
        jl.setBounds(-imgTr.getImagesAsArray()[0].getIconHeight(), (jf.getHeight() - imgTr.getImagesAsArray()[0].getIconHeight())/2, imgTr.getImagesAsArray()[0].getIconWidth(), imgTr.getImagesAsArray()[0].getIconHeight());
        jf.add(jl);
        
        Transition tr = new Transition(70, jl, imgTr);
        tr.setPontoFinal(new Point(500, 000));
        tr.start();
        
    }
    
    //Falta a documentação
    public static void blackjackWin(Player pl, JFrame jf) throws Exception{
        JLabel namePlayer = new JLabel();
        jf.add(namePlayer);
        
        ImagesTransitions imgTr = new ImagesTransitions("imgs/vencedor/#.png", 1, 120);
        
        int firstImgWidth = imgTr.getImagesAsArray()[0].getIconWidth();
        int firstImgHeight = imgTr.getImagesAsArray()[0].getIconHeight();
        
        JLabel animation = new JLabel();
        animation.setBounds((jf.getWidth() - firstImgWidth)/2, (jf.getHeight() - firstImgHeight)/2, firstImgWidth, imgTr.getImagesAsArray()[0].getIconHeight());
        jf.add(animation);
        
        Transition tr = new Transition(120, animation, imgTr){
            @Override
            public void finish(){
                int lastImgWidth = animation.getIcon().getIconWidth();
                int lastImgHeight = animation.getIcon().getIconHeight();
                
                if(firstImgHeight != lastImgHeight || firstImgWidth != lastImgWidth)
                    animation.setBounds((jf.getWidth() - lastImgWidth)/2, (jf.getHeight() - lastImgHeight)/2, lastImgWidth, lastImgHeight);
                
                int xImg = animation.getX();
                int yImg = animation.getY();
                
                namePlayer.setBounds(xImg, yImg, lastImgWidth, lastImgHeight);
                namePlayer.setHorizontalAlignment(SwingConstants.CENTER);
                
                namePlayer.setText(pl.getNome());
                try {
                    namePlayer.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("font.ttf")).deriveFont(Font.PLAIN, 85));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                namePlayer.setForeground(Color.CYAN);
                jf.repaint();
                restart();
            }
        };
        
        tr.start();
        
    }
   
}