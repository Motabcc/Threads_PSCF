import java.util.Random;
import java.util.concurrent.Semaphore;

class Main {

    public static void main(String[] args){
        Banheiro banheirounico = new Banheiro();

        Pessoas t1 = new Pessoas(banheirounico,1);
        Pessoas t2 = new Pessoas(banheirounico,2);
        Pessoas t3 = new Pessoas(banheirounico,3);
        Pessoas t4 = new Pessoas(banheirounico,4);
        Pessoas t5 = new Pessoas(banheirounico,5);
        Pessoas t6 = new Pessoas(banheirounico,6);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}

class Banheiro{
    //Fair true ele respeita a regra do FIFO ou seja a ordem de chegada

    Semaphore semaphore = new Semaphore(3, true);

    public void entrou(int idPessoa){
        try{
            System.out.println("Pessoa " + idPessoa + " ESPERA na fila do banheiro.");
            semaphore.acquire();
            System.out.println("Pessoa " + idPessoa + " ENTROU NO  banheiro.");

        }
        catch (InterruptedException e){e.printStackTrace();}
    }
    public void sair(int idPessoa){
        System.out.println("🧻 Pessoa " + idPessoa + " SAIU do banheiro.");
        semaphore.release();
    }
}

class Pessoas extends Thread{
    private final Banheiro banheiroCompartilhado;
    private final int idPessoa;

    public Pessoas(Banheiro cabine, int idPessoa){
        this.banheiroCompartilhado = cabine;
        this.idPessoa = idPessoa;
    }

    @Override
    public void run(){
        Random semente = new Random();
        while(true){
            int tempo_aleatorio = semente.nextInt(2001) + 1000;

            banheiroCompartilhado.entrou(this.idPessoa);

            try {
                Thread.sleep(tempo_aleatorio);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            banheiroCompartilhado.sair(this.idPessoa);

            try {
                Thread.sleep(tempo_aleatorio);//para não voltar direto
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

