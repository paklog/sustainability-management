# Sustainability Management

Comprehensive carbon footprint tracking, green logistics optimization, and ESG reporting platform enabling data-driven sustainability initiatives and environmental compliance.

## Overview

The Sustainability Management service is a strategic component of the Paklog WMS/WES platform, enabling organizations to measure, monitor, and reduce their environmental impact across all warehouse and logistics operations. As sustainability becomes a competitive differentiator and regulatory requirement, this service provides the data foundation and optimization capabilities needed for ESG compliance and green logistics initiatives.

Modern supply chains account for significant carbon emissions, with warehousing and transportation representing 30-40% of total logistics footprint. This service implements real-time emissions tracking, renewable energy integration monitoring, waste reduction analytics, and route optimization for environmental impact, helping organizations achieve their sustainability goals while maintaining operational efficiency.

## Domain-Driven Design

### Bounded Context

The Sustainability Management bounded context is responsible for:
- Carbon footprint calculation and tracking across operations
- Green logistics route optimization for emission reduction
- Waste stream management and recycling coordination
- Renewable energy usage tracking and reporting
- ESG reporting and sustainability scorecards
- Sustainability certifications management (LEED, ISO 14001)
- Environmental goal tracking and achievement monitoring

### Ubiquitous Language

- **Carbon Footprint**: Total greenhouse gas emissions measured in CO2 equivalent
- **Emission Source**: Origin of emissions (transportation, energy, equipment, materials)
- **Sustainability Metric**: Quantifiable measure of environmental performance
- **Waste Stream**: Category of waste material (recyclable, compostable, landfill)
- **Green Route**: Transportation route optimized for minimum emissions
- **Renewable Energy Credit**: Unit of renewable energy generation or purchase
- **ESG Score**: Environmental, Social, and Governance performance rating
- **Sustainability Goal**: Target environmental performance objective
- **Carbon Offset**: Credit purchased to compensate for emissions
- **Circularity Rate**: Percentage of materials reused or recycled

### Core Domain Model

#### Aggregates

**SustainabilityMetric** (Aggregate Root)
- Manages environmental performance measurements
- Tracks metrics over time with trend analysis
- Validates data quality and completeness
- Calculates composite sustainability scores

**CarbonFootprint**
- Calculates emissions from all sources
- Applies emission factors and conversion rates
- Tracks Scope 1, 2, and 3 emissions
- Manages carbon offset purchases

**WasteStream**
- Categorizes and tracks waste generation
- Monitors recycling and diversion rates
- Calculates waste disposal costs
- Tracks circularity improvements

**GreenInitiative**
- Manages sustainability projects and programs
- Tracks implementation progress
- Measures environmental impact
- Calculates ROI on green investments

#### Value Objects

- `EmissionFactor`: CO2 equivalent per unit of activity
- `WasteCategory`: RECYCLABLE, COMPOSTABLE, HAZARDOUS, LANDFILL, E_WASTE
- `EnergySource`: GRID, SOLAR, WIND, HYDRO, BIOMASS, BATTERY
- `SustainabilityGoal`: Target with timeline and measurement criteria
- `ESGRating`: Composite score across environmental dimensions
- `CertificationStatus`: Status of sustainability certifications
- `RouteEmissions`: Emissions calculation for transportation route
- `PackagingImpact`: Environmental impact of packaging materials

#### Domain Events

- `EmissionsCalculatedEvent`: Carbon footprint computed
- `SustainabilityGoalAchievedEvent`: Environmental target met
- `WasteStreamRecordedEvent`: Waste generation tracked
- `RenewableEnergyUsedEvent`: Clean energy consumption recorded
- `GreenRouteOptimizedEvent`: Low-emission route calculated
- `CertificationAchievedEvent`: Sustainability certification earned
- `OffsetPurchasedEvent`: Carbon offset acquired
- `ESGReportGeneratedEvent`: Sustainability report published

## Architecture

This service follows Paklog's standard architecture patterns:
- **Hexagonal Architecture** (Ports and Adapters)
- **Domain-Driven Design** (DDD)
- **Event-Driven Architecture** with Apache Kafka
- **CloudEvents** specification for event formatting
- **CQRS** for command/query separation

### Project Structure

