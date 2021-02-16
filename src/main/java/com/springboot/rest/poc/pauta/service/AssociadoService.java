package com.springboot.rest.poc.pauta.service;

import com.springboot.rest.poc.pauta.entity.Associado;
import com.springboot.rest.poc.pauta.error.ErrorResponse;
import com.springboot.rest.poc.pauta.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
public class AssociadoService {

    @Autowired
    AssociadoRepository associadoRepository;

//    @Autowired
//    ConsultarCpf consultarCpf;

    public ResponseEntity<Object> validarCPFAssociado(String cpf) throws IOException {
        StringBuffer content = new StringBuffer();

        URL url = new URL("https://user-info.herokuapp.com/users/" + cpf);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
                System.out.println(inputLine);
            }
            in.close();
            con.disconnect();
        } catch(IOException e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"CPF Invalido!",e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(content.length() > 0){
            int index = 0;
            index = content.indexOf("UNABLE_TO_VOTE");
            if(index > 0){
                return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Valição do CPF: ", content.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return null;
    }

    public ResponseEntity<Object> cadastrarAssociado(Associado associado) throws IOException {

        ResponseEntity<Object> response = this.validarCPFAssociado(associado.getCpf());
        if(response!=null){
            return response;
        }
        //Consumir serviço validar cpf com Feign/Spring Cloud
        //   Cpf cpf = consultarCpf.consultar(associado.getCpf());
        //  if (cpf !=null && !cpf.getStatus().isEmpty()){
        //       return  new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Validação CPF : "+cpf.getStatus()), HttpStatus.INTERNAL_SERVER_ERROR);
        //  }
        Optional<Associado> assoc = associadoRepository.findByCpf(associado.getCpf());
        if(assoc.isPresent()){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"CPF já cadastrado: "+associado.getCpf(),""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(associadoRepository.save(associado), HttpStatus.CREATED);
    }



}
