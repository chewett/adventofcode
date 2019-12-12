package net.chewett.adventofcode2019;

public class Moon {

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

    public void applyGravity(Moon m) {
        if(this.getPos().getX() > m.getPos().getX()) {
            this.getVelocity().setX(this.getVelocity().getX() - 1);
            m.getVelocity().setX(m.getVelocity().getX() + 1);
        }else if(this.getPos().getX() < m.getPos().getX()){
            this.getVelocity().setX(this.getVelocity().getX() + 1);
            m.getVelocity().setX(m.getVelocity().getX() - 1);
        }

        if(this.getPos().getY() > m.getPos().getY()) {
            this.getVelocity().setY(this.getVelocity().getY() - 1);
            m.getVelocity().setY(m.getVelocity().getY() + 1);
        }else if(this.getPos().getY() < m.getPos().getY()){
            this.getVelocity().setY(this.getVelocity().getY() + 1);
            m.getVelocity().setY(m.getVelocity().getY() - 1);
        }

        if(this.getPos().getZ() > m.getPos().getZ()) {
            this.getVelocity().setZ(this.getVelocity().getZ() - 1);
            m.getVelocity().setZ(m.getVelocity().getZ() + 1);
        }else if(this.getPos().getZ() < m.getPos().getZ()){
            this.getVelocity().setZ(this.getVelocity().getZ() + 1);
            m.getVelocity().setZ(m.getVelocity().getZ() - 1);
        }
    }

    public void applyVelocity() {
        this.getPos().setX(this.getPos().getX() + this.getVelocity().getX());
        this.getPos().setY(this.getPos().getY() + this.getVelocity().getY());
        this.getPos().setZ(this.getPos().getZ() + this.getVelocity().getZ());
    }

    public int getPotentialEnergy() {
        return Math.abs(this.getPos().getX()) + Math.abs(this.getPos().getY()) + Math.abs(this.getPos().getZ());
    }

    public int getKineticEnergy() {
        return Math.abs(this.getVelocity().getX()) + Math.abs(this.getVelocity().getY()) + Math.abs(this.getVelocity().getZ());
    }

    public int getTotalEnergy() {
        return this.getKineticEnergy() * this.getPotentialEnergy();
    }

}
