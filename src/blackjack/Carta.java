package blackjack;

import java.util.Random;

public class Carta {
    //Constantes que indicam o naipe de uma carta
    public static final int TIPO_INDEFINIDO = 0;
    public static final int TIPO_PAUS       = 1;
    public static final int TIPO_ESPADAS    = 2;
    public static final int TIPO_COPAS      = 3;
    public static final int TIPO_OUROS      = 4;

    //Porde armazenar os valores 1,2,3,4,5,6,7,8,9,10
    private int valorNumerico;

    //Porde armazenar os valores 1,2,3,4,5,6,7,8,9,10,J,Q,K
    private String nomeDaCarta;

    //Armazena um determinado número para um determinado tipo (naipe).
    private int tipo = 0;

    //Diz se uma determinada variável está virada para baixo
    private boolean viradaParaBaixo = false;

    //Construtor vazio
    public Carta(){
        
    }

    //Construtor com valor numérico
    public Carta(String nomeDaCarta) throws Exception{
        setNomeDaCarta(nomeDaCarta);
    }

    //Construtor com valor numérico
    public Carta(String nomeDaCarta, boolean viradaParaBaixo) throws Exception{
        setNomeDaCarta(nomeDaCarta);
        this.viradaParaBaixo = viradaParaBaixo;
    }

    //Construtor com valor numérico
    public Carta(String nomeDaCarta, boolean viradaParaBaixo, int tipo) throws Exception{
        setNomeDaCarta(nomeDaCarta);
        this.viradaParaBaixo = viradaParaBaixo;
        setTipo(tipo);
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) throws Exception{
        //Caso o tipo não seja nenhum dos naipes então vai retornar um erro
        if(tipo < 1 || tipo > 4) throw new Exception("O valor numérico de não está no período permitido 1...10 (estes inclusos). O valor informado foi: " + tipo);
        
        //Caso contrário a atribuição do naipe funcionará corretamente
        this.tipo = tipo;
    }

    //Retonar o nome da carta, por exemplo "A", "2", "3",...,"J", "Q","K"
    public String getNomeDaCarta() {
        if(isViradaParaBaixo()) throw new RuntimeException("Desvire a carta primeiro");
        return nomeDaCarta;
    }

    //Quando o nome da carta for atribuído
    public void setNomeDaCarta(String nomeDaCarta) throws Exception{
        if(nomeDaCartaParaValorNumerico(nomeDaCarta)) this.nomeDaCarta = nomeDaCarta;
    }

    //Seta um valor numérico a carta
    public void setValorNumerico(int valorNumerico) throws Exception{
        //Se o valor recebido não corresponder a um valor válido ele alerta um erro
        if(valorNumerico < 1 || valorNumerico > 11) throw new Exception("O valor numérico não está no período permitido 1...11 (estes inclusos). O valor informado foi: " + valorNumerico);

        //caso contrário a atribuição é realizada com sucesso
        this.valorNumerico = valorNumerico;
    }

    //Retorna o valor numérico da carta
    public int getValorNumerico() {
        if(isViradaParaBaixo()) throw new RuntimeException("Desvire a carta primeiro");
        return valorNumerico;
    }

    //Retorna se a carta está virada para baixo
    public boolean isViradaParaBaixo() {
        return viradaParaBaixo;
    }

    //Desvira a carta, mostra o valor dela
    public void desvirar() throws Exception{
        //Caso a carta já esteja desvirada é retornado um erro
        if(!isViradaParaBaixo()) throw new Exception("A carta já está desvirada");

        //Caso contrário somente é desvirado a Carta.
        this.viradaParaBaixo = false;
    }

    //Vira a carta, ou seja, oculta o valor dela
    public void virar() throws Exception{ 
        //Caso a carta já esteja virada é retornado um erro
        if(isViradaParaBaixo()) throw new Exception("A carta já está desvirada");

        //Caso contrário somente é desvirado a Carta.
        this.viradaParaBaixo = true;
    }

