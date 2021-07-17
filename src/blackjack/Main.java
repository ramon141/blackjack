package blackjack;

import blackjack.animacoes.ImagesTransitions;
import blackjack.animacoes.Transition;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main extends JFrame{
    
    
    ImagesTransitions img;
    
    public Main(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1300, 720);
        setLayout(null);
        setLocationRelativeTo(this);
    
        JLabel ani = new JLabel();
        ani.setBounds(500, 00, 500, 100);
        add(ani);
        
        JLabel ani1 = new JLabel();
        ani1.setBounds(500, 100, 500, 100);
        add(ani1);
        
        JLabel ani2 = new JLabel();
        ani2.setBounds(500, 200, 500, 100);
        add(ani2);
        
        JLabel ani3 = new JLabel();
        ani3.setBounds(500, 300, 500, 100);
        add(ani3);
        
        
        
        try {
            img = new ImagesTransitions("imgs/espadas/#.png", 1,10);
            Transition transicao = new Transition();
            
            transicao.setFps(5);
            transicao.setImgs(img);
            transicao.setJl(ani);
            transicao.setLoop(false);
            //transicao.setPontoFinal(new Point(400, 100));
            transicao.start();
            
            
            img = new ImagesTransitions("imgs/paus/#.png", 1,10);
            Transition transicao1 = new Transition();
            
            transicao1.setFps(5);
            transicao1.setImgs(img);
            transicao1.setJl(ani1);
            transicao1.setLoop(false);
            //transicao.setPontoFinal(new Point(400, 100));
            transicao1.start();
            
            
            img = new ImagesTransitions("imgs/copas/#.png", 1,10);
            Transition transicao2 = new Transition();
            
            transicao2.setFps(5);
            transicao2.setImgs(img);
            transicao2.setJl(ani2);
            transicao2.setLoop(false);
            //transicao.setPontoFinal(new Point(400, 100));
            transicao2.start();
            
            
            
            
            img = new ImagesTransitions("imgs/ouros/#.png", 1,10);
            Transition transicao3 = new Transition();
            
            transicao3.setFps(2);
            transicao3.setImgs(img);
            transicao3.setJl(ani3);
            transicao3.setLoop(false);
            //transicao.setPontoFinal(new Point(400, 100));
            transicao3.start();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
            new Main().setVisible(true);
            
            
            
            
            
            
            
            
            
            
            //Classe de teste aqui a @Sávia vai substituir este código pelos códigos da interface
//        try {
//            Player eu     = new Player("Ramon");
//            Player dealer = new Player("Dealer");
//
//            eu.addCartaAleatoria();
//            eu.addCartaAleatoria();
//
//            dealer.addCartaAleatoria();
//            dealer.addCartaAleatoria();
//
//            System.out.println(eu.toString());
//            System.out.println(dealer.toString());
//
//            System.out.println("\n" + eu.quemVenceu(dealer).equals(eu));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }   
}
