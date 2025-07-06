package br.ufop.edu.web2.ticket.user.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ViaCEP {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static String buscarEndereco(String cep) {
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

}
