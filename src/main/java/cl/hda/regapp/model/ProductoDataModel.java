package cl.hda.regapp.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.SelectableDataModel;

import cl.hda.regapp.entities.Producto;


public class ProductoDataModel extends ListDataModel<Producto> implements SelectableDataModel<Producto> {  

	private List<Producto> lstProducto;
    	
	public  ProductoDataModel() {
    }

    public  ProductoDataModel(List<Producto> data) {
        
    	super(data);
    	System.out.println("llamando a constructor 2");
    	lstProducto = data;
    }
    
    
       

	public List<Producto> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(List<Producto> lstProducto) {
		this.lstProducto = lstProducto;
	}

	@Override
    public Producto getRowData(String rowKey) {
        
    	
    	List<Producto> lst = (List<Producto>) getWrappedData();

        for(Producto p : lst) {
            if(p.getId().equals(rowKey))
                return p;
        }

        return null;
    }

    @Override
    public Object getRowKey(Producto p) {
        
    	return p.getId();
    }

}
