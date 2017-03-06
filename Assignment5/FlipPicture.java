import acm.graphics.GImage;
import acm.program.GraphicsProgram;


public class FlipPicture extends GraphicsProgram{
	public void run(){
		GImage image = new GImage("kid jumping.jpg");
		add(image);
		GImage flipedImage = flipImage(image);
		add(flipedImage, 600, 0);
	}
	
	public GImage flipImage(GImage image){
		int[][] pixelsArray = image.getPixelArray();
		for(int i = 0; i < pixelsArray.length; i ++){
			for(int j = 0; j < pixelsArray[0].length/2; j++){
				int temp;
				temp = pixelsArray[i][j];
				pixelsArray[i][j] = pixelsArray[i][pixelsArray[0].length - 1 - j];
				pixelsArray[i][pixelsArray[0].length - 1 - j] = temp;				
			}
		}
		return new GImage(pixelsArray);
	}
}
