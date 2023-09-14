package views;

import controller.HuespedesController;
import controller.ReservasController;
import modelo.Huespedes;
import modelo.Reservas;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private HuespedesController huespedesController = new HuespedesController();
	private ReservasController reservasController = new ReservasController();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);



		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		cargarTablaReservas();


		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		cargarTablaHuspedes();

		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
				System.out.println("Aqui voy");
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);

				System.out.println("Aqui No voy");
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Buscar(panel.getSelectedIndex());


				System.out.println("Boton Buscar Funcionando");

			}
		});
		lblBuscar.setBounds(0, 0, 122, 35);

		btnbuscar.add(lblBuscar);

		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		//Boton Editar Registros
		JLabel lblEditar = new JLabel("EDITAR");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editarRegistros(panel.getSelectedIndex());
				limpiarTabla();
				cartaTablas();

			}
		});

		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);


		//Boton Elomiar Registros
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar(panel.getSelectedIndex());
				limpiarTabla();
				cartaTablas();


			}
		});


		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

//Metodo para ver la tabla de Reservas
	private void cargarTablaReservas(){

		var reservacion = new ReservasController().lista(); //Este es un arreglo de objetos traido del Dao
		for(Reservas res: reservacion ){
			Vector Reserva = new Vector<>();
			Reserva.add(res.getId());
			Reserva.add(formatDate(res.getFechaEntrada()));
			Reserva.add(formatDate(res.getFechaSalida()));
			Reserva.add(res.getValor());
			Reserva.add(res.getFormaPago());
			this.modelo.addRow(Reserva);
			System.out.println("Revision de procesos Reserva");
		}

	}

	private void cargarTablaHuspedes(){
		var huespedes = new HuespedesController().listar();

		for(Huespedes hues : huespedes){
			Vector ReserH = new Vector<>();
			ReserH.add(hues.getId());
			ReserH.add(hues.getNombre());
			ReserH.add(hues.getApellido());
			ReserH.add(hues.getFechaNacimiento());
			ReserH.add(hues.getNacionalidad());
			ReserH.add(hues.getTelefono());
			ReserH.add(hues.getIdReserva());
			modeloHuesped.addRow(ReserH);
			System.out.println("Revision de procesos Huespedes");

		}
	}

	public void eliminar(int parametro){

		if(seleccionFila()){//Comprobacion previa para verificar que el usuario alla seleccionado algo
			JOptionPane.showMessageDialog(null, "Elija un Item, Por Favor","Hotel Alura Informacion",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try{

		if(tbReservas.getSelectedRow() !=-1 || tbHuespedes.getSelectedRow() !=-1) {
			if(parametro==0){
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedRowCount()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());

						var filasModificas = reservasController.eliminar(id);
						modelo.removeRow(tbReservas.getSelectedRow());

						JOptionPane.showMessageDialog(this, String.format("%d item eliminado con éxito!", filasModificas));
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));

			}else{
				Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedRowCount()))
						.ifPresentOrElse(fila -> {
							Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

							var filasModificas = huespedesController.EliminarHuesped(id);
							modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
							JOptionPane.showMessageDialog(this, String.format("%d Dato eliminado con éxito!", filasModificas));
						}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
			}
		}

		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error no se puedo realizar la eliminacion ","Hotel Alura Informacion",JOptionPane.ERROR_MESSAGE);
		}

	}

	public void editarRegistros(int parametro){

		if(seleccionFila()){//Comprobacion previa para verificar que el usuario alla seleccionado algo
			JOptionPane.showMessageDialog(null, "Elija un Item, Por Favor","Hotel Alura Informacion",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try{
		if(tbReservas.getSelectedRow() !=-1 || tbHuespedes.getSelectedRow() !=-1){
		if(parametro==0){
			System.out.println("Estamos en la parte de Reservas ");
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumnCount()))
					.ifPresentOrElse(fila->{
						Integer id= Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(),0).toString());
						String fechaEntrada = (String) modelo.getValueAt(tbReservas.getSelectedRow(),1);
						String fechaSalida = (String) modelo.getValueAt(tbReservas.getSelectedRow(),2);
						Double valor = Double.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(),3).toString());
						String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(),4);

						var filasModificadas=0;
						try {
							SimpleDateFormat FechaCambio = new SimpleDateFormat("yyyy-MM-dd");
							Date FechaEntrada = FechaCambio.parse(fechaEntrada);
							Date FechaSalida = FechaCambio.parse(fechaSalida);

							filasModificadas = reservasController.ModificarReservas(FechaEntrada,FechaSalida,valor,formaPago,id);
						}catch (ParseException e){
							e.printStackTrace();
						}



						JOptionPane.showMessageDialog(this,String.format("%d Dato de la Reservacion Modificado!",filasModificadas));
					},()->JOptionPane.showMessageDialog(this,"Por favor, elije un item"));
		}else{
			System.out.println("Estamos en la parte de Huespedes ");

			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),tbHuespedes.getSelectedRowCount()))
					.ifPresentOrElse(fila->{
						Integer Id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),0).toString());
						String Nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),1);
						String Apelllido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),2);
						String FechaNacimiento = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),3).toString();
						String Nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),4);
						Integer Telefono = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),5).toString());
						Integer IdReservas = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),6).toString());

						var filasModificadas=0;
						try {
							SimpleDateFormat FechaCambio = new SimpleDateFormat("yyyy-MM-dd");
							Date FechaCumpleano = FechaCambio.parse(FechaNacimiento);
							filasModificadas= huespedesController.ModificarHuespedes(Nombre, Apelllido, FechaCumpleano, Nacionalidad, Telefono, IdReservas, Id);
						}catch (ParseException e ){
							e.printStackTrace();

						}

						JOptionPane.showMessageDialog(this,String.format("%d Dato del Huesped Modificado!",filasModificadas));
					},()->JOptionPane.showMessageDialog(this,"Por favor, elije un item"));
		}

		}

		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error no se puedo realizar el cambio","Hotel Alura Informacion",JOptionPane.ERROR_MESSAGE);
		}

	}

	//Metodo para buscar
	public void Buscar(int parametro){
		try{
			if(txtBuscar.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Buscador vacio Ingrese un valor","Hotel Alura Informacion",JOptionPane.INFORMATION_MESSAGE);
				limpiarTabla();
				cartaTablas();
				return;
			}

		}catch (Exception e){
			throw new RuntimeException(e);
		}

			if (parametro == 0) {
				try{
					modelo.getDataVector().clear();
					var BReservas = reservasController.BuscarReserva(Integer.valueOf(txtBuscar.getText()));
					for(Reservas res: BReservas ){
						Vector Reserva = new Vector<>();
						Reserva.add(res.getId());
						Reserva.add(formatDate(res.getFechaEntrada()));
						Reserva.add(formatDate(res.getFechaSalida()));
						Reserva.add(res.getValor());
						Reserva.add(res.getFormaPago());
						this.modelo.addRow(Reserva);
					}

			}catch (Exception e){
				System.out.println("Funciona el controlador");
				throw new RuntimeException(e);

			}


			}else if(parametro==1){
				try{
					modeloHuesped.getDataVector().clear();
					var Bhuespedes = huespedesController.BuscarHuesped(txtBuscar.getText());;
					for(Huespedes hues: Bhuespedes){
						Vector BReserH = new Vector<>();
						BReserH.add(hues.getId());
						BReserH.add(hues.getNombre());
						BReserH.add(hues.getApellido());
						BReserH.add(formatDate(hues.getFechaNacimiento()));
						BReserH.add(hues.getNacionalidad());
						BReserH.add(hues.getTelefono());
						BReserH.add(hues.getIdReserva());
						this.modeloHuesped.addRow(BReserH);

						System.out.println("Revision de procesos Huespedes hoy martes");

					}
				}catch (Exception e){
					throw new RuntimeException(e);
				}

			}


	}


	private void cartaTablas(){
		cargarTablaReservas();
		cargarTablaHuspedes();
	}

	//Metodos Aparte
	private boolean seleccionFila(){
		if(tbReservas.getSelectedRowCount()==0 && tbReservas.getSelectedColumnCount() ==0 && tbHuespedes.getSelectedRowCount()==0 && tbHuespedes.getSelectedColumnCount()==0){
			return true;
		}else {
			return false;
		}
	}

	//Este metodo sirve para borrar todos los datos de la tabla y dejar vasio la tabla pero no afecta los datos de la base es como un actualizar registros
	private void limpiarTabla() {
		modelo.getDataVector().clear();
		modeloHuesped.getDataVector().clear();
	}
	private String formatDate(Date date) {
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}


	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
