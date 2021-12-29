package com.liumd.develop.controller;

import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liumuda
 * @date 2021/12/23 14:13
 */
@RestController
@RequestMapping("test")
public class testController {

    @RequestMapping("/matchData")
    public List<Map<String, String>> MatchingData(@RequestParam List<Map<String, String>> mapData, @RequestParam List<String> matchData){
        List<Map<String, String>> result = new ArrayList<>();
        matchData.forEach(item -> {
            mapData.forEach(mapItem -> {
                String value = mapItem.get(item);
                if (value != null){
                    Map<String, String> map = new HashMap<>();
                    map.put(value, item);
                    result.add(map);
                }
            });
        });
        return result;
    }


}
