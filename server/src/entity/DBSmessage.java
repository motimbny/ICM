package entity;

import java.io.Serializable;
import java.util.ArrayList;

import Enums.MessageType;
import Enums.MessageTypeS;
/**
 * DBmessage - Server
 * Object that will use to communicate between the Client and the Server.
 * Implements Serializable, could be sent by socket
 * @author SHIRA
 *
 */
public class DBSmessage implements Serializable
	{

	    	private static final long serialVersionUID = 1L;
			private ArrayList<Object> objM;
			private MessageTypeS type;
			public DBSmessage(MessageTypeS type, ArrayList<Object> objs) 
			{
				super();
				this.type = type;
				this.objM = objs;
			}

			public MessageTypeS getType() 
			{
				return type;
			}

			public void setType(MessageTypeS type)
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