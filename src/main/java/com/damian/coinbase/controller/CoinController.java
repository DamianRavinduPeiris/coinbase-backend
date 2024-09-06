package com.damian.coinbase.controller;

import com.damian.coinbase.dto.CoinDTO;
import com.damian.coinbase.response.Response;
import com.damian.coinbase.service.CoinBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/coins", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@RequiredArgsConstructor
public class CoinController {

    private final CoinBaseService coinBaseService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> saveCoin(@RequestBody CoinDTO coinDTO) {
        coinBaseService.addCoin(coinDTO);
        return ResponseEntity.ok(new Response(HttpStatus.CREATED.value(), "Coin added successfully!", null));
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateCoin(@RequestBody CoinDTO coinDTO) {
        coinBaseService.updateCoin(coinDTO);
        return ResponseEntity.ok(new Response(200, "Coin updated successfully!", null));
    }

    @GetMapping(path = "/search", params = "id")
    public ResponseEntity<Response> searchCoin(@RequestParam("id") String id) {
        return ResponseEntity.ok(new Response(200, "Coin fetched successfully!", coinBaseService.searchCoin(id)));
    }

    @GetMapping(path = "/searchAll")
    public ResponseEntity<Response> searchAllCoin() {
        return ResponseEntity.ok(new Response(200, "Coins fetched successfully!", coinBaseService.searchAllCoins()));
    }

    @DeleteMapping(path = "/delete", params = "id")
    public ResponseEntity<Response> deleteCoin(@RequestParam("id") String id) {
        coinBaseService.deleteCoin(id);
        return ResponseEntity.ok(new Response(200, "Coin Deleted successfully!", null));
    }
}
