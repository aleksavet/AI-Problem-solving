import java.util.Random;

public class Generating_places
{ public int[][] info(int width,int height,int places)
    {
        int[][] placecoordinates = new int[places][2];
        Random rand = new Random();
        for(int i=0;i<places; i++)
        {
            int x= (rand.nextInt(width / 10) + 1) * 10;
            int y= (rand.nextInt(height / 10) + 1) * 10;
            placecoordinates[i][0]=x;
            placecoordinates[i][1]=y;
        }
        System.out.println("Generated places: ");
        for(int i=0;i<places;i++)
        {System.out.printf("(%d,%d)%n",placecoordinates[i][0],placecoordinates[i][1]);
        }
        return placecoordinates;
    }
}