```
sustainability-management/
├── src/
│   ├── main/
│   │   ├── java/com/paklog/sustainability/management/
│   │   │   ├── domain/               # Core business logic
│   │   │   │   ├── aggregate/        # SustainabilityMetric, CarbonFootprint, WasteStream
│   │   │   │   ├── entity/           # Supporting entities
│   │   │   │   ├── valueobject/      # EmissionFactor, WasteCategory, EnergySource
│   │   │   │   ├── service/          # Domain services
│   │   │   │   ├── repository/       # Repository interfaces (ports)
│   │   │   │   └── event/            # Domain events
│   │   │   ├── application/          # Use cases & orchestration
│   │   │   │   ├── port/
│   │   │   │   │   ├── in/           # Input ports (use cases)
│   │   │   │   │   └── out/          # Output ports
│   │   │   │   ├── service/          # Application services
│   │   │   │   ├── command/          # Commands
│   │   │   │   └── query/            # Queries
│   │   │   └── infrastructure/       # External adapters
│   │   │       ├── persistence/      # MongoDB repositories
│   │   │       ├── messaging/        # Kafka publishers/consumers
│   │   │       ├── web/              # REST controllers
│   │   │       └── config/           # Configuration
│   │   └── resources/
│   │       └── application.yml       # Configuration
│   └── test/                         # Tests
├── k8s/                              # Kubernetes manifests
├── docker-compose.yml                # Local development
├── Dockerfile                        # Container definition
└── pom.xml                          # Maven configuration
```

## Features

### Core Capabilities

- **Carbon Footprint Tracking**: Real-time emissions calculation across Scope 1, 2, and 3
- **Green Logistics Optimization**: Route planning optimized for minimum environmental impact
- **Waste Management**: Comprehensive tracking and optimization of waste streams
- **Renewable Energy Tracking**: Integration monitoring for solar, wind, and other clean energy
- **ESG Reporting**: Automated sustainability scorecards and regulatory reports
- **Certification Management**: Track progress toward LEED, ISO 14001, and other standards
- **Goal Tracking**: Monitor sustainability targets and achievement milestones
- **Impact Analytics**: Data-driven insights for environmental improvement

### Emission Sources Tracked

- Transportation (inbound, outbound, last-mile delivery)
- Warehouse energy consumption (HVAC, lighting, equipment)
- Material handling equipment (forklifts, conveyors, robotics)
- Packaging materials (production and disposal)
- Business travel and commuting
- Upstream supply chain (Scope 3)

### Sustainability Certifications Supported

- LEED (Leadership in Energy and Environmental Design)
- ISO 14001 (Environmental Management Systems)
- ISO 50001 (Energy Management)
- Carbon Neutral Certification
- B Corporation Certification
- Zero Waste Certification

## Technology Stack

- **Java 21** - Programming language
- **Spring Boot 3.2.5** - Application framework
- **MongoDB** - Sustainability metrics and initiative data
- **PostgreSQL** - ESG reporting and analytics
- **Apache Kafka** - Event streaming
- **CloudEvents 2.5.0** - Event format specification
- **Resilience4j** - Fault tolerance
- **Micrometer** - Metrics collection
- **OpenTelemetry** - Distributed tracing
- **TimescaleDB** - Time-series emissions data

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- Docker & Docker Compose
- MongoDB 7.0+
- PostgreSQL 15+
- Apache Kafka 3.5+

### Local Development

1. **Clone the repository**
```bash
git clone https://github.com/paklog/sustainability-management.git
cd sustainability-management
```

2. **Start infrastructure services**
```bash
docker-compose up -d mongodb postgresql kafka
```

3. **Build the application**
```bash
mvn clean install
```

4. **Run the application**
```bash
mvn spring-boot:run
```

5. **Verify the service is running**
```bash
curl http://localhost:8100/actuator/health
```

### Using Docker Compose

```bash
# Start all services including the application
docker-compose up -d

# View logs
docker-compose logs -f sustainability-management

# Stop all services
docker-compose down
```

## API Documentation

Once running, access the interactive API documentation:
- **Swagger UI**: http://localhost:8100/swagger-ui.html
- **OpenAPI Spec**: http://localhost:8100/v3/api-docs

### Key Endpoints

#### Carbon Footprint Management
- `POST /api/v1/carbon/calculate` - Calculate emissions for activity
- `GET /api/v1/carbon/footprint/{period}` - Get carbon footprint for period
- `GET /api/v1/carbon/breakdown` - Get emissions breakdown by source
- `POST /api/v1/carbon/offset` - Record carbon offset purchase

