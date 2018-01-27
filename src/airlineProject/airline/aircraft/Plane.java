package airlineProject.airline.aircraft;

public class Plane extends Aircraft implements IPassengersPlane{
    
   public Plane(Builder builder){
       super(builder);
   }  

    public static class Builder extends Aircraft.Builder{
        
        public Builder(String name) {
            super(name);
        }

        @Override
        public Aircraft build() {
            return new Plane(this);
        }
        
    }
}
