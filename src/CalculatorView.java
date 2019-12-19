import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.text.DecimalFormat;


/**
 * @author Shrey Mittal and Suchit Bandaram
 *
 * Class which creates the viewframe for the calculator and sets up all of the buttons, functions, and most of the functions of the calculator.
 *
 */

public class CalculatorView implements ActionListener {
    protected JFrame frame;

    private static DecimalFormat df = new DecimalFormat("0.00");

    protected JTabbedPane tabs;

    protected JPanel displayPanel;
    protected JPanel buttonPanel;
    protected JPanel graphPanel;
    protected JPanel graphDisplayPanel;

    protected JTextArea inpEQ;
    protected JTextArea eqDisp;
    protected JTextArea graphEquation;

    protected CalculatorController calcControl;

    protected boolean canPOI = true;

    protected Graphics2D g;

    protected Font displayFont;
    Color colors[] = {new Color(144, 0, 58),
            new Color(255, 132, 0),
            new Color(255, 242, 0),
            new Color(50, 163, 71),
            new Color(0, 255, 224),
            new Color(0, 173, 255),
            new Color(184, 12, 227, 238)};

    Color integColor = new Color(247, 0, 0);



    public CalculatorView() {

        displayFont = new Font("Dialogue", Font.PLAIN, 18);

        frameInit();
        graphPanelInit();
        displayPanelInit();
        buttonPanelInit();
        tabInitializer();

        calcControl = new CalculatorController();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        g = (Graphics2D) graphDisplayPanel.getGraphics();
    }

