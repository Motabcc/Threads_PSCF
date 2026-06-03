import java.util.random


class Conta extends Thread{
    private long valor;

    public Conta(long valor){
        this.valor =valor
    }

    public double saque(long valor){
        return saldo-= valor;
    }

    public void deposito(long valor){
        synchronized(this){
         saldo += valor;
        }
    }

    public double saldo (){
        return  saldo()
    }



}
class Threads_Saque{
    public void main(String[] args){

        Conta t1_saque =new Conta(saque(semente));
        Conta t2_saque =new Conta(saque(semente));
        Conta t3_saque =new Conta(saque(semente));


        t1_saque.start();
        t2_saque.start();
        t3_saque.start();

    }
    public void run(){
    }
}

class Threads_Deposito extends Threads{
    public void main(String[] args){

        Conta t1_deposito =new Conta(deposito(semente));
        Conta t2_depostio =new Conta(deposito(semente));

        t1_deposito.start();
        t2_deposito.start();

    }
    public void run(){
    }

}