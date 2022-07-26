package com.example.myapplication.oops;

public class Inheritance {


    class Vhicle{
        public void color(String color){
            System.out.print("The Vhical color is "+ color);
        }

        public void wells(int wells){
            System.out.print("The Vhical wells is "+ wells);
        }
    }


    class bike extends Vhicle{
        public void running (){
            System.out.print("this bike is runing");
        }
    }


}

