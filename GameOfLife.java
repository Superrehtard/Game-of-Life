/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conwaysgame;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Pruthivi Reddy Parne
 */
public class GameOfLife {

    static JFrame frame = new JFrame("Game of Life");
    
//    try{
//    static JPanel panel = new JPanel(new GridLayout(get2DArray().length, get2DArray()[0].length));
//}catch(FileNotFoundException e){
//System.out.println("File not found");
//}

    static JPanel panel;// = new JPanel(new GridLayout(get2DArray().length, 0));

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        
        int[][] cells = deepCopy(get2DArray());
        int[][] newcells;

        newcells = deepCopy(get2DArray());
        displayCells(newcells);
        int numOfIterations = 10;
        while (numOfIterations > 0) {
            Thread.sleep(500);
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
//                    System.out.println(neighbourCount(i, j, cells));
                    if (neighbourCount(i, j, cells) < 2) {
                        newcells[i][j] = 0;
                    } else if (neighbourCount(i, j, cells) == 2 || neighbourCount(i, j, cells) == 3) {
                        if (newcells[i][j] == 0 && neighbourCount(i, j, cells) == 3) {
                            newcells[i][j] = 1;
                        }
                    } else if (neighbourCount(i, j, cells) > 3) {
                        newcells[i][j] = 0;
                    }
                }
            }
//            System.out.println();
            displayCells(newcells);
            cells = deepCopy(newcells);
//            numOfIterations--;
        }
        
    }
    
    public static int[][] get2DArray() throws FileNotFoundException{
        Scanner scan = new Scanner(new File("Game of Life patterns/Pulsar.txt"));
        
        int rows = scan.nextInt();
        int columns = scan.nextInt();
        
        int[][] cells = new int[rows][columns];
        while(scan.hasNext()){
            cells[scan.nextInt()][scan.nextInt()] = 1;                        
        }
        
        return cells;
    }

    public static void displayCells(int[][] toDisplay) throws FileNotFoundException{
        
        panel = new JPanel(new GridLayout(get2DArray().length, get2DArray()[0].length));
        panel.removeAll();

        for (int i = 0; i < toDisplay.length; i++) {
            for (int j = 0; j < toDisplay[i].length; j++) {
                if (toDisplay[i][j] == 0) {
                    JLabel l = new JLabel();
                    l.setBackground(Color.GRAY);
                    l.setOpaque(true);
//                    JLabel l = new JLabel(" ", JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
//                    l.setFont(l.getFont().deriveFont(20f));
                    panel.add(l);
                } else if (toDisplay[i][j] == 1) {
                    JLabel l = new JLabel();
                    l.setBackground(Color.BLACK);
                    l.setOpaque(true);
//                    JLabel l = new JLabel("*", JLabel.CENTER);
                    l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
//                    l.setFont(l.getFont().deriveFont(20f));
                    panel.add(l);
                }

            }
        }
//        panel.revalidate();
//        panel.repaint();
        
        frame.setContentPane(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static int specialCaseFinder(int i, int j, int n, int m) {
        if (i == 0 && j > 0 && j < m - 1) {
            return 1;
        } else if (i > 0 && i < n - 1 && j == 0) {
            return 2;
        } else if (i == 0 && j == 0) {
            return 5;
        } else if (i == 0 && j == m - 1) {
            return 6;
        } else if (i == n - 1 && j > 0 && j < m - 1) {
            return 3;
        } else if (j == m - 1 && i > 0 && i < n - 1) {
            return 4;
        } else if (i == n - 1 && j == 0) {
            return 7;
        } else if (i == n - 1 && j == m - 1) {
            return 8;
        } else {
            return 0;
        }
    }

    public static int neighbourCount(int i, int j, int[][] a) {

        switch (specialCaseFinder(i, j, a.length, a[i].length)) {
            case 1: {
                int count = 0;
                for (int m = i; m <= i + 1; m++) {
                    for (int n = j - 1; n <= j + 1; n++) {
                        if (!(m == i && n == j) && a[m][n] == 1) {
                            count++;
                        }
                    }
                }
                return count;
            }
            case 2: {
                int count = 0;
                for (int m = i - 1; m <= i + 1; m++) {
                    for (int n = j; n <= j + 1; n++) {
                        if (!(m == i && n == j) && a[m][n] == 1) {
                            count++;
                        }
                    }
                }
                return count;
            }
            case 3: {
                int count = 0;
                for (int m = i - 1; m <= i; m++) {
                    for (int n = j - 1; n <= j + 1; n++) {
                        if (!(m == i && n == j) && a[m][n] == 1) {
                            count++;
                        }
                    }
                }
                return count;
            }
            case 4: {
                int count = 0;
                for (int m = i - 1; m <= i + 1; m++) {
                    for (int n = j - 1; n <= j; n++) {
                        if (!(m == i && n == j) && a[m][n] == 1) {
                            count++;
                        }
                    }
                }
                return count;
            }
            case 5: {
                int count = 0;
                for (int m = i; m <= i + 1; m++) {
                    for (int n = j; n <= j + 1; n++) {
                        if (!(m == i && n == j) && a[m][n] == 1) {
                            count++;
                        }
                    }
                }
                return count;
            }
            case 6: {
                int count = 0;
                for (int m = i; m <= i + 1; m++) {
                    for (int n = j - 1; n <= j; n++) {
                        if (!(m == i && n == j) && a[m][n] == 1) {
                            count++;
                        }
                    }
                }
                return count;
            }
            case 7: {
                int count = 0;
                for (int m = i - 1; m <= i; m++) {
                    for (int n = j; n <= j + 1; n++) {
                        if (!(m == i && n == j) && a[m][n] == 1) {
                            count++;
                        }
                    }
                }
                return count;
            }
            case 8: {
                int count = 0;
                for (int m = i - 1; m <= i; m++) {
                    for (int n = j - 1; n <= j; n++) {
                        if (!(m == i && n == j) && a[m][n] == 1) {
                            count++;
                        }
                    }
                }
                return count;
            }
            case 0: {
                int count = 0;
                for (int m = i - 1; m <= i + 1; m++) {
                    for (int n = j - 1; n <= j + 1; n++) {
                        if (!(m == i && n == j) && a[m][n] == 1) {
                            count++;
                        }
                    }
                }
                return count;
            }
            default:
                return 0;

        }
    }

    public static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}
