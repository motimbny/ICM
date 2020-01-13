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
    	
	    /** The Constant serialVersionUID. */
	    private static final long serialVersionUID = 1L;
		
		/** The obj M. */
		private ArrayList<Object> objM;
		
		/**  will categorize the type of the message by MessageType enum. */
		private MessageType type;
		
		/**
		 * Instantiates a new d bmessage.
		 *
		 * @param type the type
		 * @param objs the objs
		 */
		public DBmessage(MessageType type, ArrayList<Object> objs) 
		{
			super();
			this.type = type;
			this.objM = objs;
		}

		/**
		 * Gets the type.
		 *
		 * @return the type
		 */
		public MessageType getType() 
		{
			return type;
		}

		/**
		 * Sets the type.
		 *
		 * @param type the new type
		 */
		public void setType(MessageType type)
		{
			this.type = type;
		}

		/**
		 * Gets the objs.
		 *
		 * @return the objs
		 */
		public ArrayList<Object> getObjs() 
		{
			return objM;
		}

		/**
		 * Sets the objs.
		 *
		 * @param objs the new objs
		 */
		public void setObjs(ArrayList<Object> objs) 
		{
			this.objM = new ArrayList<>();
			for (Object o : objs)
				this.objM.add(o);
		}
}
