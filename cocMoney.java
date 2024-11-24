import java.awt.GraphicsEnvironment;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;



public class cocMoney{
    private static final String FILE_NAME = "spending.txt";
    public static void main(String[] args) {
        System.out.println(GraphicsEnvironment.isHeadless());
        //Ftiaxnume to paratyro me titlo coc money
        JFrame frame = new JFrame("Coc money");
        //Platos kai ypsos tu paratyru
        frame.setSize(800, 600);
        //To kleisimo tu paratyru to x dhladh
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Ta ftiaxw xeirokinhta ta kubia ktl
        frame.setLayout(null);

        //Dhmiurgei mia etiketa opy rwtaei na valeis ta lefta pu xodepses
        JLabel expenseLabel = new JLabel("Enter amount spent you stupid nigga: ");
        //Megetos etiketas(x, y, platos, ypsos)
        expenseLabel.setBounds(40, 40, 400, 60);
        //Vazei thn etiketa sto paratyro
        frame.add(expenseLabel);

        //O xrhsths borei na phktrologhsei
        JTextField expenseField = new JTextField();
        //Megetos tu keimenu
        expenseField.setBounds(360, 40, 300, 60);
        //To kanei add sto paratyro mas
        frame.add(expenseField);

        //Kubi gia na prostesume ta exoda
        JButton addButton = new JButton("Add Expense: ");
        //Megetos tu kubiu
        addButton.setBounds(40, 140, 300, 60);
        //Kanume add to kubi sto paratyro
        frame.add(addButton);

        //To kubi gia na dume ta synolika exoda
        JButton viewButton = new JButton("View Total");
        viewButton.setBounds(400, 140, 300, 60);
        frame.add(viewButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(40, 240, 660, 240);
        //Orizei to keimeno na mhn einai epexergasimo
        resultArea.setEditable(false);
        frame.add(resultArea);

        
        addButton.addActionListener(new ActionListener() {
            //Otan o xrhsths pathsei to kubi
            public void actionPerformed(ActionEvent e) {
                //Pairnei thn timh pu exei eisaxtei sto pedio keimenu kai to apothkeveu
                String input = expenseField.getText();
                //Elegxei an einai aritmos kai an den einai vgazei error
                try {
                    //Metatreoei to string se double dioti to gettext den ginetai na einai double
                    double amount = Double.parseDouble(input);
                    //Synarthsh
                    addExpense(amount);
                    //Enhmerwsh sto paratyro oti ta exoda bhkan epityxws
                    resultArea.setText("Expense added succesfully!");
                    //Katarismos paratyru gia eisagwgh newn dedomenwn
                    expenseField.setText("");
                } catch (NumberFormatException ex) {
                    resultArea.setText("Invalid input");
                }
            }
        });  

        viewButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                double total = viewTotalExpenses();
                resultArea.setText("Your total expenses so far: $"+ total);

            }
        });

        frame.setVisible(true);
    }

    private static void addExpense(double amount){
        try (FileWriter writer = new FileWriter(FILE_NAME, true)){
            writer.write(amount + "\n");
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private static double viewTotalExpenses(){
        double total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null){
                total += Double.parseDouble(line);
            }
        } catch (IOException | NumberFormatException e){
            e.printStackTrace();
        }
        return total;
    }
}  