    /**
     sets up the frame size and the amount of rows and columns in it
     */
    protected void frameInit() {
        frame = new JFrame("Graphing Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2));
        frame.setSize(1250, 720);
    }


    protected void displayPanelInit() {
        displayPanel = new JPanel();
        displayPanel.setLayout(null);
        frame.add(displayPanel, BorderLayout.WEST);
        fillDisplauPanel();
    }


    protected void buttonPanelInit() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 6));
        frame.add(buttonPanel, BorderLayout.EAST);
        addButtonsToButtonPanel();
    }


    protected void graphPanelInit() {
        graphPanel = new JPanel();
        graphPanel.setLayout(null);
        graphPanel.setVisible(true);
        frame.add(graphPanel, BorderLayout.WEST);
        addToGraphPanel();

    }


    protected void tabInitializer() {
        tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tabs.addTab("Equations", displayPanel);
        tabs.addTab("Graph", graphPanel);
        tabs.setVisible(true);
        frame.add(tabs);

        tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tabs.addTab("Keyboard", buttonPanel);
        tabs.setVisible(true);
        frame.add(tabs);
    }


    protected void fillDisplauPanel() {
        inpEQ = new JTextArea("Enter equation here: ", 3, 5);
        inpEQ.setLineWrap(true);
        inpEQ.setEditable(false);
        inpEQ.setFont(displayFont);
        inpEQ.setBounds(0, 0, 600, 50);
        displayPanel.add(inpEQ);

        eqDisp = new JTextArea("Previous Equations: \n", 3, 5);
        eqDisp.setLineWrap(true);
        eqDisp.setEditable(false);
        eqDisp.setFont(displayFont);
        eqDisp.setBounds(0, 60, 600, 600);
        displayPanel.add(eqDisp);
    }


    protected void addButtonsToButtonPanel() {
        ArrayList<JButton> buttonList = new ArrayList<JButton>();

        JButton plus = new JButton("+"); buttonList.add(plus);
        JButton minus = new JButton("-"); buttonList.add(minus);
        JButton multiply = new JButton("*"); buttonList.add(multiply);
        JButton divide = new JButton("/"); buttonList.add(divide);
        JButton openParen = new JButton("("); buttonList.add(openParen);
        JButton closeParen = new JButton(")"); buttonList.add(closeParen);

        // Row two of buttons on the keyboard
        JButton seven = new JButton(Integer.toString(7)); buttonList.add(seven);
        JButton eight = new JButton(Integer.toString(8)); buttonList.add(eight);
        JButton nine = new JButton(Integer.toString(9)); buttonList.add(nine);
        JButton power = new JButton("^"); buttonList.add(power);
        //JButton squared = new JButton("x^2"); buttonList.add(squared);
        JButton sqrt = new JButton("sqrt"); buttonList.add(sqrt);

        JButton four = new JButton(Integer.toString(4)); buttonList.add(four);
        JButton five = new JButton(Integer.toString(5)); buttonList.add(five);
        JButton six = new JButton(Integer.toString(6)); buttonList.add(six);
        JButton sine = new JButton("sin()"); buttonList.add(sine);
        JButton cosine = new JButton("cos()"); buttonList.add(cosine);
        JButton tan = new JButton("tan()"); buttonList.add(tan);

        JButton one = new JButton(Integer.toString(1)); buttonList.add(one);
        JButton two = new JButton(Integer.toString(2)); buttonList.add(two);
        JButton three = new JButton(Integer.toString(3)); buttonList.add(three);
        JButton pi = new JButton("pi"); buttonList.add(pi);
        JButton ln = new JButton("ln()"); buttonList.add(ln);
        JButton e = new JButton("e"); buttonList.add(e);

        JButton period = new JButton("."); buttonList.add(period);
        JButton zero = new JButton(Integer.toString(0)); buttonList.add(zero);
        JButton negative = new JButton("(-)"); buttonList.add(negative);
        JButton x = new JButton("x"); buttonList.add(x);
        JButton graph = new JButton("Graph"); buttonList.add(graph);

        JButton delete = new JButton("Delete"); buttonList.add(delete);
        //JButton clear = new JButton("Clear"); buttonList.add(clear);
        JButton clearAll = new JButton("<html>" + "Clear" + "<br>" + "All" + "<html>"); buttonList.add(clearAll);
        JButton clearGraph = new JButton("<html>" + "Clear" + "<br>" + "Graph" + "<html>"); buttonList.add(clearGraph);

        JButton derivative = new JButton("d/dx"); buttonList.add(derivative);
        JButton integral = new JButton("∫"); buttonList.add(integral);
        JButton sndderivative = new JButton("d^2/dx"); buttonList.add(sndderivative);
        JButton defintegral = new JButton("∮"); buttonList.add(defintegral);
        JButton ftc = new JButton("FTC"); buttonList.add(ftc);

        Font f = new Font("Dialogue", Font.PLAIN, 22);

        for (int j = 0; j < buttonList.size(); j++) {
            JButton temp = buttonList.get(j);
            temp.setFont(f);
            temp.setActionCommand(temp.getText());
            temp.addActionListener(this);
            buttonPanel.add(temp);
        }
    }

    protected void addToGraphPanel() {
        // Creates a text area for the input equations and adds it to the graphPanel
        graphEquation = new JTextArea("Graph: Y = ", 600, 50);
        graphEquation.setEditable(false);
        graphEquation.setFont(displayFont);
        graphEquation.setBounds(0, 0, 600, 50);
        graphPanel.add(graphEquation);

        graphDisplayPanel = new JPanel();
        graphDisplayPanel.setVisible(true);
        graphDisplayPanel.setLayout(null);
        graphDisplayPanel.setBounds(0, 50, 650, 650);
        graphPanel.add(graphDisplayPanel);
    }


    public void actionPerformed(ActionEvent arg0) {
        String result = arg0.getActionCommand();
        String[] fullEquation;
        String[] newText = null;

        switch (result) {
            case "Enter":

                fullEquation = calcControl.update("Enter");
                String eq = fullEquation[0];
                String sol = fullEquation[1];

                eqDisp.insert("\n", 22);
                eqDisp.insert(sol, 22);
                eqDisp.insert(" = ", 22);
                eqDisp.insert(eq, 22);
                eqDisp.insert("\n", 22);
                eqDisp.insert("\n", 22);
                inpEQ.setText("");

                if (eqDisp.getLineCount() > 24) {
                    eqDisp.setText(eq + " = " + sol);
                    eqDisp.append("\n");
                }
                break;
            case "FTC":
                String coordinates[] = calcControl.update("Graph");
                Scanner s = new Scanner(System.in);
                System.out.println("What is the b value");
                int b  = s.nextInt();
                System.out.println("What is the a value");
                int a  = s.nextInt();

                System.out.println(Double.valueOf(coordinates[b+300]).intValue() - Double.valueOf(coordinates[a+300]).intValue());


            case "Graph":
                graph();
                clearAll(newText,result);
                break;
            case "d/dx":
                drawPoints(indefiniteDerivative());
                clearAll(newText,result);
                break;

            case "d^2/dx":
                drawPoints(secondDerivative());
                clearAll(newText,result);
                break;

            case "∫":
                drawPoints(indefiniteIntegral());
                clearAll(newText,result);
                break;

            case "∮":
                Scanner scan = new Scanner(System.in);
                System.out.println("What is the b value");
                int bb = scan.nextInt();
                System.out.println("What is the a value");
                int aa = scan.nextInt();

                String coordinates1[] = calcControl.update("Graph");
                System.out.println(definiteIntegral(aa, bb, 1, coordinates1));
            case "P.O.I":
                //inflectionPoints();
                break;

            case "<html>" + "Clear" + "<br>" + "All" + "<html>":
                clearAll(newText, result);
                break;
            case "<html>" + "Clear" + "<br>" + "Graph" + "<html>":
                if (graphDisplayPanel.isShowing()) {
                    clearGraph();
                    drawGrid();
                }
                break;
            default:
                newText = calcControl.update(result);
                inpEQ.setText(newText[0]);
                graphEquation.setText(newText[0]);
                break;
        }
    }


    public void drawGrid() {
        g.setColor(Color.gray);
        int boxSize = 30;

        // Loops and draws horizontal and vertical lines for every 30 units
        for (int i = 0; i <= 20; i++) {
            if (i % 10 == 0) g.setStroke(new BasicStroke(3));
            g.drawLine(boxSize * i, 0, boxSize * i, 600);
            g.drawLine(0, boxSize * i, 600, boxSize * i);
            g.setStroke(new BasicStroke(1));
        }
    }


    public void drawPoints(String[] coordinates) {
        drawGrid();
        Random rand = new Random();
        int n = rand.nextInt(colors.length);
        for (int j = 0; j < coordinates.length - 1; j++) {
            if (coordinates[j].equals("NaN") && j > 20 && j < coordinates.length - 20 && !coordinates[j + 5].equals("NaN") && !coordinates[j - 5].equals("NaN")) {
                g.setColor(new Color(0, 148, 170));
                g.drawOval(j-5, 300 - Double.valueOf(coordinates[j + 5]).intValue() +10, 10, 10);
                System.out.println("hole: ( " + (300-j) + "," + (Double.parseDouble(coordinates[j])/30) + ")");
                continue;
            }
            else if (Double.valueOf(coordinates[j]) < 0 & Double.valueOf(coordinates[j + 1]) > 0) {
                continue;
            } else if (Double.valueOf(coordinates[j]) > 0 & Double.valueOf(coordinates[j + 1]) < 0) {
                continue;
            }
            String[] hold = definiteDerivative(coordinates);
            double[] tempFirstDer = new double[hold.length];
            for (int i = 0; i < hold.length; i++) {
                tempFirstDer[i] = Double.valueOf(hold[i]);
            }
            for (int i = 0; i < tempFirstDer.length-1; i++) {
                //if(Math.abs(tempFirstDer[i])-Math.abs(tempFirstDer[i+1]) > 0.0001) {
                    if ((tempFirstDer[i] > 0 && tempFirstDer[i + 1] < 0)) {
                        g.setColor(Color.MAGENTA);
                        g.fillOval(i, 300 - Double.valueOf(coordinates[i]).intValue(), 7, 7);
                        g.setFont(new Font("Serif", 1, 8));
                        g.drawString("MAX", i + 10, 290 - Double.valueOf(coordinates[i]).intValue());
                        g.setColor(colors[n]);
                        System.out.println("Relative Max: (" + (299-i) + "," + (Double.parseDouble(coordinates[i])/30) + ")");
                    } else if ((tempFirstDer[i] < 0 && tempFirstDer[i + 1] > 0)) {
                        g.setColor(Color.BLUE);
                        g.fillOval(i, 300 - Double.valueOf(coordinates[i]).intValue(), 7, 7);
                        g.setFont(new Font("Serif", 1, 8));
                        g.drawString("MIN", i + 10, 290 - Double.valueOf(coordinates[i]).intValue());
                        g.setColor(colors[n]);
                        System.out.println("Relative Min: (" + (299-i) + "," + (Double.parseDouble(coordinates[i])/30) + ")");
                    }
                //}
            }
            g.setColor(colors[n]);
            g.setStroke(new BasicStroke(3));
            //double[] tempIndef = indefiniteIntegral();

            String[] holder = secondDerivative(coordinates);
            double[] tempSecondDer = new double[holder.length];
            for (int i = 0; i < holder.length; i++) {
                tempSecondDer[i] = Double.valueOf(holder[i]);
            }
            for (int i = 0; i < tempSecondDer.length-1; i++) {

                if(canPOI && ((Math.abs(tempSecondDer[i] - tempSecondDer[i+1]) > 0.0000001) &&
                        ((tempSecondDer[i] >= 0 && tempSecondDer[i+1] <= 0)||(tempSecondDer[i] <= 0 && tempSecondDer[i+1] >= 0)))){
                    g.setColor(integColor);
                    g.fillOval(i, 300 - Double.valueOf(coordinates[i]).intValue(), 7, 7);
                    g.setFont(new Font("Serif",1,8));
                    g.drawString("P.O.I",i+10, 290 - Double.valueOf(coordinates[i]).intValue());
                    System.out.println("(" + (299-i) + "," + (Double.parseDouble(coordinates[i])/30) + ")");
                    g.setColor(colors[n]);
                }
            }


            if(j > 2 && !coordinates[j].equals("NaN") && !coordinates[j+1].equals("NaN") && !coordinates[j-1].equals("NaN") && !coordinates[j-2].equals("NaN"))
                g.drawLine(j, 300 - Double.valueOf(coordinates[j]).intValue(), j + 1, 300 - Double.valueOf(coordinates[j + 1]).intValue());
        }

    }

    public double slope(double y2, double y1, double x2, double x1) {
        return (double) ((y2 - y1) / (x2 - x1));
    }


    public void clearGraph() {
        g.clearRect(0, 0, 600, 600);
        canPOI = true;
    }



    public double definiteIntegral(int a, int b, int N, String[] coordinates) {
        canPOI = false;

        int h = (b - a) / N;              // step size
        double sum = 0.5 * (Double.parseDouble(coordinates[a + 300]) + Double.parseDouble(coordinates[b + 300]));    // area
        for (int i = 1; i < N; i++) {
            int x = a + h * i;
            sum += Double.parseDouble(coordinates[x + 300]);
        }

        return sum * h;
    }

    public void graph() {
        if (graphDisplayPanel.isShowing()) {
            String[] coordinates = calcControl.update("Graph");
            drawPoints(coordinates);
        }
    }

    public String[] indefiniteIntegral() {
        canPOI = false;
        String[] coordinates = calcControl.update("Graph"); //gets the function
        double[] coordinatesDouble = new double[coordinates.length];
        double[] coordinatesIntegral = new double[coordinates.length];

        if (graphDisplayPanel.isShowing()) {
            for (int i = 0; i < coordinates.length; i++) {
                coordinatesDouble[i] = Double.parseDouble(coordinates[i]);
            }
            for (int negIterator = -300; negIterator < 300; negIterator++) {
                coordinatesIntegral[negIterator + 300] = (definiteIntegral(0, negIterator, 1, coordinates)) / 30;
            }

            String[] coordIntegString = new String[coordinatesIntegral.length - 1];
            for (int i = 0; i < coordIntegString.length; i++) {
                coordIntegString[i] = ("" + coordinatesIntegral[i]);
            }
            return coordIntegString;
        }
        return null;


    }

    public String[] secondDerivative() {
        canPOI = false;
        String[] coordinates = calcControl.update("Graph");
        String[] derivative = definiteDerivative(coordinates);

        double coordinatesDouble[] = new double[derivative.length];

        for (int i = 0; i < derivative.length; i++) {
            coordinatesDouble[i] = Double.parseDouble(derivative[i]);
            System.out.println(coordinatesDouble[i]);
        }
        double[] coordinatesDerivative = new double[coordinates.length - 1];
        for (int i = 0; i < coordinatesDerivative.length-1; i++) {
            coordinatesDerivative[i] = Double.valueOf(df.format( 30.0 * (coordinatesDouble[i + 1] - coordinatesDouble[i]) / (double) (i + 1 - i)));
        }


        String[] derCoordinates = new String[coordinatesDerivative.length];
        for (int i = 0; i < derCoordinates.length; i++) {
            derCoordinates[i] = coordinatesDerivative[i] + "";
        }
        return derCoordinates;

    }
    public String[] secondDerivative(String[] coordinates) {
        //canPOI = false;
        String[] derivative = definiteDerivative(coordinates);


        String[] secondDerivative = definiteDerivative(derivative);

        return secondDerivative;


    }

    public String[] indefiniteDerivative () {
        canPOI = false;
        String[] coordinates = calcControl.update("Graph");
        double[] coordinatesDouble = new double[coordinates.length];

        if (graphDisplayPanel.isShowing()) {


            for (int i = 0; i < coordinates.length; i++) {
                coordinatesDouble[i] = Double.parseDouble(coordinates[i]);

            }
            double[] coordinatesDerivative = new double[coordinates.length - 1];
            for (int i = 0; i < coordinatesDerivative.length; i++) {
                if (coordinates[i].equals("NaN") && i > 20 && i < coordinates.length - 20 && !coordinates[i + 5].equals("NaN") && !coordinates[i - 5].equals("NaN")) {
                    g.setColor(new Color(0, 148, 170));
                    g.drawOval(i-20, 300 - Double.valueOf(coordinates[i + 5]).intValue(), 10, 10);
                    System.out.println("(" + (i-300) + ", " + coordinates[i] + ")");
                    continue;

                }
                else
                    coordinatesDerivative[i] = Math.round(30 * (coordinatesDouble[i + 1] - coordinatesDouble[i]) / (i + 1 - i));
            }


            String[] derCoordinates = new String[coordinatesDerivative.length];
            for (int i = 0; i < derCoordinates.length; i++) {
                derCoordinates[i] = coordinatesDerivative[i] + "";
            }

            return derCoordinates;
        }
        return null;
    }
    public String[] definiteDerivative (String[] coordinates) {
        //canPOI = false;
        double[] coordinatesDouble = new double[coordinates.length];
        //converts the String array into a double array
        if (graphDisplayPanel.isShowing()) {

            for (int i = 0; i < coordinates.length; i++) {
                coordinatesDouble[i] = Double.parseDouble(coordinates[i]);
            }
            double[] coordinatesDerivative = new double[coordinates.length - 1];
            for (int i = 0; i < coordinatesDerivative.length; i++) {
                coordinatesDerivative[i] = (30 * (coordinatesDouble[i + 1] - coordinatesDouble[i]) / (i + 1 - i));
                //used a scale factor of 30 to graph the function
            }


            String[] derCoordinates = new String[coordinatesDerivative.length];
            for (int i = 0; i < derCoordinates.length; i++) {
                derCoordinates[i] = coordinatesDerivative[i] + ""; //turn it into a String array
            }
            return derCoordinates;
        }
        return null;
    }


    public void clearAll (String[]newText, String result) {
        canPOI = true;
        newText = calcControl.update(result);
        inpEQ.setText(newText[0]);
        graphEquation.setText(newText[0]);
        eqDisp.setText("Previous equations: ");
    }

}