import java.util.Arrays;

public class Ticket {

    static int[][] bingoTicket = new int[3][9];

    public static int getTicketRowCount(int number) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (bingoTicket[number][i] != 0)
                count++;
        }
        return count;
    }

    public static int getTicketColumnCount(int number) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (bingoTicket[i][number] != 0)
                count++;
        }
        return count;
    }

    // gives the row number of first found empty cell in given column
    public static int getNullCellInTicketColumn(int number) {
        int result = -1;
        for (int i = 0; i < 3; i++) {
            if (bingoTicket[i][number] == 0)
                return i;
        }
        return result;
    }

    public void arrangeTicketColumnWithThreeNumbers(int number) throws Exception {
        int ticketCell = getNullCellInTicketColumn(number);
        if (ticketCell != -1) {
            throw new Exception("** column has 3 cells filled and this is  Invalid");
        }
        // resolved ticket in a temporary array
        int[] resolvedTicket = new int[]{bingoTicket[0][number], bingoTicket[1][number], bingoTicket[2][number]};
        Arrays.sort(resolvedTicket);
        for (int r = 0; r < 3; r++) {
            bingoTicket[r][number] = resolvedTicket[r];
        }
    }

    public void arrangeTicketColumnWithTwoNumbers(int number) throws Exception {
        int ticketCell = getNullCellInTicketColumn(number);
        if (ticketCell == -1) {
            throw new Exception("column has 3 cells filled and this is  Invalid");
        }
        int ticketColumnOne, ticketColumnTwo;
        if (ticketCell == 0) {
            ticketColumnOne = 1;
            ticketColumnTwo = 2;
        } else if (ticketCell == 1) {
            ticketColumnOne = 0;
            ticketColumnTwo = 2;
        } else { // emptyCell == 2
            ticketColumnOne = 0;
            ticketColumnTwo = 1;
        }
        if (bingoTicket[ticketColumnOne][number] < bingoTicket[ticketColumnTwo][number]) {
        } else {
            // swap
            int swapTicket = bingoTicket[ticketColumnOne][number];
            bingoTicket[ticketColumnOne][number] = bingoTicket[ticketColumnTwo][number];
            bingoTicket[ticketColumnTwo][number] = swapTicket;
        }
    }
    /*
     * Three possible outcome:
     * a) only one number in the column - leave
     * b) The Two numbers in the column & not sorted - swap
     * c) The Three numbers in the column - sort
     */
    public void arrangeTicketColumn(int number) throws Exception {
        if (getTicketColumnCount(number) == 1) {
        } else if (getTicketColumnCount(number) == 2) {
            arrangeTicketColumnWithTwoNumbers(number);
        } else {
            arrangeTicketColumnWithThreeNumbers(number);
        }
    }
    public void arrangeAllTicketAndItsColumns() throws Exception {
        for (int c = 0; c < 9; c++) {
            arrangeTicketColumn(c);
        }
    }

}

