package com.example.demo.models;

import org.bson.types.BSONTimestamp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, ObjectId> {
    @Query("{'_id': ?0}")
    Sensor findBy_id(ObjectId id);

    List<Sensor> findAll();

    @Query("{'_id': ?0, '_id': ?1}")
    List<Data> findBy_id_And_id(String _id, BSONTimestamp time );



}

