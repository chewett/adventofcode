package net.chewett.adventofcode.aoc2019;

/**
 * Helper class for 2019 Day 12 to hold "moon information"
 */
public class Moon {

    //Store the position and velocity as int's
    private Position3D pos;
    private Position3D velocity;

    public Moon(int x, int y, int z) {
        this.pos = new Position3D(x, y, z);
        this.velocity = new Position3D(0, 0, 0);
    }

    public Position3D getPos() {
        return pos;
    }

    public Position3D getVelocity() {
        return velocity;
    }

    /**
     * Function to apply gravity in the X dimension
     * @param m Moon to apply it from
     */
    public void applyGravityX(Moon m) {
        if(this.getPos().getX() > m.getPos().getX()) {
            this.getVelocity().setX(this.getVelocity().getX() - 1);
            m.getVelocity().setX(m.getVelocity().getX() + 1);
        }else if(this.getPos().getX() < m.getPos().getX()){
            this.getVelocity().setX(this.getVelocity().getX() + 1);
            m.getVelocity().setX(m.getVelocity().getX() - 1);
        }
    }

    /**
     * Function to apply gravity in the Y dimension
     * @param m Moon to apply it from
     */
    public void applyGravityY(Moon m) {
        if(this.getPos().getY() > m.getPos().getY()) {
            this.getVelocity().setY(this.getVelocity().getY() - 1);
            m.getVelocity().setY(m.getVelocity().getY() + 1);
        }else if(this.getPos().getY() < m.getPos().getY()){
            this.getVelocity().setY(this.getVelocity().getY() + 1);
            m.getVelocity().setY(m.getVelocity().getY() - 1);
        }
    }

    /**
     * Function to apply gravity in the Z dimension
     * @param m Moon to apply it from
     */
    public void applyGravityZ(Moon m) {
        if(this.getPos().getZ() > m.getPos().getZ()) {
            this.getVelocity().setZ(this.getVelocity().getZ() - 1);
            m.getVelocity().setZ(m.getVelocity().getZ() + 1);
        }else if(this.getPos().getZ() < m.getPos().getZ()){
            this.getVelocity().setZ(this.getVelocity().getZ() + 1);
            m.getVelocity().setZ(m.getVelocity().getZ() - 1);
        }
    }

    /**
     * Apply gravity from the moon on all dimensions
     * @param m Moon to apply it from
     */
    public void applyGravity(Moon m) {
        this.applyGravityX(m);
        this.applyGravityY(m);
        this.applyGravityZ(m);
    }

    /**
     * Apply the velocity to the position for the X axis
     */
    public void applyVelocityX() {
        this.getPos().setX(this.getPos().getX() + this.getVelocity().getX());
    }

    /**
     * Apply the velocity to the position for the Y axis
     */
    public void applyVelocityY() {
        this.getPos().setY(this.getPos().getY() + this.getVelocity().getY());
    }

    /**
     * Apply the velocity to the position for the Z axis
     */
    public void applyVelocityZ() {
        this.getPos().setZ(this.getPos().getZ() + this.getVelocity().getZ());
    }

    /**
     * Apply velocity to all axis
     */
    public void applyVelocity() {
        this.applyVelocityX();
        this.applyVelocityY();
        this.applyVelocityZ();
    }

    /**
     * Work out the potential energy using the positions
     * @return Potential energy of the moon
     */
    public int getPotentialEnergy() {
        return Math.abs(this.getPos().getX()) + Math.abs(this.getPos().getY()) + Math.abs(this.getPos().getZ());
    }

    /**
     * Work out the kinetic energy using the velocity
     * @return Kinetic energy of the moon
     */
    public int getKineticEnergy() {
        return Math.abs(this.getVelocity().getX()) + Math.abs(this.getVelocity().getY()) + Math.abs(this.getVelocity().getZ());
    }

    /**
     * Work out the total energy by summing potential and kinetic energy of the moon
     * @return Total energy of the moon
     */
    public int getTotalEnergy() {
        return this.getKineticEnergy() * this.getPotentialEnergy();
    }

}
