package name.kazennikov.dafsa;

import gnu.trove.list.array.TByteArrayList;

public class IntDAFSABoolean extends AbstractIntDAFSA {
	TByteArrayList finals;
	
	@Override
	public int finalHash(int state) {
		return finals.get(state);
	}

	@Override
	public boolean finalEquals(int state1, int state2) {
		return finals.get(state1) ==  finals.get(state2);
	}

	@Override
	public void finalReset(int state) {
		finals.set(state, (byte)0);
		states.get(state).validHashCode = false;
		
	}

	@Override
	public void finalAssign(int destState, int srcState) {
		finals.set(destState, finals.get(srcState));
	}
	
	@Override
	public void initFinals() {
		finals = new TByteArrayList();
	}

	@Override
	public void newFinal(int state) {
		finals.add((byte)0);
	}
	
	boolean finalValue;
	
	public void setFinalValue(boolean finalValue) {
		this.finalValue = finalValue;
	}

	@Override
	public boolean setFinal(int state) {
		finals.set(state, finalValue? (byte)1 : (byte)0);
		states.get(state).validHashCode = false;
		return true;
	}

	@Override
	public boolean hasFinal(int state) {
		return (finals.get(state) == 1) == finalValue;
	}
	
	@Override
	public boolean isFinalState(int state) {
		return finals.get(state) != 0;
	}



}