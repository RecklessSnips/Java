package Java8.Data;

import java.util.ArrayList;
import java.util.List;

public class Jedi {

    private String name;
    private int age;

    private Jedi master;

    public Jedi(String name){
        this.name = name;
    }

    public Jedi(String name, int age){
        this.name = name;
        this.age = age;
    }

    public static List<Jedi> getListJedi(){
        List<Jedi> jedi = new ArrayList<>();

        jedi.add(new Jedi("Ahsoka", 17));
        jedi.add(new Jedi("Anakin", 21));
        jedi.add(new Jedi("Obiwan", 30));
        jedi.add(new Jedi("Dooku", 70));
        jedi.add(new Jedi("Yoda", 790));
        jedi.add(new Jedi("Windu", 50));
        jedi.add(new Jedi("Kanan", 16));
        jedi.add(new Jedi("Ezra", 15));
        jedi.add(new Jedi("Sabine", 14));
        jedi.add(new Jedi("Carl", 13));

        return jedi;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public void setMaster(Jedi jedi){
        this.master = jedi;
    }

    public Jedi getMaster(){
        return this.master;
    }

    public String toString(){
        return this.name;
    }

}
