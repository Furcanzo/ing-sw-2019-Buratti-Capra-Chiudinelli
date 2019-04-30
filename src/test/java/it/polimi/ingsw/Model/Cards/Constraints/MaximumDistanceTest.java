package it.polimi.ingsw.Model.Cards.Constraints;

import it.polimi.ingsw.Exception.NotFoundException;
import it.polimi.ingsw.Model.Map.Board;
import it.polimi.ingsw.Model.Map.Square;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.TargetParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaximumDistanceTest {
    Player owner;
    Board board;
    Square enemySquare,enemySquare2,enemySquare3;
    MaximumDistance test;
    TargetParameter target;

    @BeforeEach
    public void setup() {
        owner = new Player(true, "blue", "Franco");
        board = new Board(null, "./resources/Board/Board1.json");
        target = new TargetParameter(null, owner, null, null, null);
        test = new MaximumDistance(2);
    }

    @Test
    public void oneOutOfOneInRangeTarget(){
        try {
            owner.setPosition(board.find(1,1 ));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare = board.find(2,1);
            target.getConstraintSquareList().add(enemySquare);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(true, test.canShoot(target));
    }

    @Test
    public void oneOutOfOneNotInRangeTarget(){
        try {
            owner.setPosition(board.find(1,1 ));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare = board.find(3,2);
            target.getConstraintSquareList().add(enemySquare);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(false, test.canShoot(target));
    }

    @Test
    public void threeOfThreeEnemiesInRange(){
        try {
            owner.setPosition(board.find(1,1));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare = board.find(1,1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare2 = board.find(2,1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare3 = board.find(3,1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        target.getConstraintSquareList().add(enemySquare);
        target.getConstraintSquareList().add(enemySquare2);
        target.getConstraintSquareList().add(enemySquare3);
        assertEquals(true, test.canShoot(target));
    }

    @Test
    public void oneOfThreeEnemiesOutOfRange(){
        try {
            owner.setPosition(board.find(2,2));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare = board.find(1,1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare2 = board.find(2,1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare3 = board.find(3,1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        target.getConstraintSquareList().add(enemySquare);
        target.getConstraintSquareList().add(enemySquare2);
        target.getConstraintSquareList().add(enemySquare3);
        assertEquals(false, test.canShoot(target));
    }

    @Test
    public void allEnemiesOutOfRange(){
        try {
            owner.setPosition(board.find(2,1));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare = board.find(2,2);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare2 = board.find(2,3);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            enemySquare3 = board.find(4,2);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        target.getConstraintSquareList().add(enemySquare);
        target.getConstraintSquareList().add(enemySquare2);
        target.getConstraintSquareList().add(enemySquare3);
        assertEquals(false, test.canShoot(target));
    }
}