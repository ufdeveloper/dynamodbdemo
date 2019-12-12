package com.megshan.dynamodb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SettingsResponse {
    private boolean hasPrice;
}
