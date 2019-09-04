/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptvr17library;

import providers.ReturnBookProvider;
import providers.TakeBookProvider;
import providers.ReaderProvider;
import entity.Book;
import entity.History;
import entity.Reader;
import interfaces.Saveble;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import providers.BookProvider;

/**
 *
 * @author Melnikov
 */
public class App {
    private List<Book> listBooks = new ArrayList<>();
    private List<Reader> listReaders = new ArrayList<>();
    private List<History> listHistories = new ArrayList<>();
    
    private Saveble savable;
    public App() {
       savable = new StorageInBase();
       //savable = new StorageInFile();
        try {
            listBooks = savable.loadBookFromStorage();
        } catch (Exception e) {
            System.out.println("Нет данных");
        }
       
        try {
            listReaders = savable.loadReaderFromStorage();
        } catch (Exception e) {
            System.out.println("Нет данных");
        }
        try {
            listHistories = savable.loadHistoriesFromStorage();
        } catch (Exception e) {
            System.out.println("Нет данных");
        }
    }
    
    public void run() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("---- Наша библиотека ----");
        String repeat = "r";
        int operation;
        do{
            System.out.println("Выберите действие:");
            System.out.println("1. Создать книгу");
            System.out.println("2. Создать читателя");
            System.out.println("3. Выдать книгу читателю");
            System.out.println("4. Читатель возвращает книгу");
            System.out.println("5. Вывести список книг");
            System.out.println("6. Вывести список читателей");
            operation = scanner.nextInt();
            scanner.nextLine();
            switch (operation) {
                case 1:
                    BookProvider bookProvider = new BookProvider();
                    listBooks.add(bookProvider.createBook());
                    savable.saveBooks(listBooks);
                    for(int i=0; i < listBooks.size();i++){
                       System.out.println(
                            "Список книг: " 
                            + listBooks.get(i).getName()
                        ); 
                    }
                    break;
                case 2:
                    ReaderProvider readerProvider = new ReaderProvider();
                    listReaders.add(readerProvider.createReader());
                    savable.saveReaders(listReaders);
                    for(int i=0; i < listReaders.size();i++){
                       System.out.println(
                            "Список читателей: " 
                            + listReaders.get(i).getName()
                        ); 
                    }
                    break;
                case 3:
                    TakeBookProvider takeBookProvider = new TakeBookProvider();
                    listHistories.add(takeBookProvider.takeBook(
                            listBooks,
                            listReaders
                    ));
                    savable.saveHistories(listHistories);
                    break;
                case 4:
                    ReturnBookProvider returnBookProvider = new ReturnBookProvider();
                    listHistories = returnBookProvider.returnBook(listHistories);
                    savable.saveHistories(listHistories);
                    break;
                case 5:
                    System.out.println("Список книг: ");
                    for(int i=0;i<listBooks.size();i++){
                        System.out.println(listBooks.get(i));
                    }
                    break; 
                case 6:
                    System.out.println("Список читателей: ");
                    for(int i=0;i<listReaders.size();i++){
                        System.out.println(listReaders.get(i));
                    }
                    break;
                
                default:
                    System.out.println("Такое действие неподдерживается");
                    continue;
            }
            System.out.println("Для продолжения программы");
            System.out.println("введите \"r\"");
            System.out.println("Для окончания \"q\"");
            repeat = scanner.nextLine();
        }while("r".equals(repeat));
        System.out.println("Программа закрывается");
    }
    
}
