import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmBanco extends JFrame {

    public String[] encabezadosCuentas = new String[] { "Tipo", "Número", "Titular", "Saldo",
            "Sobregiro o Límite" };
    public String[] encabezadosTransacciones = new String[] { "Cuenta", "Tipo", "Valor", "Saldo" };
    private String[] opcionesTransaccion = new String[] { "Depósito", "Retiro" };

    private JTable tblCuentas, tblTransacciones;
    private JPanel pnlEditarCuenta, pnlEditarTransaccion;

    private JTextField txtNumero, txtTitular, txtSaldoInicial, txtLimite, txtValor;
    private JComboBox cmbTipoCuenta, cmbTipoTransaccion, cmbCuenta;

    private JTabbedPane tp;

    private List<Cuenta> cuentas = new ArrayList();
    private List<Transaccion> transacciones = new ArrayList();

    public FrmBanco() {
        setSize(600, 400);
        setTitle("Cuentas Bancarias");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar tbBanco = new JToolBar();

        JButton btnAgregarCuenta = new JButton();
        btnAgregarCuenta.setIcon(new ImageIcon(getClass().getResource("/iconos/AgregarCuenta.png")));
        btnAgregarCuenta.setToolTipText("Agregar Cuenta");
        btnAgregarCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAgregarCuentaClick();
            }
        });
        tbBanco.add(btnAgregarCuenta);

        JButton btnQuitarCuenta = new JButton();
        btnQuitarCuenta.setIcon(new ImageIcon(getClass().getResource("/iconos/QuitarCuenta.png")));
        btnQuitarCuenta.setToolTipText("Quitar Cuenta");
        btnQuitarCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnQuitarCuentaClick();
            }
        });
        tbBanco.add(btnQuitarCuenta);

        JButton btnTransaccion = new JButton();
        btnTransaccion.setIcon(new ImageIcon(getClass().getResource("/iconos/Transaccion.png")));
        btnTransaccion.setToolTipText("Realizar Transacción");
        btnTransaccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnTransaccionClick();
            }
        });
        tbBanco.add(btnTransaccion);

        // Contenedor principal de CUENTAS con BoxLayout (vertical)
        JPanel pnlCuentas = new JPanel();
        pnlCuentas.setLayout(new BoxLayout(pnlCuentas, BoxLayout.Y_AXIS));

        // Panel 1 (oculto por defecto)
        pnlEditarCuenta = new JPanel();
        pnlEditarCuenta.setPreferredSize(new Dimension(pnlEditarCuenta.getWidth(), 100)); // Altura fija de 100px
        pnlEditarCuenta.setLayout(null);

        JLabel lblNumero = new JLabel("Número");
        lblNumero.setBounds(10, 10, 100, 25);
        pnlEditarCuenta.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(110, 10, 100, 25);
        pnlEditarCuenta.add(txtNumero);

        JLabel lblTitular = new JLabel("Titular");
        lblTitular.setBounds(10, 40, 100, 25);
        pnlEditarCuenta.add(lblTitular);

        txtTitular = new JTextField();
        txtTitular.setBounds(110, 40, 100, 25);
        pnlEditarCuenta.add(txtTitular);

        JLabel lblSaldoInicial = new JLabel("Saldo Inicial");
        lblSaldoInicial.setBounds(10, 70, 100, 25);
        pnlEditarCuenta.add(lblSaldoInicial);

        txtSaldoInicial = new JTextField();
        txtSaldoInicial.setBounds(110, 70, 100, 25);
        pnlEditarCuenta.add(txtSaldoInicial);

        cmbTipoCuenta = new JComboBox();
        cmbTipoCuenta.setBounds(220, 10, 100, 25);
        String[] opciones = new String[] { "Ahorros", "Corriente", "Crédito" };
        DefaultComboBoxModel mdlTipoCuenta = new DefaultComboBoxModel(opciones);
        cmbTipoCuenta.setModel(mdlTipoCuenta);
        pnlEditarCuenta.add(cmbTipoCuenta);
        cmbTipoCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txtLimite.setVisible(cmbTipoCuenta.getSelectedIndex() != 0);
            }
        });

        JLabel lblLimite = new JLabel("Sobregiro o Límite Crédito");
        lblLimite.setBounds(220, 40, 100, 25);
        pnlEditarCuenta.add(lblLimite);

        txtLimite = new JTextField();
        txtLimite.setBounds(320, 40, 100, 25);
        txtLimite.setVisible(false);
        pnlEditarCuenta.add(txtLimite);

        JButton btnGuardarCuenta = new JButton("Guardar");
        btnGuardarCuenta.setBounds(220, 70, 100, 25);
        btnGuardarCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGuardarCuentaClick();
            }
        });
        pnlEditarCuenta.add(btnGuardarCuenta);

        JButton btnCancelarCuenta = new JButton("Cancelar");
        btnCancelarCuenta.setBounds(320, 70, 100, 25);
        btnCancelarCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCancelarCuentaClick();
            }
        });
        pnlEditarCuenta.add(btnCancelarCuenta);

        pnlEditarCuenta.setVisible(false); // Se oculta al inicio

        // Panel 2 (siempre visible)
        tblCuentas = new JTable();
        JScrollPane spListaCuentas = new JScrollPane(tblCuentas);

        DefaultTableModel dtm = new DefaultTableModel(null, encabezadosCuentas);
        tblCuentas.setModel(dtm);

        // Agregar componentes
        pnlCuentas.add(pnlEditarCuenta);
        pnlCuentas.add(spListaCuentas);

        // JScrollPane para permitir desplazamiento si es necesario
        JScrollPane spCuentas = new JScrollPane(pnlCuentas);
        spCuentas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Contenedor principal de TRANSACCIONES con BoxLayout (vertical)
        JPanel pnlTransacciones = new JPanel();
        pnlTransacciones.setLayout(new BoxLayout(pnlTransacciones, BoxLayout.Y_AXIS));

        // Panel 1 (oculto por defecto)
        pnlEditarTransaccion = new JPanel();
        pnlEditarTransaccion.setPreferredSize(new Dimension(pnlEditarTransaccion.getWidth(), 100)); // Altura fija de
                                                                                                    // 100px
        pnlEditarTransaccion.setLayout(null);

        JLabel lblCuenta = new JLabel("Cuenta");
        lblCuenta.setBounds(10, 10, 100, 25);
        pnlEditarTransaccion.add(lblCuenta);

        cmbCuenta = new JComboBox();
        cmbCuenta.setBounds(110, 10, 100, 25);
        pnlEditarTransaccion.add(cmbCuenta);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(10, 40, 100, 25);
        pnlEditarTransaccion.add(lblTipo);

        cmbTipoTransaccion = new JComboBox();
        cmbTipoTransaccion.setBounds(110, 40, 100, 25);
        DefaultComboBoxModel mdlTipoTransaccion = new DefaultComboBoxModel(opcionesTransaccion);
        cmbTipoTransaccion.setModel(mdlTipoTransaccion);
        pnlEditarTransaccion.add(cmbTipoTransaccion);

        JLabel lblValor = new JLabel("Valor");
        lblValor.setBounds(10, 70, 100, 25);
        pnlEditarTransaccion.add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(110, 70, 100, 25);
        pnlEditarTransaccion.add(txtValor);

        JButton btnGuardarTransaccion = new JButton("Guardar");
        btnGuardarTransaccion.setBounds(220, 70, 100, 25);
        btnGuardarTransaccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGuardarTransaccionClick();
            }
        });
        pnlEditarTransaccion.add(btnGuardarTransaccion);

        JButton btnCancelarTransaccion = new JButton("Cancelar");
        btnCancelarTransaccion.setBounds(320, 70, 100, 25);
        btnCancelarTransaccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCancelarTransaccionClick();
            }
        });
        pnlEditarTransaccion.add(btnCancelarTransaccion);

        pnlEditarTransaccion.setVisible(false); // Se oculta al inicio

        // Panel 2 (siempre visible)
        tblTransacciones = new JTable();
        JScrollPane spListaTransacciones = new JScrollPane(tblTransacciones);

        dtm = new DefaultTableModel(null, encabezadosTransacciones);
        tblTransacciones.setModel(dtm);

        // Agregar componentes
        pnlTransacciones.add(pnlEditarTransaccion);
        pnlTransacciones.add(spListaTransacciones);

        // JScrollPane para permitir desplazamiento si es necesario
        JScrollPane spTransacciones = new JScrollPane(pnlTransacciones);
        spTransacciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        tp = new JTabbedPane();
        tp.addTab("Cuentas", spCuentas);
        tp.addTab("Transacciones", spTransacciones);

        getContentPane().add(tbBanco, BorderLayout.NORTH);
        getContentPane().add(tp, BorderLayout.CENTER);
    }

    private void btnAgregarCuentaClick() {
        pnlEditarCuenta.setVisible(true);
        tp.setSelectedIndex(0);

    }

    private void btnQuitarCuentaClick() {
        if (tblCuentas.getSelectedRow() >= 0) {
            cuentas.remove(tblCuentas.getSelectedRow());
            mostrarCuentas();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una cuenta");
        }
    }

    private void btnGuardarCuentaClick() {
        pnlEditarCuenta.setVisible(false);
        switch (cmbTipoCuenta.getSelectedIndex()) {
            case 0:
                cuentas.add(new Ahorros(txtNumero.getText(),
                        txtTitular.getText(),
                        Double.parseDouble(txtSaldoInicial.getText())));
                break;
            case 1:
                cuentas.add(new Corriente(txtNumero.getText(),
                        txtTitular.getText(),
                        Double.parseDouble(txtSaldoInicial.getText()),
                        Double.parseDouble(txtLimite.getText())));
                break;
            case 2:
                cuentas.add(new Credito(txtNumero.getText(),
                        txtTitular.getText(),
                        Double.parseDouble(txtSaldoInicial.getText()),
                        Double.parseDouble(txtLimite.getText())));
                break;
        }
        mostrarCuentas();
    }

    private void mostrarCuentas() {
        cmbCuenta.removeAllItems();
        String[][] strCuentas = new String[cuentas.size()][encabezadosCuentas.length];
        int fila = 0;
        for (Cuenta c : cuentas) {
            strCuentas[fila][0] = c instanceof Ahorros ? "Ahorros" : c instanceof Corriente ? "Corriente" : "credito";
            strCuentas[fila][1] = c.getNumero();
            strCuentas[fila][2] = c.getTitular();
            strCuentas[fila][3] = String.valueOf(c.getSaldo());
            strCuentas[fila][4] = c instanceof Ahorros ? "No aplica"
                    : c instanceof Corriente ? String.valueOf(((Corriente) c).getSobregiro())
                            : String.valueOf(((Credito) c).getLimiteCredito());
            fila++;
            cmbCuenta.addItem(c.getNumero() + " - " + c.getTitular());
        }
        DefaultTableModel dtm = new DefaultTableModel(strCuentas, encabezadosCuentas);
        tblCuentas.setModel(dtm);
    }

    private void btnCancelarCuentaClick() {
        pnlEditarCuenta.setVisible(false);

    }

    private void btnTransaccionClick() {
        pnlEditarTransaccion.setVisible(true);
        tp.setSelectedIndex(1);

    }

    private void btnGuardarTransaccionClick() {
        if (cmbTipoTransaccion.getSelectedIndex() >= 0 && cmbCuenta.getSelectedIndex() >= 0) {
            pnlEditarTransaccion.setVisible(false);
            boolean huboTransaccion = false;
            switch (cmbTipoTransaccion.getSelectedIndex()) {
                case 0:
                    cuentas.get(cmbCuenta.getSelectedIndex()).depositar(Double.parseDouble(txtValor.getText()));
                    huboTransaccion = true;
                    break;
                case 1:
                    huboTransaccion = cuentas.get(cmbCuenta.getSelectedIndex())
                            .retirar(Double.parseDouble(txtValor.getText()));
                    break;
            }
            if (huboTransaccion) {
                transacciones.add(new Transaccion(cuentas.get(cmbCuenta.getSelectedIndex()),
                        opcionesTransaccion[cmbTipoTransaccion.getSelectedIndex()],
                        Double.parseDouble(txtValor.getText()),
                        cuentas.get(cmbCuenta.getSelectedIndex()).getSaldo()));
                mostrarTransacciones();
            }
        }
    }

    private void mostrarTransacciones() {
        String[][] strTransacciones = new String[transacciones.size()][encabezadosTransacciones.length];
        int fila = 0;
        for (Transaccion t : transacciones) {
            strTransacciones[fila][0] = t.getCuenta().getNumero() + " - " + t.getCuenta().getTitular();
            strTransacciones[fila][1] = t.getTipo();
            strTransacciones[fila][2] = String.valueOf(t.getValor());
            strTransacciones[fila][3] = String.valueOf(t.getSaldo());
            fila++;
        }
        DefaultTableModel dtm = new DefaultTableModel(strTransacciones, encabezadosTransacciones);
        tblTransacciones.setModel(dtm);
    }

    private void btnCancelarTransaccionClick() {
        pnlEditarTransaccion.setVisible(false);

    }

}
