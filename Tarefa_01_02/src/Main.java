import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class My_threads extends Thread{
    public static void main (String[] args) {

        for (int i =1 ; i<amostra.length;i++){
            Path caminho = Paths.get("src/amostra/Noticia-"+i+".txt")
            try{
                String conteudo = new String(Files.readAllBytes(caminho));
                System.out.println("CAMINHO: "+caminho+" | CONTEUDO:"+ conte);
            }catch(Exception e ){
                e.printStackTrace();
            }
        }

    }
    public My_threads(){

    }

    @Override
    public run(){

    }
}