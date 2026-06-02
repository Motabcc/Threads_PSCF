
import java.util.Random;

public class My_threads extends Thread {
    public static double[] vetor = new double[200_000_000];
    public static int inicio = 0;
    public static int fim = vetor.length;
    public static int meio = fim/2;

    public void My_threads(double[] vetor,int inicio,int fim, int meio){
        this.vetor =vetor;
        this.inicio = inicio;
        this.fim = fim;
        this.meio = meio;

    }

    public static void main(String[] args){
        System.out.println("Encerro a inicialização...");


        My_threads t1 = new My_threads(vetor,inicio,meio);
        My_threads t2 = new My_threads(vetor,meio,fim);

        t1.start();
        t2.start();
        try{
            for(int i =0 ; i<fim;i++){

            }
            t1.join();
            t2.join();
        }
        catch(InterruptedException e){
            System.out.print(e);
        }

    }

    public void run(){
        Random semente = new Random();
        System.out.println("Iniciando o vetor..");
        for (int i=0; i<vetor.length;i++){
            double valor = semente.nextDouble();
                vetor[i] = valor;
        }
    }
}