package com.spring.andre.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.spring.andre.demo.model.Client;

@DataJpaTest
@Test
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Order(2)
    void findByUsername() {
        //given
        String clientUsername = "andre";

        //when
        Client expected = clientRepository.findByUsername(clientUsername);

        //then
        assertThat(expected); //rever o problem do isTrue, sem isso o teste vale o mesmo que nada. IMPORTANTE
    }

    @Test
    @Order(1)
    void findByEmail() {

        long id = 1;
        String name = "andre";
        String password = "andre";
        String username = "andrefr770";
        String email = "andre.ferreira@decode.pt";
        Client client = new Client(id, name, password, username, email);

        clientRepository.save(client);

        String expected = clientRepository.findByEmail(email);

        assertThat(expected).isEqualTo(email);

    }

    @Test
    @Order(3)
    void findById() {
        long id = 1;
        assertThat(clientRepository.findById(id)).isEqualTo(1);
    }

}