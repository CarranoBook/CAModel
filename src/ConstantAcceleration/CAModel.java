/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantAcceleration;

import vectors.Vector;

/**
 *
 * @author nbleier
 */
public class CAModel {
    Vector[] pos;
    Vector[] vels;
    Vector accelerations;
    double time;
    
    CAModel(Vector[] positions, Vector[] velocities, Vector acceleration, double time) {
        this.pos = positions;
        this.vels = velocities;
        this.accelerations = acceleration;
        this.time = time;
        this.checkValues();
    }
    
    public void setTime(double d) {
        this.time = d;
        if ( time <= 0 ) 
            throw new IllegalStateException("Must have possitive value for time");
        
    }
    
    private void checkValues() {
        if ( pos.length != 2 || vels.length != 2)
            throw new IllegalStateException("Must pass array of length 2 for velocitie and position");
        
       /* if ( pos[0].dimensions() != pos[1].dimensions() &&
                vels[0].dimensions() != vels[1].dimensions() &&
                vels[0].dimensions() != accelerations.dimensions() )
            throw new IllegalStateException("All vectors must be of equal dimension");
        */
        if ( time <= 0 ) 
            throw new IllegalStateException("Must have possitive value for time");
    }
    
    private void generateVelocities() {
        if ( vels[1] == null && vels[0] == null )
            throw new IllegalStateException("No intial or final velocity given");
       
        double[] vals = accelerations.getCoordinates();
        
        if ( vels[1] == null ) {
            for ( int i = 0; i < vels[0].dimensions(); i++ ) {
                vals[i] = vals[i] * time + vels[0].getCoordinate(i);
            }
            vels[0] = new Vector(vals);
        }
        
        else if ( vels[0] == null ) {
            for ( int i = 0; i < vels[1].dimensions(); i++ ) {
                vals[i] =  vels[1].getCoordinate(i) - (vals[i] * time);
            }
            vels[1] = new Vector(vals);
        }
     
    }
    
}
