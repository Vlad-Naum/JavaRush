package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        switch(direction){
            case UP:
                if((this.getY()-Model.FIELD_CELL_SIZE) == gameObject.getY() && this.getX() == gameObject.getX()){
                    return true;
                }
                else{
                    return false;
                }
            case DOWN:
                if((this.getY()+Model.FIELD_CELL_SIZE) == gameObject.getY() && this.getX() == gameObject.getX()){
                    return true;
                }
                else{
                    return false;
                }
            case LEFT:
                if((this.getX() - Model.FIELD_CELL_SIZE) == gameObject.getX() && this.getY() == gameObject.getY()){
                    return true;
                }
                else{
                    return false;
                }
            case RIGHT:
                if((this.getX() + Model.FIELD_CELL_SIZE) == gameObject.getX() && this.getY() == gameObject.getY()){
                    return true;
                }
                else{
                    return false;
                }
        }
        return true;
    }
}
