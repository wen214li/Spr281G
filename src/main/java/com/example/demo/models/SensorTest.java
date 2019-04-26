package com.example.demo.models;

import com.example.demo.SensorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorTest {
    @Autowired
    private SensorService sensorService;
    @Test
    public void saveData(){
        Sensor sensor = new Sensor();
        sensor.setSensor_id("yearly");
        List<Data> dataList = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            Data data = new Data();
            Date date = new Date();
            long t = date.getTime();
            Date d = new Date(t - 60000 * 60  * 24 * 30 * ( 12 -i));
            data.setDate(d);
//            data.setValue( 40 + Math.sin(Math.PI * 2 / 24  * (i % 24)) + Math.random() *0.5 - 0.25 );
            data.setValue(40 + Math.random() - 0.5);
            dataList.add(data);
        }

        sensor.setData(dataList);
        sensorService.save(sensor);
    }
}
