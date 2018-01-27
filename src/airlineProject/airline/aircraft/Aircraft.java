package airlineProject.airline.aircraft;

public abstract class Aircraft {

    private final String NAME;
    private final int CAPACITY;
    private final int FLIGHT_RANGE;
    private final int CARRYING_CAPACITY;
    private final float FUEL_CONSUMPTION;

    Aircraft(Builder builder) {
        NAME = builder.name;
        CAPACITY = builder.capacity;
        FLIGHT_RANGE = builder.flightRange;
        CARRYING_CAPACITY = builder.carryingCapacity;
        FUEL_CONSUMPTION = builder.fuelConsumption;
    }

    public abstract static class Builder {

        private int capacity = 0;
        private int flightRange = 0;
        private float fuelConsumption = 0;
        private String name;
        private int carryingCapacity = 0;

        public Builder(String name) {
            this.name = name;
        }

        public Builder capacity(int CAPACITY) {
            this.capacity = CAPACITY;
            return this;
        }

        public Builder flightRange(int FLIGHT_RANGE) {
            this.flightRange = FLIGHT_RANGE;
            return this;
        }

        public Builder carryingCapacity(int CARRYING_CAPACITY) {
            this.carryingCapacity = CARRYING_CAPACITY;
            return this;
        }

        public Builder fuelConsumption(float FUEL_CONSUMPTION) {
            this.fuelConsumption = FUEL_CONSUMPTION;
            return this;
        }

        public abstract Aircraft build();
    }

    public int getFlightRange() {
        return FLIGHT_RANGE;
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public int getCarryingCapacity() {
        return CARRYING_CAPACITY;
    }

    public float getFuelConsumption() {
        return FUEL_CONSUMPTION;
    }

    public String getName() {
        return NAME;
    }

    @Override
    public String toString() {
        StringBuilder instatceText = new StringBuilder();
        return instatceText.append("Airplane ".toUpperCase()).append(NAME).append(":capacity ")
                .append(CAPACITY).append(",flight range ").append(FLIGHT_RANGE)
                .append(",carrying capasity ").append(CARRYING_CAPACITY)
                .append(",fuel consumption ").append(FUEL_CONSUMPTION).toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.NAME.hashCode();
        hash = 89 * hash + this.CAPACITY;
        hash = 89 * hash + this.FLIGHT_RANGE;
        hash = 89 * hash + this.CARRYING_CAPACITY;
        hash = 89 * hash + Float.floatToIntBits(this.FUEL_CONSUMPTION);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aircraft other = (Aircraft) obj;
        if (this.CAPACITY != other.CAPACITY) {
            return false;
        }
        if (this.FLIGHT_RANGE != other.FLIGHT_RANGE) {
            return false;
        }
        if (this.CARRYING_CAPACITY != other.CARRYING_CAPACITY) {
            return false;
        }
        if (this.FUEL_CONSUMPTION != other.FUEL_CONSUMPTION) {
            return false;
        }
        return this.NAME.equals(other.NAME);
    }

}
