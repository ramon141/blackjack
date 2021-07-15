package blackjack;


public class Main {
    public static void main(String[] args) {
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

            System.out.println("\n" + eu.quemVenceu(dealer).equals(eu));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