    /*
    Obtém um nome da carta (como 1,2,3,...,J,Q,K) e transforma o nome para um valor numérico, por exemplo:
    nome=2 --> valorNumerico=2
    nome=3 --> valorNumerico=3
    ...
    nome=10 --> valorNumerico=10
    ...
    nome=J --> valorNumerico=10
    nome=Q --> valorNumerico=10
    nome=K --> valorNumerico=10

    O boleano que a função retorna indica se a função conseguiu atribuir um valor numérico ou não.
    */
    public boolean nomeDaCartaParaValorNumerico(String nomeDaCarta) throws Exception{
        nomeDaCarta = nomeDaCarta.trim().toUpperCase(); //Elimina possíveis espaços que podem ter na string, por exemplo transforma "    ola     " em "ola", e transforma todos os caracteres para maiúsuclos, por exemplo "ola" para "OLA"
        try {
            //Primeiro é tentando transformar o nomeDaCarta para um número, este caso irá funcionar caso o nome da carta for 1,2,3,4,5,6,7,8,9,10; nos demais casos (A, J, Q, K) será tratado no catch
            int i = Integer.parseInt(nomeDaCarta);

            if(i == 1)//Não é permitido o valor 1
                throw new Exception("Utilize \"A\" ao invés de 1");
            
            //A linha abaixo só irá rodar caso o comando acima dê certo, ou seja, se o valor da carta for um número (1,2,3,4,5,6,7,9,10)
            setValorNumerico(i);

            return true;
        } catch (NumberFormatException e) {//Isto será executado somente quando o nomeDaCarta for um J,Q,K (ou outro valor, no caso de outros valores será feito um tratamento especial)
            if(nomeDaCarta.equals("J") || nomeDaCarta.equals("Q") || nomeDaCarta.equals("K"))//o nomeDaCarta é "J", "Q" ou "K"
                setValorNumerico(10); //Caso for "J", "Q" ou "K" o valor numérico é 10, de acordo com as regras do blackjack
            else if(nomeDaCarta.equals("A"))
                setValorNumerico(1);//Caso for a letra "A" será inicialmente atribuído como 1, mas depois de chamado a função soma de Player será mudado esse valor
            else //Caso não for nenhuma das Strings acima, é um valor inválido
                throw new Exception("O valor " + nomeDaCarta + " não corresponde a nenhuma carta");
            
            return true;//Foi atribuido com sucesso um valor numerico utilizando um valor da carta, é importante ressaltar que caso a condição caia
                        //no "else" será lançado (throw) e o programa será cancelado (caso não utilizado um try_catch), ou seja, esse "return true" somente é executado quando a condição do "if" acima é verdadeira
        }
    }

    /*Retorna uma carta aleatória em número, o tipo é passado por parâmetro*/
    public static Carta generateRandomCarta(int tipo) throws Exception{
        Random rand = new Random();
        Carta carta;
        //Eu obtenho um valor que está entre 1..13 (estes inclusos)
        Integer i = rand.nextInt(13) + 1;
        
        if(i <= 10 && i != 1) carta = new Carta(i.toString());
        else if(i == 1)       carta = new Carta("A"); /*É utilizado valores como: */
        else if(i == 11)      carta = new Carta("J"); /* A --> 1; J --> 11; etc, vale ressaltar que esses NÃO são os valores numéricos das cartas e sim uma forma de selecionar uma das 13 cartas diferentes do baralho*/
        else if(i == 12)      carta = new Carta("Q");
        else if(i == 13)      carta = new Carta("K");
        else throw new Exception("Há um erro de lógica na classe Carta. O valor para carta: " + i + " não é permitido");

        carta.setTipo(tipo);//O tipo é passado por parâmetro nessa função.
        return carta; //Retorna a carta criada.
    }

    /*Retorna uma carta aleatória em tipo e número*/
    public static Carta generateRandomCarta() throws Exception{
        Random rand = new Random();

        //Eu obtenho um valor que está entre 1..4 (estes inclusos)
        Integer i = rand.nextInt(4) + 1;
        
        /*Não gosto da estrutura switch - case*/
        if(i == TIPO_PAUS)         return generateRandomCarta(TIPO_PAUS);   //Retona uma carta do tipo de paus
        else if(i == TIPO_ESPADAS) return generateRandomCarta(TIPO_ESPADAS);//Retona uma carta do tipo de espadas
        else if(i == TIPO_COPAS)   return generateRandomCarta(TIPO_COPAS);  //Retona uma carta do tipo de copas
        else if(i == TIPO_OUROS)   return generateRandomCarta(TIPO_OUROS);  //Retona uma carta do tipo de ouros
        else throw new Exception("Há um erro de lógica na classe Carta. O valor de tipo: " + i + " não é permitido"); //Caso nenhum dos tipos seja selecionado quer dizer que há um erro na implementação da função rand(), caso isto ocorra irá retornar esse erro
        
    }

    //Sobrescreve o método "toString" da classe "Object" (o qual está implicitamente extendida)
    @Override
    public String toString() {
        return "Carta[valorNum=" + getValorNumerico() + ", nomeCarta=" + getNomeDaCarta() + "]";
    }

}