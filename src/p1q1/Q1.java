package p1q1;

import javax.swing.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.Arrays;

public class Q1 {
	public static void main(String[] args) throws IOException {
		
		//file chooser///////////////////////////////////////////
		
		
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setDialogTitle("");
		
		
		if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION){
			//
		}
		
		
		
		////////////////////////////////////////////////////////
		


		String file = fc.getSelectedFile().getAbsolutePath();

		byte[] arrayOfBytes=getFileInfo(file);
		
		
		byte[] arrOfBytesCpy=Arrays.copyOfRange(arrayOfBytes,33,35);

		ByteBuffer wrapped=ByteBuffer.wrap(arrOfBytesCpy);
		short quanBits=wrapped.getShort();	
		//System.out.println(arrayOfBytes.length);
	
		
		float[] arrayOfFloats = toFloatArray(arrayOfBytes,quanBits);
//		System.out.println(arrayOfFloats[36]);
//		System.out.println(arrayOfFloats[37]);
//		System.out.println(arrayOfFloats[38]);
//		System.out.println(arrayOfFloats[39]);
		//System.out.println(arrayOfFloats.length);
		JFrame f = new JFrame("Wave");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Wave w = new Wave(arrayOfFloats);
		f.add(new Wave(arrayOfFloats, quanBits));
		f.setSize(1200,200);
		f.setLocation(200,200);
		f.setVisible(true);
		
//		for(int i = 0 ; i<arrayOfFloats.length;i++) {
//			System.out.println(arrayOfFloats[i]);
//		}


	}
    
    
	
    ///////////////////////////////////////////////////////////////
    public static byte[] getFileInfo(String fileName) throws IOException {
        
        int read;
        
        ByteArrayOutputStream out =new ByteArrayOutputStream();
        @SuppressWarnings("resource")
		BufferedInputStream in = new BufferedInputStream(
                                 new FileInputStream(fileName));
        
        byte[] buffer = new byte[10000]; //maybe alter to maxfilesize provided in assignment description
            
        while((read = in.read(buffer))>0) { //.read gets length of file
            out.write(buffer,0,read);
        }
        out.flush();
        byte[] fileBytes = out.toByteArray();
        
        return fileBytes;
        
    }
    public static float[] toFloatArray(byte[] arr_of_bytes,int quanBits){
    
    	int exp=quanBits-1;
    	
    	if(quanBits==16){
	    	ShortBuffer sbuf = 
	    	ByteBuffer.wrap(arr_of_bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer();	
	    	
	    	short[] audioShorts = new short[sbuf.capacity()];
	    	
	    	sbuf.get(audioShorts);
	    	
	    	float[] audioFloats = new float[audioShorts.length];
	    	
	    	for (int i = 0; i <audioShorts.length;i++) {
	    		audioFloats[i] = ((float) audioShorts[i])/(float)Math.pow(2,exp);
	    		
	    	}

	    	return audioFloats;
	    	
	    	
    	}else{
    		
    		int v;
    		float[] audioFloats=new float[arr_of_bytes.length];
    		for (int i = 0;i<arr_of_bytes.length;i++) {
    			v=arr_of_bytes[i] & 0xFF;
    			audioFloats[i]=(float) (v/Math.pow(2,exp));
    		}
    		




        	return audioFloats;
    		
    	}

    	
    }
    
    
}
