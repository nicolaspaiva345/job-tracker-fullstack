package com.nicolas.jobtracker.controller;

import com.nicolas.jobtracker.entity.StatusVaga;
import com.nicolas.jobtracker.entity.Vaga;
import com.nicolas.jobtracker.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vagas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @PostMapping
    public Vaga cadastrarVaga(@RequestBody Vaga vaga) {
        return vagaService.criarVaga(vaga);
    }

    @GetMapping
    public List<Vaga> listarTodasVagas() {
        return vagaService.listarTodas();
    }

    @PutMapping("/{id}/status")
    public Vaga atualizarStatus(@PathVariable Long id, @RequestBody String novoStatus) {
        // Remove as aspas extras que o JSON envia no texto puro
        String statusLimpo = novoStatus.replace("\"", "").trim();
        return vagaService.atualizarStatus(id, StatusVaga.valueOf(statusLimpo));
    }

    @DeleteMapping("/{id}")
    public void deletarVaga(@PathVariable Long id) {
        vagaService.deletarVaga(id);
    }
}