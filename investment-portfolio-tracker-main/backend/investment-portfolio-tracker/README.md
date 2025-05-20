# Project Folder Structure

```bash
investment-portfolio-tracker/
├── .mvn
├── src/
│   └── main/
│       ├── java/
│       │   └── com.zeta_horizon.investment_portfolio_tracker/
│       │       ├── config/
│       │       │   └── ModelMapperConfig
│       │       ├── controller/
│       │       │   └── InvestmentProductController
│       │       ├── dto/
│       │       │   ├── ErrorResponse
│       │       │   ├── InvestmentProductCreateDto
│       │       │   ├── InvestmentProductDto
│       │       │   ├── InvestmentProductFilterDto
│       │       │   ├── InvestmentProductListDto
│       │       │   ├── InvestmentProductUpdateDto
│       │       │   ├── SuccessResponse
│       │       │   └── ValidationErrorResponse
│       │       ├── enums/
│       │       │   ├── InvestmentType
│       │       │   └── RiskLevel
│       │       ├── exception/
│       │       │   ├── GlobalExceptionHandler
│       │       │   └── ResourceNotFoundException
│       │       ├── model/
│       │       │   └── InvestmentProduct
│       │       ├── repository/
│       │       │   └── InvestmentProductRepository
│       │       └── service/
│       │           ├── implementation/
│       │           │   └── InvestmentProductServiceImpl
│       │           └── InvestmentProductService
│       └── resources/
│           ├── static/
│           └── templates/
├── test/
├── .env.sample
├── .gitattributes
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```