#### Green Logistics
- `POST /api/v1/green-logistics/optimize-route` - Calculate green route
- `GET /api/v1/green-logistics/route-emissions/{routeId}` - Get route emissions
- `POST /api/v1/green-logistics/compare-routes` - Compare routes by emissions
- `GET /api/v1/green-logistics/recommendations` - Get optimization recommendations

#### Waste Management
- `POST /api/v1/waste/record` - Record waste generation
- `GET /api/v1/waste/streams` - Get waste stream breakdown
- `GET /api/v1/waste/diversion-rate` - Get waste diversion metrics
- `POST /api/v1/waste/recycling-event` - Record recycling activity

#### Renewable Energy
- `POST /api/v1/energy/record-usage` - Record energy consumption
- `GET /api/v1/energy/renewable-percentage` - Get renewable energy percentage
- `GET /api/v1/energy/sources` - Get energy source breakdown
- `POST /api/v1/energy/solar-generation` - Record solar energy generation

#### ESG Reporting
- `GET /api/v1/esg/scorecard` - Get ESG scorecard
- `GET /api/v1/esg/report/{period}` - Generate ESG report
- `GET /api/v1/esg/trends` - Get sustainability trends
- `POST /api/v1/esg/publish-report` - Publish sustainability report

#### Sustainability Goals
- `POST /api/v1/goals` - Create sustainability goal
- `GET /api/v1/goals` - List all goals
- `GET /api/v1/goals/{goalId}` - Get goal details
- `PUT /api/v1/goals/{goalId}/progress` - Update goal progress
- `GET /api/v1/goals/achievements` - Get achieved goals

#### Certifications
- `GET /api/v1/certifications` - List certifications
- `POST /api/v1/certifications/{type}/track` - Track certification progress
- `GET /api/v1/certifications/{type}/requirements` - Get certification requirements
- `PUT /api/v1/certifications/{type}/achieve` - Mark certification achieved

## Configuration

Key configuration properties in `application.yml`:

```yaml
sustainability:
  management:
    carbon-tracking-enabled: true
    waste-tracking-enabled: true
    real-time-calculation: true

  emissions:
    calculation:
      scope-1-enabled: true
      scope-2-enabled: true
      scope-3-enabled: true
      emission-factors:
        transportation:
          truck-diesel-kg-co2-per-km: 0.27
          electric-vehicle-kg-co2-per-km: 0.05
          air-freight-kg-co2-per-km: 1.13
        energy:
          grid-electricity-kg-co2-per-kwh: 0.42
          natural-gas-kg-co2-per-cubic-meter: 2.0
        materials:
          cardboard-kg-co2-per-kg: 0.92
          plastic-kg-co2-per-kg: 3.5

  goals:
    carbon-neutral-target-year: 2030
    renewable-energy-target-percentage: 100
    waste-diversion-target-percentage: 90
    emissions-reduction-target-percentage: 50

  reporting:
    esg-report-frequency: QUARTERLY
    certification-tracking-enabled: true
    publish-public-reports: true
```

## Event Integration

### Published Events

- `EmissionsCalculatedEvent` - Carbon footprint computed for activity
- `SustainabilityGoalAchievedEvent` - Environmental target reached
- `WasteStreamRecordedEvent` - Waste generation logged
- `RenewableEnergyUsedEvent` - Clean energy consumption tracked
- `GreenRouteOptimizedEvent` - Low-emission route calculated
- `CertificationAchievedEvent` - Sustainability certification earned
- `OffsetPurchasedEvent` - Carbon offset credit acquired
- `ESGReportGeneratedEvent` - Sustainability report created

### Consumed Events

- `ShipmentDispatchedEvent` from Transportation Service (calculate transport emissions)
- `EnergyConsumedEvent` from Equipment Management (track energy usage)
- `PackagingUsedEvent` from Cartonization Service (packaging material impact)
- `WasteGeneratedEvent` from Warehouse Operations (waste stream tracking)
- `RouteCompletedEvent` from Last-Mile Delivery (actual vs. planned emissions)

## Deployment

### Kubernetes Deployment

```bash
# Create namespace
kubectl create namespace paklog-sustainability

# Apply configurations
kubectl apply -f k8s/deployment.yaml

# Check deployment status
kubectl get pods -n paklog-sustainability
```

