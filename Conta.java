package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conta implements IDado {

    private int numero;
    private String cpf;
    private Double saldo_inicial;;
    Lista listaOperacoes = new Lista(); // lista de clientes que operacionaram nessa conta

    public Conta(int numero, String cpf, double saldo_inicial) {
        this.numero = numero;
        this.cpf = cpf;
        this.saldo_inicial = saldo_inicial;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(double saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    @Override
    public String getID() {
        return cpf;
    }

    public Lista getListaOperacoes() {
        return listaOperacoes;
    }

    public void setListaOperacoes(Lista listaOperacoes) {
        this.listaOperacoes = listaOperacoes;
    }

    public int compareTo(IDado o) {
        Conta aux = (Conta) o;
        return Integer.compare(aux.numero, this.numero);
    }

    @Override
    public boolean equals(Object obj) {
        Cliente aux = (Cliente) obj;
        return (aux.cpf == this.cpf);
    }

    @Override
    public String toString() {
        return ("Número da Conta: " + this.numero + " \tCPF: " + this.cpf + "\tSaldo inicial: R$ "
                + this.saldo_inicial);
    }

    public void addNasOperacoes(Operacoes operacoes, Conta conta) {

        Cliente cliente = (Cliente) app.arvoreCliente.pesquisar(conta.getID());

        // DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // String dataFormatada = dateFormat.format(operacoes.getData());

        Elemento novo = new Elemento(operacoes);
        /*
         * System.out.println("operação do dia " + dataFormatada +
         * " adicionada na conta " + operacoes.getNumero_Conta() + " codigo: " +
         * operacoes.getCod_Operacao() + " id: " + operacoes.getID() + " valor: " +
         * operacoes.getValor());
         */
        listaOperacoes.Inserir(novo);

        cliente.addNasOperacoes(conta);

    }

    /*
     * gerar um extrato, dos clientes que acessaram a conta e suas operacoes
     */
    public String Extrato_Conta() {
        Elemento aux = listaOperacoes.prim.prox;
        Operacoes operacao;
        String extrato = "";

        while (aux != null) {
            operacao = (Operacoes) aux.meuDado;
            extrato += operacao.toString() + "\n\n";
            aux = aux.prox;
        }
        return extrato;
    }

    /*
     * gerar um extrato, dos clientes que acessaram a conta e suas operaçoes no
     * periodo de datas escolhido pelo cliente
     */
    public String Extrato_Conta_Data(Date dia) {
        Elemento aux = listaOperacoes.prim.prox;
        Operacoes operacao;

        String extrato = "";

        while (aux != null) {
            operacao = (Operacoes) aux.meuDado;

            if (dia.compareTo(operacao.data) >= 0) {

                extrato += operacao.toString() + "\n\n";
            }

            aux = aux.prox;
        }
        return extrato;
    }

}