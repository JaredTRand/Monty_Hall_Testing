import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class montyhall {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose number of trials: ");
        int trials = scanner.nextInt();

        int[][] doors = new int[trials][3];
        doors = populatedoors(doors, trials);

        System.out.println("Starting Non-Switching Test");
        int wins = 0;
        int losses = 0;
        for(int i=0; i <= trials-1; i++){
            int choice = randomnum(0, 2);
            int opendoor = randomnum(0, 2);
            if(opendoor == choice){
                opendoor = changenum(choice, opendoor);
            }

            if(doors[i][choice] == 1){
                wins += 1;
            }else{
                losses += 1;
            }
        }
        System.out.printf("Not switching the door resulted in %d wins and %d losses\n\n", wins, losses);

        System.out.println("Starting Switching Test");
        doors = populatedoors(doors, trials);
        wins = 0;
        losses = 0;
        for(int i=0; i <= trials-1; i++){
            int choice = randomnum(0, 2);
            int opendoor = randomnum(0, 2);
            if(opendoor == choice){
                opendoor = changenum(choice, opendoor);
            }

            choice = swapdoor(opendoor, choice);

            if(doors[i][choice] == 1){
                wins += 1;
            }else{
                losses += 1;
            }
        }
        System.out.printf("Switching the door resulted in %d wins and %d losses\n\n", wins, losses);
    }

    public static int randomnum(int low, int high){
        return ThreadLocalRandom.current().nextInt(low, high+1);
    }

    public static int changenum(int keep, int change){
        if(keep != change){
            return change;
        }
        return changenum(keep, randomnum(0, 2));
    }

    public static int swapdoor(int opendoor, int choice){
        int swapped = randomnum(0, 2);
        if(swapped == opendoor || swapped == choice){
            return swapdoor(opendoor, choice);
        }
        return swapped;
    }

    public static int[][] populatedoors(int[][] doors, int trials){
        for(int i=0; i <= trials-1; i++){
            int winningdoor = randomnum(0, 2);
            switch (winningdoor){
                case 0:
                    doors[i][0] = 1;
                    doors[i][1] = 0;
                    doors[i][2] = 0;
                    break;
                case 1:
                    doors[i][0] = 0;
                    doors[i][1] = 1;
                    doors[i][2] = 0;
                    break;
                case 2:
                    doors[i][0] = 0;
                    doors[i][1] = 0;
                    doors[i][2] = 1;
                    break;
                default:
                    break;
            }
        }
        return doors;
    }
}
