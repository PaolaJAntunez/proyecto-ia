package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/ia")
@CrossOrigin(origins = "*")
public class IaController {

    private final String API_KEY = "AIzaSyCIwpx-653shEmO1rwAzMbdAIs9YK_0UXg"; // PON TU KEY AQUÍ

    @PostMapping("/gemini")
    public Object procesarTexto(@RequestBody Map<String, String> payload) {
        String prompt = payload.get("prompt");
        String url = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

        RestTemplate restTemplate = new RestTemplate();

        // Creamos un mapa para el cuerpo de la petición (es más seguro que el texto plano)
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", new Object[]{
                Map.of("parts", new Object[]{ Map.of("text", prompt) })
        });

        try {
            // Recibimos la respuesta de Google como un Map
            return restTemplate.postForObject(url, requestBody, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("error", "Error al conectar con Google");
        }
    }
}