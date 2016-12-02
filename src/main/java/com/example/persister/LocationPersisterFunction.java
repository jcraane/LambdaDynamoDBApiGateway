package com.example.persister;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.UUID;

/**
 * Created by jamiecraane on 02/12/2016.
 */
public class LocationPersisterFunction implements RequestHandler<DeviceLocation, Void> {
    @Override
    public Void handleRequest(final DeviceLocation input, final Context context) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.EU_CENTRAL_1); // specify the region you created the table in.
        final DynamoDB dynamoDB = new DynamoDB(client);

        System.out.println("input = " + input); // Pure for testing. Do not use System.out in production code
        final Table table = dynamoDB.getTable("DeviceLocation");
        final Item item = new Item()
                .withPrimaryKey("id", UUID.randomUUID().toString()) // Every item gets a unique id
                .withString("deviceId", input.getDeviceId())
                .withDouble("lat", input.getLat())
                .withDouble("lng", input.getLng());

        table.putItem(item);
        return null;
    }
}
