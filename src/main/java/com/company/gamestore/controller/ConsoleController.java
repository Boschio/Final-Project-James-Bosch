package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {
    @Autowired
    private ConsoleRepository consoleRepository;

    @PostMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody  @Valid Console console){
        return consoleRepository.save(console);
    }

    @GetMapping("/console")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles(){
        return consoleRepository.findAll();
    }

    @GetMapping("/console/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable int id){
        Optional<Console> returnVal = consoleRepository.findById(id);
        return returnVal.isPresent() ? returnVal.get() : null;
    }

    @GetMapping("/console/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> shouldGetConsoleByManufacturer(@PathVariable String manufacturer){
        return consoleRepository.findByManufacturer(manufacturer);
    }
    @PutMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsoleById(@PathVariable int id){
        Optional<Console> resultConsole = consoleRepository.findById(id);
        if (resultConsole.isPresent()) {
            Console returnVal = resultConsole.get();
            Console updatedConsole = new Console(
                returnVal.getModel(), returnVal.getManufacturer(), returnVal.getMemoryCount(),
                    returnVal.getProcessor(), returnVal.getPrice(), returnVal.getQuantity()
            );
            updatedConsole.setId(returnVal.getId());
            consoleRepository.save(updatedConsole);
        }
    }

    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void shouldDeleteConsole(@PathVariable int id){
        Optional<Console> returnVal = consoleRepository.findById(id);
        if(returnVal.isPresent()){
            consoleRepository.deleteById(id);
        }
    }


}
