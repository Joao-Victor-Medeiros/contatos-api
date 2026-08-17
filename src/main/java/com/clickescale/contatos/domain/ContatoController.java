package com.clickescale.contatos.domain;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    private final ContatoService service;

    public ContatoController(ContatoService service) {
        this.service = service;
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf) {
        try {
            Optional<ContatoDTO> contato = service.buscarPorCpf(cpf);
            if (contato.isPresent()) {
                return ResponseEntity.ok(contato.get());
            } else {
                return ResponseEntity.status(404).body(new ErrorResponse(404, "CPF não encontrado"));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "CPF inválido"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorResponse(500, "erro interno"));
        }
    }
}
