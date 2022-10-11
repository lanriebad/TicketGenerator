import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TicketGeneratorTest {

    private Ticket ticket;

//    public int generateRandomWithinNumberOf9(){
//        Random random = new Random();
//        int rand = 0;
//        while (true){
//            rand = random.nextInt(8);
//            if(rand !=0) break;
//        }
//        System.out.println(rand);
//        return rand;
//    }
//
//    public int generateRandomWithinNumberOf3(){
//        Random random = new Random();
//        int answer = 0;
//        for(int i =0; i < 100; i++)
//        {
//            answer = random.nextInt(10) + 1;
//            //System.out.println(answer);
//        }
//        return answer;
//    }



    int[][] bingoTicket = new int[3][9];

    @Test
    @Before
    public void TestTicketRowCount() {
        int number = 0;
        int ticketRowCount = Ticket.getTicketRowCount(number);
        assertNotNull("Successful", ticketRowCount);

    }


    @Test
    @Before
    public void TestTicketColumnCount() {
        int number = 0;
        int ticketColumnCount = Ticket.getTicketColumnCount(number);
        assertNotNull("Successful", ticketColumnCount);

    }

    @Test
    @Before
    public void TestGetNullValueInTicket() {
        int number = 0;
        int nullValueInTicket = Ticket.getNullCellInTicketColumn(number);
        assertNotNull("Successful", nullValueInTicket);

    }

    @Test
    @After
    public void TestPrintTickets() {
        List<Integer> newSetColumn = null;
        List<List<Integer>> newTicketSetList;
        Ticket[] tickets = new Ticket[6];
        for (int i = 0; i < 6; i++) {
            tickets[i] = new Ticket();
        }

        List<Integer> columnOne = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            columnOne.add(i);
        }

        List<Integer> columnTwo = new ArrayList<>();
        for (int i = 10; i <= 19; i++) {
            columnTwo.add(i);
        }

        List<Integer> columnThree = new ArrayList<>();
        for (int i = 20; i <= 29; i++) {
            columnThree.add(i);
        }

        List<Integer> columnFour = new ArrayList<>();
        for (int i = 30; i <= 39; i++) {
            columnFour.add(i);
        }

        List<Integer> columnFive = new ArrayList<>();
        for (int i = 40; i <= 49; i++) {
            columnFive.add(i);
        }

        List<Integer> columnSix = new ArrayList<>();
        for (int i = 50; i <= 59; i++) {
            columnSix.add(i);
        }

        List<Integer> columnSeven = new ArrayList<>();
        for (int i = 60; i <= 69; i++) {
            columnSeven.add(i);
        }

        List<Integer> columnEight = new ArrayList<>();
        for (int i = 70; i <= 79; i++) {
            columnEight.add(i);
        }

        List<Integer> columnNine = new ArrayList<>();
        for (int i = 80; i <= 90; i++) {
            columnNine.add(i);
        }
        List<List<Integer>> ticketColumns = new ArrayList<>();
        TicketGenerator.extractedAllColumns(columnOne, columnTwo, columnThree, columnFour, columnFive, columnSix, columnSeven, columnEight, columnNine, ticketColumns);
        List<List<Integer>> ticketSetColumnOne = new ArrayList<>();
        List<List<Integer>> ticketSetColumnTwo = new ArrayList<>();
        List<List<Integer>> ticketSetColumnThree = new ArrayList<>();
        List<List<Integer>> ticketSetColumnFour = new ArrayList<>();
        List<List<Integer>> ticketSetColumnFive = new ArrayList<>();
        List<List<Integer>> ticketSetColumnSix = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ticketSetColumnOne.add(new ArrayList<>());
            ticketSetColumnTwo.add(new ArrayList<>());
            ticketSetColumnThree.add(new ArrayList<>());
            ticketSetColumnFour.add(new ArrayList<>());
            ticketSetColumnFive.add(new ArrayList<>());
            ticketSetColumnSix.add(new ArrayList<>());
        }
        List<List<List<Integer>>> ticketSets = new ArrayList<>();
        TicketGenerator.extractedAllSetOfColumns(ticketSetColumnOne, ticketSetColumnTwo, ticketSetColumnThree, ticketSetColumnFour, ticketSetColumnFive, ticketSetColumnSix, ticketSets);
        // assigning elements to each set for each column
        for (int i = 0; i < 9; i++) {
            List<Integer> assignElements = ticketColumns.get(i);
            for (int j = 0; j < 6; j++) {
                int generateRandomIndex = GeneratorUtil.getRand(0, assignElements.size() - 1);
                int generateNumber = assignElements.get(generateRandomIndex);

                List<Integer> set = ticketSets.get(j).get(i);
                set.add(generateNumber);

                assignElements.remove(generateRandomIndex);
            }
        }
        // assign element from last column to random set
        List<Integer> lastCol = ticketColumns.get(8);
        int randNumIndex = GeneratorUtil.getRand(0, lastCol.size() - 1);
        int randNum = lastCol.get(randNumIndex);
        int randSetIndex =GeneratorUtil.getRand(0, ticketSets.size() - 1);
        List<Integer> randSet = ticketSets.get(randSetIndex).get(8);
        randSet.add(randNum);
        lastCol.remove(randNumIndex);
        // 3 passes over the remaining ticketColumns
        for (int verifiedTicket = 0; verifiedTicket < 3; verifiedTicket++) {
            for (int i = 0; i < 9; i++) {
                List<Integer> ticketColumnList = ticketColumns.get(i);
                if (ticketColumnList.size() == 0)
                    continue;
                int generateRandomNumber = GeneratorUtil.getRand(0, ticketColumnList.size() - 1);
                int generateNumber = ticketColumnList.get(generateRandomNumber);
                boolean isVacantSetPresent = false;
                while (!isVacantSetPresent) {
                    int generateRandomSetIndex = GeneratorUtil.getRand(0, ticketSets.size() - 1);
                    List<List<Integer>> generateRandomSet = ticketSets.get(generateRandomSetIndex);
                    if (GeneratorUtil.getNumberOfElementsInSet(generateRandomSet) == 15 || generateRandomSet.get(i).size() == 2)
                        continue;
                    isVacantSetPresent = true;
                    generateRandomSet.get(i).add(generateNumber);
                    ticketColumnList.remove(generateRandomNumber);
                }
            }
        }
        // one more pass over the remaining ticketColumns
        for (int i = 0; i < 9; i++) {
            List<Integer> ticketColumnList = ticketColumns.get(i);
            if (ticketColumnList.size() == 0)
                continue;
            int passedRandomNumber = GeneratorUtil.getRand(0, ticketColumnList.size() - 1);
            int passedNumber = ticketColumnList.get(passedRandomNumber);
            boolean emptySetPresent = false;
            while (!emptySetPresent) {
                int generateFirstRandom = GeneratorUtil.getRand(0, ticketSets.size() - 1);
                List<List<Integer>> firstRandomList = ticketSets.get(generateFirstRandom);
                if (GeneratorUtil.getNumberOfElementsInSet(firstRandomList) == 15 || firstRandomList.get(i).size() == 3)
                    continue;
                emptySetPresent = true;
                firstRandomList.get(i).add(passedNumber);
                ticketColumnList.remove(passedRandomNumber);
            }
        }
        // sort the internal ticketSets
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                Collections.sort(ticketSets.get(i).get(j));
            }
        }
        // got the ticketSets - need to arrange in tickets now
        for (int setIndex = 0; setIndex < 6; setIndex++) {
            newTicketSetList = ticketSets.get(setIndex);
            Ticket newTicket = tickets[setIndex];
            // generate values first row by iterating through the row so if there is a value we break out of the loop
            for (int firstRow = 3; firstRow > 0; firstRow--) {
                if (newTicket.getTicketRowCount(0) == 5)
                    break;
                for (int colIndex = 0; colIndex < 9; colIndex++) {
                    if (newTicket.getTicketRowCount(0) == 5)
                        break;
                    if (newTicket.bingoTicket[0][colIndex] != 0)
                        continue;
                    newSetColumn = newTicketSetList.get(colIndex);
                    if (newSetColumn.size() != firstRow)
                        continue;
                    newTicket.bingoTicket[0][colIndex] = newSetColumn.remove(0);
                }
            }
            // generate values for second row by iterating through the row so if there is a value we break out of the loop
            for (int secondRow = 2; secondRow > 0; secondRow--) {
                if (newTicket.getTicketRowCount(1) == 5)
                    break;
                for (int colIndex = 0; colIndex < 9; colIndex++) {
                    if (newTicket.getTicketRowCount(1) == 5)
                        break;
                    if (newTicket.bingoTicket[1][colIndex] != 0)
                        continue;
                    newSetColumn = newTicketSetList.get(colIndex);
                    if (newSetColumn.size() != secondRow)
                        continue;
                    newTicket.bingoTicket[1][colIndex] = newSetColumn.remove(0);
                }
            }
            // generate values for  third row by iterating through the row  so if there is a value we break out of the loop
            for (int thirdRow = 1; thirdRow > 0; thirdRow--) {
                if (newTicket.getTicketRowCount(2) == 5)
                    break;
                for (int colIndex = 0; colIndex < 9; colIndex++) {
                    if (newTicket.getTicketRowCount(2) == 5)
                        break;
                    if (newTicket.bingoTicket[2][colIndex] != 0)
                        continue;
                    newSetColumn = newTicketSetList.get(colIndex);
                    if (newSetColumn.size() != thirdRow)
                        continue;
                    newTicket.bingoTicket[2][colIndex] = newSetColumn.remove(0);
                }
            }
        }
        try {
            // checking if all ticketColumns are sorted
            for (int i = 0; i < 6; i++) {
                Ticket newTicket = tickets[i];
                newTicket.arrangeAllTicketAndItsColumns();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int number=TicketGenerator.printAllTickets(tickets);
        assertNotSame("Not the same Ticket Generated", number);
        //assertArrayEquals(row1, number);
    }


}




