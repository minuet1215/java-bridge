package bridge.domain;

import bridge.BridgeNumberGenerator;
import bridge.model.Bridge;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bridge.util.BridgeConstant.DOWN_KEY;
import static bridge.util.BridgeConstant.DOWN_POSITION;
import static bridge.util.BridgeConstant.UP_KEY;
import static bridge.util.BridgeConstant.UP_POSITION;
import static bridge.util.BridgeConstant.FALL_BLOCK;


/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {
    private final int DOWN = 0;
    private final int BRIDGE_WIDTH = 2;
    private final BridgeNumberGenerator bridgeNumberGenerator;

    InputView inputView = InputView.getInputView();
    OutputView outputView = OutputView.getOutputView();

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public Bridge inputMakeBridge() {
        outputView.printStartMessage();
        int bridgeSize = inputView.readBridgeSize();
        return new Bridge(bridgeSize);
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        List<String> bridge = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            int key = bridgeNumberGenerator.generate();
            bridge.add(addBridge(key));
        }
        return bridge;
    }

    private String addBridge(int key) {
        if (key == DOWN) {
            return DOWN_KEY;
        }
        return UP_KEY;
    }

    public String[][] make2DBridge(List<String> bridge) {
        String[][] bridge2D = new String[BRIDGE_WIDTH][bridge.size()];
        for (int x = 0; x < bridge.size(); x++) {
            String key = bridge.get(x);
            bridge2D[UP_POSITION][x] = getKey(UP_POSITION, key);
            bridge2D[DOWN_POSITION][x] = getKey(DOWN_POSITION, key);
        }
        return bridge2D;
    }

    private String getKey(int index, String key) {
        if (index == UP_POSITION && key.equals(UP_KEY)) {
            return UP_KEY;
        } else if (index == DOWN_POSITION && key.equals(DOWN_KEY)) {
            return DOWN_KEY;
        }
        return FALL_BLOCK;
    }
}
