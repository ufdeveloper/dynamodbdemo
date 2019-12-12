/*
 * Copyright (c) 2019 LogMeIn
 * All Rights Reserved Worldwide.
 *
 * THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO LOGMEIN
 * AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.megshan.dynamodb.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.google.common.base.Splitter;
import com.megshan.dynamodb.enums.Product;
import com.megshan.dynamodb.enums.ResourceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Shantanu Sardal
 * @since 12/10/2019
 */

@Slf4j
@ToString
@DynamoDBTable(tableName = "settings")
public class Settings {

    public Settings() {
        log.info("inside empty constructor");
    }

    public Settings(ResourceType resourceType, String referenceKey, Product product, boolean hasPrice) {
        this.resourceType = resourceType;
        this.referenceKey = referenceKey;
        this.product = product;
        this.hasPrice = hasPrice;
    }

    @Id
    private SettingsId settingsId;

    public static String event(ResourceType resourceType, String referenceKey) {
        return String.join("-", resourceType.toString(), referenceKey);
    }

    @DynamoDBHashKey(attributeName = "event")
    public String getEvent() {
        return event(resourceType, referenceKey);
    }

    public void setEvent(String event) {
        List<String> eventKey = Splitter.on('-').splitToList(event);
        ResourceType resourceType = ResourceType.valueOf(eventKey.get(0));
        String referenceKey = eventKey.get(1);
        this.resourceType = resourceType;
        this.referenceKey = referenceKey;
    }

    @NotNull
    @DynamoDBAttribute
    @DynamoDBTypeConvertedEnum
    private ResourceType resourceType;

    @NotNull
    @DynamoDBAttribute
    private String referenceKey;

    @NotNull
    @DynamoDBRangeKey
    @DynamoDBTypeConvertedEnum
    private Product product;

    @NotNull
    @DynamoDBAttribute(attributeName = "hasPrice")
        private boolean hasPrice;

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getReferenceKey() {
        return referenceKey;
    }

    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isHasPrice() {
        return hasPrice;
    }

    public void setHasPrice(boolean hasPrice) {
        this.hasPrice = hasPrice;
    }
}
