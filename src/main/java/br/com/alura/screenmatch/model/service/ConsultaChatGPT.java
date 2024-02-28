package br.com.alura.screenmatch.model.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;

public class ConsultaChatGPT {

    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService(System.getenv("API_KEY_CHAT_GPT"));
        List<ChatMessage> messages = List.of(new ChatMessage( "user", "traduza para o português o texto: " + texto));

        ChatCompletionRequest requisicao = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo-0125")
                .messages(messages)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createChatCompletion(requisicao);
        return resposta.getChoices().get(0).getMessage().getContent();
    }
}
