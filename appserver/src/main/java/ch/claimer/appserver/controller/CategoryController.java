package ch.claimer.appserver.controller;

import java.rmi.RemoteException;
import java.util.List;

import ch.claimer.appserver.methods.CategoryMethod;
import ch.claimer.shared.models.Category;

public class CategoryController extends Controller<Category> implements CategoryMethod {

	private static final long serialVersionUID = -3121298108225684902L;

	public CategoryController() throws RemoteException {
		super(Category.class);
	}

	public List<Category> getAll() {
		session.beginTransaction();
		List<Category> categories = repository.getAll();
		session.getTransaction().commit();
		return categories;
	}
}
