import java.util.Scanner;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class ImageTransform {

    public static Image lighten(Image srcImage) {
        float pixels[] = srcImage.toFloatArray(Image.PixelFormat.RGB);

        for(int i = 0; i < pixels.length; i++) {
            pixels[i] *= 1.5;
        }
        return new Image((int) srcImage.getWidth(), (int) srcImage.getHeight(), pixels, Image.PixelFormat.RGB);
    }


    public static Image greenShift(Image srcImage) {
        float pixels[] = srcImage.toFloatArray(Image.PixelFormat.RGB);

        for(int i = 1; i < pixels.length; i += 3) {
            pixels[i] *= 1.25;
        }
        return new Image((int) srcImage.getWidth(), (int) srcImage.getHeight(), pixels, Image.PixelFormat.RGB);
    }

    public static Image invert(Image srcImage) {
        byte pixels[] = srcImage.toByteArray(Image.PixelFormat.RGB);

        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = (byte) (255 - pixels[i]);
        }
        return new Image((int) srcImage.getWidth(), (int) srcImage.getHeight(), pixels, Image.PixelFormat.RGB);
    }

    //public static Image rotate(Image srcImage) {

    //}

    public static void main(String[] args) {
        Image srcImage = new Image("1200px-Cow_on_Pupers.jpg");
    
        Scanner scan = new Scanner(System.in);
        System.out.println("How would you like to transform your image?");
        System.out.println("1. Lighten");
        System.out.println("2. Green Shift");
        System.out.println("3. Invert");

        System.out.print("> ");
        int choice = scan.nextInt();

        Image transformed = switch(choice) {
            default -> srcImage; // If no matching choice, display original image
            case 1 -> lighten(srcImage);
            case 2 -> greenShift(srcImage);
            case 3 -> invert(srcImage);
        };

        CanvasWindow canvas = new CanvasWindow("img", 500, 500);
        canvas.add(transformed);
        transformed.setCenter(canvas.getCenter());

        scan.close();
    }
    
}
