package br.com.alura.screenmatch.util;

import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.vo.SerieVO;
import org.springframework.stereotype.Component;

@Component
public class SerieMapper {

    public SerieVO converterSerieEmSerieVO(Serie serie) {
        return new SerieVO(serie.getTitulo(), serie.getTotalTemporadas(), serie.getAvaliacao(), serie.getPoster(), serie.getGenero());
    }
}
