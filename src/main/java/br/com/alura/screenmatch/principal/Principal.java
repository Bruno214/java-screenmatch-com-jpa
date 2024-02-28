package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.model.service.ConsumoApi;
import br.com.alura.screenmatch.model.service.ConverteDados;
import br.com.alura.screenmatch.model.service.SerieService;
import br.com.alura.screenmatch.vo.SerieVO;

import java.util.*;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private List<DadosSerie> dadosSeries = new ArrayList<>();
    private SerieService serieService;

    public Principal() {}

    public Principal(SerieService serieService) {
        this.serieService = serieService;
    }

    public void exibeMenu() {
        while (true) {

            var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar séries Buscadas
                
                0 - Sair                                
                """;

            System.out.println(menu);
            var opcao = leitura.nextLine();

            switch (opcao) {
                case "1" -> buscarSerieWeb();
                case "2" -> buscarEpisodioPorSerie();
                case "3" -> listarSeriesBuscadas();
                case "0" -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }

            if (opcao.equals("0")) break;
        }
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        this.dadosSeries.add(dados);
        serieService.salvarSerie(new Serie(dados));
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    private void listarSeriesBuscadas() {

        List<SerieVO> series = serieService.listarSeries();

        if (series.isEmpty()) {
            System.out.println("nenhuma série salva, pode salvar uma nova pelo menu com a opção '1'");
        }

        series.stream().sorted(Comparator.comparing(SerieVO::genero)).forEach(System.out::println);
    }

}