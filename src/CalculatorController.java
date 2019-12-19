

public class CalculatorController {
    private CalculatorModel calcModel;

    //new instance if calculator model
    public CalculatorController() {
        calcModel = new CalculatorModel();
    }

    //if the enter button is clicked, then it will be graphed
    public String[] update(String action) {
        if (action.equals("Graph")) {
            return calcModel.evaluateGraph();
        }
        return calcModel.performAction(action);
    }
}
