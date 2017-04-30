package main.java; /**
 * Created by bruno on 4/17/2017.
 */
import java.text.DecimalFormat;
import java.util.Scanner;

class Dice
{
        public static void main (String[] args) throws java.lang.Exception
    {
        Scanner cin = new Scanner(System.in);
        boolean run = true ;
        int[] sum = new int[13] ;
        float[] percent = new float[13];
        int streak = 0, streakValue = 0, currentStreak = 0, streakStart = 0, trials = 0 ;
        int dice1 = 0, dice2 = 0 ;
        for(int i = 0; i < sum.length; ++i)
        {
            sum[i] = 0 ;
            percent[i] = 0.0f;
        }
        do
        {
            trials = cin.nextInt();
            int prevSum = 0 ;

            for(int t = 0; t < trials; ++t)
            {
                dice1 = (int) ((Math.random() * 6) + 1);
                dice2 = (int) ((Math.random() * 6) + 1);
                sum[dice1 + dice2]++ ;
                if((dice1 + dice2) == prevSum)
                {
                    ++currentStreak ;
                    streakValue = prevSum ;
                    if(currentStreak > streak)
                    {
                        streak = currentStreak ;
                        streakStart = t - streak ;
                    }
                }
                else {
                    currentStreak = 1 ;
                }
                prevSum = dice1 + dice2 ;
            }

            System.out.println("Continue ? (y/n) : ");
            run = cin.nextLine().equals("y");
        } while(run);

        DecimalFormat df = new DecimalFormat("###.##");

        // Start from 2 - the minimum sum possible.
        for(int i = 2; i < 13; ++i)
        {
            percent[i] = (float)sum[i] / (float)trials * 100.0f ;
            System.out.println("The sum " + i + " has occurred " + df.format(percent[i]) + "% of times");
        }
        System.out.println("Longest streak of " + streakValue + " has occurred for " + streak + " times");
        System.out.println("It started at : " + streakStart);
    }

}
