package blackjack.animacoes;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Transition extends Thread{
    
    private int fps;
    private JLabel jl;
    private boolean loop;
    private int scaleInitial;
    private int scaleFinal;
    private Point pontoFinal;
    private ImagesTransitions imgs;

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

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public ImagesTransitions getImgs() {
        return imgs;
    }

    public void setImgs(ImagesTransitions imgs) {
        this.imgs = imgs;
    }

    public double getScaleInitial() {
        return scaleInitial;
    }

    public void setScaleInitial(int scaleInitial) {
        if(scaleInitial < 0) throw new RuntimeException("A escala inicial não pode ser menor que 0");
        this.scaleInitial = scaleInitial;
    }

    public double getScaleFinal() {
        return scaleFinal;
    }

    public void setScaleFinal(int scaleFinal) {
        if(scaleFinal < 0) throw new RuntimeException("A escala final não pode ser menor que 0");
        this.scaleFinal = scaleFinal;
    }
    
    
    public void setScale(int scaleInitial, int scaleFinal){
        setScaleInitial(scaleInitial);
        setScaleFinal(scaleFinal);
    }

    public Point getPontoFinal() {
        return pontoFinal;
    }

    public void setPontoFinal(Point pontoFinal) {
        this.pontoFinal = pontoFinal;
    }
    
    
    
    
    @Override
    public void run(){
        try {
            
            boolean first = true;
            
            //imgs.setScale(10);
            
            ImageIcon imgIcon[] = imgs.getImagesAsArray();
            int time = 1000 / fps;
            int i, ate = imgIcon.length;
            
            double moveX = 0, xin = 0;
            double moveY = 0, yin = 0;
            
            if (pontoFinal != null){
                moveX = (double)(pontoFinal.x - jl.getX()) / (imgIcon.length); xin = jl.getX();
                moveY = (double)(pontoFinal.y - jl.getY()) / (imgIcon.length); yin = jl.getX();
            }
            
            while(loop || first){
                for(i = 0; i < ate; i++){
                    jl.setIcon(imgIcon[i]);
                    
                    if(pontoFinal != null){
                        double dx = xin + (moveX * i);
                        double dy = yin + (moveY * i);

                        jl.setBounds((int)dx, (int)dy, jl.getWidth(), jl.getHeight());
                        if(i == ate-1){
                            dx = pontoFinal.x - jl.getX();
                            dy = pontoFinal.y - jl.getY();
                            jl.setBounds((int)dx + jl.getX(),(int)dy + jl.getY(), jl.getWidth(), jl.getHeight());
                        }
                    }
                    
                    sleep(time);
                }
                first = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restart(){
        this.stop();
        this.start();
    }   
}
