/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jptvr17library;

import entity.Book;
import entity.History;
import entity.Reader;
import interfaces.Saveble;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Melnikov
 */
public class StorageInBase implements Saveble{
    private EntityManager em;
    private EntityTransaction tx;

    public StorageInBase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTVR17LibraryPU");
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }
    
    

    @Override
    public void saveBooks(List<Book> listBooks) {
        int n = listBooks.size();
        this.tx.begin();
            for(int i=0; i<n;i++){
                if(listBooks.get(i).getId() == null){
                    em.persist(listBooks.get(i));
                }else{
                    em.merge(listBooks.get(i));
                }
            }
        this.tx.commit();
    }

    @Override
    public List<Book> loadBookFromStorage() {
        this.tx.begin();
           List<Book> listBooks = em.createQuery("SELECT b FROM Book b").getResultList();
        this.tx.commit();
        return listBooks;
    }

    @Override
    public List<Reader> loadReaderFromStorage() {
        this.tx.begin();
           List<Reader> listReaders = em.createQuery("SELECT r FROM Reader r").getResultList();
        this.tx.commit();
        return listReaders;
    }

    @Override
    public void saveReaders(List<Reader> listReaders) {
        int n = listReaders.size();
        this.tx.begin();
            for(int i=0; i<n;i++){
                if(listReaders.get(i).getId() == null){
                    em.persist(listReaders.get(i));
                }else{
                    em.merge(listReaders.get(i));
                }
            }
        this.tx.commit();
    }

    @Override
    public List<History> loadHistoriesFromStorage() {
        this.tx.begin();
           List<History> listHistories = em.createQuery("SELECT h FROM History h").getResultList();
        this.tx.commit();
        return listHistories;    
    }

    @Override
    public void saveHistories(List<History> listHistories) {
        int n = listHistories.size();
        this.tx.begin();
            for(int i=0; i<n;i++){
                if(listHistories.get(i).getId() == null){
                    em.persist(listHistories.get(i));
                }else{
                    em.merge(listHistories.get(i));
                }
            }
        this.tx.commit();
    }
    
}
