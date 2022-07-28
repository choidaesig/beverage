package com.bmilk.bev.repository;

import com.bmilk.bev.domain.Beverage;

import java.util.List;

public interface BevRepository {
    List<Beverage> findAll();
    void buy(Beverage beverage);
    Beverage findBev(String id);
}
