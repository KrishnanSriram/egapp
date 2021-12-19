package com.sb.egapp.contributor;

import com.sb.egapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PersonInfoContributor implements InfoContributor {

    @Autowired
    private PersonService personService;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Integer> personStatus = new HashMap<>();
        personStatus.put("People count", personService.getPeopleCount());
        personStatus.put("Male population", personService.getMalePopulationCount());
        personStatus.put("Female population", personService.getFemalePopulationCount());
        builder.withDetail("personStatus", personStatus);
    }
}
