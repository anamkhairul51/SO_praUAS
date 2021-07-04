package SO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
Author : Muhammad Khairul Anam
NIM    : A11.2019.11712
Kelas  : A11.4415
 */

class fcfs extends JFrame implements ActionListener {
    JButton jb[] = new JButton[3];
    JTextField jt1[], jt2[];
    JLabel jl[], jl1, jl2, jl3;
    JPanel jp, jp1;
    Container con;
    int k, p;
    String str[] = {
            "SUBMIT",
            "RESET",
            "EXIT"
    };
    String str1[] = {
            "Proses",
            "Arrival Time",
            "Burst Time",
            "Waiting Time",
            "Finish Time",
            "Turn Around Time",
            "Total Turn Around Time"
    };

    public fcfs() {
        super("Algoritma Penjadwalan FIFO");
        con = getContentPane();

        k = Integer.parseInt(JOptionPane.showInputDialog("Masukkan banyaknya proses"));

        jl1 = new JLabel("proses");
        jl2 = new JLabel("Waktu Kedatangan");
        jl3 = new JLabel("Burst Time");
        jl = new JLabel[k];
        jt1 = new JTextField[k];
        jt2 = new JTextField[k];

        for (int i = 0; i < k; i++) {
            jl[i] = new JLabel("proses" + (i + 1));
            jt1[i] = new JTextField(10);
            jt2[i] = new JTextField(10);
        }

        for (int i = 0; i < 3; i++) {
            jb[i] = new JButton(str[i]);
        }

        con.setLayout(new GridLayout(k + 2, 3));
        con.add(jl1);
        con.add(jl2);
        con.add(jl3);

        int l = 0;

        for (int i = 0; i < k; i++) {
            con.add(jl[l]);
            con.add(jt1[l]);
            con.add(jt2[l]);
            l++;
        }
        l = 0;
        for (int i = 0; i < 3; i++) {
            con.add(jb[l]);
            jb[l].addActionListener(this);
            l++;
        }
    } //end of constructor

    public void actionPerformed(ActionEvent ae) {
        int FT[] = new int[k];
        int WT[] = new int[k];
        int TAT[] = new int[k];
        float NTAT[] = new float[k];
        float sum = 0;
        float avg;
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        jp = new JPanel();
        jp1 = new JPanel();
        jp.setLayout(new GridLayout(k + 1, 7));
        jp1.setLayout(new FlowLayout());

        if (ae.getSource() == jb[2]) {
            System.exit(0);
        } else if (ae.getSource() == jb[0]) {
            FT[0] = Integer.parseInt(jt1[0].getText()) +
                    Integer.parseInt(jt2[0].getText());

            for (int i = 0; i < k; i++) {
                if (i == 0) {
                    WT[i] = 0;
                } else {
                    if (FT[i - 1] < Integer.parseInt(jt1[i].getText())) {
                        FT[i] =
                                Integer.parseInt(jt1[i].getText()) + Integer.parseInt(jt2[i].getText());
                        WT[i] = 0;
                    } else {
                        FT[i] = FT[i - 1] + Integer.parseInt(jt2[i].getText());
                        WT[i] = FT[i - 1] - Integer.parseInt(jt1[i].getText());
                    }

                }
                TAT[i] = WT[i] + Integer.parseInt(jt2[i].getText());
                NTAT[i] = TAT[i] / (Integer.parseInt(jt2[i].getText()));
                sum = sum + WT[i];

            } //end for loop


            for (int i = 0; i < 7; i++) {
                jp.add(new JLabel(str1[i]));
            }
            for (int i = 0; i < k; i++) {
                jp.add(new JLabel("proses" + (i + 1)));
                jp.add(new JLabel("   " + Integer.parseInt(jt1[i].getText())));
                jp.add(new JLabel("" + Integer.parseInt(jt2[i].getText())));
                jp.add(new JLabel("" + WT[i]));
                jp.add(new JLabel("" + FT[i]));
                jp.add(new JLabel("" + TAT[i]));
                jp.add(new JLabel("" + NTAT[i]));

            }
            avg = sum / k;
            String str2 = "Average Waiting Time = " + avg;
            jp1.add(new JLabel(str2));
            main.add(jp, BorderLayout.NORTH);
            main.add(jp1, BorderLayout.SOUTH);

            int sumTAT = 0;
            for (int i = 0; i < TAT.length; i++) {
                sumTAT = sumTAT + TAT[i];
            }

            int avTAT = sumTAT / TAT.length;

            String str3 = "Average Turn Around Time= " + avTAT;
            jp1.add(new JLabel(str3));
            main.add(jp, BorderLayout.NORTH);
            main.add(jp1, BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(null, main, "output", JOptionPane.PLAIN_MESSAGE);

        } else if (ae.getSource() == jb[1]) {
            setVisible(false);
            fcfs window = new fcfs();
            window.setSize(400, 300);
            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
    } //END ACTION PERFORMED

    public static void main(String[] args) {
        fcfs window = new fcfs();
        window.setSize(400, 300);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } //end main
} //end class