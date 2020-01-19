package unitTests;

import java.io.IOException;
import java.util.ArrayList;

import Enums.MessageType;
import controllers.MainAllControllers;
import entity.DBmessage;

public class calculatWithDB implements IExtentionUniversalScoring {

	private MainAllControllers MainAllControllers;


	@Override
	public ArrayList<Object> getActiveSuClo(ArrayList<Object> arry) {
		DBmessage dbm = new DBmessage(MessageType.makeActiveSuClo, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException we) {
		}
		return null;
	}
	

}
