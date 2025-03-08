package CMSAIML.example.CMSAIML.Entity;

public enum ResearchStatus {
    ONGOING("Research is still in progress"),
    COMPLETED("Research has been finished");

    private final String description;

    // Constructor to initialize the description for each status
    ResearchStatus(String description) {
        this.description = description;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Method to check if the status is ongoing
    public boolean isOngoing() {
        return this == ONGOING;
    }

    // Override toString to provide custom string representation
    @Override
    public String toString() {
        return description;
    }

    // Static method to get ResearchStatus from string
    public static ResearchStatus fromString(String status) {
        for (ResearchStatus researchStatus : ResearchStatus.values()) {
            if (researchStatus.name().equalsIgnoreCase(status)) {
                return researchStatus;
            }
        }
        throw new IllegalArgumentException("No enum constant with status " + status);
    }
}
