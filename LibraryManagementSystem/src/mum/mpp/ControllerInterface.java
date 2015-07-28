package mum.mpp;

import java.util.List;

import mum.mpp.dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException;
	public boolean addBookCopy(String isbn) throws LibrarySystemException;
	public Book searchBook(String isbn);
	public void login(String id, String password) throws LoginException;
}
