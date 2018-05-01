/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsociety;

import java.util.ArrayList;

/**
 *
 * @author Agustín
 */
public class SensoresVehiculo {
    private String nombre;
    private int prioridad;
    private int tipoVehiculo;
    private int x;
    private int y;
    private int battery;
    private int energy;
    private boolean goal;
    private boolean disponible;
    private ArrayList<Integer> radar;
    private boolean finalizado;
    private int desplazamiento_objetivo;
    private static int id = 0;
    public ListaPosiciones lista;
    public SensoresVehiculo(){
        this.prioridad = 0;
        this.lista = new ListaPosiciones();
        this.disponible = false;
        this.radar = new ArrayList<>();
        this.x = -100;
        this.y = -100;
        this.goal = false;
        finalizado = false;
        desplazamiento_objetivo += id;
        id++;
    }
    public ListaPosiciones getLista(){
        return lista;
    }
    public void Add(Posicion pos){
      lista.Add(pos);
      
    }
    /**
     * @return the prioridad
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the tipoVehiculo
     */
    public int getTipoVehiculo() {
        return tipoVehiculo;
    }

    /**
     * @param tipoVehiculo the tipoVehiculo to set
     */
    public void setTipoVehiculo(int tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the battery
     */
    public int getBattery() {
        return battery;
    }

    /**
     * @param battery the battery to set
     */
    public void setBattery(int battery) {
        this.battery = battery;
    }

    /**
     * @return the energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @param energy the energy to set
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * @return the goal
     */
    public boolean isGoal() {
        return goal;
    }

    /**
     * @param goal the goal to set
     */
    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    /**
     * @return the disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return the radar
     */
    public ArrayList getRadar() {
        return radar;
    }

    /**
     * @param valor the radar to set
     */
    public void setRadar(int valor) {
        this.radar.add(valor);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the finalizado
     */
    public boolean isFinalizado() {
        return finalizado;
    }

    /**
     * @param finalizado the finalizado to set
     */
    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    /**
     * @return the desplazamiento_objetivo
     */
    public int getDesplazamiento_objetivo() {
        return desplazamiento_objetivo;
    }

    /**
     * @param desplazamiento_objetivo the desplazamiento_objetivo to set
     */
    public void setDesplazamiento_objetivo(int desplazamiento_objetivo) {
        this.desplazamiento_objetivo = desplazamiento_objetivo;
    }
}
