/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsociety;

/**
 *
 * @author Borja
 */
class Propiedades {
    private int fuelrate;
    private int range;
    private boolean fly;
    private int tipoVehiculo;
    
    public Propiedades(int fuelrate, int range, boolean fly) {
        this.fuelrate = fuelrate;
        this.range = range;
        this.fly = fly;
        this.tipoVehiculo = comprobarTipoVehiculo();
        
    }
    
    private int comprobarTipoVehiculo(){
        int tipo = -1;
        
        if(this.isFly()){
            tipo = 0;// Avion
        }
        else{
            if(this.getFuelrate() == 1){
                tipo = 1;//Coche
            }
            else{
                tipo = 2;//camion
            }
        }
        
        return tipo;
    }
    

    /**
     * @return the fuelrate
     */
    public int getFuelrate() {
        return fuelrate;
    }

    /**
     * @param fuelrate the fuelrate to set
     */
    public void setFuelrate(int fuelrate) {
        this.fuelrate = fuelrate;
    }

    /**
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * @return the fly
     */
    public boolean isFly() {
        return fly;
    }

    /**
     * @param fly the fly to set
     */
    public void setFly(boolean fly) {
        this.fly = fly;
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
}
