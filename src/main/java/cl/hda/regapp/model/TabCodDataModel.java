package cl.hda.regapp.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import cl.hda.regapp.entities.TabCod;

public class TabCodDataModel  extends ListDataModel<TabCod> implements SelectableDataModel<TabCod> {  

	private List<TabCod> lstTabCods;
    	
	public  TabCodDataModel() {
    }

    public  TabCodDataModel(List<TabCod> data) {
        
    	super(data);
    	lstTabCods = data;
    }
    
	@Override
    public TabCod getRowData(String rowKey) {
 

    	List<TabCod> lst = (List<TabCod>) getWrappedData();
        for(TabCod t : lst) {
            if(t.getId().equals(rowKey))
                return t;
        }
        return null;
    }

    @Override
    public Object getRowKey(TabCod t) {
        
    	return t.getId();
    }

	public List<TabCod> getLstTabCods() {
		return lstTabCods;
	}

	public void setLstTabCods(List<TabCod> lstTabCods) {
		this.lstTabCods = lstTabCods;
	}
    
    

}
