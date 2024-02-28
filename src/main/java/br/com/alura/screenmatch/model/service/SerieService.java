package br.com.alura.screenmatch.model.service;

import br.com.alura.screenmatch.util.SerieMapper;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.vo.SerieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    @Autowired
    private SerieMapper serieMapper;

    public void salvarSerie (Serie serie) {
        serieRepository.save(serie);
    }

    public List<SerieVO> listarSeries() {

        List<Serie> series = serieRepository.findAll();
        return series.stream().map(serie -> serieMapper.converterSerieEmSerieVO(serie)).collect(Collectors.toList());

    }
}
