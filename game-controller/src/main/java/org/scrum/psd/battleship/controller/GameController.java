package org.scrum.psd.battleship.controller;
import org.scrum.psd.battleship.board.BoardStatus;

import org.scrum.psd.battleship.controller.dto.Color;
import org.scrum.psd.battleship.controller.dto.Letter;
import org.scrum.psd.battleship.controller.dto.Position;
import org.scrum.psd.battleship.controller.dto.Ship;
import org.scrum.psd.battleship.board.GameBoard;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class GameController {
    public static boolean checkIsHit(Collection<Ship> ships, Position shot) {
        if (ships == null) {
            throw new IllegalArgumentException("ships is null");
        }

        if (shot == null) {
            throw new IllegalArgumentException("shot is null");
        }

        for (Ship ship : ships) {
            for (Position position : ship.getPositions()) {
                if (position.equals(shot)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static List<Ship> initializeShips() {
        return Arrays.asList(
                new Ship("Aircraft Carrier", 5, Color.CADET_BLUE),
                new Ship("Battleship", 4, Color.RED),
                new Ship("Submarine", 3, Color.CHARTREUSE),
                new Ship("Destroyer", 3, Color.YELLOW),
                new Ship("Patrol Boat", 2, Color.ORANGE));
    }

    public static boolean isShipValid(Ship ship) {
        return ship.getPositions().size() == ship.getSize();
    }

    public static Position getRandomPosition(int size) {
        Random random = new Random();
        Letter letter = Letter.values()[random.nextInt(size)];
        int number = random.nextInt(size);
        Position position = new Position(letter, number);
        return position;
    }
	
	public static boolean isShootPositionValid(GameBoard board, Position position){
		boolean isValid = isNotShootAgain(board, position);
		
		return isNotShootAgain(board, position) && isValidPosition(position);
		
	}
	
	private static boolean isNotShootAgain(GameBoard board, Position position) {
		BoardStatus [][] board2 = board.getBoard2();
		
		int rownum = 0;

		BoardStatus status = board2[position.getRow()][position.getColumn().ordinal()];
		
		return status == BoardStatus.HIDDEN;
	}
	
	private static boolean isValidPosition(Position position) {
		return (position.getRow() > 0 && position.getRow() < 8) &&
				(position.getColumn() != null);
	}
}
