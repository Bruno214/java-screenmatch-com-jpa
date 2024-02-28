package br.com.alura.screenmatch.model;

public enum Categoria {
    ACAO("action"),
    COMEDIA("comedy"),
    ROMANCE("romance"),
    DRAMA("drama"),
    CRIME("crime"),
    DESCONHECIDA("desconhecida");

    public String categoriaOmdb;

    Categoria(String categoria) {
        this.categoriaOmdb = categoria;
    }
    public static Categoria buscarCategoria(String genero) {
        if (genero == null) {
            throw new IllegalArgumentException("Categoria não foi encontrada!");
        }
        String generosSerie = genero.split(",")[0].trim();
        for (Categoria cat : Categoria.values()) {
             if(cat.categoriaOmdb.equalsIgnoreCase(generosSerie)) {
                 return cat;
             }
        }
       throw new IllegalArgumentException("Categoria não foi encontrada!");
    }

    public String getCategoriaOmdb() {
        return categoriaOmdb;
    }

    public void setCategoriaOmdb(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }
}
