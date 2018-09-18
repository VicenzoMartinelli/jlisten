package entity;

public class Genero {

    private Integer idGenero;
    private String nome;

    public Genero() {
    }

    public Genero(Integer id, String genero) {
        this.idGenero = id;
        this.nome = genero;
    }

    public Genero(String nome) {
        this.nome = nome;
    }
    
    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String toString()
    {
        return this.getNome();
    }

    @Override
    public boolean equals(Object o) {
        if((Genero)o == null || ((Genero)o).getIdGenero() == null)
            return false;
        return ((Genero)o).getIdGenero() == this.getIdGenero();
    }
}
