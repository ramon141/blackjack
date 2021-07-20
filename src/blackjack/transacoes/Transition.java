package blackjack.transacoes;

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Transition extends Thread{
    
    //Armazena a quantidade de frames por segundo (FPS - frames per second) que irão ser mostradas na animação, quanto maior for esse valor maiot a velocidade das animações 
    private int fps;
    
    //Este é o JLabel o qual será mostrado a animação, que será criada aqui
    private JLabel jl;
    
    //Informa a esta classe a quantidade de vezes que a animação deve repetir antes que pare
    private int quantRepetir;
    
    //Variável que informa o ponto onde deverá estar contido o JLabel ao final da animação
    private Point pontoFinal;
    
    //Recebe uma ImageTransitions que basicamente é uma lista de imagens com funções voltadas para seleção de imagens
    private ImagesTransitions imgs;

    //Método construtor
    public Transition(ImagesTransitions imgs, JLabel jl) {
        this(24, jl, 1, imgs); //Recebe alguns atributos e os seta por meio do método contrutor mais completo
    }
    
    //Método construtor
    public Transition(int fps, JLabel jl, ImagesTransitions imgs) {
        this(fps, jl, 1, imgs); // Atribui alguns valors padrões para os atributos de classe, e chama o método construtor desta (this) classe.
    }
    
    //Método construtor
    public Transition(JLabel jl, ImagesTransitions imgs, int quantRepetir) {
        this(24, jl, quantRepetir, imgs); // 24 (que é o FPS) é o valor padrão para o FPS, caso o programador não informe no método construtor, este valor pode ser modificado posteriormente com o método "setFps(int)"
    }
    
    //Método construtor mais completo, todos os métodos contrutores anteriores chamam este método para atribuir os valores das variáveis.
    public Transition(int fps, JLabel jl, int quantRepetir, ImagesTransitions imgs) {
        setFps(fps);
        setJl(jl);
        setQuantRepetir(quantRepetir);
        setImgs(imgs);
    }
    
    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public JLabel getJl() {
        return jl;
    }

    public void setJl(JLabel jl) {
        this.jl = jl;
    }

    public int getQuantRepetir() {
        return quantRepetir;
    }

    public void setQuantRepetir(int quantRepetir) {
        this.quantRepetir = quantRepetir;
    }

    public ImagesTransitions getImgs() {
        return imgs;
    }

    public void setImgs(ImagesTransitions imgs) {
        this.imgs = imgs;
    }
    
    public Point getPontoFinal() {
        return pontoFinal;
    }

    public void setPontoFinal(Point pontoFinal) {
        this.pontoFinal = pontoFinal;
    }
    
    /*Método chamado ao começar (start) um Thread*/
    @Override
    public void run(){
        try {
            //Obtém a lista de imagens como array
            ImageIcon imgIcon[] = imgs.getImagesAsArray();
            
            //Calcula quantos milisegundos são necessários entre a atualização de frames, para que seja atualizado n vezes por segundo.
            //Exemplo: considere fps = 24, logo, 1000 / 24 = 41 (em inteiros)  isso quer dizer que a cada 41 segundos o frame/imagem vai ser mudado(a)
            int time = 1000 / fps;
            
            //Cria a var "i" e cria e inicia a variavel até com o tamanho do vetor, ou seja, a quantidade de imagens que esta animação possui
            int i, ate = imgIcon.length;
            
            double moveX = 0, xin = 0;
            double moveY = 0, yin = 0;
            
            if (pontoFinal != null){
                //Obtém a quantidade de pixels que deve ser movido a cada troca de frames, isto no eixo X
                moveX = (double)(pontoFinal.x - jl.getX()) / (imgIcon.length);
                //Obtém o valor atual de X do JLabel
                xin = jl.getX();
                
                //Obtém a quantidade de pixels que deve ser movido a cada troca de frames, isto no eixo Y
                moveY = (double)(pontoFinal.y - jl.getY()) / (imgIcon.length);
                //Obtém o valor atual de Y do JLabel
                yin = jl.getY();
            }
            
            int j=0;
            
            //Executa a animação uma quantidade n de vezes.
            while(j++ < quantRepetir){
                //Percorre as imagens da animação
                for(i = 0; i < ate; i++){
                    //Seta a imagem para o JLabel
                    jl.setIcon(imgIcon[i]);
                    
                    //Caso o programador tenha setado um ponto final, este if será verdadeiro
                    if(pontoFinal != null){
                        //Obtém: (valor inicial de X do Jlabel) + (quantidade de pixels que deve ser movida a cada iteração) * (valor da iteração)
                        double dx = xin + (moveX * i);
                        
                        //Obtém: (valor inicial de Y do Jlabel) + (quantidade de pixels que deve ser movida a cada iteração) * (valor da iteração)
                        double dy = yin + (moveY * i);

                        //Seta os valores de X e Y "novos", os valores de altura e largura continuam o mesmo
                        jl.setBounds((int)dx, (int)dy, jl.getWidth(), jl.getHeight());
                        
                        //Caso esta seja a ultima iteração, ele percorre uma quantidade de pixels maior, isto ocorre pois o valor de moveX é double e ao ser feito operações com ele e o resultado destas serem transformados para inteiro sofre alguns pequenos erros (ao tentar mover 100pixels, 2pixels não são movidos por causa desta transformação double-->int)
                        //Esta parte serve para mover a quantidade n de pixels até o ponto final X, Y
                        if(i == ate-1){
                            dx = pontoFinal.x - jl.getX();
                            dy = pontoFinal.y - jl.getY();
                            jl.setBounds((int)dx + jl.getX(),(int)dy + jl.getY(), jl.getWidth(), jl.getHeight());
                        }
                        
                    }
                    
                    //Aguarda o tempo entre um frame e outro
                    sleep(time);
                }
            }
            
            //Serve como um trigger (uma função que é chamada depois de ocorrer uma certa ação). Neste caso esta função é chamada ao finalizar o Thread
            finish();
        } catch (Exception e) {//Cai aqui caso algum método acima falhe.
            e.printStackTrace();
        }
    }

    
    //Chama novamente o método run (que foi sobrescrito)
    public void restart(){
        this.run();
    }

    //Este método serve como um trigger, ou seja, ele vai ser chamado quando o thread finalizar a operação. Esta mesma técnica pode ser vista na classe MouseAdpter, nativa do java.
    public void finish() {}
}
