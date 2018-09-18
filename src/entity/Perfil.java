package entity;

public class Perfil {

    private Integer id;
    private String nome;
    private String descricao;
    private Genero generoUm;
    private Genero generoDois;
    private Genero generoTres;

    public Perfil() {
    }

    public Perfil(Integer id, String perfil) {
        this.id = id;
        this.nome = perfil;
    }

    public Perfil(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Perfil(String perfil, String descricao) {
        this.descricao = descricao;
        this.nome = perfil;
    }

    public Perfil(Integer id, String nome, String descricao, Genero generoUm, Genero generoDois, Genero generoTres) {
        this.id         = id;
        this.nome       = nome;
        this.descricao  = descricao;
        this.generoUm   = generoUm;
        this.generoDois = generoDois;
        this.generoTres = generoTres;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Genero getGeneroUm() {
        return generoUm;
    }

    public void setGeneroUm(Genero generoUm) {
        this.generoUm = generoUm;
    }

    public Genero getGeneroDois() {
        return generoDois;
    }

    public void setGeneroDois(Genero generoDois) {
        this.generoDois = generoDois;
    }

    public Genero getGeneroTres() {
        return generoTres;
    }

    public void setGeneroTres(Genero generoTres) {
        this.generoTres = generoTres;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString()
    {
        return this.getNome();
    }

}
