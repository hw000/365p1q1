package p1q1;

import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.*;

public class Wave extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float[] audioFloats;
	final float PAD=10;
	int quanBits;
	int offset;
	//int samples=0;
	
	public Wave(float[] audioFloats,int quanBits){

		this.audioFloats = audioFloats;
		this.quanBits=quanBits;
		if (quanBits==16){
			offset=44;
		}else{
			offset=88;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                 			 RenderingHints.VALUE_ANTIALIAS_ON);
		int w= getWidth();
		int h= getHeight();



		float xInc=(float)(w-2*PAD)/(audioFloats.length-1);
		//float scale = (float)(h-2*PAD)/getMax();
		g2.setPaint(Color.BLUE);
		
	
		if(quanBits==16){
			for(int i=44;i<audioFloats.length-1;i++) {
				
				
				float x1 = PAD+i*xInc;
				
				float y1 = h/2- h/2*audioFloats[i];
				
				float x2 = PAD+(i+1)*xInc;
			
				float y2 = h/2-h/2*audioFloats[i+1];
				
				
				g2.draw(new Line2D.Float(x1, y1, x2, y2));
				//samples++;
				
			}
		
		} else{
			for(int i=44;i<audioFloats.length-128;i++) {
				
				
				float x1 = PAD+i*xInc;
				//float x1 = xInc+i;
				float y1 = h/2*audioFloats[i];
				
				float x2 = PAD+(i+1)*xInc;
				//float x2 = xInc+i+1;
				float y2 = h/2*audioFloats[i+1];
				
				
				g2.draw(new Line2D.Float(x1, y1, x2, y2));
				//samples++;
		
			}
		
		}
		
		
		
		
		
		String max_string = "max: "+getMax();
		String samples= "samples: "+(audioFloats.length-44);
		//String samples_s="samples: "+samples;
		g2.setPaint(Color.RED);
		g2.drawString(max_string, 0, 10);
		g2.drawString(samples, 0, 20);
	}

	
	private float getMax() {
		float max = -Integer.MAX_VALUE;
		for(int i = offset;i<audioFloats.length; i++) {
			if(audioFloats[i]>max){
				max=audioFloats[i];
			}
		}
		return max;
	}
}
