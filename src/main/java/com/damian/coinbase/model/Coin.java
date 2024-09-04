package com.damian.coinbase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Coin {
    @Id
    private String coinId;
    private String name;
    private String symbol;
    private double price;
}
