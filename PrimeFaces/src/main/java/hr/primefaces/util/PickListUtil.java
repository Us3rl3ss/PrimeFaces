package hr.primefaces.util;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;

public class PickListUtil {

	/**
	 * setPickListElement
	 */
	public static DualListModel<String> setPickListElement(List<String> refList, String element) {
		
		DualListModel<String> result = new DualListModel<String>();

		List<String> sourceList = new ArrayList<String>();
		List<String> targetList = getTargetList(element);
		
		for (String ref: refList) {
			
			boolean validRef = true;
			
			for (String target: targetList) {
				
				if (ref.equals(target))
					validRef = false;
			}
			
			if (validRef)
				sourceList.add(ref);
		}
		
		result.setSource(sourceList);
		result.setTarget(targetList);
		
		return result;
	}
	
	/**
	 * getTargetList
	 */
	public static List<String> getTargetList(String element) {

		List<String> result = new ArrayList<String>();
		
		boolean notFinish = true;
		
		int zarezPos;
		
		while (notFinish) {
			
			zarezPos = element.indexOf(",");
			
			if (zarezPos == -1) {
				
				result.add(element);
				notFinish = false;
				break;
			}
			else {
				
				result.add(element.substring(0, zarezPos));
				element = element.substring(zarezPos+1, element.length());
			}
		}
		
		return result;
	}

	/**
	 * setListFromPickListElement
	 */
	public static String setListFromPickListElement(List<String> dualList) {

		String result = "";
		
		for (int i=0; i<dualList.size(); i++) {
			
			result += dualList.get(i);
			
			if (i != dualList.size()-1)
				result += ",";
		}
		
		return result;
	}

}
