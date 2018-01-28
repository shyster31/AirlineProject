package airlineProject.airline.aircraft;

public class Airship extends Aircraft implements ICargoPlane{

    private Airship(Builder builder) {
        super(builder);
    }

    public static class Builder extends Aircraft.Builder{
        
        public Builder(String name) {
            super(name);
        }

        @Override
        public Aircraft build() {
            return new Airship(this);
        }
        
    }
}