### Production Considerations

- **Scaling**: Horizontal scaling supported via Kubernetes HPA
- **High Availability**: Deploy minimum 2 replicas
- **Resource Requirements**:
  - Memory: 1.5 GB per instance
  - CPU: 0.5 core per instance
- **Data Retention**: 7 years for compliance reporting
- **Monitoring**: Prometheus metrics exposed at `/actuator/prometheus`

## Testing

```bash
# Run unit tests
mvn test

# Run integration tests
mvn verify

# Run with coverage
mvn clean verify jacoco:report

# View coverage report
open target/site/jacoco/index.html
```

### Test Coverage Requirements
- Unit Tests: >80%
- Integration Tests: >70%
- Domain Logic: >90%
- Emissions Calculations: >95%

## Performance

### Benchmarks
- **Throughput**: 10,000 emissions calculations/hour
- **Latency**: p99 < 150ms for emission calculations
- **Route Optimization**: < 500ms for typical routes
- **Report Generation**: < 3 seconds for quarterly ESG reports
- **Data Processing**: 1M+ data points/day for analytics

### Optimization Techniques
- Pre-calculated emission factors cached in Redis
- Batch processing for historical data analysis
- Async processing for report generation
- Connection pooling for databases
- Materialized views for reporting queries

## Monitoring & Observability

### Metrics
- Total carbon emissions by period
- Emissions by source category
- Renewable energy percentage
- Waste diversion rate
- Sustainability goal progress
- ESG score trends
- Green route adoption rate

### Health Checks
- `/actuator/health` - Overall health
- `/actuator/health/liveness` - Kubernetes liveness
- `/actuator/health/readiness` - Kubernetes readiness
- `/actuator/health/carbon-calculation` - Emissions calculation engine status

### Distributed Tracing
OpenTelemetry integration for end-to-end carbon footprint calculation tracing.

### Custom Dashboards

**Sustainability Overview Dashboard**
- Real-time carbon emissions
- Year-over-year comparison
- Goal achievement status
- Certification progress

**Green Logistics Dashboard**
- Route emissions analytics
- Carrier environmental performance
- Delivery method impact comparison
- Optimization opportunities

**Waste Management Dashboard**
- Waste stream breakdown
- Recycling rates by facility
- Circularity metrics
- Cost savings from waste reduction

## Business Impact

- **Carbon Reduction**: 25-40% emissions reduction through optimization
- **Cost Savings**: $200K+ annually from energy efficiency and waste reduction
- **Regulatory Compliance**: Automated ESG reporting reducing manual effort by 80%
- **Brand Value**: Enhanced corporate reputation through sustainability leadership
- **Customer Satisfaction**: +15% increase in eco-conscious customer preference
- **Certification Achievement**: Faster path to LEED and ISO certifications
- **Investor Confidence**: Improved ESG ratings attracting sustainable investment

## Emission Calculation Methodology

### Scope 1 Emissions (Direct)
- Company-owned vehicles fuel consumption
- On-site fuel combustion (generators, forklifts)
- Refrigerant leakage from cooling systems

### Scope 2 Emissions (Indirect - Energy)
- Purchased electricity consumption
- Purchased heating and cooling
- Location-based and market-based calculations

### Scope 3 Emissions (Indirect - Value Chain)
- Transportation and distribution (upstream/downstream)
- Waste disposal
- Business travel
- Employee commuting
- Purchased goods and services
- End-of-life treatment

### Calculation Formula

```
Total CO2e = Σ (Activity Data × Emission Factor × GWP)

Where:
- Activity Data: Quantity of activity (km driven, kWh consumed, kg material)
- Emission Factor: CO2e per unit of activity (from EPA/IPCC standards)
- GWP: Global Warming Potential (CH4 = 25, N2O = 298, CO2 = 1)
```

## Green Route Optimization

### Optimization Criteria
- Minimize total carbon emissions
- Consider vehicle type and fuel efficiency
- Factor in traffic patterns and congestion
- Evaluate alternative transportation modes
- Balance speed vs. environmental impact

