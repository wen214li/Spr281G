package com.example.demo;

import com.example.demo.models.Data;
import com.example.demo.models.Sensor;
import com.example.demo.models.SensorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/")
public class SensorController  {
    @Autowired
    private SensorService sensorService;
    @Autowired
    private MongoTemplate mt;


    @RequestMapping(value = "sensors", method = RequestMethod.GET)
    public List<Sensor> getAllSensors() {
        return sensorService.findAll();
    }


    @GetMapping("/sensor")
    public Sensor findBySensor(@RequestParam String sensor_id){
        return sensorService.findBySensor_id(sensor_id);
    }

    //save one sensor
    @PostMapping("save")
    public Sensor save(@RequestParam String sensor_id,
                       @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date d,
                       @RequestParam double value){
        Data data = new Data(d, value);
        Sensor sensor = sensorService.findBySensor_id(sensor_id);
        if(sensor != null){
            List<Data> dataList = sensor.getData();
            dataList.add(data);
            sensor.setData(dataList);
        }
        else{
            sensor = new Sensor();
            List<Data> dataList = new ArrayList<>();
            dataList.add(data);
            sensor.setData(dataList);
            sensor.setSensor_id(sensor_id);
        }
        return sensorService.save(sensor);
    }

    @PostMapping("saveAll")
    public List<Sensor> saveAll(@RequestParam List<Sensor> sensorList){
        return sensorService.saveAll(sensorList);
    }

}

