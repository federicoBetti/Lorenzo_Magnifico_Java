package project.messages;

import project.controller.Constants;

public class NoMoreAction extends BonusInteraction {
	int okOrNot;
	
	public NoMoreAction () {
		this.okOrNot = 0;
	}


	//todo forse altra stringa
	@Override
	public String toString() {
		return Constants.OK_OR_NO;
	}
}
