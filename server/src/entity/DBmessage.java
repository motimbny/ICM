package entity;

import java.io.Serializable;
import java.util.ArrayList;

import Enums.MessageType;
/**
 * DBmessage - Client
 * Object that will use to communicate between the Client and the Server.
 * Implements Serializable, could be sent by socket
 * @author SHIRA
 *
 */
public class DBmessage implements Serializable
{
    	private static final long serialVersionUID = 1L;
		private ArrayList<Object> objM;
		/** will categorize the type of the message by MessageType enum */
		private MessageType type;
		public DBmessage(MessageType type, ArrayList<Object> objs) 
		{
			super();
			this.type = type;
			this.objM = objs;
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
}
