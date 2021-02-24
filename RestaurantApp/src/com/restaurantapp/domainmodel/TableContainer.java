package com.restaurantapp.domainmodel;
import java.util.ArrayList;

/*
 * Il cameriere prende un riferimento ad un composed table
 * Il cassiere mette i tavoli in stato DA PULIRE, ma non li distrugge
 * L'hall organizator gestisce creazione e distruzione dei tavoli
 */

public class TableContainer {

	private static TableContainer instance=null;
	private ArrayList<ComposedTable> tables;
	
	private TableContainer() {
		tables = new ArrayList<ComposedTable>();
	}
	
	public static TableContainer getInstance() {
		if(instance==null)
			instance= new TableContainer();
		return instance;
	}
	
	public void addTable(ComposedTable table) {
		tables.add(table);
    }
	
	public ComposedTable getTable(int id) throws IndexOutOfBoundsException {
		for(ComposedTable ct : tables) {
			if(ct.getTableID() == id)
				return ct;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public boolean deleteTable(int id) {
		for(ComposedTable ct : tables) {
			if(ct.getTableID() == id) {
				ct.resetPhisicalTable();
				tables.remove(ct);
				return true;
			}				
		}
		return false;
	}
	
	public boolean setComposedTableAvailable(int id) {
		for(ComposedTable ct : tables) {
			if(ct.getTableID() == id) {
				ct.setTableState(TableState.AVAILABLE);
				return true;
			}				
		}
		return false;
	}

	public ArrayList<ComposedTable> getTables() {
		return tables;
	}
		
}
