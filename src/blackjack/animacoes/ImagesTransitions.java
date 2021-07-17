
package blackjack.animacoes;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;

public class ImagesTransitions {
    private List<ImageIcon> photo = new ArrayList<>();
    private int scale;


    
    public ImagesTransitions(String[] photos) throws Exception{
        setImages(Arrays.asList(photos));
    }
    
    public ImagesTransitions(List<String> photos) throws Exception{
        setImages(photos);
    }
    
    public ImagesTransitions(String str, int inicio, int fim) throws Exception{
        stringToList(str, inicio, fim);
    }
    
    public double getScale() {
        return scale;
    }

    public void setScale(int scale) {
        if(scale < 0) throw new RuntimeException("A escala não pode ser menor que 0");
        
        for(ImageIcon imageIcon: photo)
            imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth() * scale, imageIcon.getIconHeight() * scale, Image.SCALE_DEFAULT));
        
    }
    
    /*
      Recebe por parâmetro um range, por exemplo 1...10 e uma string, esta string devera conter # (jogo da velha, hashtag)
      No lugar do # será colocado um contador que varia de acordo o range recebido, por exemplo caso os parâmetros da função seja: str="photo#.png", inicio=1, fim=5 o resultado será
    
        photo1.png
        photo2.png
        photo3.png
        photo4.png
        photo5.png
    
    
      Essas fotos serão automaticamente adicionadas na lista
    */
    public void stringToList(String str, int inicio, int fim) throws Exception{
        List<String> pathImgs = new ArrayList<>(fim-inicio+1);
        
        if(!str.contains("#")) throw new RuntimeException("A String informada não possui o caracter # (jogo da velha, hashtag)");
        
        if(inicio > fim) throw new RuntimeException("O valor do início é maior que o fim: " + inicio + " > " + fim);
        
        while(inicio++ <= fim)
            pathImgs.add(str.replace("#", "" + (inicio-1)));
        
        
        System.out.println(pathImgs.toString());
        setImages(pathImgs);
    }
    
    public void setImages(List<String> photos) throws Exception{
        for(String local: photos)
            if(!new File(local).exists())
                throw new Exception("O arquivo: " + local + " não foi encontrado");
        
        for(String local: photos)
            photo.add(new ImageIcon(local));
    }
    
    public List<ImageIcon> getImages(){
        return photo;
    }
    
    public ImageIcon[] getImagesAsArray(){
        ImageIcon[] img = new ImageIcon[photo.size()];
        for(int i = 0; i < img.length; i++)
            img[i] = photo.get(i);
        
        return img;
    }
}
