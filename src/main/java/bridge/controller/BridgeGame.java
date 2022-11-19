package bridge.controller;

import bridge.BridgeRandomNumberGenerator;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeMapMaker;
import bridge.domain.BridgeMove;
import bridge.domain.BridgeResult;
import bridge.domain.BridgeRetry;
import bridge.model.Bridge;
import bridge.model.BridgeMap;
import bridge.model.Player;

import static bridge.util.BridgeConstant.FALL_POSITION;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final Boolean CONTINUE = true;
    private final Boolean QUIT = false;
    private final Boolean WIN = true;
    private final Boolean LOSE = false;

    BridgeMove bridgeMove = new BridgeMove();
    BridgeRetry bridgeRetry = new BridgeRetry();
    BridgeMapMaker bridgeMapMaker = new BridgeMapMaker();
    BridgeResult bridgeResult = new BridgeResult();
    Bridge bridge;
    boolean isContinue = true;
    boolean isWin = false;

    public void init() {
        bridge = new BridgeMaker(new BridgeRandomNumberGenerator()).inputMakeBridge();
        start(new Player());
    }

    public void start(Player player) {
        while (isContinue) {
            move(player);
            check(player);
            if (reachFinalLine(player)) {
                gameSet(WIN);
            }
        }
        bridgeResult.printResult(player, isWin);
    }

    private void gameSet(boolean winOrLose) {
        isWin = winOrLose;
        isContinue = QUIT;
    }

    private void check(Player player) {
        boolean moveSuccess = getMoveSuccess(player, bridge);
        bridgeMapMaker.addBridgeMapBlock(player, moveSuccess);
        bridgeResult.printMap();
        if (!moveSuccess) {
            retry(player);
        }
    }

    private boolean reachFinalLine(Player player) {
        return player.getXPosition() == bridge.getBridge()[0].length - 1;
    }

    private boolean getMoveSuccess(Player player, Bridge bridge) {
        int positionX = player.getXPosition();
        int positionY = player.getYPosition();
        return bridge.getBridge()[positionY][positionX].equals(FALL_POSITION);
    }


    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     *
     * @param player
     */
    public void move(Player player) {
        bridgeMove.movePlayer(player);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry(Player player) {
        boolean continueCommand = bridgeRetry.getContinueCode(player);
        if (continueCommand == CONTINUE) {
            updateGameStatus(player);
            return;
        }
        gameSet(LOSE);
    }

    private void updateGameStatus(Player player) {
        player.initializePosition();
        player.addTryCount();
        BridgeMap.getBridgeMap().initializeBridges();
    }
}