package com.example.bookmachine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.bookmachine.BorrowingRepository.*;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class ProfileController {

    @FXML
    private TableColumn<BorrowingDetails, Date> borrowBorrowDateCol;

    @FXML
    private TableColumn<BorrowingDetails, Button> borrowReturnCol;

    @FXML
    private TableColumn<BorrowingDetails, Date> borrowReturnDateCol;

    @FXML
    private TableColumn<BorrowingDetails, String> borrowTitleCol;

    @FXML
    private TableColumn<BorrowingDetails, Date> reserveReserveDateCol;

    @FXML
    private TableColumn<BorrowingDetails, String> reserveTitleCol;

    @FXML
    private TableColumn<ReservationDetails, Button> reserveBorrowCol;

    @FXML
    private TableView<BorrowingDetails> borrowingDetailsTableView;
    @FXML
    private TableView<ReservationDetails> reservationDetailsTableView;

    private ObservableList<BorrowingDetails> obsBorrowings = FXCollections.observableArrayList();
    private ObservableList<ReservationDetails> obsReservations = FXCollections.observableArrayList();

    public void initialize() {
        setCellValueFactoryForBorrowingsTable();
        setCellValueFactoryForReservationsTable();

        List<Borrowing> usersBorrowings = BorrowingRepository.getUsersBorrowings();
        List<BorrowingDetails> borrowingsDetails = mapToBorrowingDetails(usersBorrowings);

        populateTableData(obsBorrowings, borrowingsDetails, borrowingDetailsTableView,
                borrowReturnCol, "Return");

        List<Reservation> usersReservations = BorrowingRepository.getUsersReservation();
        List<ReservationDetails> reservationsDetails = mapToReservationDetails(usersReservations);

        obsReservations.setAll(reservationsDetails);
        reservationDetailsTableView.setItems(obsReservations);
        addButtonToReservationTable(reserveBorrowCol, "Borrow");
    }

    private void setCellValueFactoryForBorrowingsTable() {
        borrowTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        borrowBorrowDateCol.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        borrowReturnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }

    private void setCellValueFactoryForReservationsTable() {
        reserveReserveDateCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        reserveTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
    }

    private void addButtonToBorrowingTable(TableColumn<BorrowingDetails, Button> btnCol, String buttonName) {
        Callback<TableColumn<BorrowingDetails, Button>, TableCell<BorrowingDetails, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<BorrowingDetails, Button> call(final TableColumn<BorrowingDetails, Button> param) {
                final TableCell<BorrowingDetails, Button> cell = new TableCell<BorrowingDetails, Button>() {

                    private final Button btn = new Button(buttonName);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            BorrowingDetails data = getTableView().getItems().get(getIndex());
                            Alert a = new Alert(CONFIRMATION);
                            a.setTitle("Return Book");
                            a.setContentText("Do you want to return book: " + data.getTitle());

                            Optional<ButtonType> result = a.showAndWait();
                            if (result.isEmpty()) {
                                System.out.println("WITAM WITAM");
                            } else if (result.get() == ButtonType.OK) {
                                returnBook(data.getBorrowId());

                                Alert aInfo = new Alert(INFORMATION);
                                aInfo.setTitle("Book returned!");
                                aInfo.setContentText("You have returned book: " + data.getTitle());
                                aInfo.show();
                            } else if (result.get() == ButtonType.CANCEL) {
                                System.out.println("CANCLE");
                            }
                        });
                    }

                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            BorrowingDetails data = getTableView().getItems().get(getIndex());
                            if (data.getReturnDate() != null) {
                                setGraphic(null);
                            } else {
                                setGraphic(btn);
                            }
                        }
                    }
                };
                return cell;
            }
        };

        btnCol.setCellFactory(cellFactory);
    }

    private void addButtonToReservationTable(TableColumn<ReservationDetails, Button> btnCol, String buttonName) {
        Callback<TableColumn<ReservationDetails, Button>, TableCell<ReservationDetails, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<ReservationDetails, Button> call(final TableColumn<ReservationDetails, Button> param) {
                final TableCell<ReservationDetails, Button> cell = new TableCell<ReservationDetails, Button>() {

                    private final Button btn = new Button(buttonName);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            ReservationDetails data = getTableView().getItems().get(getIndex());

                            Alert a = new Alert(CONFIRMATION);
                            a.setTitle("Borrow Book");
                            a.setContentText("Do you want to borrow book: " + data.getTitle());

                            Optional<ButtonType> result = a.showAndWait();
                            if (result.isEmpty()) {
                                System.out.println("WITAM WITAM");
                            } else if (result.get() == ButtonType.OK) {
                                borrowReservedBook(data.getBookId(), data.getPersonId(), data.getReservationId());

                                Alert aInfo = new Alert(INFORMATION);
                                aInfo.setTitle("Book borrowed!");
                                aInfo.setContentText("You have borrowed book: " + data.getTitle());
                                aInfo.show();
                            } else if (result.get() == ButtonType.CANCEL) {
                                System.out.println("CANCLE");
                            }
                        });
                    }

                    @Override
                    public void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        btnCol.setCellFactory(cellFactory);
    }


    private void populateTableData(ObservableList<BorrowingDetails> obs,
                                   List<BorrowingDetails> detailsList,
                                   TableView<BorrowingDetails> tableView,
                                   TableColumn<BorrowingDetails, Button> col,
                                   String buttonName) {
        obs.setAll(detailsList);
        addButtonToBorrowingTable(col, buttonName);

        tableView.setItems(obs);
    }

    private List<BorrowingDetails> mapToBorrowingDetails(List<Borrowing> borrowings) {
        return borrowings.stream()
                .map(borrow -> {
                    Long bookId = borrow.getBook().getId();
                    Long borrowId = borrow.getId();
                    String title = borrow.getBook().getTitle();
                    Date borrowDate = borrow.getBorrowDate();
                    Date returnDate = borrow.getReturnDate();  // returnDate will be displayed when user return books (record will not be deleted from table)

                    return new BorrowingDetails(bookId, borrowId, title, borrowDate, returnDate);
                }).toList();
    }

    private List<ReservationDetails> mapToReservationDetails(List<Reservation> borrowings) {
        return borrowings.stream()
                .map(reservation -> {
                    Long reservationId = reservation.getId();
                    String title = reservation.getBook().getTitle();
                    Date reservationDate = reservation.getReservationDate();
                    Long bookId = reservation.getBook().getId();
                    Long personId = reservation.getPerson().getId();

                    return new ReservationDetails(reservationId, title, reservationDate, bookId, personId);
                }).toList();
    }

}
