package com.alura.latam.hotelsystem.ui.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.latam.hotelsystem.controller.GuestController;
import com.alura.latam.hotelsystem.controller.ReservationController;
import com.alura.latam.hotelsystem.dto.GuestReportRecord;
import com.alura.latam.hotelsystem.dto.GuestUpdateData;
import com.alura.latam.hotelsystem.dto.ReservationUpdateData;
import com.alura.latam.hotelsystem.model.Reservation;
import com.alura.latam.hotelsystem.util.InitControllersUtil;
import com.alura.latam.hotelsystem.util.LocalDateUtil;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTabbedPane panelReporte;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReservas;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	private String imageClassPath = 
			"/com/alura/latam/hotelsystem/ui/images/";
	
	private ReservationController reservationController;
	private GuestController guestController;
	
	private List<Reservation> reservations;
	private List<GuestReportRecord> guests;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda(null, null);
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
	public Busqueda(ReservationController reservationController, 
			GuestController guestController) {
		
		this.reservationController = InitControllersUtil
				.getReservationController(reservationController);
		this.guestController = InitControllersUtil
				.getGuestController(guestController);

		reservations = this.reservationController.getReservationsReport();
		guests = this.guestController.getGuestsReport();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource(imageClassPath + "lupa2.png")));
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

		panelReporte = new JTabbedPane(JTabbedPane.TOP);
		panelReporte.setBackground(new Color(12, 138, 199));
		panelReporte.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelReporte.setBounds(20, 169, 865, 328);
		contentPane.add(panelReporte);
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReservas = (DefaultTableModel) tbReservas.getModel();
		modeloReservas.addColumn("Numero de Reserva");
		modeloReservas.addColumn("Fecha Check In");
		modeloReservas.addColumn("Fecha Check Out");
		modeloReservas.addColumn("Valor");
		modeloReservas.addColumn("Forma de Pago");
		JScrollPane scroll_tableReservas = new JScrollPane(tbReservas);
		panelReporte.addTab("Reservas", new ImageIcon(Busqueda.class.getResource(imageClassPath + "reservado.png")), scroll_tableReservas,
				null);
		scroll_tableReservas.setVisible(true);

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
		panelReporte.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource(imageClassPath + "pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource(imageClassPath + "Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
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
				MenuUsuario usuario = new MenuUsuario(
						reservationController, guestController);
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
				MenuUsuario usuario = new MenuUsuario(
						Busqueda.this.reservationController, 
						Busqueda.this.guestController);
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
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

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
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
		
		cargarReportesDeHotel();
		
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ejecutarSistemaBusqueda();
			}
		});
		
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizarRegistros();
			}
		});
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarRegistro();
			}
		});
	}

	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
	
	
	
	private void ejecutarSistemaBusqueda() {
		stopTableCellEditing();
		
		var busqueda = txtBuscar.getText().trim();
		var numeroTab = panelReporte.getSelectedIndex();
		
		switch(numeroTab) {
		case 0:
			buscarReservacion(busqueda);
			break;
		case 1:
			buscarHuesped(busqueda);
			break;
		}
	}
	
	private void buscarReservacion(String number) {
		if (number.isEmpty()) {
			reservations = reservationController
					.getReservationsReport();
			cargarReporteReservas(reservations);
			return;
		}
		var reservation = reservationController
				.searchReservationNumber(number);
		if (reservation.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encontró "
					+ "reservación con el numero indicado");
			return;
		}
		reservations = reservation;
		cargarReporteReservas(reservation);
	}
	
	private void buscarHuesped(String surname) {
		if (surname.isEmpty()) {
			guests = guestController.getGuestsReport();
			cargarReporteHuespedes(guests);
			return;
		}
		var guest = guestController.searchGuestSurname(surname);
		if (guest.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se encontró "
					+ "ningun huesped con el apellido indicado");
			return;
		}
		guests = guest;
		cargarReporteHuespedes(guest);
	}
	
	private void actualizarRegistros() {
		var numeroTab = panelReporte.getSelectedIndex();
		
		switch(numeroTab) {
		case 0:
			actualizarReservaciones();
			break;
		case 1:
			actualizarHuespedes();
			break;
		}
	}
	
	private void actualizarReservaciones() {
		var rowIndex = tbReservas.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "Por favor "
					+ "seleccione una reserva para modificar");
			return;
		}
		
		stopTableCellEditing();
		
		var reservation = reservations.get(rowIndex);
		if (reservation.getReserveNumber() != 
				modeloReservas.getValueAt(rowIndex, 0)) {
			JOptionPane.showMessageDialog(null, "El número de "
					+ "reservación es único y no se permite modificarlo");
			return;
		}
		
		var reservationUpdateData = getReservationDataFromTable(rowIndex);
		if (reservationUpdateData == null) {
			JOptionPane.showMessageDialog(null, "Parametros llenados "
					+ "incorrectamente");
			return;
		}
		
		var reservationDetails = reservationController
				.updateEntity(reservation, reservationUpdateData);
		if (reservationDetails.id() != null) {
			JOptionPane.showMessageDialog(null, "Datos guardados "
					+ "satisfactoriamente");
		} else {
			JOptionPane.showMessageDialog(null, 
					reservationDetails.validationError());
		}
		
	}

	private void actualizarHuespedes() {
		var rowIndex = tbHuespedes.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "Por favor "
					+ "seleccione un huésped para modificar");
			return;
		}
		
		stopTableCellEditing();
		
		var guestReportRecord = guests.get(rowIndex);
		if (guestReportRecord.id() != 
				modeloHuesped.getValueAt(rowIndex, 0)) {
			JOptionPane.showMessageDialog(null, "El numéro de huésped"
					+ " es único y no se permite modificarlo");
			return;
		}
		
		if (guestReportRecord.reservationNumber() != 
				modeloHuesped.getValueAt(rowIndex, 6)) {
			JOptionPane.showMessageDialog(null, "El numéro de reservación"
					+ " es único y no se permite modificarlo");
			return;
		}
		
		var guestUpdateData = getGuestDataFromTable(rowIndex);
		if (guestUpdateData == null) {
			JOptionPane.showMessageDialog(null, "Parametros llenados "
					+ "incorrectamente");
			return;
		}
		
		var guest = guestController
				.findEntityById(guestReportRecord.id());
		var guestDetails = guestController
				.updateEntity(guest, guestUpdateData);
		if (guestDetails.id() != null) {
			JOptionPane.showMessageDialog(null, "Datos guardados "
					+ "satisfactoriamente");
		} else {
			JOptionPane.showMessageDialog(null, 
					guestDetails.validationError());
		}
	}
	
	private void stopTableCellEditing() {
		if(tbReservas.isEditing()) {
			tbReservas.getCellEditor().stopCellEditing();
		}
		if (tbHuespedes.isEditing()) {
			tbHuespedes.getCellEditor().stopCellEditing();
		}
	}
	
	private ReservationUpdateData getReservationDataFromTable(
			Integer rowIndex) {
		try {
			var checkIn = LocalDateUtil.convert(
					modeloReservas.getValueAt(rowIndex, 1)
					.toString().trim());
			var checkOut = LocalDateUtil.convert(
					modeloReservas.getValueAt(rowIndex, 2)
					.toString().trim());
			var amount = new BigDecimal(
					modeloReservas.getValueAt(rowIndex, 3)
					.toString().trim())
					.setScale(2, RoundingMode.HALF_UP);
			var paymentType = modeloReservas
					.getValueAt(rowIndex, 4)
					.toString().trim();
			return new ReservationUpdateData(
					checkIn, checkOut, 
					amount, paymentType);
		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		}	
	}
	
	private GuestUpdateData getGuestDataFromTable(Integer rowIndex) {
		try {
			var name = modeloHuesped.getValueAt(rowIndex, 1)
					.toString().trim();
			var surname = modeloHuesped.getValueAt(rowIndex, 2)
					.toString().trim();
			var dateOfBirth = LocalDateUtil.convert(
					modeloHuesped.getValueAt(rowIndex, 3)
					.toString().trim());
			var nationality = modeloHuesped.getValueAt(rowIndex, 4)
					.toString().trim();
			var phoneNumber = modeloHuesped.getValueAt(rowIndex, 5)
					.toString().trim();
			return new GuestUpdateData(name, surname, 
					dateOfBirth, nationality, phoneNumber);
		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		}
	}
	
	private void eliminarRegistro() {
		var numeroTab = panelReporte.getSelectedIndex();
		
		switch(numeroTab) {
		case 0:
			eliminarReservacion();
			break;
		case 1:
			eliminarHuesped();
			break;
		}
	}
	
	private void eliminarReservacion() {
		var rowIndex = tbReservas.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "Por favor "
					+ "seleccione la reservación que desea eliminar");
			return;
		}
		
		int reply = JOptionPane.showConfirmDialog(null, 
				"¿Está seguro que desea eliminar esta reservación?", 
				"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		
		if (reply == JOptionPane.YES_OPTION) {
			var reservation = reservations.get(rowIndex);
			reservationController.deleteEntity(reservation);
			reservations = reservationController.getReservationsReport();
			cargarReporteReservas(reservations);
		}
	}
	
	private void eliminarHuesped() {
		var rowIndex = tbHuespedes.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(null, "Por favor "
					+ "seleccione el huésped que desea eliminar");
			return;
		}
		
		int reply = JOptionPane.showConfirmDialog(null, 
				"¿Está seguro que desea eliminar el huésped seleccionado?", 
				"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		
		if (reply == JOptionPane.YES_OPTION) {
			var guestReportRecord = guests.get(rowIndex);
			var guest = guestController
					.findEntityById(guestReportRecord.id());
			guestController.deleteEntity(guest);
			guests = guestController.getGuestsReport();
			cargarReporteHuespedes(guests);
		}
	}
	
	private void cargarReportesDeHotel() {
		cargarReporteReservas(reservations);
		cargarReporteHuespedes(guests);
	}
	
	private void cargarReporteReservas(List<Reservation> reservations) {
		modeloReservas.setRowCount(0);
		reservations.forEach(reservation -> modeloReservas.addRow(
						new Object[] {
								reservation.getReserveNumber(),
								reservation.getCheckInDate(),
								reservation.getCheckOutDate(),
								reservation.getAmount(),
								reservation.getPaymentType()
						}));
	}
	
	private void cargarReporteHuespedes(List<GuestReportRecord> guests) {
		modeloHuesped.setRowCount(0);
		guests.forEach(guest -> modeloHuesped.addRow(
				new Object[] {
						guest.id(),
						guest.name(),
						guest.surname(),
						guest.dateOfBirth(),
						guest.nationality(),
						guest.phoneNumber(),
						guest.reservationNumber()
		}));
	}
	
}