package com.example.demo;

import com.example.demo.models.Data;
import com.example.demo.models.Sensor;
import com.example.demo.models.SensorRepository;
import org.bson.types.BSONTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorService {
    @Autowired
    private SensorRepository sensorRepository;

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Sensor findBySensor_id(String sensor_id) {
        return sensorRepository.findBySensor_id(sensor_id);
    }

    public Sensor save(Sensor sensor){
        return sensorRepository.save(sensor);
    }

    List<Sensor> saveAll(List<Sensor> sensors){
          return  sensorRepository.saveAll(sensors);
    }


}
