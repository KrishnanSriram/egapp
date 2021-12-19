package com.sb.egapp.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Endpoint(id = "release-notes")
public class ReleaseNotes {
    private Map<String, List<String>> releaseNotes = new HashMap<>();

    @PostConstruct
    public void initNotes() {
        releaseNotes.put("v1.0", Arrays.asList("First version of SpringBoot Person controller", "Simple REST api"));
        releaseNotes.put("v1.1", Arrays.asList("Added YAML configuration", "info from application.properties"));
        releaseNotes.put("v1.2", Arrays.asList("Custom contributor for info", "Custom endpoint for info"));
    }

    @ReadOperation
    public Map<String, List<String>> allReleaseNotes() {
        return releaseNotes;
    }

    @ReadOperation
    public ResponseEntity notesForVersion(@Selector String version) {
        List<String> releaseInfo = releaseNotes.get(version);
        if(releaseInfo != null) {
            return new ResponseEntity(releaseInfo, HttpStatus.OK);
        }
        return new ResponseEntity("No match for release", HttpStatus.OK);
    }
    //@WriteOperation
    //@DeleteOperation
}
