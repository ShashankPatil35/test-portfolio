package com.zeta_horizon.investment_portfolio_tracker.service.implementation;

import com.zeta_horizon.investment_portfolio_tracker.dto.*;
import com.zeta_horizon.investment_portfolio_tracker.enums.InvestmentType;
import com.zeta_horizon.investment_portfolio_tracker.enums.RiskLevel;
import com.zeta_horizon.investment_portfolio_tracker.exception.ResourceNotFoundException;
import com.zeta_horizon.investment_portfolio_tracker.entity.InvestmentProduct;
import com.zeta_horizon.investment_portfolio_tracker.repository.InvestmentProductRepository;
import com.zeta_horizon.investment_portfolio_tracker.service.InvestmentProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestmentProductServiceImpl implements InvestmentProductService {
    private final InvestmentProductRepository investmentProductRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InvestmentProductServiceImpl(InvestmentProductRepository investmentProductRepository) {
        this.investmentProductRepository = investmentProductRepository;
        this.modelMapper = new ModelMapper();

        // Configure ModelMapper for proper mapping between entities and DTOs
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvestmentProductListDto> getAllActiveProducts() {
        return investmentProductRepository.findByIsActiveTrue().stream()
                .map(product -> modelMapper.map(product, InvestmentProductListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvestmentProductListDto> getAllProducts() {
        return investmentProductRepository.findAll().stream()
                .map(product -> modelMapper.map(product, InvestmentProductListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InvestmentProductDto getProductById(Integer id) {
        InvestmentProduct product = investmentProductRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investment product not found with id: " + id));

        return modelMapper.map(product, InvestmentProductDto.class);
    }

    @Override
    public InvestmentProductDto createProduct(InvestmentProductCreateDto createDto) {
        InvestmentProduct product = modelMapper.map(createDto, InvestmentProduct.class);
        product.setActive(true);

        InvestmentProduct savedProduct = investmentProductRepository.save(product);
        return modelMapper.map(savedProduct, InvestmentProductDto.class);
    }

    @Override
    public InvestmentProductDto updateProduct(Integer id, InvestmentProductUpdateDto updateDto) {
        InvestmentProduct existingProduct = investmentProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investment product not found with id: " + id));

        // Only update fields that are not null in the DTO
        if (updateDto.getName() != null) {
            existingProduct.setName(updateDto.getName());
        }
        if (updateDto.getType() != null) {
            existingProduct.setType(updateDto.getType());
        }
        if (updateDto.getRiskLevel() != null) {
            existingProduct.setRiskLevel(updateDto.getRiskLevel());
        }
        if (updateDto.getMinimumInvestment() != null) {
            existingProduct.setMinimumInvestment(updateDto.getMinimumInvestment());
        }
        if (updateDto.getExpectedAnnualReturnRate() != null) {
            existingProduct.setExpectedAnnualReturnRate(updateDto.getExpectedAnnualReturnRate());
        }
        if (updateDto.getCurrentNetAssetValuePerUnit() != null) {
            existingProduct.setCurrentNetAssetValuePerUnit(updateDto.getCurrentNetAssetValuePerUnit());
        }
        if (updateDto.getDescription() != null) {
            existingProduct.setDescription(updateDto.getDescription());
        }
        if (updateDto.getIsActive() != null) {
            existingProduct.setActive(updateDto.getIsActive());
        }

        InvestmentProduct updatedProduct = investmentProductRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, InvestmentProductDto.class);
    }

    @Override
    public void deleteProduct(Integer id) {
        // Soft delete - just set isActive to false
        InvestmentProduct existingProduct = investmentProductRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Investment product not found with id: " + id)
        );
        existingProduct.setActive(false);
        investmentProductRepository.save(existingProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvestmentProductListDto> filterProducts(InvestmentProductFilterDto filterDto) {
        List<InvestmentProduct> products;

        if (filterDto.getSearchTerm() != null && !filterDto.getSearchTerm().isEmpty()) {
            products = investmentProductRepository.findByNameContainingIgnoreCaseAndIsActiveTrue(filterDto.getSearchTerm());
        } else {
            products = investmentProductRepository.findByFilters(
                    filterDto.getType(),
                    filterDto.getRiskLevel(),
                    filterDto.getMaximumInvestmentAmount()
            );
        }

        return products.stream()
                .map(product -> modelMapper.map(product, InvestmentProductListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvestmentProductListDto> getProductsByRiskLevel(RiskLevel riskLevel) {
        return investmentProductRepository.findByRiskLevelAndIsActiveTrue(riskLevel).stream()
                .map(product -> modelMapper.map(product, InvestmentProductListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvestmentProductListDto> getProductsByType(InvestmentType type) {
        return investmentProductRepository.findByTypeAndIsActiveTrue(type).stream()
                .map(product -> modelMapper.map(product, InvestmentProductListDto.class))
                .collect(Collectors.toList());
    }
}
