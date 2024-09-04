package com.damian.coinbase.repo;

import com.damian.coinbase.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface CoinRepo extends JpaRepository<Coin,String> {}
