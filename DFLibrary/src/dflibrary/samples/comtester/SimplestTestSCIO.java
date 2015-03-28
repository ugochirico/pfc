package dflibrary.samples.comtester;

import dflibrary.library.*;
import dflibrary.middleware.*;
import java.util.Arrays;

public class SimplestTestSCIO {
	
	public static void main(String[] args){		
		
		ComManager cm = new SCIOComManager();		
		try{
			cm.scan();				
			String[] readers = cm.listReaders();
			System.out.println(Arrays.toString(readers));
			cm.select(readers[0]);
			
			if(cm.isCardPresent()){
				System.out.println("Card detected");
				cm.connect();					
				CardType ct = cm.getCardType();			
				if(ct == CardType.MIFARE_DESFIRE){
					System.out.println("Card identified as Mifare DESFire");
					DFCard df = new DFCard(cm);						
					System.out.println(df.getKeyVersion(0));												
				}
				else{
					System.out.println("Card is not a DESFire");
				}
				cm.disconnect();									
			}
			else{
				System.out.println("No card present");
			}
			cm.deselect();
			
		}catch(DFLException e){	e.printStackTrace();} 
		try{
			cm.release();
		}
		catch(DFLException e){e.printStackTrace();}
	}	
}
