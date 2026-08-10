package com.clickescale.contatos.domain;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContatoService {
    private final ContatoRepository repository;

    public ContatoService(ContatoRepository repository) {
        this.repository = repository;
    }

    public Optional<ContatoDTO> buscarPorCpf(String cpf) {

        String cpfNormalizado = cpf.replaceAll("\\D", "");

        if (cpfNormalizado.length() != 11) {
            throw new IllegalArgumentException("CPF inválido");
        }

        return repository.buscarPorCpf(cpfNormalizado);
    }
}
