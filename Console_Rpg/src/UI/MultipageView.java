package UI;

import java.util.ArrayList;

public class MultipageView {

    private ArrayList<String> rows;
    public void setRows(ArrayList<String> rows) {
        this.rows = rows;
        rowCnt = rows.size();
        // max page 0 olmasin istiyorum en az 1 olsun. sayfa bos olsa bile.
        maxPage = Math.max(1 , rowCnt / rowPerPage + ((rowCnt % rowPerPage == 0) ? 0 : 1));

        currentPage = Math.min(currentPage , maxPage);
        currentPage = Math.max(1 , currentPage);
    }

    private int rowPerPage;
    private String noContentText;

    private int currentPage;
    public int getCurrentPage() {
        return currentPage;
    }

    private int maxPage;
    private int rowCnt;
    public int getRowCnt() {
        return rowCnt;
    }

    public MultipageView(int rowPerPage , ArrayList<String> rows){
        this(rowPerPage , rows, "No content");
    }
    public MultipageView(int rowPerPage , ArrayList<String> rows , String noContentText){
        this.rowPerPage = rowPerPage;
        setRows(rows);
        this.noContentText = noContentText;
        this.currentPage = 1;
    }

    public boolean loadPage(int page){
        if (page < 1 || page > maxPage){
            System.out.println(" page out of range ");
            return false;
        }
        if (rows.size() == 0){
            System.out.println("-------------------");
            System.out.println(noContentText);
            System.out.println("-------------------");
            return true;
        }

        this.currentPage = page;

        System.out.println("------- " + " page: " + page + " -------");

        int startingRow = rowPerPage * (page-1) + 1;

        for (int row = startingRow ; row < startingRow + rowPerPage && row <= rows.size(); row ++){
            System.out.println("("+row+") " + rows.get(row-1));
        }
        System.out.println("------- " + 1 + " <+> " + maxPage + " -------");
        return true;
    }
}
