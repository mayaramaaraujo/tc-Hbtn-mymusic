package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.autenticador.Autenticador;
import com.ciandt.summit.bootcamp2022.dto.MusicaDTO;
import com.ciandt.summit.bootcamp2022.response.ResponseHandler;
import com.ciandt.summit.bootcamp2022.service.MusicaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/musicas")
public class MusicController {

    private static final Logger logger = LoggerFactory.getLogger(MusicaController.class);

    @Autowired
    MusicaService musicaService;

    @Autowired
    Autenticador autenticador;

    @GetMapping
    @ResponseStatus()
    public ResponseEntity<?> buscar(@RequestHeader(value = "authorization",required = false) String token,
                                    @RequestHeader(value = "usuario",required = false) String nomeUsuario,
                                    String filtro) throws Exception {
        autenticador.autenticar(nomeUsuario, token);

        logger.info("Requisição para listar músicas. Usuário: " + nomeUsuario);

        List<MusicaDTO> musicas = musicaService.buscar(filtro);

        return ResponseHandler.generateResponse(HttpStatus.OK, musicas);
    }

}
