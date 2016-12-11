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

    private static final String[] data = {"Торговая накладная", "Информация об организациии", "Заказчик",
            "Информация о продукте", "Информация о должностном лице", "Инфрмация о плательщике", "Информация о выписке",
            "Информация об ответственном лице", "Информация о банке", "Информация о производителе"};




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

        JButton button = new JButton("Добавить данные");
        pane.add(button);
        JButton deleteRow = new JButton("Удалить запись");
        pane.add(deleteRow);



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


        st = con.createStatement(); // НЕ ТРОГАТЬ!!!!

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBox.getSelectedItem() == "Торговая накладная"){
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
                                String s4 = textField3.getText();
                                String s5 = textField5.getText();
                                String s6 = textField6.getText();
                                String s7 = textField7.getText();
                                String s8 = textField8.getText();
                                String s9 = textField9.getText();
                                String s10 = textField10.getText();
                                String s11 = textField11.getText();
                                String s12 = textField12.getText();

                                String updateSQL = "INSERT INTO armbyh.waybill (Id_wb, NoWB, NameProduct, Organization, DateOfIssue, Official, Supplier, id_infPr, Id_Official, Id_organ, Id_issue, Id_consignee)" +
                                        " VALUES (" + Integer.valueOf(s1) + ", '" + s2 + "', '" + s3  + "', '" + s4  + ", '" + s5 + "', '" + s6  + "', '" + s7  + ", '" + s8 + "', '" + s9  + "', '" + s10 + "', '" + s11  + "', '" + s12  + "');";
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
                }
                if(comboBox.getSelectedItem() == "Заказчик"){
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
                                String s4 = textField3.getText();

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
                }
                if(comboBox.getSelectedItem() == "Информация об организациии"){
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
                                String s4 = textField3.getText();

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
                }
                if(comboBox.getSelectedItem() == "Информация о должностном лице"){
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
                }
                if(comboBox.getSelectedItem() == "Инфрмация о плательщике"){
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
                                String s4 = textField3.getText();
                                String s5 = textField3.getText();

                                String updateSQL = "INSERT INTO armbyh.infpayer (Id_payer, NameIE, Adress, Tel., Id_bankDetails)" +
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
                }
                if(comboBox.getSelectedItem() == "Информация о выписке"){
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
                                String s4 = textField3.getText();
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
                }
                if(comboBox.getSelectedItem() == "Информация об ответственном лице"){
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
                }
                if(comboBox.getSelectedItem() == "Информация о банке"){

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
                }
                if(comboBox.getSelectedItem() == "Информация о производителе"){
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
                }

                if(comboBox.getSelectedItem() == "Информация о продукте"){
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


}
