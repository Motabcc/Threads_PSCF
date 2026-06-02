import java.util.Random;

class My_threads extends Thread {
    public static double[] vetor = new double[200_000_000];

    public int inicio;
    public int fim;

    public My_threads(int inicio, int fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public static void main(String[] args) {
        int tamanho = vetor.length;
        int meio = tamanho / 2;

        My_threads t1 = new My_threads(0, meio);
        My_threads t2 = new My_threads(meio, tamanho);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();

            System.out.println("Encerrou inicializacao");

            int contagemTotal = 0;
            for (double valor : vetor) {
                if (valor > 0.25 && valor < 0.75) {
                    contagemTotal++;
                }
            }

            System.out.println("Quantidade de valores entre 0.25 e 0.75: " + contagemTotal);

        } catch (InterruptedException e) {
            System.out.print(e);
        }
    }

    @Override
    public void run() {
        Random semente = new Random();
        for (int i = inicio; i < fim; i++) {
            vetor[i] = semente.nextDouble();
        }
    }
}