/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import fsm.EventEmiter;
import taxikarma.Simulation;

/**
 *
 * @author Alex
 */
public class MainWindow extends javax.swing.JFrame {

    
    private static Simulation simulation;
    private static EventEmiter overlord;
    /**
     * Creates new form Input
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taxiModeGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btn_animate = new javax.swing.JButton();
        tf_animate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_path = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btn_route = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        rb_parade = new javax.swing.JRadioButton();
        rb_search = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        tf_cantClients = new javax.swing.JTextField();
        btn_cantClients = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tf_actual = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tf_work = new javax.swing.JTextField();
        btn_addClient = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tf_park = new javax.swing.JTextField();
        btn_park = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btn_mode = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tf_home = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btn_addTaxi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_Map = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        tf_map = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        tf_building = new javax.swing.JTextField();
        btn_load = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        tf_dayDuration = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tf_Percentage = new javax.swing.JTextField();
        btn_dayCycle = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtA_Cant = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        l_Time = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Animar");

        btn_animate.setText("Enviar");
        btn_animate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_animateActionPerformed(evt);
            }
        });

        tf_animate.setText("0");
        tf_animate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_animateActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Mostrar");

        btn_path.setText("Apagado");
        btn_path.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pathActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Ruta");

        btn_route.setText("Apagado");
        btn_route.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_routeActionPerformed(evt);
            }
        });

        jLabel4.setText("Modo");

        taxiModeGroup.add(rb_parade);
        rb_parade.setText("Pasear");

        taxiModeGroup.add(rb_search);
        rb_search.setText("Buscar clientes");
        rb_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_searchActionPerformed(evt);
            }
        });

        jLabel5.setText("Cant. clientes");

        btn_cantClients.setText("Enviar");
        btn_cantClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cantClientsActionPerformed(evt);
            }
        });

        jLabel6.setText("Actual");

        jLabel7.setText("Trabajo");

        btn_addClient.setText("Enviar");
        btn_addClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addClientActionPerformed(evt);
            }
        });

        jLabel8.setText("Parquear");

        btn_park.setText("Enviar");
        btn_park.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_parkActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Agregar clientes");

        btn_mode.setText("Enviar");
        btn_mode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Taxis");

        jLabel11.setText("Habitacion");

        tf_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_homeActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setText("Indiviualmente");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Varios");

        btn_addTaxi.setText("Agregar taxi");
        btn_addTaxi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addTaxiActionPerformed(evt);
            }
        });

        txtA_Map.setEditable(false);
        txtA_Map.setColumns(20);
        txtA_Map.setRows(5);
        jScrollPane1.setViewportView(txtA_Map);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Mapa");

        tf_map.setText("Map.txt");
        tf_map.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_mapActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Edificios");

        btn_load.setText("Cargar");
        btn_load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Porcentaje de trabajo");

        tf_dayDuration.setText("5000");
        tf_dayDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_dayDurationActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Duracion del dia");

        tf_Percentage.setText("30");

        btn_dayCycle.setText("Enviar");
        btn_dayCycle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dayCycleActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel18.setText("Modos");

        txtA_Cant.setEditable(false);
        txtA_Cant.setColumns(20);
        txtA_Cant.setRows(5);
        jScrollPane2.setViewportView(txtA_Cant);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Mapa");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Cantidad de habitantes");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Hora");

        l_Time.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l_Time.setText("00:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel9))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(tf_work, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel11)
                                            .addGap(18, 18, 18)
                                            .addComponent(tf_home, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(36, 36, 36)
                                            .addComponent(btn_addClient))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(tf_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel12)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(18, 18, 18)
                                            .addComponent(tf_cantClients, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btn_cantClients))
                                        .addComponent(jLabel13)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel19)
                                            .addGap(326, 326, 326))))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)
                                                .addComponent(btn_path)
                                                .addGap(65, 65, 65)
                                                .addComponent(jLabel3)
                                                .addGap(27, 27, 27)
                                                .addComponent(btn_route))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(74, 74, 74)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel10)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel4)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(rb_parade)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(rb_search)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(btn_mode))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel8)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(tf_park, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btn_park))
                                                    .addComponent(btn_addTaxi))))
                                        .addGap(78, 78, 78)))
                                .addGap(125, 125, 125))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(570, 570, 570)
                                .addComponent(jLabel20)
                                .addContainerGap(21, Short.MAX_VALUE))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(tf_animate, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_animate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(tf_dayDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(tf_Percentage, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_dayCycle)
                        .addGap(96, 96, 96)
                        .addComponent(jLabel14)
                        .addGap(23, 23, 23)
                        .addComponent(tf_map, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(tf_building, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_load))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(l_Time)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_dayDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(tf_Percentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_dayCycle)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_map, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(tf_building, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_load))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(btn_animate)
                            .addComponent(tf_animate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(l_Time))
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(rb_parade)
                                    .addComponent(rb_search)
                                    .addComponent(btn_mode))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel8)
                                    .addComponent(tf_park, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_park))
                                .addGap(18, 18, 18)
                                .addComponent(btn_addTaxi))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_path)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(btn_route)
                                .addComponent(jLabel18))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel13)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tf_cantClients, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cantClients))
                        .addGap(16, 16, 16)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(tf_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_work, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)
                            .addComponent(btn_addClient))))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rb_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_searchActionPerformed

    private void tf_animateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_animateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_animateActionPerformed

    private void btn_animateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_animateActionPerformed
       int numAnimate = -1;
        try{
            numAnimate = Integer.parseInt(tf_animate.getText());
        }catch(Exception e){
            System.out.println("Error: numero incorrecto");
        }        
               
        if(numAnimate >= 0){
            simulation.setDaemon(numAnimate);
            System.out.println(simulation.getDaemon());
        }else{
            //Valor invaludo
            System.out.println("Error: numero incorrecto");
        }
            
        
    }//GEN-LAST:event_btn_animateActionPerformed

    private void btn_parkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_parkActionPerformed

        if(tf_park.getText().length() != 0){
            char destiny = tf_park.getText().charAt(0);
            overlord.send("park-"+destiny);
        } 

    }//GEN-LAST:event_btn_parkActionPerformed

    private void btn_modeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modeActionPerformed
        if(rb_parade.isSelected()){
            overlord.send("parade");
        }else if (rb_search.isSelected()){
            overlord.send("search");
        }else{
            System.out.println("Error: ninguna opcion seleccionada");
        }
    }//GEN-LAST:event_btn_modeActionPerformed

    private void btn_pathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pathActionPerformed
        simulation.switchPath();
        if(simulation.isPathOn()){
            btn_path.setText("Encendido");
        }else{
            btn_path.setText("Apagado");
        }
    }//GEN-LAST:event_btn_pathActionPerformed

    private void btn_routeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_routeActionPerformed
        simulation.switchRoute();
        if(simulation.isRouteOn()){
            btn_route.setText("Encendido");
        }else{
            btn_route.setText("Apagado");
        }
    }//GEN-LAST:event_btn_routeActionPerformed

    private void btn_cantClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cantClientsActionPerformed
        int numClients = -1;
        try{
            numClients = Integer.parseInt(tf_cantClients.getText());
        }catch(Exception e){
            //Notify errot
            System.out.println("Error: cantidad de clientes incorrecta");
        }        
               
        if(numClients >= 0){
            simulation.addClient(numClients);
        }else{
            //Valor invaludo
            System.out.println("Error: cantidad de clientes incorrecta");
        }
    }//GEN-LAST:event_btn_cantClientsActionPerformed

    private void btn_addClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addClientActionPerformed
        if(tf_actual.getText().length() != 0 && tf_work.getText().length() != 0 && tf_home.getText().length() != 0){
            char actual = tf_actual.getText().charAt(0);
            char work = tf_work.getText().charAt(0);
            char home = tf_work.getText().charAt(0);
            
            simulation.addClient(actual, work, home);
        } 
    }//GEN-LAST:event_btn_addClientActionPerformed

    private void tf_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_homeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_homeActionPerformed

    private void btn_addTaxiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addTaxiActionPerformed
        simulation.addTaxi();
    }//GEN-LAST:event_btn_addTaxiActionPerformed

    private void tf_mapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_mapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_mapActionPerformed

    private void btn_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadActionPerformed
        if(tf_map.getText().length() != 0 && tf_building.getText().length() != 0){
            String mapFile = tf_map.getText();
            String buildingFile = tf_building.getText();

            simulation.setFiles("Map2.txt", "Buildings2.txt");
        } 
    }//GEN-LAST:event_btn_loadActionPerformed

    private void tf_dayDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_dayDurationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_dayDurationActionPerformed

    private void btn_dayCycleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dayCycleActionPerformed
        if(tf_dayDuration.getText().length() != 0 && tf_Percentage.getText().length() != 0){
            int numDuration = -1;
            int numPercentage= -1;
            try{
                numDuration = Integer.parseInt(tf_dayDuration.getText());
                numPercentage = Integer.parseInt(tf_Percentage.getText());
            }catch(Exception e){
                System.out.println("Error: numero incorrecto");
            }        
          
            if(numDuration >= 0 && numPercentage >= 0){
                simulation.setDayCycle(500, 30);
            }
            
        } 
    }//GEN-LAST:event_btn_dayCycleActionPerformed

    public static void upadateMap(String pMap){
            txtA_Map.setText(pMap);
    }
    
    public static void upadateBuildings(String pAmount){
            txtA_Cant.setText(pAmount);
    }
    
    public static void upadateTime(String pTime){
            l_Time.setText(pTime);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        simulation = new Simulation();
        overlord = simulation.getEE();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true); 
            }
        });

        new Thread(simulation).start();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addClient;
    private javax.swing.JButton btn_addTaxi;
    private javax.swing.JButton btn_animate;
    private javax.swing.JButton btn_cantClients;
    private javax.swing.JButton btn_dayCycle;
    private javax.swing.JButton btn_load;
    private javax.swing.JButton btn_mode;
    private javax.swing.JButton btn_park;
    private javax.swing.JButton btn_path;
    private javax.swing.JButton btn_route;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private static javax.swing.JLabel l_Time;
    private javax.swing.JRadioButton rb_parade;
    private javax.swing.JRadioButton rb_search;
    private javax.swing.ButtonGroup taxiModeGroup;
    private javax.swing.JTextField tf_Percentage;
    private javax.swing.JTextField tf_actual;
    private javax.swing.JTextField tf_animate;
    private javax.swing.JTextField tf_building;
    private javax.swing.JTextField tf_cantClients;
    private javax.swing.JTextField tf_dayDuration;
    private javax.swing.JTextField tf_home;
    private javax.swing.JTextField tf_map;
    private javax.swing.JTextField tf_park;
    private javax.swing.JTextField tf_work;
    private static javax.swing.JTextArea txtA_Cant;
    private static javax.swing.JTextArea txtA_Map;
    // End of variables declaration//GEN-END:variables
}
