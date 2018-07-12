package com.capgemini.airplanes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/")
public class RouteController {

    @RequestMapping(value="airplanes", method = RequestMethod.GET)
    public String airplanes(Map<String, Object> model) {
        return "airplanes";
    }

}