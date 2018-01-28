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

        /**
         * Set the builder's capacity
         * @param capacity aircraft's capacity
         * @return a reference to this object.
         */
        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        /**
         * Set the builder's flight range
         * @param flightRange aircraft's flight range
         * @return a reference to this object.
         */
        public Builder flightRange(int flightRange) {
            this.flightRange = flightRange;
            return this;
        }

        /**
         * Set the builder's carrying capacity
         * @param carryingCapacity aircraft's carrying capacity
         * @return a reference to this object.
         */
        public Builder carryingCapacity(int carryingCapacity) {
            this.carryingCapacity = carryingCapacity;
            return this;
        }

        /**
         * Set the builder's fuel consumption
         * @param fuelConsumption aircraft's fuel consumption
         * @return a reference to this object.
         */
        public Builder fuelConsumption(float fuelConsumption) {
            this.fuelConsumption = fuelConsumption;
            return this;
        }

        /**
         * Create a new instance of aircraft
         * @return aircraft's new instance with filled fields
         */
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
        return new StringBuilder().append("Airplane ".toUpperCase()).append(NAME).append(":\ncapacity ")
                .append(CAPACITY).append("\n,flight range ").append(FLIGHT_RANGE)
                .append("\n,carrying capasity ").append(CARRYING_CAPACITY)
                .append("\n,fuel consumption ").append(FUEL_CONSUMPTION).toString();
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
