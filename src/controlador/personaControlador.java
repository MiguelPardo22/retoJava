package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.persona;
import modelo.personaDao;
import vista.vista;

public class personaControlador implements ActionListener {

    personaDao pdao = new personaDao();
    persona p = new persona();
    vista view = new vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public personaControlador(vista v) {

        this.view = v;
        this.view.listar.addActionListener(this);
        this.view.crear.addActionListener(this);
        this.view.editar.addActionListener(this);
        this.view.ok.addActionListener(this);
        this.view.borrar.addActionListener(this);
        listar(view.TablaDatos);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.listar) {
            listar(view.TablaDatos);
        }

        if (e.getSource() == view.crear) {
            crear();
            limpiarTabla();
            listar(view.TablaDatos);
        }

        if (e.getSource() == view.editar) {
            int fila = view.TablaDatos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(view, "Por favor Seleccione una fila");
            } else {
                int num_doc = Integer.parseInt((String) view.TablaDatos.getValueAt(fila, 0).toString());
                String nom = (String) view.TablaDatos.getValueAt(fila, 1);
                String ape = (String) view.TablaDatos.getValueAt(fila, 2);
                String mail = (String) view.TablaDatos.getValueAt(fila, 3);
                view.num_doc.setText("" + num_doc);
                view.nombre.setText(nom);
                view.apellidos.setText(ape);
                view.mail.setText(mail);
            }
        }

        if (e.getSource() == view.ok) {
            actualizar();
            limpiarTabla();
            listar(view.TablaDatos);
        }

        if (e.getSource() == view.borrar) {
            delete();
             limpiarTabla();
            listar(view.TablaDatos);
        }

    }

    public void listar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        List<persona> lista = pdao.listar();

        Object[] object = new Object[4];

        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getNum_doc();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getApellidos();
            object[3] = lista.get(i).getMail();
            modelo.addRow(object);
        }

        view.TablaDatos.setModel(modelo);

    }

    public void crear() {

        int num_doc = Integer.parseInt(view.num_doc.getText());
        String nombre = view.nombre.getText();
        String apellidos = view.apellidos.getText();
        String mail = view.mail.getText();

        p.setNum_doc(num_doc);
        p.setNombre(nombre);
        p.setApellidos(apellidos);
        p.setMail(mail);

        int r = pdao.crear(p);

        if (r == 1) {

            JOptionPane.showMessageDialog(view, "Persona Creada Correctamente");

        } else {

            JOptionPane.showMessageDialog(view, "No se pudo crear el registro");

        }
    }

    public void actualizar() {

        int num_doc = Integer.parseInt(view.num_doc.getText());
        String nombre = view.nombre.getText();
        String apellidos = view.apellidos.getText();
        String mail = view.mail.getText();

        p.setNum_doc(num_doc);
        p.setNombre(nombre);
        p.setApellidos(apellidos);
        p.setMail(mail);

        int r = pdao.actualizar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(view, "Registro actualizado correctamente");
        } else {
            JOptionPane.showMessageDialog(view, "No se pudo actualizar el registro seleccionado");
        }
    }

    void limpiarTabla() {

        for (int i = 0; i < view.TablaDatos.getRowCount(); i++) {
            modelo.removeRow(i);
            i = -1;
        }

    }

    public void delete(){
         int fila = view.TablaDatos.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(view, "Seleccione una fila para borrar el registro");
            } else {
                int num_doc = Integer.parseInt((String) view.TablaDatos.getValueAt(fila, 0).toString());
                pdao.eliminar(num_doc);
                JOptionPane.showMessageDialog(view, "El registro fue eliminado");
            
            }
    }

}
