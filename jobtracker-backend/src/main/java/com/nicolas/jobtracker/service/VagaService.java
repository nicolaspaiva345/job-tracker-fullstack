package com.nicolas.jobtracker.service;

import com.nicolas.jobtracker.entity.StatusVaga;
import com.nicolas.jobtracker.entity.Vaga;
import com.nicolas.jobtracker.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    public Vaga criarVaga(Vaga novaVaga) {
        if (novaVaga.getStatus() == null) {
            novaVaga.setStatus(StatusVaga.INSCRITO);
        }
        return vagaRepository.save(novaVaga);
    }

    public List<Vaga> listarTodas() {
        return vagaRepository.findAll();
    }

    public Vaga atualizarStatus(Long id, StatusVaga novoStatus) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
        vaga.setStatus(novoStatus);
        return vagaRepository.save(vaga);
    }

    public void deletarVaga(Long id) {
        if (!vagaRepository.existsById(id)) {
            throw new RuntimeException("Vaga não encontrada");
        }
        vagaRepository.deleteById(id);
    }
}

