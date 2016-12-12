package com.example.persister;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Created by jamiecraane on 12/12/2016.
 */
public class RetrieveLocationFunction implements RequestHandler<RetrieveLocationRequest, RetrieveLocationResponse>{

    @Override
    public RetrieveLocationResponse handleRequest(final RetrieveLocationRequest input, final Context context) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new EnvironmentVariableCredentialsProvider());
        client.withRegion(Regions.EU_CENTRAL_1); // specify the region you created the table in.
        final DynamoDB dynamoDB = new DynamoDB(client);

        System.out.println("input = " + input); // Pure for testing. Do not use System.out in production code

        final Table table = dynamoDB.getTable("DeviceLocation");
        final Index index = table.getIndex("deviceId-index");
        final ItemCollection<QueryOutcome> items = index.query("deviceId", input.getDeviceId());

        final RetrieveLocationResponse response = new RetrieveLocationResponse();
        for (final Item item : items) {
            final DeviceLocation deviceLocation = new DeviceLocation();
            deviceLocation.setDeviceId(item.getString("deviceId"));
            deviceLocation.setLat(item.getDouble("lat"));
            deviceLocation.setLng(item.getDouble("lng"));
            response.getLocations().add(deviceLocation);
        }

        return response;
    }
}
