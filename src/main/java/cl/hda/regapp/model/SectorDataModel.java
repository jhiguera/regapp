package cl.hda.regapp.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import cl.hda.regapp.entities.Sector;

public class SectorDataModel extends ListDataModel<Sector> implements SelectableDataModel<Sector> {  

	private List<Sector> lstSectores;
    	
	public  SectorDataModel() {
    }

    public  SectorDataModel(List<Sector> data) {
        
    	super(data);
    	System.out.println("llamando a constructor 2");
    	lstSectores = data;
    }
    
    
       

	

	public List<Sector> getLstSectores() {
		return lstSectores;
	}

	public void setLstSectores(List<Sector> lstSectores) {
		this.lstSectores = lstSectores;
	}

	@Override
    public Sector getRowData(String rowKey) {
        
    	
    	List<Sector> lst = (List<Sector>) getWrappedData();

        for(Sector s : lst) {
            if(s.getId().equals(rowKey))
                return s;
        }

        return null;
    }

    @Override
    public Object getRowKey(Sector s) {
        
    	return s.getId();
    }

}
