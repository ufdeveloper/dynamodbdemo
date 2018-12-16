package com.megshan.dynamodb.data;

import com.megshan.dynamodb.domain.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by shantanu on 11/16/18.
 */
@EnableScan
public interface UserRepository extends CrudRepository<User, String> {

}
