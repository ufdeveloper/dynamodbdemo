package com.megshan.dynamodb.service;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.megshan.dynamodb.data.SettingsRepository;
import com.megshan.dynamodb.domain.Settings;
import com.megshan.dynamodb.domain.SettingsId;
import com.megshan.dynamodb.dto.SettingsResponse;
import com.megshan.dynamodb.enums.Product;
import com.megshan.dynamodb.enums.ResourceType;
import com.megshan.dynamodb.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.isTrue;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.springframework.util.Assert.notNull;

@Slf4j
@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingsRepository settingsDao;

    public SettingsResponse getSettingsForProductAndKeys(Product product, String ids) {

        notNull(product, "product cannot be null");
        isTrue(isNotBlank(ids), "ids string cannot be empty or null");

        log.info("received getSettings request for product={}, ids={}", product, ids);

        List<String> idsList = Splitter.on(',').trimResults().omitEmptyStrings().splitToList(ids);
        List<SettingsId> settingsIdList = getSettingsIdsList(idsList, product);

        List<Settings> settingsList = Lists.newArrayList(settingsDao.findAll(settingsIdList));

        if(CollectionUtils.isEmpty(settingsList)) {
            log.error("settings not found for product={} and ids={}", product, ids);
            throw new NotFoundException("settings not found");
        }

        // merge settings into a singular response object
        return getMergedSettings(settingsList);
    }

    protected List<SettingsId> getSettingsIdsList(List<String> idsList, Product product) {

        List<SettingsId> settingsIdList = new ArrayList<>();
        idsList.forEach(id -> {
            List<String> eventKey = Splitter.on('-').splitToList(id);
            ResourceType resourceType = ResourceType.valueOf(eventKey.get(0));
            String referenceKey = eventKey.get(1);
            settingsIdList.add(new SettingsId(resourceType, referenceKey, product));
        });

        return settingsIdList;
    }

    protected SettingsResponse getMergedSettings(List<Settings> settingsList) {

        // for now, we expect only one object in the list
        if(settingsList.size() > 1) {
            log.error("Hierarchical settings not supported yet");
            throw new IllegalStateException("Hierarchical settings not supported yet");
        }

        SettingsResponse settingsResponse = SettingsResponse.builder().hasPrice(settingsList.get(0).isHasPrice()).build();
        log.info("successfully created settingsResponse={}", settingsResponse);

        return settingsResponse;
    }
}
