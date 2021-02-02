package io.klib.log.toolings;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;

public class LogTail_Listener implements TailerListener {
	
	private String triggerText;
	private boolean found = false;
	
	public LogTail_Listener(String triggerText) {
		this.triggerText = triggerText;
	}
	
	@Override
	public void fileNotFound() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileRotated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handle(String arg0) {
		found = arg0.contains(triggerText);
		if (found) {
			System.out.println(triggerText + " was found!");
		}
		
	}

	@Override
	public void handle(Exception arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(Tailer arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getFound() {
		return found;
	}

}
