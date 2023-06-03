package com.example.bookmachine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.Date;
import java.util.List;

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
                            System.out.println("selectedData: " + data);
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

    private void addButtonToReservationTable(TableColumn<ReservationDetails, Button> btnCol, String buttonName) {
        Callback<TableColumn<ReservationDetails, Button>, TableCell<ReservationDetails, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<ReservationDetails, Button> call(final TableColumn<ReservationDetails, Button> param) {
                final TableCell<ReservationDetails, Button> cell = new TableCell<ReservationDetails, Button>() {

                    private final Button btn = new Button(buttonName);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            ReservationDetails data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
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
                    String title = borrow.getBook().getTitle();
                    Date borrowDate = borrow.getBorrowDate();
                    Date returnDate = borrow.getReturnDate();  // returnDate will be displayed when user return books (record will not be deleted from table)

                    return new BorrowingDetails(title, borrowDate, returnDate);
                }).toList();
    }

    private List<ReservationDetails> mapToReservationDetails(List<Reservation> borrowings) {
        return borrowings.stream()
                .map(reservation -> {
                    String title = reservation.getBook().getTitle();
                    Date reservationDate = reservation.getReservationDate();

                    return new ReservationDetails(title, reservationDate);
                }).toList();
    }

}
