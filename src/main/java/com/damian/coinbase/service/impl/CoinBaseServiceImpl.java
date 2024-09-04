package com.damian.coinbase.service.impl;

import com.damian.coinbase.dto.CoinDTO;
import com.damian.coinbase.exceptions.CoinAlreadyExists;
import com.damian.coinbase.exceptions.CoinDoesNotExists;
import com.damian.coinbase.model.Coin;
import com.damian.coinbase.repo.CoinRepo;
import com.damian.coinbase.service.CoinBaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Log4j2
public class CoinBaseServiceImpl implements CoinBaseService {

    private final CoinRepo coinRepo;
    private final ModelMapper mapper;

    @Override
    public void addCoin(CoinDTO coinDTO) {
        execute(coinDTO1 -> {
            if (coinRepo.findById(coinDTO.coinId()).isPresent()) {
                throw new CoinAlreadyExists("Coin already exists!");
            }
            coinRepo.save(mapper.map(coinDTO1, Coin.class));
        }, coinDTO);
    }

    @Override
    public void updateCoin(CoinDTO coinDTO) {
        execute(coinDTO1 -> coinRepo.save(mapper.map(coinDTO,searchCoin(coinDTO.coinId()).getClass())), coinDTO);
    }

    @Override
    public void deleteCoin(String s) {
        execute(coinDTO -> coinRepo.delete(searchCoin(s)), null);
    }

    @Override
    public Coin searchCoin(String s) {
        Optional<Coin> coin = coinRepo.findById(s);
        if (coin.isPresent()) {
            return coin.get();
        }
        throw new CoinDoesNotExists("Coin does not exists!");
    }

    @Override
    public List<Coin> searchAllCoins() {
        List<Coin> coins = coinRepo.findAll();
        if (coins.isEmpty()) {
            throw new CoinDoesNotExists("No coins found!");
        }
        return coins;
    }

    @Override
    public void execute(Consumer<CoinDTO> consumer, CoinDTO coinDTO) {
        try {
            consumer.accept(coinDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
