package com.zeta_horizon.investment_portfolio_tracker.repository;

import com.zeta_horizon.investment_portfolio_tracker.enums.InvestmentType;
import com.zeta_horizon.investment_portfolio_tracker.enums.RiskLevel;
import com.zeta_horizon.investment_portfolio_tracker.entity.InvestmentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvestmentProductRepository extends JpaRepository<InvestmentProduct, Integer> {
    // Find all active products
    List<InvestmentProduct> findByIsActiveTrue();

    Optional<InvestmentProduct> findByIdAndIsActiveTrue(Integer Id);

    // Find by type
    List<InvestmentProduct> findByTypeAndIsActiveTrue(InvestmentType type);

    // Find by risk level
    List<InvestmentProduct> findByRiskLevelAndIsActiveTrue(RiskLevel riskLevel);

    // Search by name
    List<InvestmentProduct> findByNameContainingIgnoreCaseAndIsActiveTrue(String name);

    // Advanced query with multiple criteria
    @Query("SELECT i FROM InvestmentProduct i WHERE " +
            "i.isActive = true AND " +
            "(:type IS NULL OR i.type = :type) AND " +
            "(:riskLevel IS NULL OR i.riskLevel = :riskLevel) AND " +
            "(:maxAmount IS NULL OR i.minimumInvestment <= :maxAmount)")
    List<InvestmentProduct> findByFilters(
            @Param("type") InvestmentType type,
            @Param("riskLevel") RiskLevel riskLevel,
            @Param("maxAmount") BigDecimal maxAmount
    );
}
