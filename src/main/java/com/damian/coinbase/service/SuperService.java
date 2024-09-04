package com.damian.coinbase.service;

import com.damian.coinbase.dto.CoinDTO;
import com.damian.coinbase.model.Coin;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface SuperService <T,ID>{
    void addCoin(T t);

    void updateCoin(T t);

    void deleteCoin(ID id);

    Coin searchCoin(ID id);

    List<Coin> searchAllCoins();

    void execute(Consumer<T> consumer, T t);
}
