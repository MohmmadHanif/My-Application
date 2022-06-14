package com.example.myapplication.OOPS;


interface vhicle{
    void running();
}

class bike implements vhicle{

    public void running(){
        System.out.print("Bike is running");
    }
}

class car implements vhicle{

    @Override
    public void running() {
        System.out.print("Car is running");
    }
}

public class Interface {

}
