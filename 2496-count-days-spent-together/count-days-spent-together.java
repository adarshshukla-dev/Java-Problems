class Solution {
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) 
    {
        int aliceStart = dateToDayOfYear(arriveAlice);
        int aliceEnd = dateToDayOfYear(leaveAlice);
        int bobStart = dateToDayOfYear(arriveBob);
        int bobEnd = dateToDayOfYear(leaveBob);

        int overlapStart = Math.max(aliceStart, bobStart);
        int overlapEnd = Math.min(aliceEnd, bobEnd);

        return Math.max(0, overlapEnd - overlapStart + 1);
    }

    private int dateToDayOfYear(String date) 
    {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int dayOfYear = day;
        for (int i = 0; i < month - 1; i++) 
        {
            dayOfYear += DAYS_IN_MONTH[i];
        }

        return dayOfYear;
    }
}