### Algorithm Approach
```java
public GreenRoute optimizeRoute(RouteRequest request) {
    // Multi-objective optimization
    // Minimize: (emissions, cost, time)
    // Constraints: delivery windows, vehicle capacity

    return geneticAlgorithm.optimize(
        objectives: [EMISSIONS, COST, TIME],
        weights: [0.6, 0.2, 0.2],  // Prioritize emissions
        constraints: deliveryConstraints,
        populationSize: 100,
        generations: 500
    );
}
```

## Sustainability Certifications

### LEED Certification Requirements
- Energy performance tracking
- Water efficiency monitoring
- Materials and resource optimization
- Indoor environmental quality
- Innovation in operations

### ISO 14001 Requirements
- Environmental policy documentation
- Aspect and impact assessment
- Legal compliance tracking
- Operational controls
- Performance evaluation
- Continuous improvement

### Zero Waste Certification
- 90%+ waste diversion from landfill
- Comprehensive recycling programs
- Composting initiatives
- Waste prevention strategies
- Supplier engagement

## Troubleshooting

### Common Issues

1. **Emissions Calculation Discrepancies**
   - Verify emission factors are up to date
   - Check data quality from source systems
   - Review calculation methodology
   - Validate unit conversions
   - Compare against industry benchmarks

2. **Missing Energy Data**
   - Check IoT sensor connectivity
   - Verify meter reading schedules
   - Review data ingestion pipelines
   - Examine Kafka event consumption
   - Validate data mapping from equipment

3. **Report Generation Delays**
   - Review database query performance
   - Check data completeness for period
   - Examine concurrent report requests
   - Verify sufficient compute resources
   - Optimize aggregation queries

4. **Goal Tracking Inaccuracies**
   - Validate baseline measurements
   - Check goal definition parameters
   - Review progress calculation logic
   - Verify event processing completeness
   - Examine data reconciliation processes

## Integration with Other Services

### Cartonization Service
- Receive packaging material usage data
- Calculate packaging environmental impact
- Provide sustainable packaging recommendations
- Track packaging waste reduction

### Transportation Management
- Consume shipment and route data
- Calculate transportation emissions
- Provide green routing recommendations
- Track carrier environmental performance

### Last-Mile Delivery
- Monitor delivery method emissions
- Compare delivery options (drone, EV, bike)
- Optimize delivery consolidation
- Track customer preference for green delivery

### Equipment Management
- Track equipment energy consumption
- Monitor equipment efficiency degradation
- Calculate lifecycle environmental impact
- Recommend energy-efficient replacements

### Warehouse Operations
- Monitor facility energy usage
- Track operational waste generation
- Optimize HVAC and lighting
- Calculate worker commute impact

## Regulatory Compliance

### Supported Frameworks
- GHG Protocol (Greenhouse Gas Protocol)
- CDP (Carbon Disclosure Project)
- TCFD (Task Force on Climate-related Financial Disclosures)
- SASB (Sustainability Accounting Standards Board)
- GRI (Global Reporting Initiative)
- EU CSRD (Corporate Sustainability Reporting Directive)

### Automated Reporting
- Quarterly ESG reports
- Annual sustainability reports
- Regulatory submissions
- Investor disclosures
- Customer sustainability credentials

## Data Privacy & Security

### Sensitive Data Protection
- Energy consumption data encryption
- Secure transmission of ESG reports
- Role-based access control for sustainability data
- Audit trails for all calculations
- Compliance with data retention policies

## Future Enhancements

### Planned Features
- AI-powered sustainability recommendations
- Predictive modeling for emissions scenarios
- Blockchain-based carbon credit tracking
- Real-time supplier sustainability scoring
- Customer carbon footprint visibility
- Automated sustainability certification applications

## Contributing

1. Follow hexagonal architecture principles
2. Maintain domain logic in domain layer
3. Keep infrastructure concerns separate
4. Write comprehensive tests for all changes
5. Document emission calculation methodologies
6. Follow ESG reporting standards
7. Validate against industry benchmarks

## Support

For issues and questions:
- Create an issue in GitHub
- Contact the Paklog Sustainability Team
- Check the [documentation](https://paklog.github.io/docs/sustainability)
- Review [ESG reporting standards](https://paklog.github.io/docs/esg-standards)

## License

Copyright © 2024 Paklog. All rights reserved.

---

**Version**: 1.0.0
**Phase**: 4 (Excellence)
**Priority**: P3 (Differentiation)
**Maintained by**: Paklog Sustainability Team
**Last Updated**: November 2024
