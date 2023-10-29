public class Kindle {
//--------------------------------------------
    private int numberPages;
    private int currentPageNumber;
//--------------------------------------------
//--------------------------------------------
    public Kindle (){
        currentPageNumber = 1;
        setNumberPages(100);
    }
    public Kindle(int numberOfPages) {
        numberPages = numberOfPages;
       currentPageNumber = 1;
    }
//---------------------------------------------
    public void setNumberPages( int updateNumberOfPage){
        this.numberPages = updateNumberOfPage;
    }

    public int getNumberPages (){
        return this.numberPages;
    }




public void turnPages () {
        turnPages(1);
}

public void turnPages (int turnedPages) {
        if(currentPageNumber + turnedPages <= numberPages) {
            currentPageNumber += turnedPages;
        }
        else  {
            System.out.println("You were on               : " + toString());
            currentPageNumber = numberPages;
            System.out.println("Turning 8 pages would take you past the last page.");
            System.out.println("You are now on            : " + toString());
        }

}

public String toString() {
        return "Page " + currentPageNumber + " of " + numberPages;
}
}
