package com.megshan.dynamodb.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.megshan.dynamodb.enums.Product;
import com.megshan.dynamodb.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public class SettingsId implements Serializable {
    private static final long serialVersionUID = 1L;

    public SettingsId() {
        log.info("seetingsId");
    }

    public SettingsId(ResourceType resourceType, String referenceKey, Product product) {
        this.resourceType = resourceType;
        this.referenceKey = referenceKey;
        this.product = product;
    }


    public static String event(ResourceType resourceType, String referenceKey) {
        return String.join("-", resourceType.toString(), referenceKey);
    }

    @DynamoDBHashKey
    public String getEvent() {
        return event(resourceType, referenceKey);
    }

    public void setEvent(String any) {
        // no-op; this method is required by DynamoDbMapper
    }

    @DynamoDBAttribute
    @DynamoDBTypeConvertedEnum
    private ResourceType resourceType;

    @DynamoDBAttribute
    private String referenceKey;

    @DynamoDBRangeKey
    @DynamoDBTypeConvertedEnum
    private Product product;

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
}