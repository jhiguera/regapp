package cl.hda.regapp.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import cl.hda.regapp.entities.DetAplicacion;


public class DetAplicacionDataModel  extends ListDataModel<DetAplicacion> implements SelectableDataModel<DetAplicacion> {  

	private List<DetAplicacion> lstCosechaDet;
    
	
	public  DetAplicacionDataModel() {
		System.out.println("llamando a constructor 1");
    }

    public  DetAplicacionDataModel(List<DetAplicacion> data) {
        
    	super(data);
    	System.out.println("llamando a constructor 2");
    	lstCosechaDet = data;


    }
    
    
    public List<DetAplicacion> getLstCosechaDet() {
		return lstCosechaDet;
	}

	public void setLstCosechaDet(List<DetAplicacion> lstCosechaDet) {
		this.lstCosechaDet = lstCosechaDet;
	}

	@Override
    public DetAplicacion getRowData(String rowKey) {
        
    	System.out.println("id"+rowKey);
    	
    	List<DetAplicacion> cars = (List<DetAplicacion>) getWrappedData();

        for(DetAplicacion car : cars) {
            if(car.getId().equals(rowKey))
                return car;
        }

        return null;
    }

    @Override
    public Object getRowKey(DetAplicacion car) {
        
    	System.out.println("getRowKey");
    	return car.getId();
    }

}

