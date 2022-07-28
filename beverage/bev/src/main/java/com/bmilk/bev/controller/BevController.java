package com.bmilk.bev.controller;

import com.bmilk.bev.domain.Beverage;
import com.bmilk.bev.service.BevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BevController {

    private final BevService bevService;
    private Beverage beveragetemp;

    public BevController(BevService bevService) {
        this.bevService = bevService;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        List<Beverage> beverages = bevService.findBevs();
        model.addAttribute("beverages", beverages);
        return "home";
    }

    @RequestMapping(value = "/Subd")
    public String bevDetail(@RequestParam String id, Model model){
        Beverage beverage = new Beverage();
        beverage = bevService.findBeverage(id);
        beveragetemp = beverage;
        model.addAttribute("beverage", beverage);
        return "Sub";
    }

    @PostMapping(value = "bev/buy")
    public String buy(){
        bevService.buyBeverage(beveragetemp);
        return "redirect:/";
    }


}
