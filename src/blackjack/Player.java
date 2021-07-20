package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String nome;
    private List<Carta> cartas = new ArrayList<Carta>();
    private int quantVitorias = 0;
    private boolean parado = false;

    public Player() {

    }

    public Player(String nome) throws Exception{
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void addCarta(Carta carta) throws Exception{
        if(isParado()) throw new Exception("Este jogador já parou de jogar, e é impossível adicionar mais cartas");
        cartas.add(carta);
        A_11_ou_01();
    }

    //Adiciona uma carta totalmente aleatória
    public void addCartaAleatoria() throws Exception{
        addCarta(Carta.generateRandomCarta());
    }

    //Adiciona uma carta de dado naipe
    public void addCartaAleatoria(int tipo) throws Exception{
        addCarta(Carta.generateRandomCarta(tipo));
    }

    //Adiciona uma carta de dado naipe
    public void addCartaAleatoria(boolean viradaParaBaixo) throws Exception{
        Carta carta = Carta.generateRandomCarta();
        if(viradaParaBaixo) carta.virar();
        else carta.desvirar();
        addCarta(carta);
    }

    //Retorna a quantidade de vitórias deste player
    public int getQuantVitorias() {
        return quantVitorias;
    }

    //Adiciona somente uma vitória para este player, vale ressaltar que o programador deve chamar essa função ao identificar que um player venceu
    //ESTA FUNÇÃO NÃO É CHAMADA NO FIM DO MÉTODO "quemVenceu(Player)"
    public void addVitoria() {
        this.quantVitorias++;
    }

    //Retorna se o jogador já decidiu parar
    public boolean isParado() {
        return parado;
    }
    
    //Esta função para o jogador, não é necessário o "setParado", pois no blackjack (pelo menos segundo as regras estabelecidas no comando) não tem como continuar depois de parar uma única vez
    public void parar() {
        this.parado = true;
    }
    
    public void setNome(String nome) throws Exception{
        //Verifica se o nome está em branco, ou se é somente espaços vazios tipo "         "
        if(nome.trim().isEmpty()) throw new Exception("O nome está em branco");

        //Caso contrário é atribuído com sucesso
        this.nome = nome;
    }

    
    //Faz uma soma excluindo os últimos n (n dado por parâmetro) elementos
    private int soma(int excluirQuantElementos){
        int soma = 0;

        for(int i = 0; i < cartas.size()-excluirQuantElementos; i++)
            soma += cartas.get(i).getValorNumerico();
        
        return soma;
    }

    //Soma das cartas deste player
    public void A_11_ou_01() throws Exception{
        //Só entra se o último elemento adicionado for um A

        if(cartas.get(cartas.size()-1).getNomeDaCarta().equals("A")){
            if((soma(1)+11) <= 21){//Atualmente o valor de "A" é 1 só é trocado caso o valor 11 deixar o player mais próximo de 21 e não ultrapassar 21. Não é necessário verificar (soma(1)+11) > (soma(1)+1), pois a resposta é obvia e sempre a mesma.
                cartas.get(cartas.size()-1).setValorNumerico(11);
            }
        }
    }

    //Esta função recebe um player por parâmetro e compara com o da instância atual e verifica qual o player é o vencedo. A função retorna o player vencedor, ou "null" em caso de empate
    public Player quemVenceu(Player pl){
        //Isto ocorre quando ambos perdem (empatam)
        if(pl.soma() > 21 && this.soma() > 21) throw new RuntimeException("Caso esteja vendo esta mensagem o código está implementado errado");
        
        //Não é necessário usar else if, pois caso cais no primeiro "if" o segundo "if" não será executado
        
        //Caso a subtração da soma das cartas do "pl" com este player que dizer que este player se aproximou mais de 21. Por exemplo, consideramos alguns número hipotéticos:
        //    18     -     19      = -1, ou seja um número menor que 0, e é perceptível que este (this) jogador se aproximou mais de 21. 
        if(pl.soma() - this.soma() < 0 && this.soma() <= 21) return this;
        
        //É a mesma ideia do "if" passado, entretanto, com o outro player de nome "pl". Novamente números hipotéticos podem ser usados.
        //    20     -     17      = 3, ou seja um número maior que 0, e é perceptível o jogador "pl" está mais próximo de 21, logo ele é o vencedor.
        if(pl.soma() - this.soma() > 0 && pl.soma() <= 21) return pl;

        //Isto ocorre quando empata, ou seja quando a soma de carta seja a mesma
        if(pl.soma() == this.soma()) return null;

        //Caso a soma das cartas do player "pl" seja maior que 21, logo ele perdeu segundo as regras do blackjack
        if(this.soma() > 21) return pl;

        //Caso a soma das cartas deste player seja maior que 21, logo ele perdeu segundo as regras do blackjack
        if(pl.soma()   > 21) return this;

        throw new RuntimeException("Há um erro de lógico na classe Player --- this: " + this.soma() + ", pl: " + pl.soma());
    }

    //Soma das cartas deste player
    public int soma(){
        return soma(0);
    }

    
    //Sobrescreve o método "toString" da classe "Object", este método retorna um resultado como:
    //Considere as seguintes características deste player.
    /*
        Nome: Ramon
        Lista de Cartas: {Carta[valorNum=1, nomeCarta=A], Carta[valorNum=2, nomeCarta=2], Carta[valorNum=10, nomeCarta=J]}
        Quantidade de vitórias: 2
        Estas informações implicam em:
            Soma: 1 + 2 + 10 = 13
        
        O resultado retornado por esta função considerando tais dados será:
        Player[
            Carta[valorNum=1, nomeCarta=A]
            Carta[valorNum=2, nomeCarta=2]
            Carta[valorNum=10, nomeCarta=J]
            
            Soma: 13
            Vitórias: 2
            Nome: Ramon
        ]
    */
    @Override
    public String toString() {
        String mostrar = "Player[\n";
        for(int i = 0; i < cartas.size(); i++)
            mostrar += "\t" + cartas.get(i).toString() + "\n";
        
        mostrar += "\n\tSoma: " + soma() + "\n\tVitória(s): "+getQuantVitorias()+"\n\tNome: "+getNome()+"\n]";

        return mostrar;
    }
}
