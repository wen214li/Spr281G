package com.example.demo.models;

import com.mongodb.internal.connection.Time;
import lombok.*;
import org.bson.BsonDateTime;
import org.bson.types.BSONTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Data {

    private Date date;
    private double value;
}
