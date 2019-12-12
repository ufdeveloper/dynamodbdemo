package com.megshan.dynamodb.data;

import com.megshan.dynamodb.domain.Settings;
import com.megshan.dynamodb.domain.SettingsId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by shantanu on 12/09/19.
 */
public interface SettingsRepository extends CrudRepository<Settings, SettingsId> {

    List<Settings> findBySettingsId(List<SettingsId> settingsIdList);
}
