package br.com.alura.screenmatch.vo;


import br.com.alura.screenmatch.model.Categoria;

public record SerieVO(String titulo, Integer totalTemporadas, Double avaliacao, String poster, Categoria genero) {

    @Override
    public String toString() {
        return "genero=" + genero +
                ", titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacao=" + avaliacao +
                ", poster='" + poster;
    }
}
