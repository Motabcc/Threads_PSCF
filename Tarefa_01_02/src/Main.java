import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class My_threads extends Thread {
    //1.Parametrosd para as Threads
    private int inicio;
    private int fim;
    //2. vetor para a contagem
    private long[] contacaracter = new long[26];

    //classe Builder
    public My_threads(int inicio, int fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    //classe get
    public long[] getContacaracter() {
        return contacaracter;
    }

    public static void main(String[] args) {
        File pasta = new File("Tarefa_01_02\\amostra");
        File[] listaArquivos = pasta.listFiles((dir, nome) -> nome.toLowerCase().endsWith(".txt"));

        if (listaArquivos == null) return;

        int tamanho = listaArquivos.length;

        My_threads t1 = new My_threads(1, 20);
        My_threads t2 = new My_threads(20 + 1, 40);
        My_threads t3 = new My_threads(40 + 1, 60);
        My_threads t4 = new My_threads(60 + 1, 80);
        My_threads t5 = new My_threads(80 + 1, tamanho);



        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();



        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();


        }catch(InterruptedException e){
            e.printStackTrace();
        }

        long[] totalConsolidado = new long[26];
        long[] resultadoT1 = t1.getContacaracter();
        long[] resultadoT2 = t2.getContacaracter();
        long[] resultadoT3 = t3.getContacaracter();
        long[] resultadoT4 = t4.getContacaracter();
        long[] resultadoT5 = t5.getContacaracter();


        for (int i = 0; i < 26; i++) {
            totalConsolidado[i] = resultadoT1[i] + resultadoT2[i] + resultadoT3[i]+resultadoT4[i]+resultadoT5[i];
        }

        System.out.println("\nRESULTADO CONTAGEM ");
        for (int i = 0; i < 26; i++) {
            char letra = (char) ('A' + i);
            System.out.println(letra + ": " + totalConsolidado[i]);
        }
    }

    @Override
    public void run() {
        //abre a pasta amostra e abre cada noticia dela.
        for (int i = inicio; i <= fim; i++) {
            Path caminho = Paths.get("Tarefa_01_02\\amostra\\Noticia-" + i + ".txt");
            try {
                String conteudoDoArquivo = new String(Files.readAllBytes(caminho));
                System.out.println("CAMINHO ATUAL: " + caminho + " | CONTEUDO: " + conteudoDoArquivo);

                String conteudo = conteudoDoArquivo.toUpperCase();

                //guarda a quantidade de vezes de caracter na ordem da tabela ascci
                for (int j = 0; j < conteudo.length(); j++) {
                    char c = conteudo.charAt(j);
                    if (c >= 'A' && c <= 'Z') {
                        contacaracter[c - 'A']++;
                    }
                }

            } catch (Exception e) {
                System.out.println("Erro ao ler o arquivo Noticia-" + i + ": " + e.getMessage());
            }
        }
    }
}