package org.example;


// Section 1: The Foundation - The Drone Abstract Class
abstract class Drone {
    private String callsign;

    public Drone(String callsign) {
        this.callsign = callsign;
    }

    // Concrete method: All drones take off the same way.
    public void takeOff() {
        System.out.println(callsign + " is taking off.");
    }

    // Concrete method: All drones land the same way.
    public void land() {
        System.out.println(callsign + " is landing.");
    }

    // Abstract method: The flight pattern is specific to the drone model.
    // We force subclasses to define how they fly.
    public abstract void fly();

    public String getCallsign() {
        return this.callsign;
    }
}

// Section 2: Defining a Capability - The VisualRecon Interface
interface VisualRecon {

    // 1. Variable: All variables in an interface are public, static, and final by default.
    String SENSOR_TYPE = "High-Resolution Optical Camera";

    // 2. Abstract Method: The core capability this interface guarantees.
    //    Any class implementing this must provide a body for this method.
    void takePicture();

    // 3. Default Method: Provides a default implementation.
    //    A class can use this directly or choose to override it.
    default void record4kVideo() {
        System.out.println("Recording 4K video using default settings.");
    }

    // 4. Static Method: A utility function related to the interface.
    //    It is called on the interface itself (VisualRecon.getStandardLensType()).
    static String getStandardLensType() {
        return "50mm Standard Lens";
    }
}

// Section 3: The First Unit - Implementing the QuadCopter
class QuadCopter extends Drone implements VisualRecon {

    public QuadCopter(String callsign) {
        super(callsign); // Call the parent constructor
    }

    // Implementing the abstract method from the Drone class
    @Override
    public void fly() {
        System.out.println(getCallsign() + " is hovering with four rotors.");
    }

    // Implementing the abstract method from the VisualRecon interface
    @Override
    public void takePicture() {
        System.out.println(getCallsign() + " takes a picture with its " + SENSOR_TYPE);
    }
}

// Section 5: Expanding Capabilities by Extending Interfaces
interface SignalIntel {
    void interceptSignal();
}

// This interface extends two other interfaces!
interface AdvancedRecon extends VisualRecon, SignalIntel {
    // This interface is a combination of two contracts.
    // It doesn't need a body, it just aggregates other interfaces.
}

// Section 6: Deploying an Advanced Drone
class FixedWingDrone extends Drone implements AdvancedRecon {

    public FixedWingDrone(String callsign) {
        super(callsign);
    }

    @Override
    public void fly() {
        System.out.println(getCallsign() + " is cruising at high altitude.");
    }

    @Override
    public void takePicture() {
        System.out.println(getCallsign() + " captures high-resolution satellite imagery.");
    }

    @Override
    public void interceptSignal() {
        System.out.println(getCallsign() + " intercepts and analyzes radio frequencies.");
    }

    // Let's override the default method for this advanced drone.
    @Override
    public void record4kVideo() {
        System.out.println("Engaging gimbal-stabilized 4K video recording.");
    }
}

// Section 4: The Mission Control and Execution
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Deploying QuadCopter Unit ---");
        QuadCopter bravo_1 = new QuadCopter("Bravo-1");

        // Methods from the abstract Drone class
        bravo_1.takeOff();
        bravo_1.fly();

        System.out.println("\n--- Engaging Recon Capabilities ---");

        // Method from the VisualRecon interface (implemented in QuadCopter)
        bravo_1.takePicture();

        // Default method from the VisualRecon interface
        bravo_1.record4kVideo();

        // Static method from the VisualRecon interface
        System.out.println("Standard Lens Type: " + VisualRecon.getStandardLensType());

        // Method from the abstract Drone class
        bravo_1.land();

        System.out.println("\n\n--- Deploying Advanced Fixed-Wing Unit ---");
        FixedWingDrone phoenix_7 = new FixedWingDrone("Phoenix-7");
        phoenix_7.takeOff();
        phoenix_7.fly();
        phoenix_7.takePicture();
        phoenix_7.interceptSignal();
        phoenix_7.record4kVideo(); // Calls the overridden version
        phoenix_7.land();
    }
}