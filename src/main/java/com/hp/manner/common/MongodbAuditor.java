package com.hp.manner.common;

import com.hp.manner.model.User;
import com.hp.manner.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("mongodbAuditor")
public class MongodbAuditor implements AuditorAware<User> {

    private static final Logger logger = LoggerFactory.getLogger(MongodbAuditor.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getCurrentAuditor() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        //String email = "yao.xiao@hp.com";
        logger.info("Current Auditor is " + email);
        return userRepository.findByEmail(email);
    }
}
