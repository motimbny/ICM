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

	    	/** The Constant serialVersionUID. */
	    	private static final long serialVersionUID = 1L;
			
			/** The obj M. */
			private ArrayList<Object> objM;
			
			/** The type. */
			private MessageTypeS type;
			
			/**
			 * Instantiates a new DB smessage.
			 *
			 * @param type the type
			 * @param objs the objs
			 */
			public DBSmessage(MessageTypeS type, ArrayList<Object> objs) 
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
			public MessageTypeS getType() 
			{
				return type;
			}

			/**
			 * Sets the type.
			 *
			 * @param type the new type
			 */
			public void setType(MessageTypeS type)
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