package com.paklog.sustainability.domain.valueobject;

public enum EmissionType {
    SCOPE1_DIRECT,           // Owned vehicles, forklifts
    SCOPE2_ENERGY,           // Purchased electricity
    SCOPE3_TRANSPORTATION,   // Third-party logistics
    SCOPE3_SUPPLY_CHAIN     // Supplier emissions
}
