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

import static com.example.bookmachine.BookRepository.getAllNotBorrowedBooks;
import static com.example.bookmachine.BookRepository.getAllNotReservedBooks;
import static com.example.bookmachine.BorrowingRepository.borrowBook;
import static com.example.bookmachine.BorrowingRepository.reserveBook;
import static com.example.bookmachine.HelloApplication.loggedInUser;
import static com.example.bookmachine.LoginController.loadFXML;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class HomeController {

    @FXML
    private TableColumn<BookDetails, String> borAuthorCol;

    @FXML
    private TableColumn<BookDetails, Button> borBorrowCol;

    @FXML
    private TableColumn<BookDetails, String> borCoverCol;

    @FXML
    private TableColumn<BookDetails, String> borGenreCol;

    @FXML
    private TableColumn<BookDetails, String> borPublisherCol;

    @FXML
    private TableColumn<BookDetails, String> borTitleCol;

    @FXML
    private TableColumn<BookDetails, Date> borYearCol;

    @FXML
    private TableView<BookDetails> borrowings;

    @FXML
    private Button profileBtn;

    @FXML
    private TableColumn<BookDetails, String> resAuthorCol;

    @FXML
    private TableColumn<BookDetails, String> resCoverCol;

    @FXML
    private TableColumn<BookDetails, String> resGenreCol;

    @FXML
    private TableColumn<BookDetails, String> resPublisherCol;

    @FXML
    private TableColumn<BookDetails, Button> resReserveCol;

    @FXML
    private TableColumn<BookDetails, String> resTitleCol;

    @FXML
    private TableColumn<BookDetails, String> resYearCol;

    @FXML
    private TableView<BookDetails> reservations;

    private ObservableList<BookDetails> obsBorrowings = FXCollections.observableArrayList();
    private ObservableList<BookDetails> obsReservations = FXCollections.observableArrayList();


    public void initialize() {
        setCellValueFactoryForBorrowingsTable();
        setCellValueFactoryForReservationsTable();

        List<Book> notBorrowedBooks = getAllNotBorrowedBooks();
        List<BookDetails> booksDetails = mapToBookDetails(notBorrowedBooks);

        populateTableData(obsBorrowings, booksDetails, borrowings, borBorrowCol, "Borrow");

        List<Book> notReservedBooks = getAllNotReservedBooks();
        booksDetails = mapToBookDetails(notReservedBooks);

        populateTableData(obsReservations, booksDetails, reservations, resReserveCol, "Reserve");

        profileBtn.setOnAction(event -> {
            loadFXML(event, "profile-view.fxml");
        });
    }

    private void populateTableData(ObservableList<BookDetails> obs,
                                   List<BookDetails> booksDetails,
                                   TableView<BookDetails> tableView,
                                   TableColumn<BookDetails, Button> col,
                                   String buttonName) {
        obs.setAll(booksDetails);
        addButtonToTable(col, buttonName);

        tableView.setItems(obs);
    }

    private List<BookDetails> mapToBookDetails(List<Book> books) {
        return books.stream()
                .map(book -> {
                    List<Author> authors = book.getAuthors();
                    List<String> authorsNames = authors.stream().map(author -> author.getFirstName() + " " +
                            author.getLastName()).toList();
                    String publisherName = book.getPublisher().getName();
                    String genreName = book.getGenre().getName();
                    Long id = book.getId();

                    return new BookDetails(id, book.getTitle(), book.getPublicationDate(),
                            authorsNames.toString(), publisherName, genreName, book.getCover());
                }).toList();
    }

    private void addButtonToTable(TableColumn<BookDetails, Button> btnCol, String buttonName) {
        Callback<TableColumn<BookDetails, Button>, TableCell<BookDetails, Button>> cellFactory = new Callback<>() {
            @Override
            public TableCell<BookDetails, Button> call(final TableColumn<BookDetails, Button> param) {
                final TableCell<BookDetails, Button> cell = new TableCell<BookDetails, Button>() {

                    private final Button btn = new Button(buttonName);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            BookDetails data = getTableView().getItems().get(getIndex());
                            if (btnCol == resReserveCol) {
                                Alert a = new Alert(CONFIRMATION);
                                a.setTitle("Reserve Book");
                                a.setContentText("Do you want to reserve book: " + data.getTitle());

                                Optional<ButtonType> result = a.showAndWait();
                                if (result.isEmpty()) {
                                    return;
                                } else if (result.get() == ButtonType.OK) {
                                    reserveBook(data.getId(), loggedInUser.getId());

                                    Alert aInfo = new Alert(INFORMATION);
                                    aInfo.setTitle("Book reserved!");
                                    aInfo.setContentText("You have reserved book: " + data.getTitle());
                                    aInfo.show();
                                } else if (result.get() == ButtonType.CANCEL) {
                                    return;
                                }
                            } else {
                                Alert a = new Alert(CONFIRMATION);
                                a.setTitle("Borrow Book");
                                a.setContentText("Do you want to borrow book: " + data.getTitle());

                                Optional<ButtonType> result = a.showAndWait();
                                if (result.isEmpty()) {
                                    return;
                                } else if (result.get() == ButtonType.OK) {
                                    borrowBook(data.getId(), loggedInUser.getId());
                                    Alert aInfo = new Alert(INFORMATION);
                                    aInfo.setTitle("Book borrowed!");
                                    aInfo.setContentText("You have borrowed book: " + data.getTitle());
                                    aInfo.show();
                                } else if (result.get() == ButtonType.CANCEL) {
                                    return;
                                }
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

    private void setCellValueFactoryForBorrowingsTable() {
        borAuthorCol.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        borYearCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        borCoverCol.setCellValueFactory(new PropertyValueFactory<>("cover"));
        borGenreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        borPublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        borTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

    }

    private void setCellValueFactoryForReservationsTable() {
        resAuthorCol.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        resYearCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        resCoverCol.setCellValueFactory(new PropertyValueFactory<>("cover"));
        resGenreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        resPublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        resTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
    }
}
