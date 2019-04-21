package com.example.demo.models;

import org.bson.types.BSONTimestamp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, ObjectId> {
    @Query("{'sensor_id': ?0}")
    Sensor findBySensor_id(String sensor_id);

    List<Sensor> findAll();

//    @Query("{'_id': ?0, '_id': ?1}")
//    List<Data> findBy_id(String _id);
//

}

