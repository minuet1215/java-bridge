package bridge.model;

import java.util.ArrayList;
import java.util.List;

public class BridgeMap {
    private static final BridgeMap bridgeMap = new BridgeMap();
    private static final List<String> upperBridgeMap = new ArrayList<>();
    private static final List<String> lowerBridgeMap = new ArrayList<>();
    private static final String BLANK_BLOCK = " ";

    private BridgeMap() {
    }

    public static BridgeMap getBridgeMap() {
        return bridgeMap;
    }

    public List<String> getUpperBridgeMap() {
        return upperBridgeMap;
    }

    public List<String> getLowerBridgeMap() {
        return lowerBridgeMap;
    }

    public void addUpperBridgeMap(String block) {
        upperBridgeMap.add(block);
        lowerBridgeMap.add(BLANK_BLOCK);
    }

    public void addLowerBridgeMap(String block) {
        upperBridgeMap.add(BLANK_BLOCK);
        lowerBridgeMap.add(block);
    }

    public void initializeBridges() {
        upperBridgeMap.clear();
        lowerBridgeMap.clear();
    }
}
