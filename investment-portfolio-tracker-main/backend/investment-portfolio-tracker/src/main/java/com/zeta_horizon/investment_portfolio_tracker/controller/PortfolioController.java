package com.zeta_horizon.investment_portfolio_tracker.controller;

import com.zeta_horizon.investment_portfolio_tracker.dto.*;
import com.zeta_horizon.investment_portfolio_tracker.entity.User;
import com.zeta_horizon.investment_portfolio_tracker.service.JWTService;
import com.zeta_horizon.investment_portfolio_tracker.service.PortfolioService;
import com.zeta_horizon.investment_portfolio_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    UserService userService;
    @Autowired
    JWTService jwtService;
    @GetMapping
    public ResponseEntity<PortfolioResponseDto> getUserPortfolio(@RequestHeader("Authorization") String bearer) {
        String username = getUserName(bearer);
        User user = userService.getUserByEmail(username);
        return ResponseEntity.ok(portfolioService.getUserPortfolio(user));
    }

    @PostMapping("/buy")
    public ResponseEntity<PortfolioItemDto> buyInvestment(
            @RequestHeader("Authorization") String bearer,
            @Valid @RequestBody BuyInvestmentRequestDto request) {
        String username = getUserName(bearer);
        User user = userService.getUserByEmail(username);
        return ResponseEntity.ok(portfolioService.buyInvestment(user, request));
    }

    @PostMapping("/sell")
    public ResponseEntity<PortfolioItemDto> sellInvestment(
            @RequestHeader("Authorization") String bearer,
            @Valid @RequestBody SellInvestmentRequestDto request) {
        String username = getUserName(bearer);
        User user = userService.getUserByEmail(username);
        return ResponseEntity.ok(portfolioService.sellInvestment(user, request));
    }

    @GetMapping("/transactions")
    public ResponseEntity<TransactionHistoryResponseDto> getTransactionHistory(@RequestHeader("Authorization") String bearer) {
        String username = getUserName(bearer);
        User user = userService.getUserByEmail(username);
        return ResponseEntity.ok(portfolioService.getTransactionHistory(user));
    }

    public String getUserName(String bearerToken) {
        String token = bearerToken.substring(7);
        return jwtService.extractUsername(token);
    }
}
