package com.spring.andre.demo.service;

import com.spring.andre.demo.dto.RegisterHomeDTO;
import com.spring.andre.demo.model.RegisterHome;
import com.spring.andre.demo.repository.RegisterHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterHomeService {

    @Autowired
   private  RegisterHomeRepository registerHomeRepository;

    public RegisterHome registerHome(RegisterHomeDTO registerHomeDTO){
        RegisterHome home = new RegisterHome();

        home.setAreaBruta(registerHomeDTO.getAreaBruta());
        home.setEstacionamento(registerHomeDTO.getEstacionamento());
        home.setAnoDeConstrucao(registerHomeDTO.getAnoDeConstrucao());
        home.setLocation(registerHomeDTO.getLocation());
        home.setPiso(registerHomeDTO.getPiso());
        home.setQuartos(registerHomeDTO.getQuartos());
        home.setTotalDoLote(registerHomeDTO.getTotalDoLote());
        home.setWcs(registerHomeDTO.getWcs());

        return registerHomeRepository.save(home);

    }

}
