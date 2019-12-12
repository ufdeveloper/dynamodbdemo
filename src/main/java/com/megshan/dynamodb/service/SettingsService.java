package com.megshan.dynamodb.service;

import com.megshan.dynamodb.dto.SettingsResponse;
import com.megshan.dynamodb.enums.Product;

public interface SettingsService {

    SettingsResponse getSettingsForProductAndKeys(Product product, String keys);
}
