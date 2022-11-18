package bridge.view;

import bridge.model.BridgeMap;
import bridge.model.Player;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    public final String GAME_START_MESSAGE = "다리 건너기 게임을 시작합니다.";
    public final String GAME_FINAL_RESULT_MESSAGE = "최종 게임 결과";
    public final String GAME_SUCCESS_OR_NOT_MESSAGE = "게임 성공 여부: %s%n";
    public final String GAME_TOTAL_TRY_COUNT_MESSAGE = "총 시도한 횟수: %d%n";
    public final String BRIDGE_START_BRACKET = "[ ";
    public final String BRIDGE_END_BRACKET = " ]";
    public final String SUCCESS = "성공";
    public final String FAIL = "실패";

    public void printStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(BridgeMap bridgeMap) {
        System.out.println(BRIDGE_START_BRACKET + String.join(" | ", bridgeMap.getUpperBridgeMap()) + BRIDGE_END_BRACKET);
        System.out.println(BRIDGE_START_BRACKET + String.join(" | ", bridgeMap.getLowerBridgeMap()) + BRIDGE_END_BRACKET);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(Player player, BridgeMap bridgeMap, boolean win) {
        System.out.println(GAME_FINAL_RESULT_MESSAGE);
        printMap(bridgeMap);
        String result = FAIL;

        if (win) {
            result = SUCCESS;
        }

        System.out.printf(GAME_SUCCESS_OR_NOT_MESSAGE, result);
        System.out.printf(GAME_TOTAL_TRY_COUNT_MESSAGE, player.getTryCount());
    }

}
