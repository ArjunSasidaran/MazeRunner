package ca.mcmaster.se2aa4.mazerunner;

    public enum Direction{
        UP,RIGHT,DOWN,LEFT;

        // get the direction to the right
        public Direction getNextRight(){
            Direction[] directions = values();
            int rightIndex = (this.ordinal() + 1 + directions.length) % directions.length;
            return directions[rightIndex];
        }
        // get the direction to the left
        public Direction getNextLeft(){
            Direction[] directions = values();
            int leftIndex = (this.ordinal() - 1 + directions.length) % directions.length;
            return directions[leftIndex];
        }
    }

    

