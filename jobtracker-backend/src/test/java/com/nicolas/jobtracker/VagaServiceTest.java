package com.nicolas.jobtracker;

import com.nicolas.jobtracker.entity.StatusVaga;
import com.nicolas.jobtracker.entity.Vaga;
import com.nicolas.jobtracker.repository.VagaRepository;
import com.nicolas.jobtracker.service.VagaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class VagaServiceTest {

    @Mock
    private VagaRepository vagaRepository; // Carrinho de mão de brinquedo

    @InjectMocks
    private VagaService vagaService; // O cozinheiro real que vai receber o brinquedo

    @Test
    public void deveDefinirStatusComoInscritoQuandoStatusForNulo() {
        // 1. Cenário: Criamos uma vaga sem nenhum status (nulo)
        Vaga vagaSemStatus = new Vaga();
        vagaSemStatus.setEmpresa("Netflix");
        vagaSemStatus.setCargo("Dev Java");

        // Aqui dizemos ao Mockito: "Quando o repositório tentar salvar qualquer vaga, devolva a própria vaga"
        Mockito.when(vagaRepository.save(any(Vaga.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // 2. Ação: Passamos essa vaga pelo nosso método de criarVaga
        Vaga vagaSalva = vagaService.criarVaga(vagaSemStatus);

        // 3. Validação (O Alarme): O JUnit checa se o sistema aplicou o status correto automaticamente
        assertNotNull(vagaSalva.getStatus());
        assertEquals(StatusVaga.INSCRITO, vagaSalva.getStatus());
    }
}