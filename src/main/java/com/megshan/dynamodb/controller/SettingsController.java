/*
 * Copyright (c) 2019 LogMeIn
 * All Rights Reserved Worldwide.
 *
 * THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO LOGMEIN
 * AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.megshan.dynamodb.controller;

import com.megshan.dynamodb.dto.SettingsResponse;
import com.megshan.dynamodb.enums.Product;
import com.megshan.dynamodb.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @since
 */
@RestController
public class SettingsController {

    private static final String SETTINGS_BASE_PATH = "/v1/settings";
    private static final String GET_SETTINGS_PATH = SETTINGS_BASE_PATH + "/products/{product}/identifiers/{ids}";

    @Autowired
    private SettingsService settingsService;

    @GetMapping(GET_SETTINGS_PATH)
    @ResponseStatus(code = HttpStatus.OK)
    public SettingsResponse getSettings(@PathVariable Product product, @PathVariable String ids) {
        return settingsService.getSettingsForProductAndKeys(product, ids);
    }
}
