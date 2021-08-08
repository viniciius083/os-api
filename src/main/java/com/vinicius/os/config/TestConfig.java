package com.vinicius.os.config;

import com.vinicius.os.domain.Cliente;
import com.vinicius.os.domain.OrdemServico;
import com.vinicius.os.domain.Tecnico;
import com.vinicius.os.enums.Prioridade;
import com.vinicius.os.enums.Status;
import com.vinicius.os.repository.ClienteRepository;
import com.vinicius.os.repository.OrdemServicoRepository;
import com.vinicius.os.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Bean
    public void criarDados(){
        Tecnico tecnico = new Tecnico(null, "Seu zé", "684.010.340-43", "(83) 8888-1111");
        Cliente cliente = new Cliente(null, "Dona Maria", "105.732.670-49", "(83) 8888-3333");
        Cliente c1 =  new Cliente(null, "Dona Zefa", "812.555.490-41", "(83) 8888-2222");
        Tecnico t1 = new Tecnico(null, "Seu Jao", "073.650.730-20", "(83) 8888-4444");

        OrdemServico ordemServico = new OrdemServico(null, LocalDateTime.now(), null, Prioridade.BAIXA, "", Status.ABERTO, tecnico,cliente);

        tecnico.getServicos().add(ordemServico);
        cliente.getServicos().add(ordemServico);

        clienteRepository.saveAll(Arrays.asList(cliente, c1));
        tecnicoRepository.saveAll(Arrays.asList(tecnico, t1));
        ordemServicoRepository.saveAll(Arrays.asList(ordemServico));

    }
}
