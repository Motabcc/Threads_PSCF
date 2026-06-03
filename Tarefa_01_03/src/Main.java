import java.util.Random;

class Conta {
    private  double valor;
    private double saldo;


    public Conta(double valor){
        this.valor =valor;
    }
    public void saque(double valor){
        synchronized (this){
            while(this.saldo <valor){
                try{
                System.out.println("[SAQUE RECUSADO] SALDO ATUAL: R$"+String.format("%.2f",saldo) +" | TENTATIVA: "+ String.format("%.2f",valor));
                this.wait();}
                catch (InterruptedException e){e.printStackTrace(); }
            }
            saldo-= valor;
            System.out.println("[SAQUE REALIZADO] SALDO ATUAL: R$"+String.format("%.2f",saldo) +" | VALOR SACADO: "+ String.format("%.2f",valor));
        }
    }
    public void deposito(double valor){
        synchronized(this){
         saldo += valor;
         System.out.println("[DEPOSITO] SALDO ATUAL: "+String.format("%.2f",saldo) +" | VALOR DEPOSITADO: "+ String.format("%.2f",valor));

         this.notifyAll();
        }
    }
    public double saldo (){
        return  this.saldo;
    }
}
class Threads_Saque extends Thread{
    private  Conta contaCompartilhda;

    public Threads_Saque(Conta conta){
        this.contaCompartilhda = conta;
    }
    public void run(){
        Random semente = new Random();

        while(true){
            double valor_random = semente.nextDouble() *100;
            contaCompartilhda.saque(valor_random);
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Threads_Deposito extends Thread{
    private Conta contaCompartilhada;
    public Threads_Deposito(Conta conta){
        this.contaCompartilhada =conta;
    }

    public void run(){
        Random semente = new Random();

        while(true){
            double valor_random = semente.nextDouble() *100;
            contaCompartilhada.deposito(valor_random);
            try{
            Thread.sleep(100);
            } catch(InterruptedException e ){
                e.printStackTrace();
            }
        }
    }
}

class Main{
    public static void main(String[] args){
        Conta contaPrincipal = new Conta(0.0);

        Threads_Saque t1_saque = new Threads_Saque(contaPrincipal);
        Threads_Saque t2_saque = new Threads_Saque(contaPrincipal);
        Threads_Saque t3_saque = new Threads_Saque(contaPrincipal);

        Threads_Deposito t1_deposito =new Threads_Deposito(contaPrincipal);
        Threads_Deposito t2_deposito =new Threads_Deposito(contaPrincipal);

        t1_saque.start();
        t2_saque.start();
        t3_saque.start();

        t1_deposito.start();
        t2_deposito.start();

    }
}