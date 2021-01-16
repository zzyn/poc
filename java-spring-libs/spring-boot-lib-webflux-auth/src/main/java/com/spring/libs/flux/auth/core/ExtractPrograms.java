package com.spring.libs.flux.auth.core;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ExtractPrograms {
    public List<String> execute(List<Map<String, Object>> acls) {
        return acls.stream()
                .map(programAcl -> (String) programAcl.get("program"))
                .collect(Collectors.toList());
    }
}
