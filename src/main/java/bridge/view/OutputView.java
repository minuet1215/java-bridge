package bridge.view;

import bridge.model.Player;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    public final String GAME_START_MESSAGE = "다리 건너기 게임을 시작합니다.";
    public final String GAME_SUCCESS_OR_NOT_MESSAGE = "게임 성공 여부: %s%n";
    public final String GAME_TOTAL_TRY_COUNT_MESSAGE = "총 시도한 횟수: %d%n";

    public void printStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap() {
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(Player player, boolean win) {
        String result = "실패";

        if (win) {
            result = "성공";
        }

        System.out.printf(GAME_SUCCESS_OR_NOT_MESSAGE, result);
        System.out.printf(GAME_TOTAL_TRY_COUNT_MESSAGE, player.getTryCount());
    }

}
