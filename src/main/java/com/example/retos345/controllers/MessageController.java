package com.example.retos345.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retos345.entities.Message;
import com.example.retos345.services.MessageService;

@Service
@RestController
@RequestMapping("api/Message")
public class MessageController {    

    @Autowired
    MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getMessages(){
        return new ResponseEntity<List<Message>>(this.messageService.getListMessages(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable("id") int id){
        return new ResponseEntity<Message>(this.messageService.getMessage(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> crearMessage(@RequestBody Message message){
        return new ResponseEntity<Message>(this.messageService.crearMessage(message), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMessage(@PathVariable("id") int id){
        this.messageService.eliminarMessage(id);
        return new ResponseEntity<String>("Message Eliminado", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<String> actualizarMessage(@RequestBody Message message){
        this.messageService.actualizarMessage(message.getId(), message);
        return new ResponseEntity<String>("Message Actualizado", HttpStatus.CREATED);
    }
    

}
