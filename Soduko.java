import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Soduko
{
    int rows = 9;
    int cols = rows;

    public class tile extends JButton{
        private int x;
        private int y;
        private int value;
        tile(int x , int y){
            this.x = x;
            this.y = y;
            this.value = 0;

        }
        public void setValue(int val){
            this.value = val;
        }
        public int getValue(){
            return value;
        }
    }
    JFrame frame;
    //Title Panel and label
    JPanel titlePanel;
    JLabel titleLabel;
    
    //middlePanel
    JPanel mainBoard;
    tile[][] box = new tile[rows][cols];

    //checking
    ArrayList<tile> wrongCols = new ArrayList<>();
    ArrayList<tile> wrongrows = new ArrayList<>();
    ArrayList<tile> wrongGrid = new ArrayList<>();

    HashSet<tile> preFilled = new HashSet<>(); 
    Random random;
    
    int count = 0;

    Soduko(){

        frame = new JFrame("Soduko");        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(600,100)); 
        titlePanel.setBackground(Color.cyan);
        
        titleLabel = new JLabel();
        titleLabel.setText("Soduko");        
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));

        mainBoard = new JPanel();
        mainBoard.setBackground(Color.darkGray);
        mainBoard.setLayout(new GridLayout(rows, cols));

        

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                tile tiles = new tile(i,j);
                box[i][j] = tiles;
                tiles.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));
                tiles.setBackground(Color.white);

                tiles.addActionListener(e -> {
                    
                    tiles.requestFocus();
                });
                int r = i;
                int c = j;

                tiles.addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyTyped(KeyEvent e) {
                        
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        char key = e.getKeyChar();
                        if (key >= '1' && key <= '9') {
                            int num = Integer.parseInt(String.valueOf(key));
                            box[r][c].setValue(num);
                            tiles.setText(String.valueOf(key));

                            resetBoardBackground();
                            checkColumns(c, num);
                            checkRows(r, num);
                            checkGrid(r, c, num);

                            highlightWrongTiles();

                            
                        } else {
                            tiles.setText("");
                        }
                        
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                    
                });
                
                mainBoard.add(tiles);
            }
        }


        random = new Random();
        generatePrefilledNumbers();



        titlePanel.add(titleLabel);
        


        frame.add(mainBoard,BorderLayout.CENTER);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.setVisible(true);

    }
    public void resetBoardBackground() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                box[i][j].setBackground(Color.white);  // Reset to white
            }
        }
    }
    public void highlightWrongTiles(){
        
        for (tile t : wrongCols) {
            t.setBackground(Color.red);
        }
    
        for (tile t : wrongrows) {
            t.setBackground(Color.red);
        }
    
        for (tile t : wrongGrid) {
            t.setBackground(Color.red);
        }
        

    }


    public boolean checkColumns(int col, int val){
        wrongCols.clear();
        int count = 0;
        for(int i = 0; i < 9; i++)
        {
            
            if (box[i][col].getValue() == val)
            {
                wrongCols.add(box[i][col]);
                count += 1;
            }
        }
        return count == 1;


    }
    public boolean checkRows(int row, int val){
        wrongrows.clear();
        int count = 0;
        for(int i = 0; i < 9; i++)
        {
            
            if (box[row][i].getValue() == val)
            {
                wrongrows.add(box[row][i]);
                count += 1;
            }
        }
        return count == 1;


    }

    public boolean checkGrid(int row, int col, int val) {
        wrongGrid.clear();
        HashSet<Integer> seen = new HashSet<>(); 
        
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;


        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {

                int currentValue = box[i][j].getValue();

                if (currentValue == val) {
                    wrongGrid.add(box[i][j]);
                    return false; 
                }
                if (currentValue != 0) { 
                    seen.add(currentValue);
                }
            }
        }

        return true;
}
        private void generatePrefilledNumbers() {
            int count = 0;

            while (count < 40) {
                int x = random.nextInt(0, 9); // Random row (0 to 8)
                int y = random.nextInt(0, 9); // Random column (0 to 8)
                int number = random.nextInt(1, 10); // Random number (1 to 9)

                if (canPlaceNumber(x, y, number)) {
                    box[x][y].setValue(number); // Place the number on the tile
                    box[x][y].setText(String.valueOf(number));
                    box[x][y].setEnabled(false); 
                    count++; // Increment count
                }
            }
        }
        private boolean canPlaceNumber(int x, int y, int number) {
            // Check row
            for (int j = 0; j < cols; j++) {
                if (box[x][j].getValue() == number) {
                    return false; // Number already exists in the row
                }
            }
    
            // Check column
            for (int i = 0; i < rows; i++) {
                if (box[i][y].getValue() == number) {
                    return false; // Number already exists in the column
                }
            }
    
            // Check 3x3 grid
            int gridRowStart = (x / 3) * 3;
            int gridColStart = (y / 3) * 3;
            for (int i = gridRowStart; i < gridRowStart + 3; i++) {
                for (int j = gridColStart; j < gridColStart + 3; j++) {
                    if (box[i][j].getValue() == number) {
                        return false; // Number already exists in the 3x3 grid
                    }
                }
            }
    
            return true; // Number can be placed
        }


}