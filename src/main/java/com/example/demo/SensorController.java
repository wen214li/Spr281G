package com.example.demo;

import com.example.demo.models.Sensor;
import com.example.demo.models.SensorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class SensorController  {
    @Autowired
    private SensorRepository repository;
    @Autowired
    private MongoTemplate mt;


    @RequestMapping(value = "sensors", method = RequestMethod.GET)
    public List<Sensor> getAllSensors() {
        return repository.findAll();
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public List<Sensor> getAllSensors(@Param("id") ObjectId id) {
//        return repository.findAll();
//    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Sensor getPetById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }
}

