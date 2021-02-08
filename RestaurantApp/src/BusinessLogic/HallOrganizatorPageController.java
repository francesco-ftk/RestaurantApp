package BusinessLogic;

import java.util.ArrayList;

import DomainModel.ComposedTable;
import DomainModel.PhisicalTable;
import DomainModel.TableContainer;
import DomainModel.TableState;


/*
 * Login attraverso interfaccia che chiama il costruttore.
 * Metodo per settare i tavoli non usabili a inizio serata, 
 * per distruggere un composed table o per renderlo disponibile senza distruggerlo.
 * (Non ci sono controlli sullo stato del composed table prima di distruggerlo).
 */
public class HallOrganizatorPageController {

	//il riferimento ai tavoli fisici si trova sia nel composed che nella lista. 
	private ArrayList<PhisicalTable> tables;
	private TableContainer tableContainer;
	
	public HallOrganizatorPageController(ArrayList<PhisicalTable> tables, TableContainer tc) {
		this.tables = tables;
		this.tableContainer = tc;
	}
	
	//TODO: Creare l'id potrebbe dare problemi, cosa si può fare?
	public boolean createComposedTable(int id, int[] IDs) {
		ComposedTable c = new ComposedTable(TableState.AVAILABLE, id);
		ArrayList<PhisicalTable> tmp = new ArrayList<PhisicalTable>();
		
		for(int i=0; i<IDs.length; i++) {
			for(PhisicalTable t : tables) {
				if(t.getTableID() == IDs[i] && t.isAvailable())
					tmp.add(t);
				else if(t.getTableID() == IDs[i] && !t.isAvailable())
					return false;		
			}
		}
		
		for(PhisicalTable t : tmp) {
			c.addTable(t);
		}
		
		tableContainer.addTable(c);
		return true;
	}
	
	public boolean setTablesUnusable(int[] IDs) {
		ArrayList<PhisicalTable> tmp = new ArrayList<PhisicalTable>();
		
		for(int i=0; i<IDs.length; i++) {
			for(PhisicalTable t : tables) {
				if(t.getTableID() == IDs[i])
					tmp.add(t);
			}
		}		
		for(PhisicalTable t : tmp) {
			ComposedTable c = new ComposedTable(TableState.UNUSABLE, t.getTableID());
			c.addTable(t);
			tableContainer.addTable(c);		
		}			
		return true;
	}
	
	public boolean deleteComposedTable(int id) {
		return tableContainer.deleteTable(id);
	}
	
	public boolean setComposedTableAvailable(int id) {
		return tableContainer.setComposedTableAvailable(id);
	}
}