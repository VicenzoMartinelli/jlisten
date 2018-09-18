package entity;

public class Music {

    private Integer idMusica;
    private Genero genero;
    private String autor;
    private String album;
    private int numFaixa;
    private String titulo;
    private int ano;
    private String caminho;
    private long duracao;

    public Music(Genero genero, String autor, String album, int numFaixa, String titulo, int ano, String caminho, long duracao) {
        this.genero = genero;
        this.autor = autor;
        this.album = album;
        this.numFaixa = numFaixa;
        this.titulo = titulo;
        this.ano = ano;
        this.caminho = caminho;
        this.duracao = duracao;
    }

    public Music(int idMusica, Genero genero, String autor, String album, int numFaixa, String titulo, int ano, String caminho, long duracao) {
        this.idMusica = idMusica;
        this.genero = genero;
        this.autor = autor;
        this.album = album;
        this.numFaixa = numFaixa;
        this.titulo = titulo;
        this.ano = ano;
        this.caminho = caminho;
        this.duracao = duracao;
    }


    public long getDuracao() {
        return duracao;
    }

    public void setDuracao(long duracao) {
        this.duracao = duracao;
    }
    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getnumFaixa() {
        return numFaixa;
    }

    public void setNumFaixa(int numFaixa) {
        this.numFaixa = numFaixa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Integer getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null || ((Music)o).getIdMusica() == null)
            return false;
        return ((Music)o).getIdMusica() == this.getIdMusica();
    }
}
