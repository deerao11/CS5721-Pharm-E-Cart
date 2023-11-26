package Boundary;

public class Page {
    public void start(){
        System.out.println();
        System.out.println("【Page title】");
        drawDivider("*");
    }
    public void drawDivider(String s){
        for (int i=0; i<80; i++){
            System.out.print(s);
        }
        System.out.println();
    }
}
