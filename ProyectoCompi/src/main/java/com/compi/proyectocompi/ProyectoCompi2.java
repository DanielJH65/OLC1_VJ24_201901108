/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.compi.proyectocompi;

import Abstract.Instruction;
import Analizador.lexico;
import Analizador.parser;
import Exceptions.Errores;
import Reportes.Simbolo;
import Symbol.SymbolsTable;
import Symbol.Tree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author daniel
 */
public class ProyectoCompi2 extends javax.swing.JFrame {

    /**
     * Creates new form Proyecto1_201901108
     */
    String activeFile = "";
    String nameFile = "";
    String salidaErrores = "";
    LinkedList<Errores> errores = new LinkedList<>();
    LinkedList<Simbolo> symbols = new LinkedList<>();

    public ProyectoCompi2() {
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsola = new javax.swing.JTextPane();
        jSeparator2 = new javax.swing.JSeparator();
        TabCodigo = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        btnAbrirArchivo = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        btnNuevo = new javax.swing.JMenuItem();
        btnGuardar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnEjecutar = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnRTokens = new javax.swing.JMenuItem();
        btnRSimbolos = new javax.swing.JMenuItem();
        btnRErrores = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnSalir = new javax.swing.JMenuItem();
        btnCerrarP = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtConsola.setEditable(false);
        txtConsola.setBorder(javax.swing.BorderFactory.createTitledBorder("Consola"));
        txtConsola.setMargin(new java.awt.Insets(0, 0, 0, 0));
        txtConsola.setName("txtConsola"); // NOI18N
        jScrollPane1.setViewportView(txtConsola);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(TabCodigo))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TabCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAbrirArchivo.setText("Archivo");

        jMenuItem2.setText("Abrir Archivo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        btnAbrirArchivo.add(jMenuItem2);

        btnNuevo.setText("Archivo Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        btnAbrirArchivo.add(btnNuevo);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnAbrirArchivo.add(btnGuardar);

        jMenuBar1.add(btnAbrirArchivo);

        jMenu2.setText("Ejecutar");

        btnEjecutar.setText("Ejecutar Código");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });
        jMenu2.add(btnEjecutar);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Reportes");

