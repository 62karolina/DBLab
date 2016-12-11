import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

/**
 * Created by Каролина on 08.12.2016.
 */
public class GUI{

    private static final String url = "jdbc:mysql://localhost:3306/armbyh";
    private static final String user = "root";
    private static final String password = "123";
    DatabaseTableModel model;
    // JDBC variables for opening and managing connection
    private Connection con;
    private Statement st = null;
    private JTable table;
    JComboBox comboBox;
    JComboBox sortBy;

    private static final String[] data = {"Торговая накладная", "Информация об организациии", "Заказчик",
            "Информация о продукте", "Информация о должностном лице", "Инфрмация о плательщике", "Информация о выписке",
            "Информация об ответственном лице", "Информация о банке", "Информация о производителе"};
    private static final String[] infOfProd = {"Id", "Manufacturer", "Date of supply", "Id of product"};

/*  ############################
        Не забывать коммитить
    ############################
 */


    public void  CreateGUI() throws SQLException, ClassNotFoundException {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel(new BorderLayout());


        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        JTextField textField3 = new JTextField();
        JTextField textField4 = new JTextField();
        JTextField textField5 = new JTextField();
        JTextField textField6 = new JTextField();
        JTextField textField7 = new JTextField();
        JTextField textField8 = new JTextField();
        JTextField textField9 = new JTextField();
        JTextField textField10= new JTextField();
        JTextField textField11 = new JTextField();
        JTextField textField12 = new JTextField();




        JFrame frame = new JFrame("Курсовая");
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 600);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        model = new DatabaseTableModel();


        Container pane = new Container();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        panel.add(pane, BorderLayout.LINE_START);



        comboBox = new JComboBox(data);
        panel.add(comboBox, BorderLayout.PAGE_START);

        sortBy = new JComboBox();
        panel.add(sortBy, BorderLayout.PAGE_END);

        JButton button = new JButton("Добавить данные");
        pane.add(button);
        JButton deleteRow = new JButton("Удалить запись");
        pane.add(deleteRow);
        JButton updateBut = new JButton("Изменить данные");
        pane.add(updateBut);


        button.setPreferredSize(new Dimension(150,50));
        pane.add(textField1).setVisible(true);
        pane.add(textField2).setVisible(true);
        pane.add(textField3).setVisible(true);
        pane.add(textField4).setVisible(true);
        pane.add(textField5).setVisible(true);
        pane.add(textField6).setVisible(true);
        pane.add(textField7).setVisible(true);
        pane.add(textField8).setVisible(true);
        pane.add(textField9).setVisible(true);
        pane.add(textField10).setVisible(true);
        pane.add(textField11).setVisible(true);
        pane.add(textField12).setVisible(true);


