package cl.hda.regapp.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import cl.hda.regapp.entities.CabAplicacion;


public class CabAplicacionDataModel extends ListDataModel<CabAplicacion> implements SelectableDataModel<CabAplicacion> {  

	private List<CabAplicacion> lstCabAp;
    
	
	public  CabAplicacionDataModel() {
		System.out.println("llamando a constructor 1");
    }

    public  CabAplicacionDataModel(List<CabAplicacion> data) {
        
    	super(data);
    	System.out.println("llamando a constructor 2");
    	lstCabAp = data;
    }
    
       

	@Override
    public CabAplicacion getRowData(String rowKey) {
        
    	
    	List<CabAplicacion> cars = (List<CabAplicacion>) getWrappedData();

        for(CabAplicacion car : cars) {
            if(car.getId().equals(rowKey))
                return car;
        }

        return null;
    }

    @Override
    public Object getRowKey(CabAplicacion car) {
        
    	return car.getId();
    }

	public List<CabAplicacion> getLstCabAp() {
		return lstCabAp;
	}

	public void setLstCabAp(List<CabAplicacion> lstCabAp) {
		this.lstCabAp = lstCabAp;
	}
    
    
    

}
