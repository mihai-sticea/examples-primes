public class PrintPrimes {
  int numberOfPrimes;
  int numberOfRows;
  int numberOfColumns;
  int primeNumberToBenextSquaredPrimedMAX;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int numberOfRows, int numberOfColumns, int primeNumberToBenextSquaredPrimedMAX) {
    this.numberOfPrimes   = numberOfPrimes;
    this.numberOfRows  = numberOfRows;
    this.numberOfColumns  = numberOfColumns;

    this.primeNumberToBenextSquaredPrimedMAX = primeNumberToBenextSquaredPrimedMAX;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }


  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 30);
      printPrimes.calculatePrimes();
      printPrimes.printPrimes();
  }

  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = 2;
      calculateOddPrimes();
  }

  private void calculateOddPrimes() {
      boolean numberIsPrime;
      int N;
      int MULT[] = new int[primeNumberToBenextSquaredPrimedMAX + 1];

      int oddNumber = 1;
      int primeNumberToBenextSquaredPrimed = 2;
      int nextSquaredPrime = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          oddNumber = oddNumber + 2;
          if (oddNumber == nextSquaredPrime) {
            primeNumberToBenextSquaredPrimed = primeNumberToBenextSquaredPrimed + 1;
            nextSquaredPrime = listOfPrimes[primeNumberToBenextSquaredPrimed] * listOfPrimes[primeNumberToBenextSquaredPrimed];
            MULT[primeNumberToBenextSquaredPrimed - 1] = oddNumber;
          }
          N = 2;
          numberIsPrime = true;
          while (N < primeNumberToBenextSquaredPrimed && numberIsPrime) {
            while (MULT[N] < oddNumber)
              MULT[N] = MULT[N] + listOfPrimes[N] + listOfPrimes[N];
            if (MULT[N] == oddNumber)
              numberIsPrime = false;
            N = N + 1;
          }
        } while (!numberIsPrime);
        listOfPrimes[primesFoundSoFar] = oddNumber;
      }
    }

    public void printPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          for (int rowOffset = pageOffset; rowOffset < pageOffset + numberOfRows; rowOffset++){
            for (int i = 0; i < numberOfColumns;i++)
              if (rowOffset + i * numberOfRows <= numberOfPrimes)
                System.out.format("%10d", listOfPrimes[rowOffset + i * numberOfRows]);
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + numberOfRows * numberOfColumns;
        }
    }
}
