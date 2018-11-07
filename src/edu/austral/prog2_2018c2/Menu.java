package edu.austral.prog2_2018c2;

import javax.swing.*;
import java.awt.event.*;

public class Menu extends JFrame implements ItemListener{

    JComboBox menu;

    Menu(){
        setSize(300,250);
        setLayout(null);
        menu = new JComboBox();
        menu.setBounds(90,40,125,20);
        menu.addItem("Jugar");
        menu.addItem("Ranking");
        add(menu);
        menu.addItemListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == menu){
            setTitle((String)menu.getSelectedItem());
        }
    }

    public static void main (String [] args){
        new Menu();
    }
}
