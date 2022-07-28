package com.bmilk.bev.service;

import com.bmilk.bev.domain.Beverage;
import com.bmilk.bev.repository.BevRepository;

import java.util.List;

public class BevService {
    private final BevRepository bevRepository;

    public BevService(BevRepository bevRepository) {
        this.bevRepository = bevRepository;
    }

    public List<Beverage> findBevs() {
        return bevRepository.findAll();
    }

    public Long buyBeverage(Beverage beverage){
        bevRepository.buy(beverage);
        return beverage.getId();
    }

    public Beverage findBeverage(String id){
        return bevRepository.findBev(id);
    }

}
