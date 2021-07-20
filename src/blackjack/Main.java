package blackjack;

import blackjack.transacoes.Animacoes;
import blackjack.transacoes.ImagesTransitions;
import blackjack.transacoes.Transition;
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
    
        
        
        try {
            
            Animacoes.blackjackWin(new Player("Éfren"), this);
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
            new Main().setVisible(true);
            
            
            
            
            
            
            
            
            
            
            //Classe de teste aqui a @Sávia vai substituir este código pelos códigos da interface
        try {
            Player eu     = new Player("Ramon");
            Player dealer = new Player("Dealer");

            eu.addCartaAleatoria();
            eu.addCartaAleatoria();

            dealer.addCartaAleatoria();
            dealer.addCartaAleatoria();

            System.out.println(eu.toString());
            System.out.println(dealer.toString());

            Player pla = eu.quemVenceu(dealer);
            if(pla != null)
                System.out.println("\n" + eu.quemVenceu(dealer).equals(eu));
            else
                System.out.println("empate");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
