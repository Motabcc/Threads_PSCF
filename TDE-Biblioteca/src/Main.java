import java.util.Random;

class Main{

    public static void main(String[] args){
        Livros bibliounica = new Livros();
        Usuario t1 = new Usuario(bibliounica,1);
        Usuario t2 = new Usuario(bibliounica,2);
        Usuario t3 = new Usuario(bibliounica,3);

        t1.start();
        t2.start();
        t3.start();
    }
}
class Livros{
    //array bool True = Disponivel e False = Indisponivel
    private final boolean[] livros = new boolean[10];

    //para preencher o vetor
    public Livros(){
        for(int i=0;i<livros.length;i++){
            livros[i] = true;
        }

    }

    public void emprestado(int id,int idUser)  {
        //uso compartilhado de memoria
        synchronized (this){
            //se o livro escolhido estiver indisponivel espere
            while(!livros[id]){
                try {
                    System.out.println("Usuário " + idUser + " | ESPERANDO O livro " + (id + 1)+ " ficar disponível.");
                    this.wait();


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            livros[id] = false;
            System.out.println("Usuário " + idUser + " | EMPRESTOU O livro " + (id + 1));

        }
    }


    public void disponivel(int id,int idUser){
        synchronized (this){
                livros[id] = true;
                notifyAll();
                System.out.println("Usuário " + idUser + " | DEVOLVEU livro " + (id + 1));

            }
    }
}


class Usuario extends Thread{
    private final Livros bibliocompartilada ;
    private final int idUser;

    public Usuario(Livros biblio,int idUser){
        this.bibliocompartilada=biblio;
        this.idUser = idUser;
    }

    @Override
    public void run() {
        Random semente = new Random();
        while(true){
            int gerador = semente.nextInt(10);
            int tempoAleatorio = semente.nextInt(1001) + 1000;
            try{
                bibliocompartilada.emprestado(gerador,idUser);
                sleep(tempoAleatorio);
                bibliocompartilada.disponivel(gerador,idUser);
                sleep(tempoAleatorio);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}