        btnRTokens.setText("Generar AST");
        btnRTokens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRTokensActionPerformed(evt);
            }
        });
        jMenu4.add(btnRTokens);

        btnRSimbolos.setText("Tabla de Simbolos");
        btnRSimbolos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRSimbolosActionPerformed(evt);
            }
        });
        jMenu4.add(btnRSimbolos);

        btnRErrores.setText("Errores");
        btnRErrores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRErroresActionPerformed(evt);
            }
        });
        jMenu4.add(btnRErrores);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Salir");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jMenu3.add(btnSalir);

        btnCerrarP.setText("Cerrar Pestaña");
        btnCerrarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarPActionPerformed(evt);
            }
        });
        jMenu3.add(btnCerrarP);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu3MouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        javax.swing.JScrollPane panel = new javax.swing.JScrollPane();
        javax.swing.JTextArea editor = new javax.swing.JTextArea();
        panel.setViewportView(editor);
        TabCodigo.add("Nuevo", panel);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        javax.swing.JScrollPane panel = new javax.swing.JScrollPane();
        javax.swing.JTextArea editor = new javax.swing.JTextArea();

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de entrada", "jc");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                activeFile = chooser.getSelectedFile().getAbsolutePath();
                nameFile = chooser.getSelectedFile().getName();
                File entrada = new File(activeFile);
                String texto = "";
                String temp = "";
                BufferedReader lectura = new BufferedReader(new FileReader(entrada));

                while ((temp = lectura.readLine()) != null) {
                    texto += temp + "\n";
                }
                editor.setText(texto);
                editor.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Archivo Cargado correctamente", "Satisfactorio", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex + "No se ha encontrado el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ProyectoCompi2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

        panel.setViewportView(editor);
        TabCodigo.add(nameFile, panel);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        JFileChooser guardarComo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de entrada", "jc");
        guardarComo.setFileFilter(filtro);
        guardarComo.showSaveDialog(null);
        guardarComo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        JScrollPane panel = (JScrollPane) TabCodigo.getSelectedComponent();
        JViewport txtArea = (JViewport) panel.getComponents()[0];
        JTextArea area = (JTextArea) txtArea.getComponents()[0];
        try {
            File archivo = guardarComo.getSelectedFile();
            activeFile = guardarComo.getSelectedFile().getAbsolutePath();
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(area.getText());
            bw.close();
            JOptionPane.showMessageDialog(this, "Archivo creado", "Satisfactorio", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error Ingrese el nombre del Archivo", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al crear el archivo SS", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        // TODO add your handling code here:
        errores.clear();
        symbols.clear();
        JScrollPane panel = (JScrollPane) TabCodigo.getSelectedComponent();
        JViewport txtArea = (JViewport) panel.getComponents()[0];
        JTextArea area = (JTextArea) txtArea.getComponents()[0];
        try {
            lexico lex = new lexico(new BufferedReader(new StringReader(area.getText())));
            parser par = new parser(lex);
            var result = par.parse();
            var ast = new Tree((LinkedList<Instruction>) result.value);
            var table = new SymbolsTable();
            table.setName("Global");
            ast.setConsole("");
            for (var ins : ast.getInstructions()) {
                if (ins == null) {
                    continue;
                }
                var res = ins.interpretar(ast, table);
                if (res instanceof Errores) {
                    ast.getErrores().add((Errores) res);
                }
            }
            txtConsola.setText(ast.getConsole());
            table.getSymbols().addAll(table.getSimbolos());
            symbols.addAll(table.getSymbols());
            errores.addAll(lex.errores);
            errores.addAll(par.errores);
            errores.addAll(ast.getErrores());
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void btnRTokensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRTokensActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRTokensActionPerformed

    private void btnCerrarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarPActionPerformed
        // TODO add your handling code here:
        TabCodigo.remove(TabCodigo.getSelectedComponent());
    }//GEN-LAST:event_btnCerrarPActionPerformed

    private void btnRSimbolosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRSimbolosActionPerformed
        // TODO add your handling code here:
        reporteSimbolos(symbols);
    }//GEN-LAST:event_btnRSimbolosActionPerformed

    private void btnRErroresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRErroresActionPerformed
        // TODO add your handling code here:
        reporteErrores(errores);
    }//GEN-LAST:event_btnRErroresActionPerformed

    private void reporteErrores(LinkedList<Errores> errores) {
        String salida;
        salida = "<!DOCTYPE html>\n" + "<html lang=\"es\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n" + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + "    <link href=\"https://bootswatch.com/4/superhero/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" + "    <title>Reporte de Errores</title>\n" + "</head>\n" + "<body style=\"background: linear-gradient(to right, #141e30, #243b55);\">\n" + "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-secondary\">\n" + "        <a class=\"navbar-brand\" href=\"#\">Proyecto 1 - Organizaci\u00f3n de Lenguajes y Compiladores 1</a>\n" + "        <a class=\"navbar-brand\" href=\"#\">Walter Daniel Jimenez Hernandez 201901108</a>\n" + "    </nav>\n";
        salida += "<div class=\"jumbotron my-4 mx-4\">\n";
        salida += "<br><center><h3>Listado de Errores</h3></center><br>\n";
        salida += "<table class=\"table table-hover\">\n";
        salida += "<thead>\n";
        salida += "<tr>\n";
        salida += "<th scope=\"col\">Tipo</th>\n";
        salida += "<th scope=\"col\">Descripción</th>\n";
        salida += "<th scope=\"col\">Linea</th>\n";
        salida += "<th scope=\"col\">Columna</th>\n";
        salida += "</tr>\n";
        salida += "</thead>\n";
        salida += "<tbody>\n";

        for (Errores er : errores) {
            salida += "<tr class=\"table-danger\">\n";
            salida += "<td>" + er.getType() + "</td>\n";
            salida += "<td>" + er.getDescription() + "</td>\n";
            salida += "<td>" + er.getLine() + "</td>\n";
            salida += "<td>" + er.getColumn() + "</td>\n";
        }

        salida += "</tbody></table><div>\n";

        try {
            FileWriter fw = new FileWriter("Reporte de Errores.html");
            PrintWriter pw = new PrintWriter(fw);

            pw.println(salida);

            fw.close();
            JOptionPane.showMessageDialog(null, "Reporte de errores Creado", "Satisfactorio", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void reporteSimbolos(LinkedList<Simbolo> symbols) {
        String salida;
        salida = "<!DOCTYPE html>\n" + "<html lang=\"es\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n" +"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" 
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" 
                + "    <link href=\"https://bootswatch.com/4/superhero/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" 
                + "    <title>Reporte de Simbolos</title>\n" + "</head>\n" + "<body style=\"background: linear-gradient(to right, #141e30, #243b55);\">\n" 
                + "    <nav class=\"navbar navbar-expand-lg navbar-dark bg-secondary\">\n" 
                + "        <a class=\"navbar-brand\" href=\"#\">Proyecto 1 - Organizaci\u00f3n de Lenguajes y Compiladores 1</a>\n" 
                + "        <a class=\"navbar-brand\" href=\"#\">Walter Daniel Jimenez Hernandez 201901108</a>\n" + "    </nav>\n";
        salida += "<div class=\"jumbotron my-4 mx-4\">\n";
        salida += "<br><center><h3>Listado de Simbolos</h3></center><br>\n";
        salida += "<table class=\"table table-hover\">\n";
        salida += "<thead>\n";
        salida += "<tr>\n";
        salida += "<th scope=\"col\">Id</th>\n";
        salida += "<th scope=\"col\">Tipo</th>\n";
        salida += "<th scope=\"col\">Mutabilidad</th>\n";
        salida += "<th scope=\"col\">Entorno</th>\n";
        salida += "<th scope=\"col\">Valor</th>\n";
        salida += "<th scope=\"col\">Linea</th>\n";
        salida += "<th scope=\"col\">Columna</th>\n";
        salida += "</tr>\n";
        salida += "</thead>\n";
        salida += "<tbody>\n";

        for (Simbolo sym : symbols) {
            salida += "<tr class=\"table-primary\">\n";
            salida += "<td>" + sym.getId() + "</td>\n";
            salida += "<td>" + sym.getType() + "</td>\n";
            salida += "<td>" + sym.getMutability() + "</td>\n";
            salida += "<td>" + sym.getScope() + "</td>\n";
            salida += "<td>" + sym.getValue() + "</td>\n";
            salida += "<td>" + sym.getLine() + "</td>\n";
            salida += "<td>" + sym.getColumn() + "</td>\n";
        }

        salida += "</tbody></table><div>\n";

        try {
            FileWriter fw = new FileWriter("Reporte de Simbolos.html");
            PrintWriter pw = new PrintWriter(fw);

            pw.println(salida);

            fw.close();
            JOptionPane.showMessageDialog(null, "Reporte de simbolos Creado", "Satisfactorio", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ProyectoCompi2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ProyectoCompi2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ProyectoCompi2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ProyectoCompi2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ProyectoCompi2().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabCodigo;
    private javax.swing.JMenu btnAbrirArchivo;
    private javax.swing.JMenuItem btnCerrarP;
    private javax.swing.JMenuItem btnEjecutar;
    private javax.swing.JMenuItem btnGuardar;
    private javax.swing.JMenuItem btnNuevo;
    private javax.swing.JMenuItem btnRErrores;
    private javax.swing.JMenuItem btnRSimbolos;
    private javax.swing.JMenuItem btnRTokens;
    private javax.swing.JMenuItem btnSalir;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextPane txtConsola;
    // End of variables declaration//GEN-END:variables
}
