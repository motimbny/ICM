package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class DBmessage implements Serializable
{

    	private static final long serialVersionUID = 1L;
		public enum MessageType
		{
			UPDATE, SELECT, DBData,Exception,SetDB, DBStatus, INSERT;
		}
		private ArrayList<Object> objM;
		private String clas = null;
		private MessageType type;
		public DBmessage(MessageType type, ArrayList<Object> objs, String clas) 
		{
			super();
			this.type = type;
			this.objM = objs;
			this.clas=clas;
		}

		public MessageType getType() 
		{
			return type;
		}

		public void setType(MessageType type)
		{
			this.type = type;
		}

		public ArrayList<Object> getObjs() 
		{
			return objM;
		}

		public void setObjs(ArrayList<Object> objs) 
		{
			this.objM = new ArrayList<>();
			for (Object o : objs)
				this.objM.add(o);
		}
		
		public String getClasz() 
		{
			return clas;
		}

		public void setClasz(String clas)
		{
			this.clas = clas;
		}
}
