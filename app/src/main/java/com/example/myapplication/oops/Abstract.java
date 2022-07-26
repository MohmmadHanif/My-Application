package com.example.myapplication.oops;

public class Abstract {



}
abstract class Vhicle{

    abstract void color();
}

class Bike1 extends Vhicle{

    void color(){
        System.out.print("The Bike Color Is Black");
    }
}
class Car1 extends Vhicle{

    @Override
    void color() {
        System.out.print("The Car Color is Red");
    }
}