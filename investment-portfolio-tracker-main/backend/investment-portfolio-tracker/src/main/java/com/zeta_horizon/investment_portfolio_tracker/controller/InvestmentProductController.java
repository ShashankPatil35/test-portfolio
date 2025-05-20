package com.zeta_horizon.investment_portfolio_tracker.controller;

import com.zeta_horizon.investment_portfolio_tracker.dto.*;
import com.zeta_horizon.investment_portfolio_tracker.enums.InvestmentType;
import com.zeta_horizon.investment_portfolio_tracker.enums.RiskLevel;
import com.zeta_horizon.investment_portfolio_tracker.service.InvestmentProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class InvestmentProductController {
    private final InvestmentProductService investmentProductService;

    @Autowired
    public InvestmentProductController(InvestmentProductService investmentProductService) {
        this.investmentProductService = investmentProductService;
    }

    // Public endpoints

    @GetMapping("investments")
    public ResponseEntity<SuccessResponse<List<InvestmentProductListDto>>> getAllActiveProducts() {
        List<InvestmentProductListDto> products = investmentProductService.getAllActiveProducts();
        return new ResponseEntity<>(new SuccessResponse<>(HttpStatus.OK.value(),
                products, "Successfully fetched "+products.size()+" investment products from database",
                LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping("investments/{id}")
    public ResponseEntity<SuccessResponse<InvestmentProductDto>> getProductById(@PathVariable Integer id) {
        InvestmentProductDto product = investmentProductService.getProductById(id);
        return new ResponseEntity<>(new SuccessResponse<>(HttpStatus.OK.value(),
                product, "Successfully fetched investment product with id " + id,
                LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping("investments/type/{type}")
    public ResponseEntity<SuccessResponse<List<InvestmentProductListDto>>> getProductsByType(
            @PathVariable InvestmentType type) {
        List<InvestmentProductListDto> products = investmentProductService.getProductsByType(type);
        return new ResponseEntity<>(new SuccessResponse<>(HttpStatus.OK.value(),
                products, "Successfully fetched "+type+" type investment products",
                LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping("investments/risk/{riskLevel}")
    public ResponseEntity<SuccessResponse<List<InvestmentProductListDto>>> getProductsByRiskLevel(
            @PathVariable RiskLevel riskLevel) {
        List<InvestmentProductListDto> products = investmentProductService.getProductsByRiskLevel(riskLevel);
        return new ResponseEntity<>(new SuccessResponse<>(HttpStatus.OK.value(),
                products, "Successfully fetched investment products with "+riskLevel+" risk level",
                LocalDateTime.now()), HttpStatus.OK);
    }

    @PostMapping("investments/filter")
    public ResponseEntity<SuccessResponse<List<InvestmentProductListDto>>> filterProducts(
            @RequestBody InvestmentProductFilterDto filterDto) {
        List<InvestmentProductListDto> products = investmentProductService.filterProducts(filterDto);
        return new ResponseEntity<>(new SuccessResponse<>(HttpStatus.OK.value(),
                products, "Successfully fetched investment products with filter " + filterDto,
                LocalDateTime.now()), HttpStatus.OK);
    }

    // Admin endpoints

    @PostMapping("admin/investments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InvestmentProductDto> createProduct(
            @Valid @RequestBody InvestmentProductCreateDto createDto) {
        InvestmentProductDto createdProduct = investmentProductService.createProduct(createDto);
        return ResponseEntity
                .created(URI.create("/api/investments/" + createdProduct.getId()))
                .body(createdProduct);
    }

    @GetMapping("admin/investments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SuccessResponse<List<InvestmentProductListDto>>> getAllProducts() {
        List<InvestmentProductListDto> products = investmentProductService.getAllProducts();
        return new ResponseEntity<>(new SuccessResponse<>(HttpStatus.OK.value(),
                products, "Successfully fetched "+products.size()+" investment products from database",
                LocalDateTime.now()), HttpStatus.OK);
    }

    @PutMapping("admin/investments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SuccessResponse<InvestmentProductDto>> updateProduct(
            @PathVariable Integer id,
            @Valid @RequestBody InvestmentProductUpdateDto updateDto) {
        InvestmentProductDto updatedProduct = investmentProductService.updateProduct(id, updateDto);
        return new ResponseEntity<>(new SuccessResponse<>(HttpStatus.OK.value(),
                updatedProduct, "Successfully updated investment product with id " + id,
                LocalDateTime.now()), HttpStatus.OK);
    }

    @DeleteMapping("admin/investments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SuccessResponse<String>> deleteProduct(@PathVariable Integer id) {
        investmentProductService.deleteProduct(id);
        return new ResponseEntity<>(new SuccessResponse<>(HttpStatus.OK.value(),
                "Deleted", "Successfully deleted investment product with id " + id,
                LocalDateTime.now()), HttpStatus.OK);
    }
}
