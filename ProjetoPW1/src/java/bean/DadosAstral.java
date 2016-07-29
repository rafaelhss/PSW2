package bean;


import java.util.ArrayList;

public class DadosAstral {
    
    private String nome;
    private String data;
    private int numeroSorte;
    private String signo;
    private String enderecoImagem;
    private String nomeCriptografado;
    private ArrayList<String> listaPrevisoes;
    private ArrayList<String> listaPrevisoesUsuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getListaPrevisoesUsuario() {
        return listaPrevisoesUsuario;
    }

    public void setListaPrevisoesUsuario(ArrayList<String> listaPrevisoesUsuario) {
        this.listaPrevisoesUsuario = listaPrevisoesUsuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getNumeroSorte() {
        return numeroSorte;
    }

    public void setNumeroSorte(int numeroSorte) {
        this.numeroSorte = numeroSorte;
    }

    public String getSigno() {
        return signo;
    }

    public void setSigno(String signo) {
        this.signo = signo;
    }

    public String getEnderecoImagem() {
        return enderecoImagem;
    }

    public void setEnderecoImagem(String enderecoImagem) {
        this.enderecoImagem = enderecoImagem;
    }

    public String getNomeCriptografado() {
        return nomeCriptografado;
    }

    public void setNomeCriptografado(String nomeCriptografado) {
        this.nomeCriptografado = nomeCriptografado;
    }
    
    public ArrayList<String> getListaPrevisoes() {
        return listaPrevisoes;
    }

    public void setListaPrevisoes(ArrayList<String> listaPrevisoes) {
        this.listaPrevisoes = listaPrevisoes;
    }    
}