        st = con.createStatement(); // НЕ ТРОГАТЬ, НЕ УДАЛЯТЬ БОЛЬШЕ!!!!

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBox.getSelectedItem() == "Торговая накладная"){
                    sortBy.removeAllItems();
                    textField1.setVisible(true);
                    textField2.setVisible(true);
                    textField3.setVisible(true);
                    textField4.setVisible(true);
                    textField5.setVisible(true);
                    textField6.setVisible(true);
                    textField7.setVisible(true);
                    textField8.setVisible(true);
                    textField9.setVisible(true);
                    textField10.setVisible(true);
                    textField11.setVisible(true);
                    textField12.setVisible(true);
                    try {
                        String requestSQL = dorm.Waybill();
                        ResultSet rs = st.executeQuery(requestSQL);
                        model.setDataSource(rs);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = textField1.getText();
                                String s2 = textField2.getText();
                                String s3 = textField3.getText();
                                String s4 = textField4.getText();
                                String s5 = textField5.getText();
                                String s6 = textField6.getText();
                                String s7 = textField7.getText();
                                String s8 = textField8.getText();
                                String s9 = textField9.getText();
                                String s10 = textField10.getText();
                                String s11 = textField11.getText();
                                String s12 = textField12.getText();

                                String updateSQL = "INSERT INTO armbyh.waybill (Id_wb, NoWB, NameProduct, Organization, DateOfIssue, Official, Supplier, id_infPr, Id_Official, Id_organ, Id_issue, Id_consignee)" +
                                        " VALUES (" + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3  + "', '" + s4  + "', '" + s5 + "', '" + s6  + "', '" + s7  + "', '" + s8 + "', '" + s9  + "', '" + s10 + "', '" + s11  + "', '" + s12  + "');";
                                try {
                                    st.executeUpdate(updateSQL);
                                    try {
                                        String requestSQL = dorm.Waybill();
                                        ResultSet rs = st.executeQuery(requestSQL);
                                        model.setDataSource(rs);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    deleteRow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                delete("waybill");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    sortBy.addItem("Id_wb");
                    sortBy.addItem("NoWB");
                    sortBy.addItem("NameProduct");
                    sortBy.addItem("Organization");
                    sortBy.addItem("DateOfIssue");
                    sortBy.addItem("Official");
                    sortBy.addItem("Supplier");

                    sortBy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String sq = String.valueOf(sortBy.getSelectedItem());
                            if((sortBy.getSelectedItem() == "Id_wb") || (sortBy.getSelectedItem() == "NoWB")
                                    || (sortBy.getSelectedItem() == "NameProduct") || (sortBy.getSelectedItem() == "Organization")
                                    || (sortBy.getSelectedItem() == "DateOfIssue") || (sortBy.getSelectedItem() == "Official")
                                    || (sortBy.getSelectedItem() == "Supplier")){
                                try {
                                    sortBy("waybill", sq);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
                if(comboBox.getSelectedItem() == "Заказчик"){
                    sortBy.removeAllItems();
                    textField5.setVisible(false);
                    textField6.setVisible(false);
                    textField7.setVisible(false);
                    textField8.setVisible(false);
                    textField9.setVisible(false);
                    textField10.setVisible(false);
                    textField11.setVisible(false);
                    textField12.setVisible(false);
                    try {
                        String requestSQL = dorm.createConsignee();
                        ResultSet rs = st.executeQuery(requestSQL);
                        model.setDataSource(rs);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = textField1.getText();
                                String s2 = textField2.getText();
                                String s3 = textField3.getText();
                                String s4 = textField4.getText();

                                String updateSQL = "INSERT INTO armbyh.consignee (Id_consignee, Name, Adress, Id_payes)" +
                                        " VALUES (" + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3  + "', '" + s4  +"');";
                                try {
                                    st.executeUpdate(updateSQL);
                                    try {
                                        String requestSQL = dorm.createConsignee();
                                        ResultSet rs = st.executeQuery(requestSQL);
                                        model.setDataSource(rs);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    deleteRow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                delete("consignee");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    sortBy.addItem("Id_consignee");
                    sortBy.addItem("Name");
                    sortBy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String sq = String.valueOf(sortBy.getSelectedItem());
                            if((sortBy.getSelectedItem() == "Id_consignee") || (sortBy.getSelectedItem() == "Name")){
                                try {
                                    sortBy("consignee", sq);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
                if(comboBox.getSelectedItem() == "Информация об организациии"){
                    sortBy.removeAllItems();
                    textField5.setVisible(false);
                    textField6.setVisible(false);
                    textField7.setVisible(false);
                    textField8.setVisible(false);
                    textField9.setVisible(false);
                    textField10.setVisible(false);
                    textField11.setVisible(false);
                    textField12.setVisible(false);
                    try {
                        String requestSQL = dorm.infOrganization();
                        ResultSet rs = st.executeQuery(requestSQL);
                        model.setDataSource(rs);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = textField1.getText();
                                String s2 = textField2.getText();
                                String s3 = textField3.getText();
                                String s4 = textField4.getText();

                                String updateSQL = "INSERT INTO armbyh.inforganization (Id_organ, FIOofficial, NoLicense, DateOfIssue)" +
                                        " VALUES (" + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3  + "', '" + s4  +"');";
                                try {
                                    st.executeUpdate(updateSQL);
                                    try {
                                        String requestSQL = dorm.infOrganization();
                                        ResultSet rs = st.executeQuery(requestSQL);
                                        model.setDataSource(rs);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    deleteRow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                delete("inforganization");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    sortBy.addItem("Id_organ");
                    sortBy.addItem("FIOofficial");
                    sortBy.addItem("NoLicense");
                    sortBy.addItem("DateOfIssue");
                    sortBy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String sq = String.valueOf(sortBy.getSelectedItem());
                            if((sortBy.getSelectedItem() == "Id_organ") || (sortBy.getSelectedItem() == "FIOofficial")
                                    || (sortBy.getSelectedItem() == "NoLicense") || (sortBy.getSelectedItem() == "DateOfIssue")){
                                try {
                                    sortBy("inforganization", sq);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
                if(comboBox.getSelectedItem() == "Информация о должностном лице"){
                    sortBy.removeAllItems();
                    textField4.setVisible(false);
                    textField5.setVisible(false);
                    textField6.setVisible(false);
                    textField7.setVisible(false);
                    textField8.setVisible(false);
                    textField9.setVisible(false);
                    textField10.setVisible(false);
                    textField11.setVisible(false);
                    textField12.setVisible(false);
                    try {
                        String requestSQL = dorm.infOfficial();
                        ResultSet rs = st.executeQuery(requestSQL);
                        model.setDataSource(rs);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = textField1.getText();
                                String s2 = textField2.getText();
                                String s3 = textField3.getText();

                                String updateSQL = "INSERT INTO armbyh.infofficial (Id_off, FIO, ThePost)" +
                                        " VALUES (" + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3  + "');";
                                try {
                                    st.executeUpdate(updateSQL);
                                    try {
                                        String requestSQL = dorm.infOfficial();
                                        ResultSet rs = st.executeQuery(requestSQL);
                                        model.setDataSource(rs);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    deleteRow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                delete("infofficial");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    sortBy.addItem("Id_off");
                    sortBy.addItem("FIO");
                    sortBy.addItem("ThePost");
                    sortBy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String sq = String.valueOf(sortBy.getSelectedItem());
                            if((sortBy.getSelectedItem() == "Id_payer") || (sortBy.getSelectedItem() == "NameIE")
                                    || (sortBy.getSelectedItem() == "Tel")){
                                try {
                                    sortBy("infofficial", sq);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
                if(comboBox.getSelectedItem() == "Инфрмация о плательщике"){
                    sortBy.removeAllItems();
                    textField6.setVisible(false);
                    textField7.setVisible(false);
                    textField8.setVisible(false);
                    textField9.setVisible(false);
                    textField10.setVisible(false);
                    textField11.setVisible(false);
                    textField12.setVisible(false);
                    try {
                        String requestSQL = dorm.infPayer();
                        ResultSet rs = st.executeQuery(requestSQL);
                        model.setDataSource(rs);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = textField1.getText();
                                String s2 = textField2.getText();
                                String s3 = textField3.getText();
                                String s4 = textField4.getText();
                                String s5 = textField5.getText();

                                String updateSQL = "INSERT INTO armbyh.infpayer (Id_payer, NameIE, Adress, Tel, Id_bankDetails)" +
                                        " VALUES (" + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3  + "', '" + s4 + "', '" + s5 + "');";
                                try {
                                    st.executeUpdate(updateSQL);
                                    try {
                                        String requestSQL = dorm.infPayer();
                                        ResultSet rs = st.executeQuery(requestSQL);
                                        model.setDataSource(rs);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    deleteRow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                delete("infpayer");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    sortBy.addItem("Id_payer");
                    sortBy.addItem("NameIE");
                    sortBy.addItem("Tel");
                    sortBy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String sq = String.valueOf(sortBy.getSelectedItem());
                            if((sortBy.getSelectedItem() == "Id_payer") || (sortBy.getSelectedItem() == "NameIE")
                                    || (sortBy.getSelectedItem() == "Tel")){
                                try {
                                    sortBy("infpayer", sq);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
                if(comboBox.getSelectedItem() == "Информация о выписке"){
                    sortBy.removeAllItems();
                    textField5.setVisible(false);
                    textField6.setVisible(false);
                    textField7.setVisible(false);
                    textField8.setVisible(false);
                    textField9.setVisible(false);
                    textField10.setVisible(false);
                    textField11.setVisible(false);
                    textField12.setVisible(false);
                    try {
                        String requestSQL = dorm.infIssue();
                        ResultSet rs = st.executeQuery(requestSQL);
                        model.setDataSource(rs);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = textField1.getText();
                                String s2 = textField2.getText();
                                String s3 = textField3.getText();
                                String s4 = textField4.getText();
                                String updateSQL = "INSERT INTO armbyh.infissue (Id_issue, DateOfIssue, ResponsibleOfficial, Id_supply)" +
                                        " VALUES (" + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3  + "', '" + s4 + "');";
                                try {
                                    st.executeUpdate(updateSQL);
                                    try {
                                        String requestSQL = dorm.infIssue();
                                        ResultSet rs = st.executeQuery(requestSQL);
                                        model.setDataSource(rs);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    deleteRow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                delete("infissue");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    sortBy.addItem("Id_issue");
                    sortBy.addItem("DateOfIssue");
                    sortBy.addItem("ResponsibleOfficial");
                    sortBy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String sq = String.valueOf(sortBy.getSelectedItem());
                            if((sortBy.getSelectedItem() == "Id_issue") || (sortBy.getSelectedItem() == "DateOfIssue")
                                    || (sortBy.getSelectedItem() == "ResponsibleOfficial")){
                                try {
                                    sortBy("infissue", sq);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });

                }
                if(comboBox.getSelectedItem() == "Информация об ответственном лице"){
                    sortBy.removeAllItems();
                    textField4.setVisible(false);
                    textField5.setVisible(false);
                    textField6.setVisible(false);
                    textField7.setVisible(false);
                    textField8.setVisible(false);
                    textField9.setVisible(false);
                    textField10.setVisible(false);
                    textField11.setVisible(false);
                    textField12.setVisible(false);
                    try {
                        String requestSQL = dorm.infSupply();
                        ResultSet rs = st.executeQuery(requestSQL);
                        model.setDataSource(rs);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = textField1.getText();
                                String s2 = textField2.getText();
                                String s3 = textField3.getText();
                                String updateSQL = "INSERT INTO armbyh.infsupply (Id_supply, FIO, ThePost)" +
                                        " VALUES (" + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3 + "');";
                                try {
                                    st.executeUpdate(updateSQL);
                                    try {
                                        String requestSQL = dorm.infSupply();
                                        ResultSet rs = st.executeQuery(requestSQL);
                                        model.setDataSource(rs);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    deleteRow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                delete("infsupply");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    sortBy.addItem("Id_supply");
                    sortBy.addItem("FIO");
                    sortBy.addItem("ThePost");

                    sortBy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String sq = String.valueOf(sortBy.getSelectedItem());
                            if((sortBy.getSelectedItem() == "Id_supply") || (sortBy.getSelectedItem() == "FIO")
                                    || (sortBy.getSelectedItem() == "ThePost")){
                                try {
                                    sortBy("infsupply", sq);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
                if(comboBox.getSelectedItem() == "Информация о банке"){
                    sortBy.removeAllItems();
                    textField5.setVisible(false);
                    textField6.setVisible(false);
                    textField7.setVisible(false);
                    textField8.setVisible(false);
                    textField9.setVisible(false);
                    textField10.setVisible(false);
                    textField11.setVisible(false);
                    textField12.setVisible(false);
                    try {
                        String requestSQL = dorm.bankDetails();
                        ResultSet rs = st.executeQuery(requestSQL);
                        model.setDataSource(rs);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = textField1.getText();
                                String s2 = textField2.getText();
                                String s3 = textField3.getText();
                                String s4 = textField4.getText();
                                String updateSQL = "INSERT INTO armbyh.bankdetails (Id_BD , ChekingAccount, NameBank, AdressOfBank)" +
                                        " VALUES (" + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3 + "', '" + s4 + "');";
                                try {
                                    st.executeUpdate(updateSQL);
                                    try {
                                        String requestSQL = dorm.bankDetails();
                                        ResultSet rs = st.executeQuery(requestSQL);
                                        model.setDataSource(rs);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    deleteRow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                delete("bankdetails");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    sortBy.addItem("Id_BD");
                    sortBy.addItem("ChekingAccount");
                    sortBy.addItem("NameBank");

                    sortBy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String sq = String.valueOf(sortBy.getSelectedItem());
                            if((sortBy.getSelectedItem() == "Id_BD") || (sortBy.getSelectedItem() == "ChekingAccount")
                                    || (sortBy.getSelectedItem() == "NameBank")){
                                try {
                                    sortBy("bankdetails", sq);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
                if(comboBox.getSelectedItem() == "Информация о производителе"){
                    sortBy.removeAllItems();
                    //sortBy.addItem("");
                    textField5.setVisible(false);
                    textField6.setVisible(false);
                    textField7.setVisible(false);
                    textField8.setVisible(false);
                    textField9.setVisible(false);
                    textField10.setVisible(false);
                    textField11.setVisible(false);
                    textField12.setVisible(false);
                    try {
                        String requestSQL = dorm.infManufacturer();
                        ResultSet rs = st.executeQuery(requestSQL);
                        model.setDataSource(rs);


                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = textField1.getText();
                                String s2 = textField2.getText();
                                String s3 = textField3.getText();
                                String s4 = textField4.getText();
                                String updateSQL = "INSERT INTO armbyh.infmanufacturer (Id_Man, Manufacturer, DateOfSupply, id_prod)" +
                                        " VALUES (" + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3 + "', '" + s4 + "');";
                                try {
                                    st.executeUpdate(updateSQL);
                                    try {
                                        String requestSQL = dorm.infManufacturer();
                                        ResultSet rs = st.executeQuery(requestSQL);
                                        model.setDataSource(rs);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    deleteRow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                delete("infmanufacturer");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                    updateBut.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {


                        }
                    });

                    sortBy.addItem("Id_Man");
                    sortBy.addItem("Manufacturer");
                    sortBy.addItem("DateOfSupply");

                    sortBy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String sq = String.valueOf(sortBy.getSelectedItem());
                            if((sortBy.getSelectedItem() == "Id_Man") || (sortBy.getSelectedItem() == "DateOfSupply ") || (sortBy.getSelectedItem() == "Manufacturer")){
                                try {
                                    sortBy("infmanufacturer", sq);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });


                }

                if(comboBox.getSelectedItem() == "Информация о продукте"){
                    sortBy.removeAllItems();
                    textField7.setVisible(false);
                    textField8.setVisible(false);
                    textField9.setVisible(false);
                    textField10.setVisible(false);
                    textField11.setVisible(false);
                    textField12.setVisible(false);
                    try {
                        String requestSQL = dorm.infProduct();
                        ResultSet rs = st.executeQuery(requestSQL);
                        model.setDataSource(rs);

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String s1 = textField1.getText();
                                String s2 = textField2.getText();
                                String s3 = textField3.getText();
                                String s4 = textField4.getText();
                                String s5 = textField5.getText();
                                String s6 = textField6.getText();

                                String updateSQL = "INSERT INTO armbyh.infproduct (id_prod, Name, Price, Count, Supplier, Id_manuf) VALUES ("
                                        + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3 + "', '" + s4 + "', '" + s5 + "', '" + s6 + "');";

                                try {
                                    st.execute(updateSQL);
                                    try {
                                        String requestSQL = dorm.infProduct();
                                        ResultSet rs = st.executeQuery(requestSQL);
                                        model.setDataSource(rs);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }

                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }

                            }
                        });


                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    deleteRow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                delete("infproduct");
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            } catch (ClassNotFoundException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

                    sortBy.addItem("Id_prod");
                    sortBy.addItem("Name");
                    sortBy.addItem("Price");
                    sortBy.addItem("Count");
                    sortBy.addItem("Supplier");
                    sortBy.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            String sq = String.valueOf(sortBy.getSelectedItem());
                            if((sortBy.getSelectedItem() == "Id_prod") || (sortBy.getSelectedItem() == "Name ") || (sortBy.getSelectedItem() == "Price")
                            || (sortBy.getSelectedItem() == "Count") || (sortBy.getSelectedItem() == "Supplier")){
                                try {
                                    sortBy("infproduct", sq);
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }
        });


        table = new JTable(model);
        panel.add(table, BorderLayout.CENTER);
        panel.add(new JScrollPane(table) );


    }

    public void delete(String string) throws SQLException, ClassNotFoundException {
        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();
        Object value = table.getValueAt(row, column);


        st.executeUpdate("DELETE FROM armbyh." + string + " WHERE "
                + table.getColumnName(column) + "='"
                + value +"';");
        String rss = "select * from " + string + ";";
        ResultSet resultSet = st.executeQuery(rss);
        model.setDataSource(resultSet);
    }



    public void sortBy(String string, String s1) throws SQLException, ClassNotFoundException {
        String requestSort = "select * from " + string + " order by " + s1 + ";";
        ResultSet rss = st.executeQuery(requestSort);
        model.setDataSource(rss);
    }

}
/*
                                   **
       ****************           *****
      ******************          *******
     ********************        **********
    **********************        *********
      *                *          ********
      *   ***          *            ***
      *   ***          *            **
      *                *            **
      ******************           ****
 */